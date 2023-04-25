package classes;

public class Sinistro {
    
    private static int ID_generator;

    
    private int gerarid() {  
        ID_generator++;
        return ID_generator;
    }  
    
    //atributos

    
    private final int ID;
    private String data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Cliente cliente;
        

    //Constructor

    public Sinistro(String data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente) {
        this.ID = gerarid();
        this.data = data;
        this.endereco = endereco;
        this.seguradora = seguradora;
        this.veiculo = veiculo;
        this.cliente = cliente;
    }

    // Getters e Setters

    public int getId() {
        return ID;
    }

    /*public void setId(int id) { // o id não deve ser alterado pelo usuário
        this.ID = id;
    } */
    
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public Seguradora getSeguradora() {
        return seguradora;
    }
    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }
    public Veiculo getVeiculo() {
        return veiculo;
    }
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    @Override
    public String toString() {
        return "Sinistro: \n    ID: " + ID + "\n    data: " + data + "\n    endereco: " + endereco + "\n    seguradora:" + seguradora.getNome()
                + "\n    veiculo: " + veiculo + "\n    cliente:\n " + cliente ;
    }
}
