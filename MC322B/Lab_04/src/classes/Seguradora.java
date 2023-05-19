package classes;
import java.util.ArrayList;

public class Seguradora {

	//atributos

	private String nome ;
	private String telefone ;
	private String email ;
	private String endereco ;
	private ArrayList <Sinistro> listaSinistros;
	private ArrayList <Cliente> listaClientes;

	// Construtor

	public Seguradora ( String nome , String telefone , String email , String endereco ) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		this.listaClientes = new ArrayList<Cliente>();
		this.listaSinistros = new ArrayList<Sinistro>();
	}

	// Getters e setters

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
	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}

	// Métodos para manipular lista de clientes

	public boolean cadastrarCliente (Cliente new_cliente){
		for (Cliente client: listaClientes){ 				//checando se o cliente já está cadastrado
			if(new_cliente.getNome().equals(client.getNome())){
				System.out.println("Não foi possível cadastrar o cliente " + new_cliente.getNome() + ": cliente com mesmo nome encontrado");
				return false;
			}
		}
		if (new_cliente.validaCliente()){
			listaClientes.add(new_cliente);
			calcularPrecoSeguro();
			return true;
		}
		return false;
	}
	
	public boolean removeCliente(String documento){
		Cliente cliente_remover = getCliente(documento);
		boolean status = listaClientes.remove(cliente_remover);
		if (status){
			for (Sinistro sinistro: listaSinistros){
				if(cliente_remover.equals(sinistro.getCliente())){
					listaSinistros.remove(sinistro);
				}
			}		
		}
		System.out.println("Não foi possível remover o cliente "+ cliente_remover.getNome()+": Cliente não Encontrado");
		return status;

	}
	public boolean removeCliente(Cliente cliente_remover){ //método sobrecarregado
		boolean status = listaClientes.remove(cliente_remover);
		if (status){
			for (Sinistro sinistro: listaSinistros){
				if(cliente_remover.equals(sinistro.getCliente())){
					listaSinistros.remove(sinistro);
				}
			}		
		}
		System.out.println("Não foi possível remover o cliente "+ cliente_remover.getNome()+": Cliente não Encontrado");
		return status;

	}

	public void listarCLientes(String tipoCliente){
		
		System.out.println("Lista de clientes:");
		
		if (listaClientes.size()==0){								// verifica se a lista está vazia
			System.out.println("*Lista de Clientes Vazia*");
			return;
		}
		
		else if (tipoCliente.equals("All")){								// imprime o nome de todos os clientes
			for (Cliente client: listaClientes){
				System.out.println(client.getNome());
			}
		}
		else{
			for (Cliente client: listaClientes){ 					// percorre a lista de clientes
				if(tipoCliente.equals(client.getTipo())){				// checa se o tipo do cliente é do tipo desejado
					System.out.println(client.getNome());			// imprime o nome do cliente
				}
			}

		}
		System.out.println("*Fim da Lista de Clientes*");
	}
	
	// Métodos para manipular a lista de sinistros

	public boolean gerarSinistro(String data, String endereco, String placa_veiculo, String documento){
		Cliente cliente = getCliente(documento);
		for (Cliente client: listaClientes){ 				//checando se o cliente pertence a seguradora
			if(cliente.equals(client)){
				Veiculo veiculo = getVeiculo(placa_veiculo, client);	// encontrando o veiculo na lista de veiculos do cliente
				if(veiculo==null){
					System.out.println("Veiculo não Encontrado para este Cliente");
					return false;
				}
				Sinistro novo_sinistro = new Sinistro(data, endereco, this, veiculo, client);
				listaSinistros.add(novo_sinistro);
				calcularPrecoSeguro();
				return true;
			}
		}
		System.out.println("Não foi possível gerar sinistro: cliente não cadastrado na seguradora");
		return false;
	}
	public boolean visualizarSinistro(String documento){
		int existe_sinistro = 0;
		Cliente cliente = getCliente(documento);
		for (Sinistro sinistro: listaSinistros){ 				//checando se o cliente pertence a seguradora
			if(cliente.equals(sinistro.getCliente())){
				System.out.println(sinistro.informacoes());
				existe_sinistro++;
			}
		}
		if(existe_sinistro!=0){
			return true;
		}
		else{
			System.out.println("*Não existem sinistros para esse cliente ou esse cliente não está cadastrado*");
			return false;
		}
	}

	public void listarSinistros(){
		
		System.out.println("Lista de Sinistros:");
		
		if (listaSinistros.size()==0){								// verifica se a lista está vazia
			System.out.println("*Lista de Sinistros Vazia*");
			return;
		}
		
		for (Sinistro sinistro: listaSinistros){
				System.out.println(sinistro.toString());
			}
		
		System.out.println("*Fim da Lista de Sinistros*");
	}

	//Outros métodos necessários

	private void calcularPrecoSeguro() {
		for (Cliente cliente: listaClientes){
			double score  = cliente.calculaScore();
				// verifica o numero de sinistros
				int qtdSinistros = 0;
				for (Sinistro sinistro: listaSinistros){ 				//checando se o cliente pertence a seguradora
					if(cliente.equals(sinistro.getCliente())){
						qtdSinistros++;
					}
				}		
				// atualiza o valor do seguro no objeto do cliente
				cliente.setValorSeguro(score * (1 + qtdSinistros));
		}	
	}

	public boolean adicionaVeiculoCliente(String documento, Veiculo veiculo) {
        Cliente cliente = getCliente(documento);
        if (cliente != null) {
            cliente.adicionaVeiculo(veiculo);
            calcularPrecoSeguro();
            return true;
        }
        return false;
	}

	public boolean removeVeiculoCliente(String documento, String placa) {
        Cliente cliente = getCliente(documento);
        if (cliente != null) {
            boolean removido = cliente.removeVeiculo(placa);
            if (removido) {
                calcularPrecoSeguro();
            }	
            return removido;
        }
        return false;
    }
	public double calcularReceita() {
		double soma = 0;
		calcularPrecoSeguro();
		
		for(Cliente cliente : listaClientes)
			soma += cliente.getValorSeguro();
		
		return soma;
	}

	public void transferirSeguro(String documentoPartida, String documentoDestino) {
		Cliente clientePartida = getCliente(documentoPartida);
		Cliente clienteDestino = getCliente(documentoDestino);
		
		if(clientePartida == null){
			System.out.println("Cliente de Partida não encontrado");
			return;
		}
		else if(clienteDestino == null){
			System.out.println("Cliente de Destino não encontrado");
			return;
		}

		ArrayList<Veiculo> veiculos_transferir = new ArrayList<>(clientePartida.getListaVeiculos());
		// se o cliente fonte não tiver nada no seu seguro, a transferência é trivial
		if(veiculos_transferir.size()!= 0) {
			for(Veiculo veiculo: veiculos_transferir) {

				clientePartida.removeVeiculo(veiculo.getPlaca());
				clienteDestino.adicionaVeiculo(veiculo);
			}
			
			this.calcularPrecoSeguro();
			for(Sinistro sinistro: listaSinistros){
				if(sinistro.getCliente().equals(clientePartida)){
					sinistro.setCliente(clienteDestino);
				}
			}			
		}
		calcularPrecoSeguro();
		return;
	}

	public void removerSinistro(int id){
		for (Sinistro sinistro: listaSinistros){
			if(sinistro.getId()==id){
				listaSinistros.remove(sinistro);
				return;
			}
		}
		System.out.println("ID não encontrado");
	}

	//Métodos auxiliares

	public Veiculo getVeiculo(String placa, Cliente client){
		for (Veiculo veiculo: client.getListaVeiculos()){
			if ((veiculo.getPlaca().equals(placa))){
				return veiculo;
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
	/* o método a seguir deveria ser implementado como uma das opções do menu interativo, visto que nao ha nenhuma opção
		para visualizar em detalhes todas as informações de um cliente */
	/*
	public void visualizarCliente(String documento){
		Cliente cliente = getCliente(documento);
		for (Cliente client: listaClientes){ 				//checando se o cliente pertence a seguradora
			if(cliente.equals(client)){
				System.out.println(client.toString());
				System.out.println(visualizarSinistro(documento));
				return;
			}
		}
		System.out.println("Cliente Não Encontrado");
	}*/
}