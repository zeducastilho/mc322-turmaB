package classes.Cliente;


public abstract class Cliente {
	
    //atributos

	private String nome;
	private String endereco;
    private String email;
    private String telefone;

	//creator

    public Cliente(String nome, String endereco, String telefone, String email) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    //getters e setters

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
	public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // to String
    
    @Override
    public String toString() {
        return " { Nome: " + nome + "\n endereco: " + endereco +  "\n Email: " + email + "\n Telefone:" + telefone ;
        // as chaves não são fechadas pois o método só será utilizado concomitantemente com os métodos toString das classes filhas
    }
    
    //Metodos abstratos a serem implementados pelas classes clientePF e ClientePJ

    public abstract String getDocumento();

    public abstract String getTipo();
}
