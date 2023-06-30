package classes.Seguro;

import java.util.ArrayList;
import java.util.Date;

import classes.Validacao;
import classes.Seguradora.Sinistro;


public class Condutor {
       
    //atributos

    private final String CPF;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private Date dataNascimento;
    private ArrayList<Sinistro> listaSinistros;

    public Condutor(String nome, String endereco, String email, String telefone, String CPF, Date dataNascimento) {
        this.dataNascimento = dataNascimento;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.CPF = CPF;
        this.listaSinistros = new ArrayList<Sinistro>();
        
    }
    public static Condutor construtorCondutor(String nome, String endereco, String email, String telefone, String CPF, String dataNascimento){
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
            System.out.println("Condutor não pode ser cadastrado: menor de idade!");
            return null;
        }
        return new Condutor(nome, endereco, email, telefone, CPF, dataDeNascimento);
    }

    // getters e setters
    
    public String getCPF() {
        return CPF;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = Validacao.formatacaoData(dataNascimento);
    }
    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }
    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    // Métodos Solicitados

    // não utilizei o .size() dessa lista para contar os sinistros de um condutor. acredito que os sinistros de um cliente
    // em outra seguradora não devem ser levados em conta para o cálculo do custo do seguro. sendo assim, não encontro uma utilidade
    // para esse método. acredito inclusive que essa é uma informação "particular" a qual as seguradoras não deveriam ter acesso
    public boolean adicionarSinistro(Sinistro sinistro){        
        return listaSinistros.add(sinistro);
    }
    
    // Métodos Auxiliares

    public String getDocumento(){
        return CPF.replaceAll("[^0-9]+", "");
    }
}



