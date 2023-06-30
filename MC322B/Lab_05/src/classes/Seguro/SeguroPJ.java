package classes.Seguro;

import classes.Cliente.*;
import classes.Seguradora.Seguradora;
import classes.Seguradora.Sinistro;
import classes.Veiculos.Frota;
import classes.Validacao;
import classes.CalcSeguro;

import java.util.Date;


public class SeguroPJ extends Seguro {

    private Frota frota;
    private ClientePJ cliente;

    public SeguroPJ(Frota frota, ClientePJ cliente, Date dataInicio, Date dataFim, Seguradora seguradora) {
        super(dataInicio, dataFim, seguradora);
        this.frota = frota;
        this.cliente = cliente;
    }

    public static SeguroPJ construtorSeguroPJ(Frota frota, ClientePJ cliente, String dataInicio, String dataFim, Seguradora seguradora) {
        Date dataDeInicio = Validacao.formatacaoData(dataInicio);
        Date dataDeFim = Validacao.formatacaoData(dataFim);
        
        if(dataDeInicio==null){
            System.out.println("Não foi possível formatar a data de início!");
            return null;
        }
        if(dataDeFim==null){
            System.out.println("Não foi possível formatar a data de fim!");
            return null;
        }
        if(seguradora.getListaClientes().contains(cliente)) {
            if (cliente.getListaFrotas().contains(frota)){
                return new SeguroPJ(frota, cliente, dataDeInicio, dataDeFim, seguradora);
            }
            System.out.println("Frota não encontrada");
            return null;
        }
        System.out.println("Cliente não encontrado");
        return null;
    }

    // Métodos getters e setters para os atributos da classe

    public Frota getFrota() {
        return frota;
    }
    public void setFrota(Frota frota) {
        this.frota = frota;
    }
    public ClientePJ getCliente() {
        return cliente;
    }
    public void setCliente(ClientePJ cliente) {
        this.cliente = cliente;
    }
    // Métodos herdados da classe Seguro

     public int[] quantidadeSinistros() {
        int[] qtdSinistros = new int[2];
        int qtdCliente = 0;
        int qtdCondutores = 0;
        
        for (Seguro seguro : getSeguradora().getListaSeguros()) {
            for (Sinistro sinistro: seguro.getListaSinistros()){
                Condutor condutor = sinistro.getCondutor();
                Cliente cliente = sinistro.getSeguro().getCliente();
            
                if (getListaCondutores().contains(condutor)) {
                    qtdCondutores++;
                }
                
                if (cliente.equals(this.cliente)) {
                    qtdCliente++;
                }

            }
        }
        qtdSinistros[0] = qtdCliente;
        qtdSinistros[1] = qtdCondutores;
        
        return qtdSinistros;
    }

    public void calcularValor() {
        double valorBase = CalcSeguro.VALOR_BASE.getValor();
        int quantidadeFunc = cliente.getQtdFuncionarios();
        int quantidadeVeiculos = frota.getListaVeiculos().size();
        int anosPosFundacao = cliente.anosFundacao();
        int quantidadeSinistrosCliente = quantidadeSinistros()[0];
        int quantidadeSinistrosCondutor = quantidadeSinistros()[1];
    
        double valorMensal = valorBase
                * (10 + (quantidadeFunc) / 10)
                * (1 + 1 / (quantidadeVeiculos + 2))
                * (1 + 1 / (anosPosFundacao + 2))
                * (2 + quantidadeSinistrosCliente / 10)
                * (5 + quantidadeSinistrosCondutor / 10);
    
        super.setValorMensal(valorMensal);
    }
    @Override
        public String toString() {
            String retorno = "";
            retorno += "Cliente: " + cliente.getNome() + " (" + cliente.getCNPJ() + ")\n";
            retorno += "ID: " + super.getID() + "\n";
            retorno += "Data de Início: " + super.getDataInicio() + "\n";
            retorno += "Data de Fim: " + super.getDataFim() + "\n";
            retorno += "Frota: " + frota.getCode() + "\n";
            retorno += "Seguradora: " + super.getSeguradora().getNome() + "\n";
            retorno += "Lista de Condutores:\n";
        
            if (super.getListaCondutores().isEmpty()) {
                retorno += "\tNenhum condutor cadastrado\n";
            } else {
                for (Condutor condutor : super.getListaCondutores()) {
                    retorno += "\t" + condutor.getNome() + "\n";
                }
            }
        
            retorno += "Número de Sinistros: " + super.getListaSinistros().size() + "\n";
            retorno += "Valor Mensal: " + super.getValorMensal() + "\n";
        
            return retorno;
    }

}
