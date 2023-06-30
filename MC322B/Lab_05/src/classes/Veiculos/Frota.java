package classes.Veiculos;
import java.util.ArrayList;

public class Frota {
    
    // atributos

    private String code;
    private ArrayList<Veiculo> listaVeiculos;
   
    // construtor

    public Frota(String code) {
        this.code = code;
        this.listaVeiculos = new ArrayList<Veiculo>();
    }

    //getters e setters

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }
    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }
    public String getSize(){
        return String.valueOf(listaVeiculos.size());
    }

    // to string
    
    public String listarVeiculosResumo(){
        String retorno = "";
        if(listaVeiculos!=null && listaVeiculos.size()>0){
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
        return "Frota " + code + ":\n" + listarVeiculosResumo(); 
    }

    public boolean cadastrarVeiculo(Veiculo veiculo){
        try {
            // Verifica se a placa já está cadastrada
            for (Veiculo veic : listaVeiculos) {
                if (veic.getPlaca().equals(veiculo.getPlaca())) {
                    System.out.println("Placa já cadastrada: " + veiculo.getPlaca());
                    return false;
                }
            }
            // Adiciona o veículo à lista de veículos
            listaVeiculos.add(veiculo);
            
        } catch (Exception e) {
            // Caso ocorra algum erro ao adicionar o veículo
            return false;
        }
        return true;
    }

    public boolean removerVeiculo(String placa){
        boolean status = false;
        for (Veiculo veiculo : listaVeiculos) {
            if (veiculo.getPlaca().equals(placa)){
                status = listaVeiculos.remove(veiculo);
                break;
            }
        }
        return status;
    }
}
