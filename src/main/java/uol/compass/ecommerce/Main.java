package uol.compass.ecommerce;

import uol.compass.ecommerce.controller.ConfigController;
import uol.compass.ecommerce.controller.DatabaseController;
import uol.compass.ecommerce.model.AccountType;


import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        setupConfig();
        DatabaseController database = new DatabaseController();
        database.setupTables();
        Scanner scan = new Scanner(System.in);
        int option = 0;
        boolean auth = false;
        AccountType accountType = null;
        do{
            if(!auth){
                System.out.println("--------------[Escolha o metodo de entrada]---------------");
                System.out.println("1- Gerente");
                System.out.println("2- Cliente");
                System.out.println("6- Sair");
                System.out.println("--------------------------------------------");
                System.out.print("Digite a opcao: ");
                option = scan.nextInt();
                switch (option){
                    case 1:
                        accountType = AccountType.MANAGER;
                        auth = true;
                        option = 0;
                        break;
                    case 2:
                        accountType = AccountType.CLIENT;
                        auth = true;
                        option = 0;
                        break;
                    case 6:
                        System.out.println("Voce escolheu sair....");
                        break;
                    default:
                        System.out.println("Escolha uma opcao valida.");
                        option = 0;
                        break;
                }
            }else{
                if(accountType == AccountType.CLIENT){
                    System.out.println("--------------[Menu Principal ECommerce Manager]---------------");
                    System.out.println("1- Listar produtos");
                    System.out.println("2- Deletar produto");
                    System.out.println("3- Adicionar estoque");
                    System.out.println("4- Remover estoque");
                    System.out.println("");
                    System.out.println("6- Sair");
                    System.out.println("----------------------------------");
                    System.out.print("Digite a opcao: ");
                    option = scan.nextInt();
                    switch (option){
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 6:
                            System.out.println("Voce escolheu sair...");
                            break;
                        default:
                            System.out.println("Escolha uma opcao valida.");
                            break;
                    }
                } else if (accountType == AccountType.MANAGER) {
                    System.out.println("--------------[Menu Principal ECommerce Manager]---------------");
                    System.out.println("1- Listar Produtos");
                    System.out.println("2- Cadastrar Produto");
                    System.out.println("3- Deletar Produto");
                    System.out.println("4- Adicionar Estoque");
                    System.out.println("5- Remover Estoque");
                    System.out.println("6- Sair");
                    System.out.println("----------------------------------");
                    System.out.print("Digite a opcao: ");
                    option = scan.nextInt();
                }
            }
        }while(option != 6);


    }

    public static void setupConfig() {
        try {
            ConfigController config = new ConfigController();
            config.createConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
