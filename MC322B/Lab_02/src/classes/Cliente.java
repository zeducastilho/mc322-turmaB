package classes;

public class Cliente {
	
    //atributos

	private String nome;
	private String cpf;
	private String dataNascimento;
	private int idade;
	private String endereco;
	
	//creator

    public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.endereco = endereco;
    }

    //getters e setters

    public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
    public boolean verificaCPF(String cpf) {                       //função que valida o cpf do cliente
		
        cpf = cpf.replaceAll("[^0-9]+", "");     //substitui todos os caracteres não numéricos (0-9) por vazio
		
        if(cpf.length()!=11){                                      //verifica se o cpf tem 11 digitos
			return false;
		}

        // verifica se todos os dígitos são iguais

        int iguais = 0;
        for (int i =1; i<11; i++){
            if(cpf.charAt(i)==cpf.charAt(0)){               //conta quantos dígitos são iguais ao primeiro
                iguais++;
            }
        }
        if (iguais == 10){                                        // se houverem 10 dígitos iguais ao primeiro, todos os dígitos são iguais e o cpf é invalido
            return false;
        }
         
        //cálculo do primeiro dígito verificador
        
        int soma = 0; int peso = 10; int digito; int resultado; int verificador;
        
        for (int i=0; i<9; i++) {
                digito = (int)(cpf.charAt(i) - 48);
                soma = soma + (digito * peso);
                peso = peso - 1;
        }
        verificador = ((soma*10) % 11);
        if ((verificador == 10)){
            verificador = 0;
        }

        //verificação do primeiro dígito

        if (verificador != (int)(cpf.charAt(9) - 48)){
            return false;
        }

        //cálculo do segundo dígito

        peso = 11;
        soma = 0;
        
        for (int i=0; i<10; i++) {
                digito = (int)(cpf.charAt(i) - 48);
                soma = soma + (digito * peso);
                peso = peso - 1;
        }
        resultado = ((soma*10) % 11);
        if ((resultado == 10)){
            verificador = 0;
        }
        else{
            verificador = resultado;
        }

        //verifica o segundo dígito

        if (verificador != (int)(cpf.charAt(10) - 48)){
            return false;
        }
        return true;
        
	}

    public String toString() {
        return "Nome: " + nome + ", CPF: " + cpf + ", Data de Nascimento: " + dataNascimento + ", Idade: " + idade + ", Endereço: " + endereco;
    }

}
