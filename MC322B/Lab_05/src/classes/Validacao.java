package classes;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.text.SimpleDateFormat;


public class Validacao {

    protected static SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy"); // será utilizado pelas classses filhas

    public static boolean validaCPF(String CPF){
        String cpf = CPF.replaceAll("[^0-9]+", "");     //substitui todos os caracteres não numéricos (0-9) por vazio
    
    if(cpf.length()!=11){                                      //verifica se o cpf tem 11 digitos
        System.out.println("O número de digitos do CPF está incorreto!");
        return false;
    }

    // verifica se todos os dígitos são iguais

    int iguais = 0;
    for (int i =1; i<11; i++){
        if(cpf.charAt(i)==cpf.charAt(0)){               //conta quantos dígitos são iguais ao primeiro
            iguais++;
        }
    }
    if (iguais == 10){                                        // se houverem 10 dígitos iguais ao primeiro, todos os dígitos são iguais e o cpf é invalido
        System.out.println("Os digitos do CPF não podem ser todos iguais!");
        return false;
    }
    
    //cálculo do primeiro dígito verificador
    
    int soma = 0; int peso = 10; int digito; int resultado; int verificador;
    
    for (int i=0; i<9; i++) {
            digito = (int)(cpf.charAt(i) - 48);
            soma = soma + (digito * peso);
            peso = peso - 1;
    }
    verificador = ((soma*10) % 11);
    if ((verificador == 10)){
        verificador = 0;
    }

    //verificação do primeiro dígito

    if (verificador != (int)(cpf.charAt(9) - 48)){
        System.out.println("O primeiro digito verificador está incorreto!");
        return false;
    }

    //cálculo do segundo dígito

    peso = 11;
    soma = 0;
    
    for (int i=0; i<10; i++) {
            digito = (int)(cpf.charAt(i) - 48);
            soma = soma + (digito * peso);
            peso = peso - 1;
    }
    resultado = ((soma*10) % 11);
    if ((resultado == 10)){
        verificador = 0;
    }
    else{
        verificador = resultado;
    }

    //verifica o segundo dígito

    if (verificador != (int)(cpf.charAt(10) - 48)){
        System.out.println("O segundo digito verificador está incorreto!");
        return false;
    }
    return true;
        
    }
    
    public static boolean validaCNPJ(String cnpj) {                       //função que valida o cnpj do cliente
            
        String CNPJ = cnpj.replaceAll("[^0-9]+", "");     //substitui todos os caracteres não numéricos (0-9) por vazio
        
        if(CNPJ.length()!=14){                                      //verifica se o cnpj tem 14 digitos
            System.out.println("O CNPJ deve conter 14 dígitos!");
            return false;
        }

        // verifica se todos os dígitos são iguais

        int iguais = 0;
        for (int i =1; i<14; i++){
            if(CNPJ.charAt(i)==CNPJ.charAt(0)){               //conta quantos dígitos são iguais ao primeiro
                iguais++;
            }
        }
        if (iguais == 13){                                        // se houverem 13 dígitos iguais ao primeiro, todos os dígitos são iguais e o cpf é invalido
            System.out.println("Os digitos do CNPJ não podem ser todos iguais!");
            return false;
        }
        
        //cálculo do primeiro dígito verificador
        
        int soma = 0; int peso = 2; int digito; int resultado; int verificador;
        
        for (int i=11; i>=0; i--) {
                digito = (int)(CNPJ.charAt(i) - 48);
                soma = soma + (digito * peso);
                peso = peso + 1;
                if(peso ==10){
                    peso =2;
                }
        }
        verificador = ((soma*10) % 11);
        if ((verificador == 10)){
            verificador = 0;
        }

        //verificação do primeiro dígito

        if (verificador != (int)(CNPJ.charAt(12) - 48)){
            System.out.println("O primeiro digito verificador está incorreto!");
            return false;
        }

        //cálculo do segundo dígito

        peso = 2;
        soma = 0;
        
        for (int i=12; i>=0; i--) {
                digito = (int)(CNPJ.charAt(i) - 48);
                soma = soma + (digito * peso);
                peso = peso + 1;
                if(peso ==10){
                    peso =2;
                }
        }
        resultado = ((soma*10) % 11);
        if ((resultado == 10)){
            verificador = 0;
        }
        else{
            verificador = resultado;
        }

        //verifica o segundo dígito

        if (verificador != (int)(CNPJ.charAt(13) - 48)){
            System.out.println("O segundo digito verificador está incorreto!");
            return false;
        }
        return true;
        
    }

    public static boolean validaNome(String nome){
        String name = nome.replaceAll(" ", "");
        if(!name.matches("^[a-zA-Z]*$")){
            System.out.println("Nome Inválido: não deve conter caracteres especiais");
            return false;
        }
        return true;
    }
    
    public static int idade(Date dataNascimento){
        //obtem e formata a data do dia de hoje
        LocalDateTime hoje = LocalDateTime.now();
        String dataHoje = hoje.toString();
        dataHoje = dataHoje.substring(8, 10) + "/" + dataHoje.substring(5, 7) + "/" + dataHoje.substring(0,4);
        try{
            Date dataFormatada= formataData.parse(dataHoje);
            //obtem a diferença em milisegundos de uma data até outra
            long diferencaEmMilisegundos = dataFormatada.getTime() - dataNascimento.getTime();
            /*esse if foi necessário para poder considerar as datas em anos bissextos
              ao tentar apenas dividir por 365.25 (1 dia a cada ano bissexto), caso a data de nascimento tivesse
              o mesmo dia do mês da data atual, o retorno era como se a pessoa não tivesse completado mais um ano
              Exemplo: o retorno para a data de nascimendo 03/05/1980 em um teste realizado no dia 03/05/2023
              foi 82 anos, e não 83 anos como era de se esperar. o if abaixo trata esse caso especial 
            */
            if(dataNascimento.getDate()==dataFormatada.getDate()){ 
                return (int)(diferencaEmMilisegundos / 1000 / 60 / 60 / 24/ 365);
            }
            return (int)(diferencaEmMilisegundos / 1000 / 60 / 60 / 24/ 365.25);
        } catch (ParseException ex) {
            return 0; // necessário para compilar, é o caso em que por algum motivo não é possível formatar a data
        }
        
    }

    public static Date formatacaoData(String data){
        try{
            return formataData.parse(data);
        } catch (ParseException ex) { 
            //Caso ocorra um erro na formatação da data de nascimento
            return null;
        }
    }
    
    public static String formatacaoData(Date data){
        return formataData.format(data);  
    }
}
