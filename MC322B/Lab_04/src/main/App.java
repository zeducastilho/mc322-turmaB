package main;
import classes.ClientePF;
import classes.ClientePJ;
import classes.Veiculo;
import classes.Seguradora;



public class App {
	
    public static void main(String[] args) throws Exception {//Instanciando veículos
        Veiculo veiculo1 = new Veiculo("DWG-8678", "Chevrolet", "Classic", 2005);
        Veiculo veiculo3 = new Veiculo("GGG-8888", "Toyota", "Etios", 2020);
        
        //Instanciando a seguradora        
        
        Seguradora seguradora1 = new Seguradora("Azul Seguros", "(19)98752-6322", "comercial@azulseguros.br", "Av. Barão de Itapura, 432, Campinas-SP");

        //Instanciando Clientes:
        ClientePF cliente1PF = new ClientePF("Jose", "Rua Ficticia, 129, Campinas", "338.547.318-76", "Masculino", "15/06/2022", "Ensino Médio Completo", "03/05/2002", "Media");
        cliente1PF.adicionaVeiculo(veiculo1);
        
        ClientePJ cliente1PJ = new ClientePJ("Mercado do Ze", "Rua Buarque de Macedo, 23", "53.559.156/0001-73", "21/05/2012", 20);
        cliente1PJ.adicionaVeiculo(veiculo3);
        
        ClientePJ cliente2PJ = new ClientePJ("Concessionaria Autos", "Avenida João Jorge, 525, Campinas-SP", "67.966.374/0001-37", "21/05/2003",25);
        
        System.out.println("Os clientes ainda não foram adicionados a seguradora, portanto o custo do seguro ainda deve estar zerado para os dois clientes abaixo");
        System.out.println(cliente1PF.toString());
        System.out.println(cliente1PJ.toString());

        //Adicionando Clientes à seguradora:
        seguradora1.cadastrarCliente(cliente1PF);
        seguradora1.cadastrarCliente(cliente1PJ);
        seguradora1.cadastrarCliente(cliente2PJ);
    
        System.out.println("\nImprimindo todos os clientes\n");
        seguradora1.listarCLientes("All");
        System.out.println("\nImprimindo os clientes PF\n");
        seguradora1.listarCLientes("PF");
        System.out.println("\nImprimindo os clientes PJ\n");
        seguradora1.listarCLientes("PJ");
        

        //Testando metodo gerar Sinistros
        
        seguradora1.gerarSinistro("21/05/2022", "Av Dr Romeu Tortma, 500", "DWG-8678", "338.547.318-76");
        seguradora1.gerarSinistro("22/04/2022", "Rua Buarque de Macedo, 1500, Campinas-SP", "GGG-8888","53.559.156/0001-73");
        
        //testando os outros métodos solicitados

        System.out.println("\n");
        seguradora1.listarSinistros();
        System.out.println("\n");
        seguradora1.visualizarSinistro("53.559.156/0001-73");
        System.out.println("\n");
        System.out.println("A receita é: " + seguradora1.calcularReceita() + " reais"); 

      //devido a sua extensao, o código do menu foi colocado em um arquivo separado

      main.Menu.menu();

    }
}
        