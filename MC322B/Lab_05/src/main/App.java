package main;
import classes.Cliente.*;
import classes.Seguradora.*;
import classes.Veiculos.*;
import classes.Seguro.*;



public class App {
	
    public static void main(String[] args) throws Exception {
        
      //Instanciando veículos
      Veiculo veiculo1 = new Veiculo("DWG-8678", "Chevrolet", "Classic", 2005);
      Veiculo veiculo2 = new Veiculo("GGG-8888", "Toyota", "Etios", 2020);
      // daqui para baixo serão utilizados para instanciar duas frotas
      Veiculo veiculo3 = new Veiculo("ABC-1234", "Ford", "Fiesta", 2012);
      Veiculo veiculo4 = new Veiculo("XYZ-5678", "Volkswagen", "Gol", 2018);
      Veiculo veiculo5 = new Veiculo("HJK-9876", "Honda", "Civic", 2019);
      Veiculo veiculo6 = new Veiculo("QWE-2468", "Hyundai", "HB20", 2017);
      Veiculo veiculo7 = new Veiculo("MNB-1357", "Fiat", "Uno", 2010);
    

      //Instanciando Frotas

      Frota frota1 = new Frota("Frota 1");
      Frota frota2 = new Frota("Frota 2");

      frota1.cadastrarVeiculo(veiculo3);
      frota1.cadastrarVeiculo(veiculo4);
      frota2.cadastrarVeiculo(veiculo5);
      frota2.cadastrarVeiculo(veiculo6);
      frota2.cadastrarVeiculo(veiculo6); // testando cadastrar um veiculo repetido

      //Instanciando a seguradora        
        
      Seguradora seguradora1 = new Seguradora("Azul Seguros", "(19)98752-6322", "comercial@azulseguros.br", "Av. Barão de Itapura, 432, Campinas-SP", "43.613.563/0001-68");

        //Instanciando Clientes:
        ClientePF cliente1PF = ClientePF.constrututorClientePF("Jose", "Rua Ficticia, 129, Campinas", "zeducastilho@gmail.com", "19981930581", "338.547.318-76", "Masculino", "Ensino Médio Completo", "03/05/2002");
        cliente1PF.cadastrarVeiculo(veiculo1);
        cliente1PF.cadastrarVeiculo(veiculo2);
        
        ClientePJ cliente1PJ = ClientePJ.construtorClientePJ("Mercado do Ze", "Rua Buarque de Macedo, 23", "3242-7314", "mercadoze@gmail.com", "53.559.156/0001-73", "21/05/2012", 20);
        cliente1PJ.cadastrarFrota(frota1);
        cliente1PJ.cadastrarFrota(frota2);
        cliente1PJ.atualizarFrota(frota2, veiculo6); // remove veiculo 6
        cliente1PJ.atualizarFrota(frota2, veiculo7); // adc veiculo 7
        System.out.println(frota2.toString());

        //Adicionando Clientes à seguradora:
        seguradora1.cadastrarCliente(cliente1PF);
        seguradora1.cadastrarCliente(cliente1PJ);
    
        System.out.println("\n##Imprimindo todos os clientes##");
        System.out.println(seguradora1.listarCLientes("All"));
        System.out.println("\n##Imprimindo os clientes PF##");
        System.out.println(seguradora1.listarCLientes("PF"));
        System.out.println("\n##Imprimindo os clientes PJ##");
        System.out.println(seguradora1.listarCLientes("PJ"));
        

        //Testando metodo gerar Seguros

        System.out.println("Receita antes de gerar Seguros:" + seguradora1.calcularReceita());
        
        seguradora1.gerarSeguro("21/05/2023", "21/05/2024", "Frota 1", cliente1PJ.getDocumento());
        seguradora1.gerarSeguro("21/06/2023", "21/06/2023", "GGG-8888", cliente1PF.getDocumento());
        
        
        Condutor condutor1 = Condutor.construtorCondutor("Joao", "Av Dr Romeu Tortma, 500", "joaozinho@gmail.com", "1998181-0505", "283.130.770-85", "19/09/2001");
        Condutor condutor2 = Condutor.construtorCondutor("Maria", "Rua das Flores, 123", "mariazinha@gmail.com", "1999999-9999", "990.687.090-70", "10/12/1990");
        Condutor condutor3 = Condutor.construtorCondutor("Pedro", "Avenida Principal, 456", "pedrinho@gmail.com", "1988888-8888", "121.029.890-20", "05/06/1995");
        Condutor condutor4 = Condutor.construtorCondutor("Joana", "Rua das Flores, 123", "joana@gmail.com", "987654321", "888.734.350-09", "10/05/1990");
        Condutor condutor5 = Condutor.construtorCondutor("Lucas", "Av. Principal, 456", "lucas@gmail.com", "987654321", "802.122.660-98", "20/08/1995");

        seguradora1.getSeguro("Frota 1", cliente1PJ.getDocumento()).autorizarCondutor(condutor1);
        seguradora1.getSeguro("Frota 1", cliente1PJ.getDocumento()).autorizarCondutor(condutor2);
        seguradora1.getSeguro("Frota 1", cliente1PJ.getDocumento()).autorizarCondutor(condutor3);
        seguradora1.getSeguro("GGG-8888", cliente1PF.getDocumento()).autorizarCondutor(condutor4);
        seguradora1.getSeguro("GGG-8888", cliente1PF.getDocumento()).autorizarCondutor(condutor5);
        seguradora1.getSeguro("GGG-8888", cliente1PF.getDocumento()).autorizarCondutor(condutor1);
        
        System.out.println("\n"+ seguradora1.visualizarSeguros(cliente1PJ.getDocumento())+ "\n");
        System.out.println(seguradora1.visualizarSeguros(cliente1PF.getDocumento())+ "\n");
        
        System.out.println("Receita depois de gerar Seguros:" + seguradora1.calcularReceita());
        
        seguradora1.getSeguro("Frota 1", cliente1PJ.getDocumento()).gerarSinistro("21/05/2022", "Av Dr Romeu Tortma, 500","283.130.770-85");
        seguradora1.getSeguro("GGG-8888", cliente1PF.getDocumento()).gerarSinistro("22/04/2022", "Rua Buarque de Macedo, 1500, Campinas-SP", "888.734.350-09");
        
        //testando os outros métodos solicitados

        System.out.println(seguradora1.getSeguro("Frota 1", cliente1PJ.getDocumento()).listarSinistros());
        System.out.println(seguradora1.getSeguro("GGG-8888", cliente1PF.getDocumento()).listarSinistros());
        System.out.println("A receita é: " + seguradora1.calcularReceita() + " reais"); 

    }
}
        