package classes;
import java.util.Date;
import java.text.ParseException;

public class ClientePJ extends Cliente{
    
    //atributos
    private final String CNPJ;
    private Date dataFundacao;
    
    //creator
    public ClientePJ(String nome, String endereco, String CNPJ, String dataFundacao) {
        super(nome, endereco);
        this.CNPJ = CNPJ;
        try{
            this.dataFundacao = formataData.parse(dataFundacao);
        } catch (ParseException ex) {
            // ignore
          }
    }

    // getters and setters
    
    public String getCNPJ() {
        return CNPJ;
    }

   public Date getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }
    public boolean verificaCNPJ() {                       //função que valida o cnpj do cliente
            
        String CNPJ = this.CNPJ.replaceAll("[^0-9]+", "");     //substitui todos os caracteres não numéricos (0-9) por vazio
        
        if(CNPJ.length()!=14){                                      //verifica se o cnpj tem 14 digitos
            return false;
        }

        // verifica se todos os dígitos são iguais

        int iguais = 0;
        for (int i =1; i<14; i++){
            if(CNPJ.charAt(i)==CNPJ.charAt(0)){               //conta quantos dígitos são iguais ao primeiro
                iguais++;
            }
        }
        if (iguais == 13){                                        // se houverem 13 dígitos iguais ao primeiro, todos os dígitos são iguais e o cpf é invalido
            return false;
        }
        
        //cálculo do primeiro dígito verificador
        
        int soma = 0; int peso = 2; int digito; int resultado; int verificador;
        
        for (int i=11; i>=0; i--) {
                digito = (int)(CNPJ.charAt(i) - 48);
                soma = soma + (digito * peso);
                peso = peso + 1;
                if(peso ==10){
                    peso =2;
                }
        }
        verificador = ((soma*10) % 11);
        if ((verificador == 10)){
            verificador = 0;
        }

        //verificação do primeiro dígito

        if (verificador != (int)(CNPJ.charAt(12) - 48)){
            return false;
        }

        //cálculo do segundo dígito

        peso = 2;
        soma = 0;
        
        for (int i=12; i>=0; i--) {
                digito = (int)(CNPJ.charAt(i) - 48);
                soma = soma + (digito * peso);
                peso = peso + 1;
                if(peso ==10){
                    peso =2;
                }
        }
        resultado = ((soma*10) % 11);
        if ((resultado == 10)){
            verificador = 0;
        }
        else{
            verificador = resultado;
        }

        //verifica o segundo dígito

        if (verificador != (int)(CNPJ.charAt(13) - 48)){
            return false;
        }
        return true;
        
    }

    @Override
    public String toString() {
        String retorno = super.toString();
        retorno += ", Informações Cliente PJ: [CNPJ: " + CNPJ + ", dataFundacao: " + formataData.format(dataFundacao) + "]";
        return retorno;
    }
}
