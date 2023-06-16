package uol.compass.ecommerce.controller;

import uol.compass.ecommerce.Main;
import uol.compass.ecommerce.model.Product;
import uol.compass.ecommerce.model.config.Locales;
import uol.compass.ecommerce.model.config.Messages;
import uol.compass.ecommerce.view.Menus;


import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeSet;

public class MenuController {
    private static final String EXIT = Main.getMessage(Messages.EXIT);
    private static final String BACK = Main.getMessage(Messages.BACK);
    private static final String INVALID_OPTION = Main.getMessage(Messages.INVALID_OPTION);
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
                System.out.print(Main.getMessage(Messages.CONFIG_MENU_REQUEST_HOST));
                config.setHost(scan.next());
                menus.showConfigMenu();
                break;
            case 2:
                System.out.print(Main.getMessage(Messages.CONFIG_MENU_REQUEST_PORT));
                config.setPort(String.valueOf(scan.nextInt()));
                menus.showConfigMenu();
                break;
            case 3:
                System.out.print(Main.getMessage(Messages.CONFIG_MENU_REQUEST_DATABASE));
                config.setDatabase(scan.next());
                menus.showConfigMenu();
                break;
            case 4:
                System.out.print(Main.getMessage(Messages.CONFIG_MENU_REQUEST_USER));
                config.setUser(scan.next());
                menus.showConfigMenu();
                break;
            case 5:
                System.out.print(Main.getMessage(Messages.CONFIG_MENU_REQUEST_PASSWORD));
                scan.nextLine();
                config.setPassword(scan.nextLine());
                menus.showConfigMenu();
                break;
            case 6:
                Main.testConnection();
                menus.showConfigMenu();
                break;
            case 7:
                menus.showLanguageMenu();
                break;
            case 8:
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
                    System.out.print(Main.getMessage(Messages.MANAGER_MENU_REQUEST_PRODUCT_CREATE_NAME));
                    scan.nextLine();
                    name = scan.nextLine();
                    System.out.print(Main.getMessage(Messages.MANAGER_MENU_REQUEST_PRODUCT_CREATE_PRICE));
                    price = scan.nextDouble();
                    System.out.print(Main.getMessage(Messages.MANAGER_MENU_REQUEST_PRODUCT_CREATE_AMOUNT));
                    amount = scan.nextInt();

                    if (productController.postProduct(new Product(name, price, amount))) {
                        System.out.println(Main.getMessage(Messages.MANAGER_MENU_PRODUCT_CREATED));
                    }
                    menus.showManagerMenu();
                    break;
                case 3:
                    System.out.print(Main.getMessage(Messages.MANAGER_MENU_REQUEST_PRODUCT_DELETE_ID));

                    if (productController.deleteProduct(scan.nextInt())) {
                        System.out.println(Main.getMessage(Messages.MANAGER_MENU_PRODUCT_DELETED));
                    }
                    break;
                case 4:
                    System.out.print(Main.getMessage(Messages.MANAGER_MENU_REQUEST_PRODUCT_EDIT_ID));
                    menus.showEditionMenu(scan.nextInt());
                    break;
                case 5:
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
            System.err.println(Main.getMessage(Messages.INPUT_MISMATCH_ERROR));
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

    public void editMenu(Integer option, Integer prodID) {
        Product oldProduct = productController.getProduct(prodID);
        int amount = 0;
        switch (option) {
            case 1:
                System.out.print(Main.getMessage(Messages.EDITION_MENU_REQUEST_PRODUCT_NEW_NAME));
                oldProduct.setName(scan.next());
                if (productController.updateProduct(prodID, oldProduct)) {
                    System.out.println(Main.getMessage(Messages.EDITION_MENU_PRODUCT_NAME_CHANGED));
                }
                break;
            case 2:
                System.out.print(Main.getMessage(Messages.EDITION_MENU_REQUEST_PRODUCT_NEW_PRICE));
                oldProduct.setPrice(scan.nextDouble());
                if (productController.updateProduct(prodID, oldProduct)) {
                    System.out.println(Main.getMessage(Messages.EDITION_MENU_PRODUCT_PRICE_CHANGED));
                }
                break;
            case 3:
                System.out.print(Main.getMessage(Messages.EDITION_MENU_REQUEST_PRODUCT_NEW_STOCK));
                oldProduct.setQuantity(scan.nextInt());
                if (productController.updateProduct(prodID, oldProduct)) {
                    System.out.println(Main.getMessage(Messages.EDITION_MENU_PRODUCT_STOCK_CHANGED));
                }
                break;
            case 4:
                System.out.print(Main.getMessage(Messages.EDITION_MENU_REQUEST_PRODUCT_ADD_STOCK_AMOUNT));
                amount = scan.nextInt();
                if (productController.addStock(prodID, amount)) {
                    System.out.println(Main.getMessage(Messages.EDITION_MENU_PRODUCT_STOCK_ADDED));
                }
                break;
            case 5:
                System.out.print(Main.getMessage(Messages.EDITION_MENU_REQUEST_PRODUCT_REMOVE_STOCK_AMOUNT));
                amount = scan.nextInt();
                if (productController.removeStock(prodID, amount)) {
                    System.out.println(Main.getMessage(Messages.EDITION_MENU_PRODUCT_STOCK_REMOVED));
                }
                break;
            case 0:
                exit();
                break;
            default:
                System.out.println(INVALID_OPTION);
                break;
        }
    }

    public void languageMenu(Integer option){
        ConfigController config = new ConfigController();
        if(option == 1){
            Main.changeCurrentLocale(Locales.en);
            config.setLocale(Locales.en);
            System.out.println(Main.getMessage(Messages.LANGUAGE_MENU_LANGUAGE_SELECTED));
            menus.showConfigMenu();
        }else{
            Main.changeCurrentLocale(Locales.br);
            config.setLocale(Locales.br);
            System.out.println(Main.getMessage(Messages.LANGUAGE_MENU_LANGUAGE_SELECTED));
            menus.showConfigMenu();
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
        System.out.println("|   ID   | " + Main.getMessage(Messages.SHOW_PRODUCTS_NAME) + placeText(largerName - 4, " ") + "|      " + Main.getMessage(Messages.SHOW_PRODUCTS_PRICE) + "      |   " + Main.getMessage(Messages.SHOW_PRODUCTS_QUANTITY) + "   |");
        for (Product prod : products) {
            System.out.println("|   " + prod.getId() + "   | " + prod.getName() + placeText(largerName - prod.getName().length(), " ") + "| " + prod.getPrice() + placeText(16 - (String.valueOf(prod.getPrice()).length()), " ") + "| " + prod.getQuantity() + placeText(15 - (String.valueOf(prod.getQuantity()).length()), " ") + "|");
        }
        System.out.println("------------" + hyphen + "-----------------------------------");
    }

    public String placeText(Integer size, String textToPlace) {

        return new String(new char[size]).replace("\0", textToPlace);
    }
}
