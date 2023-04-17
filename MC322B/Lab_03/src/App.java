import classes.Cliente;
import classes.ClientePF;
import classes.ClientePJ;
import classes.Veiculo;

public class App {

    public static void main(String[] args) throws Exception {
        
        //Instanciando veículos
        Veiculo Veiculo1 = new Veiculo("DWG-8678", "Chevrolet", "Classic", 2005);
        Veiculo Veiculo2 = new Veiculo("FTW8T58", "Honda", "Civic", 2020);
        
        //Instanciando Clientes:
        Cliente Cliente1 = new Cliente("Jose","Rua Ficticia, 23, Campinas");
        Cliente1.adicionaVeiculo(Veiculo1);
        Cliente1.adicionaVeiculo(Veiculo2);
        
        //Imprimindo todos os atributos dos objetos instanciados da classe Cliente através do toString()
        System.out.println("Testando o toString()");
        System.out.println("Cliente 1: [ " + Cliente1.toString() + " ]");

        ClientePF Cliente1PF = new ClientePF("Jose", "Rua Ficticia, 129, Campinas", "338.547.318-76", "Masculino", "15/06/2022", "Ensino Médio Completo", "01/08/2001", "Media");
        System.out.println(Cliente1PF.verificaCPF());
        System.out.println(Cliente1PF.toString());

        ClientePJ Cliente1PJ = new ClientePJ("Mercado do Zé", "Rua Buarque de Macedo, 23", "53.559.156/0001-73", "21/05/2012");
        System.out.println(Cliente1PJ.verificaCNPJ());
        System.out.println(Cliente1PJ.toString());
    }
}