package classes;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Sinistro {
    
    private static int ID_generator;
    private SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");

    
    private int gerarid() {  
        ID_generator++;
        return ID_generator;
    }  
    
    //atributos

    private final int ID;
    private Date data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Cliente cliente;
        
    //Constructor

    public Sinistro(String data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente) {
        this.ID = gerarid();
        try{
            this.data = formataData.parse(data);
        } catch (ParseException ex) {
            // ignore
          }
        this.endereco = endereco;
        this.seguradora = seguradora;
        this.veiculo = veiculo;
        this.cliente = cliente;
    }

    // Getters e Setters

    public int getId() {
        return ID;
    }
    public Date getData() {
        return data;
    }
    public void setData(String data) {
        try{
            this.data = formataData.parse(data);
        } catch (ParseException ex) {
            // ignore
          }
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
        return "Sinistro: \n    ID: " + ID + "\n    data: " + formataData.format(data) + "\n    endereco: " + endereco + "\n    seguradora:" + seguradora.getNome()
                + "\n    veiculo: " + veiculo + "\n    cliente: " + cliente.getNome() + "(" + cliente.getDocumento() +")" ;
    }

    /* método semelhante ao tostring, porem suprime as informações sobre o cliente, uma vez que o método é utilziado 
    apenas nos casos onde as informações do sinistro estão sendo apresentadas de maneira já relacionada ao cliente.
    sendo assim, não é necessário repetir tais informações*/
    
    public String informacoes() { 
        return "Sinistro: \n    ID: " + ID + "\n    data: " + formataData.format(data) + "\n    endereco: " + endereco + "\n    seguradora:" + seguradora.getNome()
                + "\n    veiculo: " + veiculo + "\n";
    }
}
