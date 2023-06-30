package classes.Cliente;

import java.util.ArrayList;
import java.util.Date;

import classes.CalcSeguro;
import classes.Validacao;
import classes.Veiculos.Veiculo;

public class ClientePF extends Cliente{

    //atributos

    private final String CPF;
    private String genero;
    private String educacao;
    private Date dataNascimento;
    private ArrayList<Veiculo> listaVeiculos;

    //construtor

    public ClientePF(String nome, String endereco, String email, String telefone, String CPF, String genero, String educacao,
                    Date dataNascimento) {
        super(nome, endereco, telefone, email); // Chama o construtor da classe pai (Cliente)
        this.CPF = CPF;
        this.genero = genero;
        this.educacao = educacao;
        this.dataNascimento = dataNascimento;
        this.listaVeiculos = new ArrayList<Veiculo>();
    }

    // Método construtor que faz todas as verificações necessárias antes de, de fato, invocar o construtor

    public static ClientePF constrututorClientePF(String nome, String endereco, String email, String telefone, String CPF, String genero, String educacao,
                  String dataNascimento){     

        Date dataDeNascimento = Validacao.formatacaoData(dataNascimento);
        
        if(dataDeNascimento==null){
            System.out.println("Não foi possível formatar a data de nascimento!");
            return null;
        }
        if(!(Validacao.validaNome(nome))){
            return null;
        }
        if(!(Validacao.validaCPF(CPF))){
            return null;
        }
        if(Validacao.idade(dataDeNascimento)<18){
            System.out.println("Cliente não pode ser cadastrado: menor de idade!");
            return null;
        }
        return new ClientePF(nome, endereco, email, telefone, CPF, genero, educacao, dataDeNascimento);
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
        Date dataDeNascimento = Validacao.formatacaoData(dataNascimento);
        if(dataDeNascimento==null){
            System.out.println("Não foi possível formatar a data de nascimento!");
            return;
        }
        this.dataNascimento=dataDeNascimento;
    }
    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }
    
    // Métodos solicitados

    public boolean cadastrarVeiculo(Veiculo veiculo) {
        // Verifica se a placa já está cadastrada
        for (Veiculo veic : listaVeiculos) {
            if (veic.getPlaca().equals(veiculo.getPlaca())) {
                System.out.println("Placa já cadastrada");
                return false;
            }
        }
        // Adiciona o veículo à lista de veículos
        return listaVeiculos.add(veiculo);
    }

    public boolean removerVeiculo(String placa) {
        for (Veiculo veiculo : listaVeiculos) {
            if (veiculo.getPlaca().equals(placa)) {
                listaVeiculos.remove(veiculo);
                return true;
            }
        }
        System.out.println("Veículo não encontrado");
        return false;
    }

    //to String
    
    public String listarVeiculosResumo(){
        String retorno = "";
        if(listaVeiculos!=null && !listaVeiculos.isEmpty()){
            for(int i = 0; i<(listaVeiculos.size()); i++){
                retorno += "    [" + listaVeiculos.get(i).getModelo() + ", " + listaVeiculos.get(i).getPlaca() + "]\n";
            }
        }        
        else{
            retorno += "Sem Veículos Cadastrados";
        }
        return retorno;
    }
    
    @Override
    public String toString() {
        String retorno = super.toString();
        retorno += "\n CPF: " + CPF + "\n Gênero: " + genero + "\n Educação: " + educacao + "\n Data de nascimento: " + Validacao.formatacaoData(dataNascimento)
        + "\n Lista de Veiculos:\n "+ listarVeiculosResumo() + "\n}";
        return retorno;
    }

    public String getTipo(){    // será utilizada no método listaClientes da classe seguradoras
        return "PF";
    }
    
    public String getDocumento(){
        return CPF.replaceAll("[^0-9]+", "");
    }

    public double getFator() {
        int idade = Validacao.idade(dataNascimento);
        if (idade >= 18 && idade < 30) {
            return CalcSeguro.FATOR_18_30.getValor();
        } else if (idade >= 30 && idade < 60) {
            return CalcSeguro.FATOR_30_60.getValor();
        } else if (idade >= 60) {
            return CalcSeguro.FATOR_60_90.getValor();
        } else {
            return 0; // será utilizado para mensagem de erro caso a idade não esteja nas faixas compreendidas
        }
    }
    
}



