import classes.Cliente;
import classes.ClientePF;
import classes.ClientePJ;
import classes.Veiculo;
import classes.Sinistro;
import classes.Seguradora;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        
        //Instanciando veículos
        Veiculo Veiculo1 = new Veiculo("DWG-8678", "Chevrolet", "Classic", 2005);
        Veiculo Veiculo2 = new Veiculo("FTW8T58", "Honda", "Civic", 2020);
        Veiculo Veiculo3 = new Veiculo("GGG-8888", "Toyota", "Etios", 2020);

        System.out.println(Veiculo2.toString());
        
        //Instanciando a seguradora        
        
        Seguradora Seguradora1 = new Seguradora("Azul Seguros", "(19)98752-6322", "comercial@azulseguros.br", "Av. Barão de Itapura, 432, Campinas-SP");
        
        //Tentando gerar um sinistro de um cliente não adicionado à seguradora
            
        Seguradora1.gerarSinistro("22/04/2022", "Rua Buarque de Macedo, 1500, Campinas-SP", "GGG-7777","Joao");
                
        //Testando os metodos listar antes de adicionar elementos
        Seguradora1.listarCLientes("All");
        Seguradora1.listarSinistros();

        //Instanciando Clientes:
        ClientePF Cliente1PF = new ClientePF("Jose", "Rua Ficticia, 129, Campinas", "338.547.318-76", "Masculino", "15/06/2022", "Ensino Médio Completo", "01/08/2001", "Media");
        System.out.println("CPF Cliente1PF é valido: " + Cliente1PF.verificaCPF());
        System.out.println("Imprimindo o to String antes de cadastrar veículos");
        System.out.println(Cliente1PF.toString());
        Cliente1PF.adicionaVeiculo(Veiculo1);
        Cliente1PF.adicionaVeiculo(Veiculo2);
        System.out.println("Veículos Cadastrados");
        System.out.println(Cliente1PF.toString());

        ClientePJ Cliente1PJ = new ClientePJ("Mercado do Zé", "Rua Buarque de Macedo, 23", "53.559.156/0001-73", "21/05/2012");
        System.out.println("Validando CNPJ Cliente1PJ: " + Cliente1PJ.verificaCNPJ());
        Cliente1PJ.adicionaVeiculo(Veiculo3);
        System.out.println(Cliente1PJ.toString());

        ClientePJ Cliente2PJ = new ClientePJ("Concessionária Autos", "Avenida João Jorge, 525, Campinas-SP", "67.966.374/0001-37", "21/05/2003");
        
        //Adicionando Clientes à seguradora:
        Seguradora1.cadastrarCliente(Cliente1PF);
        Seguradora1.cadastrarCliente(Cliente1PJ);
        Seguradora1.cadastrarCliente(Cliente1PF); // tentando cadastrar um cliente repetido
        Seguradora1.removeCLiente(Cliente2PJ); // tentando remover um cliente que ainda não foi adicionado
        Seguradora1.cadastrarCliente(Cliente2PJ);
        // tentando cadastrar cliente com documento inválido
        Seguradora1.cadastrarCliente(new ClientePJ("Concessionária AutoMoveis", "Avenida João Jorge, 525, Campinas-SP", "68.966.374/0001-37", "21/05/2003"));
        
        System.out.println("Imprimindo todos os clientes");
        Seguradora1.listarCLientes("All");
        System.out.println("Imprimindo os clientes PF");
        Seguradora1.listarCLientes("PF");
        System.out.println("Imprimindo os clientes PJ");
        Seguradora1.listarCLientes("PJ");
        
        //Testando classe sinistros
        Sinistro Sinistro1 = new Sinistro("21/05/2022", "Av Dr Romeu Tortma, 500",Seguradora1, Veiculo2, Cliente1PJ);
        System.out.println(Sinistro1.toString());

        //Testando metodo gerar Sinistros

        Seguradora1.gerarSinistro("22/04/2022", "Rua Buarque de Macedo, 1500, Campinas-SP", "GGG-7777","Jose"); //Tentando gerar um sinistro para um veiculo que o cliente nao tem cadastrado
        Seguradora1.gerarSinistro("21/05/2022", "Av Dr Romeu Tortma, 500", "DWG-8678", "Jose");
        Seguradora1.gerarSinistro("22/04/2022", "Rua Buarque de Macedo, 1500, Campinas-SP", "GGG-8888","Mercado do Zé");
        Seguradora1.listarSinistros();

        //Menu Iterativo
        
        Scanner scan = new Scanner(System.in);
        Scanner scan1 = new Scanner(System.in);
        int comando = 0;      
        do{System.out.println("\nSelecione o comando desejado: \n 0 para interromper a execução \n 1 para listar os sinistros gerados \n 2 para gerar um novo sinistro \n 3 para listar os clientes cadastrados");
        comando = Integer.parseInt(scan.next());
        switch (comando){
            case 0:
                break;
            case 1:
                Seguradora1.listarSinistros();
                break;
            case 2:
                System.out.println("Digite o nome do Cliente como consta no Cadastro");
                String nome = scan.next();
                System.out.println("Digite a placa do veículo");
                String placa = scan.next();
                System.out.println("Digite o endereço do ocorrido");
                String endereco = scan1.nextLine();
                System.out.println("Digite a data do ocorrido");
                String data = scan.next();
                
           
                Seguradora1.gerarSinistro(data, endereco, placa, nome);
                break;
            
            case 3:
                System.out.println("Insira o tipo de cliente desejado: \n '1' para imprimir todos\n '2' para imprimir pessoas físicas \n '3' para imprimir pessoas jurídicas");
                int tipo_cliente = Integer.parseInt(scan.next());
                String tipoCliente="none";
                switch (tipo_cliente) {
                    case 1:
                        tipoCliente = "All";
                        break;
                    case 2:
                        tipoCliente = "PF";
                        break;
                    case 3:
                        tipoCliente = "PJ";
                        break;
                
                }
                Seguradora1.listarCLientes(tipoCliente);
                break;
        }

        }while (comando!=0);
        scan.close();

    
    
    
    }
}