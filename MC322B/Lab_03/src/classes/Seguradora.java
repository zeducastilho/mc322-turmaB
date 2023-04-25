package classes;
import java.util.ArrayList;

public class Seguradora {

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
	public boolean cadastrarCliente (Cliente new_cliente){
		for (Cliente client: listaClientes){ 				//checando se o cliente já está cadastrado
			if(new_cliente.getNome()== client.getNome()){
				System.out.println("Não foi possível cadastrar o cliente " + new_cliente.getNome() + ": cliente com mesmo nome encontrado");
				return false;
			}
		}
		if (new_cliente.validaCliente()){					//chama o método de cliente que valida o documento caso tenha sido fornecido (cnpj/cpf)
			listaClientes.add(new_cliente);
			return true;
		}
		System.out.println("Não foi possível cadastrar o cliente " + new_cliente.getNome() + ": cliente com documento inválido");
		return false;
	}
	
	public boolean removeCLiente(Cliente cliente_remover){
		/*for (Cliente client: listaClientes){ 				//checando se o cliente existe
			if(cliente_remover.getNome() == client.getNome()){
				listaClientes.remove(client)
				return true;
			}
		}
		return false;*/
		boolean status = listaClientes.remove(cliente_remover);
		if (!status){
			System.out.println("Não foi possível remover o cliente "+ cliente_remover.getNome()+": Cliente não Encontrado");
		}
		return status;

	}
	public void listarCLientes(String tipoCliente){
		
		System.out.println("Lista de clientes:");
		
		if (listaClientes.size()==0){								// verifica se a lista está vazia
			System.out.println("*Lista de Clientes Vazia*");
			return;
		}
		
		else if (tipoCliente == "All"){								// imprime o nome de todos os clientes
			for (Cliente client: listaClientes){
				System.out.println(client.getNome());
			}
		}
		else{
			for (Cliente client: listaClientes){ 					// percorre a lista de clientes
				if(tipoCliente == client.getTipo()){				// checa se o tipo do cliente é do tipo desejado
					System.out.println(client.getNome());			// imprime o nome do cliente
				}
			}

		}
		System.out.println("*Fim da Lista de Clientes*");
	}

	public boolean gerarSinistro(String data, String endereco, String placa_veiculo, String nome_cliente){
		for (Cliente client: listaClientes){ 				//checando se o cliente pertence a seguradora
			if(nome_cliente.equals(client.getNome())){
				Veiculo veiculo = getVeiculo(placa_veiculo, client);	// encontrando o veiculo na lista de veiculos do cliente
				if(veiculo==null){
					System.out.println("Veiculo não Encontrado para este Cliente");
					return false;
				}
				Sinistro novo_sinistro = new Sinistro(data, endereco, this, veiculo, client);
				listaSinistros.add(novo_sinistro);
				return true;
			}
		}
		System.out.println("Não foi possível gerar sinistro: cliente não cadastrado na seguradora");
		return false;
	}
	public boolean visualizarSinistro(String cliente){
		int existe_sinistro = 0;
		for (Sinistro sinistro: listaSinistros){ 				//checando se o cliente pertence a seguradora
			if(cliente == sinistro.getCliente().getNome()){
				System.out.println(sinistro.toString());
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

	public Veiculo getVeiculo(String placa, Cliente client){
		for (Veiculo veiculo: client.getListaVeiculos()){
			if ((veiculo.getPlaca().equals(placa))){
				return veiculo;
			}
		}
		return null;
	}
}