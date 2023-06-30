package classes.Seguro;

import java.util.Date;
import java.util.ArrayList;

import classes.Validacao;
import classes.Cliente.Cliente;
import classes.Seguradora.Seguradora;
import classes.Seguradora.Sinistro;


public abstract class Seguro {
    
    private static int ID_generator;
   
    private int gerarid() {  
        ID_generator++;
        return ID_generator;
    }  
    
    //atributos

    private final int ID;
    private Date dataInicio;
    private Date dataFim;
    private Seguradora seguradora;
    private ArrayList<Condutor> listaCondutores;
    private ArrayList<Sinistro> listaSinistros;
    private double valorMensal;


    public Seguro(Date dataInicio, Date dataFim, Seguradora seguradora) {
        this.ID=gerarid();
        this.dataFim = dataFim;
        this.dataInicio = dataInicio;
        this.listaCondutores = new ArrayList<Condutor>();
        this.listaSinistros = new ArrayList<Sinistro>();
        this.seguradora = seguradora;
    }

    public int getID() {
        return ID;
    }
    public Date getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(String dataInicio) {
        Date dataDeInicio = Validacao.formatacaoData(dataInicio);
        
        if(dataDeInicio==null){
            System.out.println("Não foi possível formatar a data de nascimento!");
            return;
        }
        this.dataInicio=dataDeInicio;
    }
    public Date getDataFim() {
        return dataFim;
    }
    public void setDataFim(String dataFim) {
        Date dataDeFim = Validacao.formatacaoData(dataFim);
        
        if(dataDeFim==null){
            System.out.println("Não foi possível formatar a data de nascimento!");
            return;
        }
        this.dataInicio=dataDeFim;
    }
    public Seguradora getSeguradora() {
        return seguradora;
    }
    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }
    public ArrayList<Condutor> getListaCondutores() {
        return listaCondutores;
    }
    public void setListaCondutores(ArrayList<Condutor> listaCondutores) {
        this.listaCondutores = listaCondutores;
    }
    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }
    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }
    public double getValorMensal() {
        return valorMensal;
    }
    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }

    //Métodos solicitados

    public boolean autorizarCondutor(Condutor condutor){
        for (Condutor condutor_lista: listaCondutores){
            if(condutor_lista.getCPF().equals(condutor.getCPF())){
                System.out.println("Condutor com o mesmo CPF cadastrado");
                return false;
            }      
        }
        boolean status = listaCondutores.add(condutor);
        calcularValor();
        seguradora.calcularReceita();
        return status;
    }

    public boolean desautorizarCondutor(String cpfCondutor){
         for(Condutor condutor : listaCondutores){
            if(condutor.getCPF().equals(cpfCondutor)){
                boolean status = listaCondutores.remove(condutor);
                calcularValor();
                seguradora.calcularReceita();
                return status;
            }
        }
        System.out.println("Condutor não autorizado");
        return false;  
    }
    

    public void gerarSinistro(String data, String endereco, String cpfCondutor){
        for(Condutor condutor : listaCondutores){
            if(condutor.getCPF().equals(cpfCondutor)){
                Sinistro sinistro = new Sinistro(data, endereco, this, condutor);
                listaSinistros.add(sinistro);
                condutor.adicionarSinistro(sinistro); 
                calcularValor();
                seguradora.calcularReceita();
                return;
            }
        }
        System.out.println("Condutor não autorizado");
        
    }

    public String listarCondutores(){
        String retorno = "Lista de Condutores:";
        if (listaCondutores.isEmpty()){
            retorno += "Lista Vazia\n";
        }
        for (Condutor condutor: listaCondutores){
            retorno += "\n" + condutor.toString();

        }
        return retorno;
    }

    public String listarSinistros(){
        String retorno = "Lista de Sinistros:";
        if (listaSinistros.isEmpty()){
            retorno += "Lista Vazia\n";
        }
        for (Sinistro sinistro: listaSinistros){
            retorno += "\n" + sinistro.toString();

        }
        return retorno;
    }

    public abstract void calcularValor();
    public abstract String toString();
    public abstract Cliente getCliente();


}
