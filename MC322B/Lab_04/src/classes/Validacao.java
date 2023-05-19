package classes;

public class Validacao {

    public static boolean validaCPF(String CPF){
        String cpf = CPF.replaceAll("[^0-9]+", "");     //substitui todos os caracteres não numéricos (0-9) por vazio
    
    if(cpf.length()!=11){                                      //verifica se o cpf tem 11 digitos
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
        return false;
    }
    return true;
        
    }
    
    public static boolean validaCNPJ(String cnpj) {                       //função que valida o cnpj do cliente
            
        String CNPJ = cnpj.replaceAll("[^0-9]+", "");     //substitui todos os caracteres não numéricos (0-9) por vazio
        
        if(CNPJ.length()!=14){                                      //verifica se o cnpj tem 14 digitos
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
            return false;
        }
        return true;
        
    }

    public static boolean validaNome(String nome){
        String name = nome.replaceAll(" ", "");
        if(!name.matches("^[a-zA-Z]*$")){
            return false;
        }
        return true;
    }
    
    
}
