package classes.Cliente;

import java.util.Date;
import java.util.ArrayList;

import classes.Validacao;
import classes.Veiculos.Frota;
import classes.Veiculos.Veiculo;


public class ClientePJ extends Cliente{
    
    //atributos

    private final String CNPJ;
    private Date dataFundacao;
    private ArrayList<Frota> listaFrotas;
    private int qtdFuncionarios;

    //creator

    public ClientePJ(String nome, String endereco, String telefone, String email, String CNPJ, Date dataFundacao, int funcionarios) {
        super(nome, endereco, telefone, email); // Chama o construtor da classe pai (Cliente)
        this.CNPJ = CNPJ;
        this.dataFundacao = dataFundacao;
        this.listaFrotas = new ArrayList<Frota>();
        this.qtdFuncionarios = funcionarios;
    }

    public static ClientePJ construtorClientePJ(String nome, String endereco, String telefone, String email, String CNPJ,
                                                String dataFundacao, int funcionarios){

        Date dataDeFundacao = Validacao.formatacaoData(dataFundacao);
        
        if(dataDeFundacao==null){
            System.out.println("Não foi possível formatar a data de fundação!");
            return null;
        }
        if(!(Validacao.validaNome(nome))){
            return null;
        }
        if(!(Validacao.validaCNPJ(CNPJ))){
            return null;   
        }
        return new ClientePJ(nome, endereco, telefone, email, CNPJ, dataDeFundacao, funcionarios);
    }

    // getters and setters

    public String getCNPJ() {
        return CNPJ;
    }
    public Date getDataFundacao() {
        return dataFundacao;
    }
    public void setDataFundacao(String dataFundacao) {
        Date dataDeFundacao = Validacao.formatacaoData(dataFundacao);
        if (dataDeFundacao == null) {
            System.out.println("Não foi possível formatar a data");
            return;
        }
        this.dataFundacao = dataDeFundacao;
    }
    public ArrayList<Frota> getListaFrotas() {
        return listaFrotas;
    }
    public void setListaFrotas(ArrayList<Frota> listaFrotas) {
        this.listaFrotas = listaFrotas;
    }
    public int getQtdFuncionarios() {
        return qtdFuncionarios;
    }
    public void setQtdFuncionarios(int qtdFuncionarios) {
        this.qtdFuncionarios = qtdFuncionarios;
    }

    // to string

    public String listarFrotas() {
        if (listaFrotas != null && !listaFrotas.isEmpty()) {
            String retorno = "";
            for (Frota frota : listaFrotas) {
                retorno += "    [" + frota.getCode() + ", " + frota.getSize() + " veículos cadastrados]\n";
            }
            return retorno;
        }
        else {
            return "Sem Frotas Cadastradas";
        }
    }
    
    @Override
    public String toString() {
        return super.toString() + "\n CNPJ: " + CNPJ + "\n Data de Fundacao: " + Validacao.formatacaoData(dataFundacao) +
        "\n Frotas: \n " + listarFrotas() + "\n}";
    }
    
    // Métodos solicitados
    
    public boolean cadastrarFrota(Frota frota) {
		for (Frota frota_atual: listaFrotas){
            if(frota.getCode().equals(frota_atual.getCode())){
                System.out.println("Não foi possível cadastrar: código duplicado");
                return false;
            }
        }
        return this.listaFrotas.add(frota);
	}

    public boolean atualizarFrota(Frota frota) {    // caso não seja passado como argumento nenhum veículo, remove a frota inteira
        if(frota == null || !this.listaFrotas.contains(frota)){
            System.out.println("Frota não existe");	
            return false;
        }
		return this.listaFrotas.remove(frota);
	}

    public boolean atualizarFrota(Frota frota, Veiculo veiculo) {
		if(!this.listaFrotas.contains(frota) || this.listaFrotas.isEmpty()){
            System.out.println("Lista de Frotas está vazia ou não contém a frota especificada");
            return false;
        }
        if (frota.removerVeiculo(veiculo.getPlaca())){
            System.out.println("Veículo Removido: " + veiculo.getPlaca());
            return true;
        }
        if(frota.cadastrarVeiculo(veiculo)){
            System.out.println("Veículo Adicionado: " + veiculo.getPlaca());
            return true;
        }
		return false;
	}

    public boolean getVeiculosPorFrota(String code){
        for(Frota frota: listaFrotas){
            if(frota.getCode().equals(code)){
                System.out.println(frota.listarVeiculosResumo());
                return true;
            }
        }
        System.out.println("Frota não encontrada");
        return false;
    }
   
    // Métodos Auxiliares

   public String getTipo(){    // será utilizada no método listaClientes da classe seguradoras
        return "PJ";
    }
    @Override
    public String getDocumento(){
        return CNPJ.replaceAll("[^0-9]+", "");
    }

    public int anosFundacao(){ // metodo identico ao metodo idade() de clientePF. os comentários serão suprimidos para não ficar repetitivo
        return Validacao.idade(dataFundacao);
    }

}

