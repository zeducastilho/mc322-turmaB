import classes.Cliente;
import classes.Sinistro;
import classes.Veiculo;
import classes.Seguradora;

public class App {
    public static void main(String[] args) throws Exception {
        
        //Instanciando Sinistros
        Sinistro Sinistro1 = new Sinistro("21/05","Rua Cunha Mota, 23, Campinas");
        int ID_Sinistro_1 = Sinistro1.getId();
        Sinistro Sinistro2 = new Sinistro("19/03","Rua Baronesa Geraldo de Resende, 120, Campinas");
        int ID_Sinistro_2 = Sinistro2.getId();

        //Imprimindo atributos dos objetos da classe sinistros
        System.out.println("Testando Sinistros");
        System.out.println("Sinistro 1: [" + " ID: " + ID_Sinistro_1 + "; Data: " + Sinistro1.getData() + "; Endereço: " + Sinistro1.getEndereco() + " ]");
        System.out.println("Sinistro 2: [" + " ID: " + ID_Sinistro_2 + "; Data: " + Sinistro2.getData() + "; Endereço: " + Sinistro2.getEndereco() + " ]");

        Sinistro1.setEndereco("Rua Buarque de Macedo, 23, Campinas"); //Alteração do atributo endereço do Sinistro 1

        //Imprimindo novamente os dois sinistros para verificar se nenhum atributo indesejado foi alterado
        System.out.println("Testando Sinistros após alteração no atributo endereço");
        System.out.println("Sinistro 1: [ ID: " + ID_Sinistro_1 + "; Data: " + Sinistro1.getData() + "; Endereço: " + Sinistro1.getEndereco() + " ]");
        System.out.println("Sinistro 2: [ ID: " + ID_Sinistro_2 + "; Data: " + Sinistro2.getData() + "; Endereço: " + Sinistro2.getEndereco() + " ]");


        //Instanciando Clientes:
        Cliente Cliente1 = new Cliente("Jose", "338.547.318-76", "19/09/2002", 20, "Rua Fictia, 23, Campinas");
        //ultimo dígito do cpf abaixo está incorreto
        Cliente Cliente2 = new Cliente("Joao", "15995158818", "15/02/2001", 19, "Avenida Brasil, 72, Campinas"); 
        
        //imprimindo atributos dos objetos instanciados para a classe clientes:
        System.out.println("Verificando CPF");
        System.out.println("CPF Cliente 1: " + Cliente1.verificaCPF(Cliente1.getCpf()));
        System.out.println("CPF Cliente 2: " + Cliente2.verificaCPF(Cliente2.getCpf()));
        
        //corrigindo o cpf
        Cliente2.setCpf("15995158813");
        System.out.println("CPF Cliente 2 após correção do digito verificador: " + Cliente2.verificaCPF(Cliente2.getCpf()));
        
        //Imprimindo todos os atributos dos objetos instanciados da classe Cliente através do toString()
        System.out.println("Testando o toString()");
        System.out.println("Cliente 1: [ " + Cliente1.toString() + " ]");
        System.out.println("Cliente 2: [ " + Cliente2.toString() + " ]");

        //Instanciando veículos
        Veiculo Veiculo1 = new Veiculo("DWG-8678", "Chevrolet", "Classic");
        Veiculo Veiculo2 = new Veiculo("FTW8T58", "Honda", "Civic");
        
        //imprimindo atributos dos objetos instanciados para a classe clientes:
        System.out.println("Imprimindo Veículos");
        System.out.println("Veículo 1: [ Placa: " + Veiculo1.getPlaca() + "; Marca: " + Veiculo1.getMarca() + "; Modelo: " + Veiculo1.getModelo() + " ]");
        System.out.println("Veículo 2: [ Placa: " + Veiculo2.getPlaca() + "; Marca: " + Veiculo2.getMarca() + "; Modelo: " + Veiculo2.getModelo() + " ]");
    
        //Instanciando Seguradoras
        Seguradora Seguradora1 = new Seguradora("SegCar", "3333-4444", "contato@segcar.br", "Avenida Francisco Glicério, 1230, Campinas");
        Seguradora Seguradora2 = new Seguradora("CarSeg", "4444-5555", "contato@carseg.br", "Avenida Orosimbo Maia, 94, Campinas");
        
        //Imprimindo atributos dos objetos instanciados para a classe Seguradora
        System.out.println("Testando Seguradora");
        System.out.println("Seguradora 1: [" + " Nome: " + Seguradora1.getNome() + "; Telefone: " + Seguradora1.getTelefone() + "; Endereço: " + Seguradora1.getEndereco() + " ]");
        System.out.println("Seguradora 2: [" + " Nome: " + Seguradora2.getNome() + "; Telefone: " + Seguradora2.getTelefone() + "; Endereço: " + Seguradora2.getEndereco() + " ]");
    }
}
