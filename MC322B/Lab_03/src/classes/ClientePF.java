package classes;
import java.util.Date;
import java.text.ParseException;

public class ClientePF extends Cliente{

    private final String CPF;
    private String genero;
    private Date dataLicenca;
    private String educacao;
    private Date dataNascimento;
    private String classeEconomica;

    
    public ClientePF(String nome, String endereco, String CPF, String genero, String dataLicenca, String educacao,
            String dataNascimento, String classeEconomica) {
        super(nome, endereco);
        this.CPF = CPF;
        this.genero = genero;
        try{
            this.dataLicenca = formataData.parse(dataLicenca);
        } catch (ParseException ex) {
            // ignore
          }
        this.educacao = educacao;
        try{
            this.dataNascimento = formataData.parse(dataNascimento);
        } catch (ParseException ex) {
            // ignore
          }
        this.classeEconomica = classeEconomica;
    }

    public String getTipo(){    // será utilizada no método listaClientes da classe seguradoras
        return "PF";
    }
    public String getCPF() {
        return CPF;
    }
    /*public void setCPF(String cPF) {
        CPF = cPF; 
    }*/// como CPF está definido como final, não é possível criar um setter para o CPF
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public Date getDataLicenca() {
        return dataLicenca;
    }
    public void setDataLicenca(Date dataLicenca) {
        this.dataLicenca = dataLicenca;
    }
    public String getEducacao() {
        return educacao;
    }
    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }
    public Date getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public String getClasseEconomica() {
        return classeEconomica;
    }
    public void setClasseEconomica(String classeEconomica) {
        this.classeEconomica = classeEconomica;
    }
    public boolean verificaCPF() {                       //função que valida o cpf do cliente
            
        String cpf = this.CPF.replaceAll("[^0-9]+", "");     //substitui todos os caracteres não numéricos (0-9) por vazio
        
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

    @Override
    public String toString() {
        String retorno = super.toString();
        retorno += "\n    CPF: " + CPF + "\n    gênero: " + genero + "\n    data de emissão da licença: " + formataData.format(dataLicenca) + "\n    educação: "
                + educacao + "\n    data de nascimento: " + formataData.format(dataNascimento) + "\n    classe econômica: " + classeEconomica + "\n    }";
        return retorno;
    }
   @Override
   public boolean validaCliente(){ //Metodo sobrescrito utilizado para validar o cliente antes de inseri-lo na seguradora
    return verificaCPF();
}

}



