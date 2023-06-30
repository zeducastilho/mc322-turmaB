package classes.Seguradora;

import classes.Seguro.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sinistro {
    
    private static int ID_generator;
    private SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");

    
    private int gerarid() {  
        ID_generator++;
        return ID_generator;
    }  
    
    //atributos

    private final int ID;
    private Date data;
    private String endereco;
    private Seguro seguro;
    private Condutor condutor;
        
    //Constructor

    public Sinistro(String data, String endereco, Seguro seguro, Condutor condutor) {
        this.ID = gerarid();
        try{
            this.data = formataData.parse(data);
        } catch (ParseException ex) {
            // ignore
          }
        this.endereco = endereco;
        this.seguro = seguro;
        this.condutor = condutor;
    }

    // Getters e Setters

    public int getId() {
        return ID;
    }
    public Date getData() {
        return data;
    }
    public void setData(String data) {
        try{
            this.data = formataData.parse(data);
        } catch (ParseException ex) {
            // ignore
          }
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public Seguro getSeguro() {
        return seguro;
    }
    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }
    public Condutor getCondutor() {
        return condutor;
    }
    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }
    
    @Override
    public String toString() {
        String retorno = "Sinistro:\n";
        retorno += "    ID: " + ID + "\n";
        retorno += "    Data: " + formataData.format(data) + "\n";
        retorno += "    Endereço: " + endereco + "\n";
        retorno += "    Seguro:\n" + "        " + seguro.toString().replaceAll("\n", "\n        ") + "\n";
        retorno += "    Condutor: " + condutor.getNome();
        
        return retorno;
    }
}