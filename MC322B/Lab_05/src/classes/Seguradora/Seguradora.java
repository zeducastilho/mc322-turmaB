package classes.Seguradora;
import java.util.ArrayList;

import classes.Cliente.*;
import classes.Seguro.*;
import classes.Veiculos.Frota;
import classes.Veiculos.Veiculo;

public class Seguradora {

	//atributos

	private String nome;
	private final String cnpj;
	private String telefone ;
	private String email ;
	private String endereco ;
	private ArrayList <Seguro> listaSeguros;
	private ArrayList <Cliente> listaClientes;

	// Construtor

	public Seguradora (String nome , String telefone , String email , String endereco, String CNPJ) {
		this.nome = nome;
		this.cnpj = CNPJ;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		this.listaClientes = new ArrayList<Cliente>();
		this.listaSeguros = new ArrayList<Seguro>();
	}

	// Getters e setters

	public String getCnpj() {
		return cnpj;
	}
	public ArrayList<Seguro> getListaSeguros() {
		return listaSeguros;
	}
	public void setListaSeguros(ArrayList<Seguro> listaSeguros) {
		this.listaSeguros = listaSeguros;
	}
	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}
	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	public String getNome () {
		return nome ;
	}
	public void setNome ( String nome ) {
		this.nome = nome ;
	}
	public String getTelefone () {
		return telefone ;
	}
	public void setTelefone ( String telefone ) {
		this.telefone = telefone ;
	}
	public String getEmail () {
		return email ;
	}
	public void setEmail ( String email ) {
		this.email = email ;
	}
	public String getEndereco () {
		return endereco ;
	}
	public void setEndereco ( String endereco ) {
		this.endereco = endereco ;
	}


	// Métodos para manipular lista de clientes

	public String listarCLientes(String tipoCliente){
		
		String retorno = "Lista de clientes: \n";
		
		if (listaClientes.size()==0){								// verifica se a lista está vazia
			retorno += "*Lista de Clientes Vazia*";
			return retorno;
		}
		
		else if (tipoCliente.equals("All")){								// imprime o nome de todos os clientes
			for (Cliente client: listaClientes){
				retorno += client.getNome() + "\n";
			}
		}
		else{
			for (Cliente client: listaClientes){ 					// percorre a lista de clientes
				if(tipoCliente.equals(client.getTipo())){				// checa se o tipo do cliente é do tipo desejado
					retorno += client.getNome() + "\n";			// imprime o nome do cliente
				}
			}

		}
		return retorno;
	}

	public String visualizarCliente(String documento){
		for (Cliente client: listaClientes){ 							// percorre a lista de clientes
			if(documento.equals(client.getDocumento())){				// checa se o cpf é do cliente desejado
				return client.toString();								// imprime o to string
			}
		}
		return "Cliente não encontrado";
		
	}

	public boolean cadastrarCliente (Cliente new_cliente){
		for (Cliente client: listaClientes){ 				//checando se o cliente já está cadastrado
			if(new_cliente.getDocumento().equals(client.getDocumento())){
				System.out.println("Não foi possível cadastrar o cliente " + new_cliente.getNome() + ": cliente com mesmo documento encontrado");
				return false;
			}
		}
		listaClientes.add(new_cliente);
		calcularReceita();
		return true;
	}
	
	public boolean removeCliente(String documento){
		Cliente cliente_remover = getCliente(documento);
		if (cliente_remover ==  null){
			return false;
		}
		boolean status = listaClientes.remove(cliente_remover);
		calcularReceita();
		return status;
	}

	
	// Métodos para manipular a lista de seguros

	public boolean gerarSeguro(String dataInicio, String dataFim, String placa_frota, String documento){
		
		Seguro seguro = null;

		Cliente cliente = getCliente(documento);
		if (cliente==null){
			return false;
		}

		if (cliente.getTipo().equals("PF")){
			Veiculo veiculo = getVeiculo(placa_frota, documento);	// encontrando o veiculo na lista de veiculos do cliente
			if(veiculo==null){
				System.out.println("Veiculo não Encontrado para este Cliente");
				return false;
			}	
			seguro = SeguroPF.construtorSeguroPF(veiculo, (ClientePF)cliente, dataInicio, dataFim, this);
		}

		else if (cliente.getTipo().equals("PJ")){
			Frota frota = getFrota(placa_frota,documento);
			if(frota == null){
				System.out.println("Frota não Encontrada para este Cliente");
				return false;
			}
			seguro = SeguroPJ.construtorSeguroPJ(frota, (ClientePJ)cliente, dataInicio, dataFim, this);
		}
		
		if (seguro == null){
				return false;
		}
		boolean status = listaSeguros.add(seguro);
		calcularReceita();
		return status;
	}

	public boolean cancelarSeguro(String documento, String placa_frota){
		Seguro seguro = null;

		Cliente cliente = getCliente(documento);
		if (cliente==null){
			return false;
		}

		if (cliente.getTipo().equals("PF")){
			Veiculo veiculo = getVeiculo(placa_frota, documento);	// encontrando o veiculo na lista de veiculos do cliente
			if(veiculo==null){
				System.out.println("Veiculo não Encontrado para este Cliente");
				return false;
			}	
			for(Seguro seg: listaSeguros){
				if(seg.getCliente().getDocumento().equals(documento) && ((SeguroPF)seg).getVeiculo().equals(veiculo)){
					seguro = seg;
				}
			}
		}

		else if (cliente.getTipo().equals("PJ")){
			Frota frota = getFrota(placa_frota,documento);
			if(frota == null){
				System.out.println("Frota não Encontrada para este Cliente");
				return false;
			}
			for(Seguro seg: listaSeguros){
				if(seg.getCliente().getDocumento().equals(documento) && ((SeguroPJ)seg).getFrota().equals(frota)){
					seguro = seg;
				}
			}
		}
		
		if (seguro == null){
			System.out.println("Seguro não encontrado");
				return false;
		}
		boolean status = listaSeguros.remove(seguro);
		calcularReceita();
		return status;

	}

	public String visualizarSeguros(String documento){
		int existe_seguro = 0;
		Cliente cliente = getCliente(documento);
		if(cliente == null){
			return null;
		}
		String retorno = "Seguros do cliente " + cliente.getNome() + ":";
		for (Seguro seguro: listaSeguros){ 				
			if(cliente.equals(seguro.getCliente())){
				retorno += "\n" + seguro.toString();
				existe_seguro++;
			}
		}
		if(existe_seguro == 0){
			retorno += "*Não existem sinistros para esse cliente*";
			return retorno;
		}
		return retorno;
	}
	 
	public void listarSeguros(){
		
		System.out.println("Lista de Seguros:");
		
		if (listaSeguros.isEmpty()){								// verifica se a lista está vazia
			System.out.println("*Lista de Seguros Vazia*");
			return;
		}
		
		for (Seguro seguro: listaSeguros){
				System.out.println(seguro.toString());
			}
		
		System.out.println("*Fim da Lista de Seguros*");
	}

	//Outros métodos necessários

	public double calcularReceita() {
		double soma = 0;		
		for(Seguro seguro : listaSeguros){
			seguro.calcularValor();
			soma += seguro.getValorMensal();
		}
		return soma;
	}


	//Métodos auxiliares

	public Veiculo getVeiculo(String placa, String documento){
		Cliente client = getCliente(documento);
		for (Veiculo veiculo: ((ClientePF)client).getListaVeiculos()){
			if ((veiculo.getPlaca().equals(placa))){
				return veiculo;
			}
		}
		return null;
	}
	public Frota getFrota(String code, String documento){
		Cliente client = getCliente(documento);
		for (Frota frota: ((ClientePJ)client).getListaFrotas()){
			if ((frota.getCode().equals(code))){
				return frota;
			}
		}
		return null;
	}

	public Cliente getCliente (String documento){
		documento = documento.replaceAll("[^0-9]+", "");
		for (Cliente client: listaClientes){
			if(client.getDocumento().equals(documento)){
			return client;
			}
		}
		System.out.println("Cliente não encontrado");
		return null;
	}
  	public ArrayList<Sinistro> getSinistrosPorCliente(String documento) {
        ArrayList<Sinistro> sinistrosCliente = new ArrayList<>();
		Cliente cliente = getCliente(documento);
        for (Seguro seguro : listaSeguros) {
        	if (seguro.getCliente().equals(cliente)) {
                sinistrosCliente.addAll(seguro.getListaSinistros());
        	}
        }
        return sinistrosCliente;
	}
	public ArrayList<Seguro> getSegurosPorCliente(String documento) {
        ArrayList<Seguro> segurosCliente = new ArrayList<>();
		Cliente cliente = getCliente(documento);
        for (Seguro seguro : listaSeguros) {
            if (seguro.getCliente().equals(cliente)) {
                segurosCliente.add(seguro);
            }
        }
        return segurosCliente;
    }

	public Seguro getSeguro(String placa_frota, String documento) {
		ArrayList<Seguro> segurosCliente = getSegurosPorCliente(documento);
		Cliente cliente = getCliente(documento);
		if (cliente == null) {
			return null;
		}

		if (cliente.getTipo().equals("PF")) {
			Veiculo veiculo = getVeiculo(placa_frota, documento);    // encontrando o veiculo na lista de veiculos do cliente
			if (veiculo == null) {
				System.out.println("Veiculo não Encontrado para este Cliente");
				return null;
			}
			for (Seguro seguro : segurosCliente) {
				if (seguro.getCliente().equals(cliente) && ((SeguroPF) seguro).getVeiculo().equals(veiculo)) {
					return seguro;
				}
			}
		}
		else if (cliente.getTipo().equals("PJ")) {
			Frota frota = getFrota(placa_frota, documento);
			if (frota == null) {
				System.out.println("Frota não Encontrada para este Cliente");
				return null;
			}
			for (Seguro seguro : segurosCliente) {
				if (seguro.getCliente().equals(cliente) && ((SeguroPJ) seguro).getFrota().equals(frota)) {
					return seguro;
				}
			}
		}
		return null;
	}		

}