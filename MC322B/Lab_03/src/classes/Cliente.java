package classes;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class Cliente {
	
    protected SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");

    //atributos

	private String nome;
	private String endereco;
    private ArrayList<Veiculo> listaVeiculos;
	
	//creator

    public Cliente(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = new ArrayList<Veiculo>();
    }

    //getters e setters

    public String getTipo(){    // será utilizada no método listaClientes da classe seguradoras
        return "All";
    }
    public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public ArrayList<Veiculo> getListaVeiculos(){
        return listaVeiculos;
    }
    //public void setListaVeiculos(){ /*não faz sentido haver um setListaVeiculos*/ }
    public void adicionaVeiculo (Veiculo veiculo){
        listaVeiculos.add(veiculo);
    }

    // to String
    @Override
    public String toString() {
        String retorno = "   {Nome: " + nome + "\n    endereco: " + endereco + "\n    Lista de Veiculos:\n        ";
        for(int i = 0; i<(listaVeiculos.size() - 1); i++){
            retorno += listaVeiculos.get(i).toString() + "\n        ";
        }
        if(listaVeiculos.size()>0){
            retorno += listaVeiculos.get(listaVeiculos.size()-1).toString();
        }
        else{
            retorno += "Sem Veículos Cadastrados";
        }
        return retorno;
    }
    public boolean validaCliente(){ //Metodo a ser sobrescrito pelas classes clientePF e ClientePJ, será utilizado para validar o cliente antes de inseri-lo na seguradora
        return true;
    }
    
}
