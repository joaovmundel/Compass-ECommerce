package uol.compass.ecommerce.controller;

import uol.compass.ecommerce.Main;
import uol.compass.ecommerce.model.Product;
import uol.compass.ecommerce.view.Menus;


import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeSet;

public class MenuController {
    private static final String EXIT = "Voce escolheu sair...";
    private static final String BACK = "Voltando...";
    private static final String INVALID_OPTION = "Digite apenas opcoes validas.";
    private Menus menus;
    private Scanner scan;
    private ProductController productController;

    public MenuController(Menus menus, Scanner scan) {
        this.menus = menus;
        this.scan = scan;
        this.productController = new ProductController();
    }

    public MenuController(Menus menus, Scanner scan, ProductController productController) {
        this.menus = menus;
        this.scan = scan;
        this.productController = productController;
    }

    public void mainMenu(Integer option) {
        switch (option) {
            case 1:
                initDatabase();
                menus.showManagerMenu();
                break;
            case 2:
                initDatabase();
                menus.showClientMenu();
                break;
            case 3:
                menus.showConfigMenu();
                break;
            case 0:
                exit();
                break;
            default:
                System.out.println(INVALID_OPTION);
                break;
        }
    }

    public void initDatabase() {
        DatabaseController database = new DatabaseController();
        database.setupTables();
        productController.createDefaultProducts();
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
                scan.nextLine();
                config.setPassword(scan.nextLine());
                menus.showConfigMenu();
                break;
            case 6:
                Main.testConnection();
                menus.showConfigMenu();
                break;
            case 7:
                System.out.println(BACK);
                menus.showMainMenu();
                break;
            case 0:
                exit();
                break;
            default:
                System.out.println(INVALID_OPTION);
                break;
        }
    }

    public void managerMenu(Integer option) {
        int prodID;
        String name;
        double price;
        int amount;
        try {
            switch (option) {
                case 1:
                    showProducts();
                    menus.showManagerMenu();
                    break;
                case 2:
                    System.out.print("Digite o nome do produto: ");
                    scan.nextLine();
                    name = scan.nextLine();
                    System.out.print("Digite o valor do produto (Ex: 12,99): ");
                    price = scan.nextDouble();
                    System.out.print("Digite a quantidade do produto: ");
                    amount = scan.nextInt();

                    if (productController.postProduct(new Product(name, price, amount))) {
                        System.out.println("Produto criado com sucesso.");
                    }
                    menus.showManagerMenu();
                    break;
                case 3:
                    System.out.print("Digite o id do produto a ser deletado: ");

                    if (productController.deleteProduct(scan.nextInt())) {
                        System.out.println("Produto deletado com sucesso.");
                    }
                    break;
                case 4:
                    System.out.print("Digite o id do produto para alterar o nome: ");
                    menus.showEditionMenu(scan.nextInt());
                    break;
                case 5:
                    System.out.print("Digite o id do produto para adicionar estoque: ");
                    prodID = scan.nextInt();

                    System.out.print("Digite a quantidade a ser adicionado: ");
                    amount = scan.nextInt();

                    if (productController.addStock(prodID, amount)) {
                        System.out.println("Foram adicionados +" + amount + " ao estoque.");
                    }
                    break;
                case 6:
                    System.out.print("Digite o id do produto para remover estoque: ");
                    prodID = scan.nextInt();

                    System.out.print("Digite a quantidade a ser removida: ");
                    amount = scan.nextInt();

                    if (productController.removeStock(prodID, amount)) {
                        System.out.println("Foram removidos " + amount + " do estoque.");
                    }
                    break;
                case 7:
                    System.out.print("Digite o id do produto para alterar o preco: ");
                    prodID = scan.nextInt();

                    System.out.print("Digite o novo preco (Ex: 12,99): ");
                    price = scan.nextDouble();

                    if (productController.setPrice(prodID, price)) {
                        System.out.println("O preco foi setado para R$ " + price);
                    }
                    break;
                case 8:
                    System.out.println(BACK);
                    menus.showMainMenu();
                    break;
                case 0:
                    exit();
                    break;
                default:
                    System.err.println(INVALID_OPTION);
                    managerMenu(option);
                    break;
            }
        } catch (InputMismatchException e) {
            System.err.println("\nO tipo de dado inserido nao corresponde com o esperado.");
            System.err.println("Aperte [ENTER] para continuar.");
            managerMenu(option);
        }
    }

    public void clientMenu(Integer option) {
        switch (option) {
            default:
                System.out.println(INVALID_OPTION);
                break;
        }
    }

    public void editMenu(Integer option, Integer productdID) {
        Product oldProduct = productController.getProduct(productdID);
        switch (option) {
            case 1:
                System.out.print("Digite o novo nome: ");
                oldProduct.setName(scan.next());
                productController.updateProduct(productdID, oldProduct);
                break;
            case 2:
                System.out.print("Digite o novo preco (Ex: 12,99): ");
                oldProduct.setPrice(scan.nextDouble());
                productController.updateProduct(productdID, oldProduct);
                break;
            case 3:
                System.out.print("Digite a nova quantidade: ");
                oldProduct.setQuantity(scan.nextInt());
                productController.updateProduct(productdID, oldProduct);
                break;
            case 0:
                exit();
                break;
            default:
                System.out.println(INVALID_OPTION);
                break;
        }
    }

    public void confirmPurchaseMenu(Integer option) {
        switch (option) {
            default:
                System.out.println(INVALID_OPTION);
                break;
        }
    }

    public void exit() {
        System.out.println(EXIT);
        System.exit(0);
    }

    public void showProducts() {
        TreeSet<Product> products = productController.getAllProducts();
        int largerName = 0;
        for (Product prod : products) {
            if (prod.getName().length() > largerName) {
                largerName = prod.getName().length();
            }
        }
        String hyphen = placeText(largerName, "-");
        System.out.println("------------" + hyphen + "-----------------------------------");
        System.out.println("|   ID   | Nome" + placeText(largerName - 4, " ") + "|      Preco      |   Quantidade   |");
        for (Product prod : products) {
            System.out.println("|   " + prod.getId() + "   | " + prod.getName() + placeText(largerName - prod.getName().length(), " ") + "| " + prod.getPrice() + placeText(16 - (String.valueOf(prod.getPrice()).length()), " ") + "| " + prod.getQuantity() + placeText(15 - (String.valueOf(prod.getQuantity()).length()), " ") + "|");
        }
        System.out.println("------------" + hyphen + "-----------------------------------");
    }

    public String placeText(Integer size, String textToPlace) {

        return new String(new char[size]).replace("\0", textToPlace);
    }
}
