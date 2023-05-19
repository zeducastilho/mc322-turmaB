package classes;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class Cliente {
	
    protected SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");

    //atributos

	private String nome;
	private String endereco;
    private ArrayList<Veiculo> listaVeiculos;
    private double valorSeguro;
	
	//creator

    public Cliente(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = new ArrayList<Veiculo>();
        this.valorSeguro = 0.0;
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
    public double getValorSeguro() {
        return valorSeguro;
    }
    public void setValorSeguro(double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    
    //Métodos para manipulação da lista de veiculos

    public void adicionaVeiculo (Veiculo veiculo){
        listaVeiculos.add(veiculo);
    }
    public boolean removeVeiculo (String placa){
        for (Veiculo veiculo : listaVeiculos) {
            if (veiculo.getPlaca().equals(placa)){
                listaVeiculos.remove(veiculo);
                return true;
            }
        }
        System.out.println("Não foi possível remover: veículo não cadastrado");
        return false;
    }

    public void listarVeiculos(){
        for(int i = 0; i<(listaVeiculos.size() - 1); i++){
            System.out.println(listaVeiculos.get(i).toString());
        }
        if(listaVeiculos.size()>0){
            System.out.println(listaVeiculos.get(listaVeiculos.size()-1).toString());
        }
        else{
           System.out.println("Sem Veículos Cadastrados");
        }
    }

    // to String
    
    @Override
    public String toString() {
        String retorno = "   {Nome: " + nome + "\n    endereco: " + endereco +  "\n    Valor do Seguro: " + valorSeguro + "\n    Lista de Veiculos:\n        ";
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
    
    //Metodos a serem sobrescritos pelas classes clientePF e ClientePJ

    public String getDocumento(){   //com os métodos sobrescritos pelas classes filhas, é possível fazer cliente.getdocumento()
        return null;                //não sendo necessário verificar o tipo de cliente para poder usar .getCPF ou .getCNPJ
    }
    public double calculaScore(){ 
        return 0;
    }
    public boolean validaCliente(){
        if(Validacao.validaNome(this.nome)){		// checando se o nome tem apenas letras
			return true;
		}
		System.out.println("Não foi possível cadastrar o cliente: nome inválido (digite apenas letras) ");
		return false; 
    }
    
}
