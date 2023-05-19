package classes;
import java.util.Date;
import java.text.ParseException;
import java.time.LocalDateTime;

public class ClientePF extends Cliente{

    //atributos

    private final String CPF;
    private String genero;
    private Date dataLicenca;
    private String educacao;
    private Date dataNascimento;
    private String classeEconomica;

    //creator

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

    //getters e setters

    public String getCPF() {
        return CPF;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public Date getDataLicenca() {
        return dataLicenca;
    }
    public void setDataLicenca(String dataLicenca) {
        try{
            this.dataLicenca = formataData.parse(dataLicenca);
        } catch (ParseException ex) {
            // ignore
        }
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
    public void setDataNascimento(String dataNascimento) {
        try{
            this.dataNascimento = formataData.parse(dataNascimento);
        } catch (ParseException ex) {
            // ignore
        }
    }
    public String getClasseEconomica() {
        return classeEconomica;
    }
    public void setClasseEconomica(String classeEconomica) {
        this.classeEconomica = classeEconomica;
    }
    
    //to String
    @Override
    public String toString() {
        String retorno = super.toString();
        retorno += "\n    CPF: " + CPF + "\n    gênero: " + genero + "\n    data de emissão da licença: " + formataData.format(dataLicenca) + "\n    educação: "
                + educacao + "\n    data de nascimento: " + formataData.format(dataNascimento) + "\n    classe econômica: " + classeEconomica + "\n    }";
        return retorno;
    }

    //Métodos auxiliares
    
    public int idade(){
        //obtem e formata a data do dia de hoje
        LocalDateTime hoje = LocalDateTime.now();
        String dataHoje = hoje.toString();
        dataHoje = dataHoje.substring(8, 10) + "/" + dataHoje.substring(5, 7) + "/" + dataHoje.substring(0,4);
        try{
            Date dataFormatada= formataData.parse(dataHoje);
            //obtem a diferença em milisegundos de uma data até outra
            long diferencaEmMilisegundos = dataFormatada.getTime() - dataNascimento.getTime();
            /*esse if foi necessário para poder considerar as datas em anos bissextos
              ao tentar apenas dividir por 365.25 (1 dia a cada ano bissexto), caso a data de nascimento tivesse
              o mesmo dia do mês da data atual, o retorno era como se a pessoa não tivesse completado mais um ano
              Exemplo: o retorno para a data de nascimendo 03/05/1980 em um teste realizado no dia 03/05/2023
              foi 82 anos, e não 83 anos como era de se esperar. o if abaixo trata esse caso especial 
            */
            if(dataNascimento.getDate()==dataFormatada.getDate()){ 
                return (int)(diferencaEmMilisegundos / 1000 / 60 / 60 / 24/ 365);
            }
            return (int)(diferencaEmMilisegundos / 1000 / 60 / 60 / 24/ 365.25);
        } catch (ParseException ex) {
            return 0; // necessário para compilar, é o caso em que por algum motivo não é possível formatar a data
        }
        
    }
    @Override
    public double calculaScore(){
        double fator = 0;
        if (this.idade()>=18 && this.idade()<30){
            fator = CalcSeguro.FATOR_18_30.getValor();
        }
        else if(this.idade()>=30 && this.idade()<60){
            fator = CalcSeguro.FATOR_30_60.getValor();
        }
        else if(this.idade()>=60 && this.idade()<=90){
            fator = CalcSeguro.FATOR_60_90.getValor();
        }
        else{
            fator = 0; //será utilizado para mensagem de erro casa a idade nao esteja nas faixas compreendidas
        }
        return CalcSeguro.VALOR_BASE.getValor() * fator * this.getListaVeiculos().size();
    }   

    @Override
    public String getTipo(){    // será utilizada no método listaClientes da classe seguradoras
        return "PF";
    }

    @Override
    public boolean validaCliente(){
        if(Validacao.validaNome(this.getNome())){		// checando se o nome tem apenas letras
			if (Validacao.validaCPF(this.CPF)){
                return true;
            }
            System.out.println("Não foi possível cadastrar o cliente " + this.getNome() + ": cliente com documento inválido");
            return false;
		}
		System.out.println("Não foi possível cadastrar o cliente: nome inválido (digite apenas letras) ");
		return false; 
    }

    @Override
    public String getDocumento(){
        return CPF.replaceAll("[^0-9]+", "");
    }
}



