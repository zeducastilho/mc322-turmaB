package main;
import java.util.ArrayList;
import java.util.Scanner;
import classes.Seguradora;
import classes.Veiculo;
import classes.ClientePF;
import classes.ClientePJ;

public class Menu {
    
    private static ArrayList<Seguradora> seguradoras_cadastradas = new  ArrayList<>();

    private static Scanner scanner = new Scanner(System.in);
    private static Scanner scanner1 = new Scanner(System.in);

    //métodos para a exibição dos menus

    private static void exibirMenu() {
        MenuOperacoes menuOperacoes[] = MenuOperacoes.values();
        System.out.println("\nMenu principal\n");
        for(MenuOperacoes op: menuOperacoes) {
            System.out.println(" "+(op.ordinal()) + " - " + op.getDescricao());
        }
    }

    private static void exibirSubmenu(MenuOperacoes op) {
		SubmenuOperacoes[] submenu = op.getSubmenu();
        System.out.println("");
		System.out.println(op.getDescricao());
        System.out.println("");
		for(int i=0; i<submenu.length; i++) {
			System.out.println(i +" - " + submenu[i].getDescricao());
		}
	}

    //ler opções do menu externo

	private static MenuOperacoes lerOpcaoMenuExterno() {
		int opUsuario;
		MenuOperacoes opUsuarioConst;
		do {
			System.out.println("\nDigite uma opcao: ");
			opUsuario = scanner.nextInt();
		}while(opUsuario < 0 || opUsuario > MenuOperacoes.values().length - 1);
		opUsuarioConst = MenuOperacoes.values()[opUsuario];
        return opUsuarioConst;
	}
	
	//ler opção dos submenus

	private static SubmenuOperacoes lerOpcaoSubmenu(MenuOperacoes op) {
		int opUsuario;
		SubmenuOperacoes opUsuarioConst;
		do {
			System.out.println("Digite uma opcao: ");
			opUsuario = scanner.nextInt();
		}while(opUsuario < 0 || opUsuario > op.getSubmenu().length - 1);
		opUsuarioConst = op.getSubmenu()[opUsuario];
        return opUsuarioConst;
	}
	
	//executar opções do menu externo

	private static void executarOpcaoMenuExterno(MenuOperacoes op) {
		switch(op) {
			case CADASTROS:
			case LISTAR:
			case EXCLUIR:
				executarSubmenu(op);
				break;
			case GERAR_SINISTRO:
				gerarSinistro();
				break;
			case TRANSFERIR_SEGURO:
				transferirSeguro();
				break;
			case CALCULAR_RECEITA:
				calcularReceita();
				break;
			case SAIR:
                break;
		}
	}

	//executar opções do submenu

	public static void executarOpcaoSubMenu(SubmenuOperacoes opSubmenu) {
		switch(opSubmenu) {
		case CADASTRAR_CLIENTE:
            cadastrarCliente();
			break;
		case CADASTRAR_VEICULO:
			cadastrarVeiculo();
			break;
		case CADASTRAR_SEGURADORA:
            cadastrarSeguradora();
			break;
		case LISTAR_CLIENTES:
			listarClientes();
			break;
		case LISTAR_SINISTROS:
			listarSinistros();
			break;
		case LISTAR_VEICULOS:
			listarVeiculos();
			break;
		case EXCLUIR_CLIENTE:
			excluirCliente();
			break;
		case EXCLUIR_VEICULO:
			excluirVeiculo();
			break;
		case EXCLUIR_SINISTRO:
			excluirSinistro();;
			break;
		case VOLTAR:
			break;
		}
	}
	
	//executa os submenus: exibição do menu, leitura da opção e execução dos métodos

	private static void executarSubmenu(MenuOperacoes op) {
		SubmenuOperacoes opSubmenu;
		do {
			exibirSubmenu(op);
			opSubmenu = lerOpcaoSubmenu(op);
			executarOpcaoSubMenu(opSubmenu);
		}while(opSubmenu != SubmenuOperacoes.VOLTAR);
	}

    public static void menu(){
        MenuOperacoes op;
          do {
              main.Menu.exibirMenu();
              op = lerOpcaoMenuExterno();
              executarOpcaoMenuExterno(op);
          }while(op != MenuOperacoes.SAIR);
          System.out.println("Saiu do sistema");
    }

   
    /* 
     *
     * Abaixo, há vários métodos, mas o cometário será genérico para todos os métodos abaixo devido a semelhança na sua implementação
     * em essência, todos os métodos dependem de uma seguradora. Indo do topo da pirâmide para a base: o sinistro é ligado a um veículo
     * que por sua vez é ligado a um cliente, que por sua vez, só faz sentido ser manipulado dentro de uma seguradora, apesar de poder
     * existir indepentendemente dela
     * 
     * Sendo assim, todos os métodos iniciam solicitando ao usuário o nome da seguradora e buscando na lista de seguradoras a seguradora
     * em questao. Após isso, são solicitadas todas as informações necessárias para invocar os métodos base, como informações para cadastro
     * e identificadores únicos (nomes, documentos, ids). Essas informações são passadas como parâmetros para os métodos base, em sua 
     * maioria declarados na classe seguradora 
     * 
     */

    private static void cadastrarSeguradora() {

        // Leitura das Informações

        System.out.println("Insira as informações da nova seguradora.\n");
            
        System.out.println("Nome: (sem caracteres especiais, acentos e ç)\n");
        
        String nome = scanner1.nextLine();

        System.out.print("\nTelefone: ");
        String telefone = scanner1.nextLine();

        System.out.print("\nEmail: ");
        String email = scanner1.nextLine();

        System.out.print("\nEndereco: ");
        String endereco = scanner1.nextLine();

        //Cadastro da Seguradora
        
        Seguradora novaSeguradora = new Seguradora(nome, telefone, email, endereco);
        seguradoras_cadastradas.add(novaSeguradora);
        System.out.println("\nSeguradora criada!\n");
        
    }
    private static void cadastrarVeiculo() {
        
        System.out.println("Insira as informações do novo veiculo.\n");
        
        System.out.println("Digite o nome da seguradora (sem caracteres especiais, acentos e ç)");

        String nome_seguradora = scanner1.nextLine();
        Seguradora seguradora_pelo_nome = null;

        for(Seguradora seguradora: seguradoras_cadastradas){
            if(seguradora.getNome().equals(nome_seguradora)){
                seguradora_pelo_nome = seguradora;
                break;
            }
        }
        if(seguradora_pelo_nome == null){
            System.out.println("Seguradora não encontrada");
            return;
        }
            
        System.out.print("\nPlaca: ");
        String placa = scanner1.nextLine();
        
        System.out.print("\nMarca: ");
        String marca = scanner1.nextLine();
        
        System.out.print("\nModelo: ");
        String modelo = scanner1.nextLine();
        
        System.out.print("\nAno de Fabricacao: ");
        int ano_fabricacao = scanner1.nextInt();

        scanner1.nextLine();
        
        System.out.print("\nDocumento do cliente: ");
        String documento = scanner1.nextLine();
        

        seguradora_pelo_nome.adicionaVeiculoCliente(documento, new Veiculo(placa, marca, modelo, ano_fabricacao));
        
    }
    private static void cadastrarCliente() {

        //Cadastro de Cliente

        System.out.println("Insira as informações do novo cliente.\n");     
        System.out.println("Digite o nome da seguradora (sem caracteres especiais, acentos e ç)");

        //Atrelando o cliente a uma seguradora

        String nome_seguradora = scanner1.nextLine();
        Seguradora seguradora_pelo_nome = null;

        for(Seguradora seguradora: seguradoras_cadastradas){
            if(seguradora.getNome().equals(nome_seguradora)){
                seguradora_pelo_nome = seguradora;
                break;
            }
        }
        if(seguradora_pelo_nome == null){
            System.out.println("Seguradora não encontrada");
            return;
        }
        
        //Obtendo informações do Cliente

        System.out.print("\nNome do cliente (sem caracteres especiais, acentos e ç): ");
        String nome = scanner1.nextLine();

    
        System.out.print("\nEndereço: ");
        String endereco = scanner1.nextLine();

        System.out.print("\nTipo de Cliente PF/PJ: ");
        String tipo = scanner1.nextLine();
        
        if(tipo.equals("PF")){
            System.out.print("\nCPF: ");
            String cpf = scanner1.nextLine();

            System.out.print("\nGênero: ");
            String genero = scanner1.nextLine();

            System.out.print("\nData de Licença (dd/mm/aaaa): ");
            String dataLicenca = scanner1.nextLine();

            System.out.print("\nNível de Educação: ");
            String educacao = scanner1.nextLine();

            System.out.print("\nData de Nascimento (dd/mm/aaaa): ");
            String dataNascimento = scanner1.nextLine();

            System.out.print("\nClasse Econômica: ");
            String classeEconomica = scanner1.nextLine();

            // Cadastrando cliente tipo PF

            seguradora_pelo_nome.cadastrarCliente(new ClientePF(nome, endereco, cpf, genero, dataLicenca, educacao, dataNascimento, classeEconomica));
            return;
            }

            else if(tipo.equals("PJ")){
                System.out.print("\nCNPJ: ");
                String cnpj = scanner1.nextLine();

                System.out.print("\nData de Fundação (dd/mm/aaaa): ");
                String dataFundacao = scanner1.nextLine();

                System.out.print("\nNúmero de Funcionários: ");
                int funcionarios = scanner1.nextInt();

                seguradora_pelo_nome.cadastrarCliente(new ClientePJ(nome, endereco, cnpj, dataFundacao, funcionarios));
            }

    }
    private static void listarClientes() {        
        
        //Entrar em uma seguradora
        System.out.println("Digite o nome da seguradora (sem caracteres especiais, acentos e ç)");
        String nome_seguradora = scanner1.nextLine();
        Seguradora seguradora_pelo_nome = null;

        for(Seguradora seguradora: seguradoras_cadastradas){
            if(seguradora.getNome().equals(nome_seguradora)){
                seguradora_pelo_nome = seguradora;
                break;
            }
        }
        if(seguradora_pelo_nome == null){
            System.out.println("Seguradora não encontrada");
            return;
        }
        
        System.out.println("Digite o tipo de cliente para visualizar (PF / PJ / All)");
        
        String tipo_cliente = scanner1.nextLine();
        seguradora_pelo_nome.listarCLientes(tipo_cliente);
    }
    private static void listarSinistros() {        
        
        //Entrar em uma seguradora
        System.out.println("Digite o nome da seguradora (sem caracteres especiais, acentos e ç)");
        String nome_seguradora = scanner1.nextLine();
        Seguradora seguradora_pelo_nome = null;

        for(Seguradora seguradora: seguradoras_cadastradas){
            if(seguradora.getNome().equals(nome_seguradora)){
                seguradora_pelo_nome = seguradora;
                break;
            }
        }
        if(seguradora_pelo_nome == null){
            System.out.println("Seguradora não encontrada");
            return;
        }
        
        seguradora_pelo_nome.listarSinistros();
    }
    private static void listarVeiculos(){
        System.out.println("Digite o nome da seguradora (sem caracteres especiais, acentos e ç)");

        String nome_seguradora = scanner1.nextLine();
        Seguradora seguradora_pelo_nome = null;

        for(Seguradora seguradora: seguradoras_cadastradas){
            if(seguradora.getNome().equals(nome_seguradora)){
                seguradora_pelo_nome = seguradora;
                break;
            }
        }
        if(seguradora_pelo_nome == null){
            System.out.println("Seguradora não encontrada");
            return;
        }

        System.out.print("\nDocumento do cliente: ");
        String documento = scanner1.nextLine();

        seguradora_pelo_nome.getCliente(documento).listarVeiculos();


    }
    private static void excluirCliente(){
        System.out.println("Digite o nome da seguradora (sem caracteres especiais, acentos e ç)");

        //Atrelando o cliente a uma seguradora

        String nome_seguradora = scanner1.nextLine();
        Seguradora seguradora_pelo_nome = null;

        for(Seguradora seguradora: seguradoras_cadastradas){
            if(seguradora.getNome().equals(nome_seguradora)){
                seguradora_pelo_nome = seguradora;
                break;
            }
        }
        if(seguradora_pelo_nome == null){
            System.out.println("Seguradora não encontrada");
            return;
        }

        System.out.print("\nDocumento do cliente: ");
        String documento = scanner1.nextLine();

        seguradora_pelo_nome.removeCliente(documento);

    }
    private static void excluirVeiculo(){
        System.out.println("Digite o nome da seguradora (sem caracteres especiais, acentos e ç)");

        //Atrelando o cliente a uma seguradora

        String nome_seguradora = scanner1.nextLine();
        Seguradora seguradora_pelo_nome = null;

        for(Seguradora seguradora: seguradoras_cadastradas){
            if(seguradora.getNome().equals(nome_seguradora)){
                seguradora_pelo_nome = seguradora;
                break;
            }
        }
        if(seguradora_pelo_nome == null){
            System.out.println("Seguradora não encontrada");
            return;
        }

        System.out.print("\nDocumento do cliente: ");
        String documento = scanner1.nextLine();

        System.out.print("\nPlaca do Veiculo: ");
        String placa = scanner1.nextLine();

        seguradora_pelo_nome.getCliente(documento).removeVeiculo(placa);

    }
    private static void excluirSinistro() {        
        
        //Entrar em uma seguradora
        System.out.println("Digite o nome da seguradora (sem caracteres especiais, acentos e ç)");
        String nome_seguradora = scanner1.nextLine();
        Seguradora seguradora_pelo_nome = null;

        for(Seguradora seguradora: seguradoras_cadastradas){
            if(seguradora.getNome().equals(nome_seguradora)){
                seguradora_pelo_nome = seguradora;
                break;
            }
        }
        if(seguradora_pelo_nome == null){
            System.out.println("Seguradora não encontrada");
            return;
        }
        System.out.println("Abaixo serão listados todos os sinistros. Atente-se ao ID do Sinistro que deseja remover");
        seguradora_pelo_nome.listarSinistros();
        System.out.println("Digite o ID do Sinistro a ser excluido");

        int id = scanner1.nextInt();
        seguradora_pelo_nome.removerSinistro(id);
        
    }
    private static void gerarSinistro(){
        
        System.out.println("Insira as informações do novo sinistro.\n");

        System.out.println("Digite o nome da seguradora (sem caracteres especiais, acentos e ç)");

        String nome_seguradora = scanner1.nextLine();
        Seguradora seguradora_pelo_nome = null;

        for(Seguradora seguradora: seguradoras_cadastradas){
            if(seguradora.getNome().equals(nome_seguradora)){
                seguradora_pelo_nome = seguradora;
                break;
            }
        }
        if(seguradora_pelo_nome == null){
            System.out.println("Seguradora não encontrada");
            return;
        }
            
        System.out.print("\nDocumento do cliente: ");
        String documento = scanner1.nextLine();
        
        System.out.print("\nPlaca do veículo: ");
        String placa = scanner1.nextLine();
        
        System.out.print("\nEndereço: ");
        String endereco = scanner1.nextLine();
        
        System.out.print("\nData do Sinistro: ");
        String data = scanner1.nextLine();
        

        seguradora_pelo_nome.gerarSinistro(data, endereco, placa, documento);

    }
    private static void transferirSeguro(){

        System.out.println("Digite o nome da seguradora (sem caracteres especiais, acentos e ç)");

        String nome_seguradora = scanner1.nextLine();
        Seguradora seguradora_pelo_nome = null;

        for(Seguradora seguradora: seguradoras_cadastradas){
            if(seguradora.getNome().equals(nome_seguradora)){
                seguradora_pelo_nome = seguradora;
                break;
            }
        }
        if(seguradora_pelo_nome == null){
            System.out.println("Seguradora não encontrada");
            return;
        }
            
        System.out.print("\nDocumento do transferente: ");
        String transferente = scanner1.nextLine();
        
        System.out.print("\nDocumento do recebedor: ");
        String recebedor = scanner1.nextLine();

        seguradora_pelo_nome.transferirSeguro(transferente, recebedor);

    }
    private static void calcularReceita(){

        System.out.println("Digite o nome da seguradora (sem caracteres especiais, acentos e ç)");

        String nome_seguradora = scanner1.nextLine();
        Seguradora seguradora_pelo_nome = null;

        for(Seguradora seguradora: seguradoras_cadastradas){
            if(seguradora.getNome().equals(nome_seguradora)){
                seguradora_pelo_nome = seguradora;
                break;
            }
        }
        if(seguradora_pelo_nome == null){
            System.out.println("Seguradora não encontrada");
            return;
        }
        System.out.println(seguradora_pelo_nome.calcularReceita());

    }
}
