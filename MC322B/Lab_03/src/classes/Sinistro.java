package classes;

public class Sinistro {
    
    static int ID;
    
    private int gerarid() {  
        ID++;
        return ID;
    }  
    
    //atributos

    private int id;
    private String data;
    private String endereco;
    

    //Constructor

    public Sinistro(String data, String endereco) {
        this.id = gerarid();
        this.data = data;
        this.endereco = endereco;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

     public void setId(int id) { // o id não deve ser alterado pelo usuário
        this.id = id;
    } 
    


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
    
}
