package classes;
import java.util.Date;
import java.text.ParseException;

public class ClientePJ extends Cliente{
    
    //atributos

    private final String CNPJ;
    private Date dataFundacao;
    private int qtdFuncionarios;

    //creator

    public ClientePJ(String nome, String endereco, String CNPJ, String dataFundacao, int funcionarios) {
        super(nome, endereco);
        this.CNPJ = CNPJ;
        this.qtdFuncionarios = funcionarios;
        try{
            this.dataFundacao = formataData.parse(dataFundacao);
        } catch (ParseException ex) {
            // ignore
          }
    }

    // getters and setters

    public int getQtdFuncionarios() {
        return qtdFuncionarios;
    }
    public void setQtdFuncionarios(int qtdFuncionarios) {
        this.qtdFuncionarios = qtdFuncionarios;
    }
    public String getCNPJ() {
        return CNPJ;
    }
   public Date getDataFundacao() {
        return dataFundacao;
    }
    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    // to string

    @Override
    public String toString() {
        String retorno = super.toString();
        retorno += "\n    CNPJ: " + CNPJ + "\n    Quantidade de Funcionários: "+ qtdFuncionarios + "\n    Data de Fundacao: " + formataData.format(dataFundacao) + "\n    }";
        return retorno;
    }
    
   // Métodos Auxiliares

    @Override
    public double calculaScore(){
        return ( CalcSeguro.VALOR_BASE.getValor() * (1+(qtdFuncionarios/100.00)) * super.getListaVeiculos().size());
    }
    @Override
    public String getTipo(){    // será utilizada no método listaClientes da classe seguradoras
        return "PJ";
    }
    @Override
    public boolean validaCliente(){
        if(Validacao.validaNome(this.getNome())){		// checando se o nome tem apenas letras
			if (Validacao.validaCNPJ(this.CNPJ)){
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
        return CNPJ.replaceAll("[^0-9]+", "");
    }
}

