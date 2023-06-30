package classes.Seguro;

import classes.Cliente.*;
import classes.Seguradora.Seguradora;
import classes.Seguradora.Sinistro;
import classes.Veiculos.Veiculo;
import classes.Validacao;

import java.util.Date;

import classes.CalcSeguro;

public class SeguroPF extends Seguro {
    
    private Veiculo veiculo;
    private ClientePF cliente;
    
    public SeguroPF(Veiculo veiculo, ClientePF cliente, Date dataInicio, Date dataFim, Seguradora seguradora) {
        super(dataInicio, dataFim, seguradora);
        this.veiculo = veiculo;
        this.cliente = cliente;
    }
    
    public static SeguroPF construtorSeguroPF(Veiculo veiculo, ClientePF cliente, String dataInicio, String dataFim, Seguradora seguradora) {
        
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

        for (Cliente clientes : seguradora.getListaClientes()) {
            if (clientes.equals(cliente)) {
                for (Veiculo veiculos : cliente.getListaVeiculos()) {
                    if (veiculos.getPlaca().equals(veiculo.getPlaca())) {
                        return new SeguroPF(veiculo, cliente, dataDeInicio, dataDeFim, seguradora);
                    }
                }
                System.out.println("Veículo não pertence ao Cliente");
                return null;
            }
        }
        System.out.println("Cliente não encontrado");
        return null;
    }

    // Getters e Setters
    
    public Veiculo getVeiculo() {
        return veiculo;
    }
    
    public void setVeiculo(Veiculo veiculo) {
        for (Veiculo veiculos : cliente.getListaVeiculos()) {
            if (veiculos.getPlaca().equals(veiculo.getPlaca())) {
                this.veiculo = veiculo;
                return;
            }
        }
        System.out.println("Veículo não pertence ao cliente");    
    }
    
    public ClientePF getCliente() {
        return cliente;
    }
    
    public void setCliente(ClientePF cliente) {
        for (Cliente clientes : getSeguradora().getListaClientes()) {
            if (clientes.equals(cliente)) {
                this.cliente = cliente;
                return;
            }
        }
        System.out.println("Cliente não pertence à seguradora");
    }
    
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
        double fatorIdade = cliente.getFator();
        
        double qtdSinistrosCliente = quantidadeSinistros()[0];
        double qtdSinistrosCondutores = quantidadeSinistros()[1];
        double qtdVeiculos = cliente.getListaVeiculos().size();
        
        double valorMensal = CalcSeguro.VALOR_BASE.getValor() * fatorIdade
                             * (1 + 1 / (qtdVeiculos + 2))
                             * (2 + (qtdSinistrosCliente / 10))
                             * (5 + (qtdSinistrosCondutores / 10));
        
        super.setValorMensal(valorMensal);
    }

    @Override
    public String toString() {
        String retorno = "";
        retorno += "Cliente: " + cliente.getNome() + " (" + cliente.getCPF() + ")\n";
        retorno += "ID: " + super.getID() + "\n";
        retorno += "Data de Início: " + super.getDataInicio() + "\n";
        retorno += "Data de Fim: " + super.getDataFim() + "\n";
        retorno += "Veículo: " + veiculo.getMarca() + " " + veiculo.getModelo() + " (" + veiculo.getPlaca() + ")\n";
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
