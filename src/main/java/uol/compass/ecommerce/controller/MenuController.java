package uol.compass.ecommerce.controller;

import uol.compass.ecommerce.view.Menus;


import java.util.Scanner;

public class MenuController {
    private static final String EXIT = "Voce escolheu sair...";
    private static final String BACK = "Voltando...";
    private static final String INVALID_OPTION = "Digite apenas opcoes validas.";
    private Menus menus;
    private Scanner scan;

    public MenuController(Menus menus, Scanner scan) {
        this.menus = menus;
        this.scan = scan;
    }

    public void mainMenu(Integer option) {
        if(option == 1 || option == 2){
            DatabaseController database = new DatabaseController();
            ProductController productController = new ProductController(database);
            database.setupTables();
            productController.createDefaultProducts();
        }
        switch (option) {
            case 1:
                menus.showManagerMenu();
                break;
            case 2:
                menus.showClientMenu();
                break;
            case 3:
                menus.showConfigMenu();
                break;
            case 0:
                System.out.println(EXIT);
                break;
            default:
                System.out.println(INVALID_OPTION);
                break;
        }
    }

    public void configMenu(Integer option) {
        ConfigController config = new ConfigController();
        switch (option) {
            case 1:
                System.out.print("Digite o host: ");
                config.setHost(scan.next());
                menus.showConfigMenu();
                break;
            case 2:
                System.out.print("Digite a porta: ");
                config.setPort(String.valueOf(scan.nextInt()));
                menus.showConfigMenu();
                break;
            case 3:
                System.out.print("Digite a database: ");
                config.setDatabase(scan.next());
                menus.showConfigMenu();
                break;
            case 4:
                System.out.print("Digite o usuario: ");
                config.setUser(scan.next());
                menus.showConfigMenu();
                break;
            case 5:
                System.out.print("Digite a senha: ");
                config.setPassword(scan.next());
                menus.showConfigMenu();
                break;
            case 6:
                System.out.println(BACK);
                menus.showMainMenu();
                break;
            case 0:
                System.out.println(EXIT);
                break;
            default:
                System.out.println(INVALID_OPTION);
                break;
        }
    }

    public void clientMenu(Integer option) {
        switch (option) {
            default:
                System.out.println(INVALID_OPTION);
                break;
        }
    }

    public void managerMenu(Integer option) {
        switch (option) {
            default:
                System.out.println(INVALID_OPTION);
                break;
        }
    }

    public void confirmPurchaseMenu(Integer option){
        switch (option){
            default:
                System.out.println(INVALID_OPTION);
                break;
        }
    }

}
