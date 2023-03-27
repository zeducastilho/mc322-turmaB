import classes.Cliente;
import classes.Sinistro;
import classes.Seguradora;
import classes.Sinistro;

public class App {
    public static void main(String[] args) throws Exception {
        
        //Instanciando Sinistros
        Sinistro Sinistro1 = new Sinistro("21/05","Rua Cunha Mota, 23, Campinas");
        int ID_Sinistro_1 = Sinistro1.getId();
        Sinistro Sinistro2 = new Sinistro("19/03","Rua Baronesa Geraldo de Resende, 120, Campinas");
        int ID_Sinistro_2 = Sinistro2.getId();

        //Imprimindo atributos dos objetos da classe sinistros
        System.out.println("Sinistro 1:");
        System.out.println("ID: " + ID_Sinistro_1 + "; Data: " + Sinistro1.getData() + "; Endereço: " + Sinistro1.getEndereco());
        System.out.println("Sinistro 2:");
        System.out.println("ID: " + ID_Sinistro_2 + "; Data: " + Sinistro2.getData() + "; Endereço: " + Sinistro2.getEndereco());

        Sinistro1.setEndereco("Rua Buarque de Macedo, 23, Campinas"); //Alteração do atributo endereço do Sinistro 1

        //Imprimindo novamente os dois sinistros para verificar se nenhum atributo indesejado foi alterado
        System.out.println("Sinistro 1:");
        System.out.println("ID: " + ID_Sinistro_1 + "; Data: " + Sinistro1.getData() + "; Endereço: " + Sinistro1.getEndereco());
        System.out.println("Sinistro 2:");
        System.out.println("ID: " + ID_Sinistro_2 + "; Data: " + Sinistro2.getData() + "; Endereço: " + Sinistro2.getEndereco());


        //Instanciando Clientes:
        Cliente Cliente1 = new Cliente("Jose", "338.547.318-76", "19/09/2002", 20, "Rua Fictia, 23, Campinas");
        //ultimo dígito do cpf abaixo está incorreto
        Cliente Cliente2 = new Cliente("Joao", "15995158818", "15/02/2001", 19, "Avenida Brasil, 72, Campinas"); 
        
        //imprimindo atributos dos objetos instanciados para a classe clientes:
        System.out.println("CPF Cliente 1: " + Cliente1.verificaCPF(Cliente1.getCpf()));
        System.out.println("CPF Cliente 2: " + Cliente2.verificaCPF(Cliente2.getCpf()));
        //corrigindo o cpf
        Cliente2.setCpf("15995158813");
        System.out.println("CPF Cliente 2: " + Cliente2.verificaCPF(Cliente2.getCpf()));
        System.out.println("Cliente 2: [" + Cliente2.toString() + "]");
        System.out.println(Cliente1.toString());
        System.out.println(Cliente2.toString());

    
    
    }
}
