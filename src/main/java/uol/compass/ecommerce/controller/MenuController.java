package uol.compass.ecommerce.controller;

import uol.compass.ecommerce.Main;
import uol.compass.ecommerce.model.Cart;
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
    private Cart cart = new Cart();

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
                menus.showMainMenu();
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
                config.setHost(InputController.requestUserString(this.scan));
                menus.showConfigMenu();
                break;
            case 2:
                System.out.print(Main.getMessage(Messages.CONFIG_MENU_REQUEST_PORT));
                config.setPort(String.valueOf(InputController.requestUserInt(this.scan)));
                menus.showConfigMenu();
                break;
            case 3:
                System.out.print(Main.getMessage(Messages.CONFIG_MENU_REQUEST_DATABASE));
                config.setDatabase(InputController.requestUserString(this.scan));
                menus.showConfigMenu();
                break;
            case 4:
                System.out.print(Main.getMessage(Messages.CONFIG_MENU_REQUEST_USER));
                config.setUser(InputController.requestUserString(this.scan));
                menus.showConfigMenu();
                break;
            case 5:
                System.out.print(Main.getMessage(Messages.CONFIG_MENU_REQUEST_PASSWORD));
                config.setPassword(InputController.requestUserString(this.scan));
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
                    name = InputController.requestUserLongString(this.scan);
                    System.out.print(Main.getMessage(Messages.MANAGER_MENU_REQUEST_PRODUCT_CREATE_PRICE));
                    price = InputController.requestUserDouble(this.scan);
                    System.out.print(Main.getMessage(Messages.MANAGER_MENU_REQUEST_PRODUCT_CREATE_AMOUNT));
                    amount = InputController.requestUserInt(this.scan);

                    if (productController.postProduct(new Product(name, price, amount))) {
                        System.out.println(Main.getMessage(Messages.MANAGER_MENU_PRODUCT_CREATED));
                    }
                    menus.showManagerMenu();
                    break;
                case 3:
                    System.out.print(Main.getMessage(Messages.MANAGER_MENU_REQUEST_PRODUCT_DELETE_ID));

                    if (productController.deleteProduct(InputController.requestUserInt(this.scan))) {
                        System.out.println(Main.getMessage(Messages.MANAGER_MENU_PRODUCT_DELETED));
                    } else {
                        System.err.println(Main.getMessage(Messages.NONEXISTENT_PRODUCT_ERROR));
                    }
                    menus.showManagerMenu();
                    break;
                case 4:
                    System.out.print(Main.getMessage(Messages.MANAGER_MENU_REQUEST_PRODUCT_EDIT_ID));
                    int prodID = InputController.requestUserInt(this.scan);
                    if (productController.productExists(prodID)) {
                        menus.showEditionMenu(prodID);
                    } else {
                        System.err.println(Main.getMessage(Messages.NONEXISTENT_PRODUCT_ERROR));
                        menus.showManagerMenu();
                    }
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
                    menus.showManagerMenu();
                    break;
            }
        } catch (InputMismatchException e) {
            System.err.println("\n" + Main.getMessage(Messages.INPUT_MISMATCH_ERROR));
            menus.showManagerMenu();
        }
    }

    public void clientMenu(Integer option) {
        CartController cartController = new CartController();
        int id = -1;
        int amount = -1;
        switch (option) {
            case 1:
                showProducts();
                menus.showClientMenu();
                break;
            case 2:
                System.out.print(Main.getMessage(Messages.MENU_CLIENT_REQUEST_PRODUCT_ID_TO_ADD));
                id = InputController.requestUserInt(scan);
                System.out.print(Main.getMessage(Messages.MENU_CLIENT_REQUEST_AMOUNT));
                amount = InputController.requestUserInt(scan);
                cartController.addProduct(id, amount, cart);
                menus.showClientMenu();
                break;
            case 3:
                System.out.print(Main.getMessage(Messages.MENU_CLIENT_REQUEST_PRODUCT_ID_TO_REMOVE));
                id = InputController.requestUserInt(scan);
                System.out.print(Main.getMessage(Messages.MENU_CLIENT_REQUEST_AMOUNT));
                amount = InputController.requestUserInt(scan);
                cartController.removeProduct(id, amount, cart);
                menus.showClientMenu();
                break;
            case 4:
                System.out.print(Main.getMessage(Messages.MANAGER_MENU_REQUEST_PRODUCT_EDIT_ID));
                id = InputController.requestUserInt(scan);
                System.out.print(Main.getMessage(Messages.MENU_CLIENT_REQUEST_AMOUNT));
                amount = InputController.requestUserInt(scan);
                cartController.setProductAmount(id, amount, cart);
                menus.showClientMenu();
                break;
            case 5:
                cartController.showProducts(cart);
                menus.showClientMenu();
                break;
            case 6:
                System.out.println(Main.getMessage(Messages.MENU_CLIENT_CLEAR_CART_TITLE));
                System.out.println("1- " + Main.getMessage(Messages.MENU_CLIENT_CLEAR_CART_OPTION_1));
                System.out.println("2- " + Main.getMessage(Messages.MENU_CLIENT_CLEAR_CART_OPTION_2));
                System.out.print(Main.getMessage(Messages.REQUEST_USER_INPUT));
                int clear = InputController.requestUserInt(scan);
                switch (clear) {
                    case 1:
                        cart.getProducts().clear();
                        System.out.println(Main.getMessage(Messages.MENU_CLIENT_CLEAR_CART_SUCCESS));
                        break;
                    case 2:
                        menus.showClientMenu();
                        break;
                    default:
                        System.out.println(Main.getMessage(Messages.INVALID_OPTION));
                        menus.showClientMenu();
                        break;
                }
                menus.showClientMenu();
                break;
            case 7:
                double total = 0;
                for(Integer cartProdID : cart.getProducts().keySet()){
                    Product prod = productController.getProduct(cartProdID);
                    total+=prod.getPrice() * cart.getProducts().get(cartProdID);
                }
                cart.setTotal(total);
                menus.showCheckoutConfirmationMenu();
                break;
            case 8:
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

    public void editMenu(Integer option, Integer prodID) {
        Product oldProduct = productController.getProduct(prodID);
        int amount = 0;
        switch (option) {
            case 1:
                System.out.print(Main.getMessage(Messages.EDITION_MENU_REQUEST_PRODUCT_NEW_NAME));
                oldProduct.setName(InputController.requestUserLongString(this.scan));
                if (productController.updateProduct(prodID, oldProduct)) {
                    System.out.println(Main.getMessage(Messages.EDITION_MENU_PRODUCT_NAME_CHANGED));
                    menus.showEditionMenu(prodID);
                }
                break;
            case 2:
                System.out.print(Main.getMessage(Messages.EDITION_MENU_REQUEST_PRODUCT_NEW_PRICE));
                oldProduct.setPrice(InputController.requestUserDouble(this.scan));
                if (productController.updateProduct(prodID, oldProduct)) {
                    System.out.println(Main.getMessage(Messages.EDITION_MENU_PRODUCT_PRICE_CHANGED));
                    menus.showEditionMenu(prodID);
                }
                break;
            case 3:
                System.out.print(Main.getMessage(Messages.EDITION_MENU_REQUEST_PRODUCT_NEW_STOCK));
                oldProduct.setQuantity(InputController.requestUserInt(this.scan));
                if (productController.updateProduct(prodID, oldProduct)) {
                    System.out.println(Main.getMessage(Messages.EDITION_MENU_PRODUCT_STOCK_CHANGED));
                    menus.showEditionMenu(prodID);
                }
                break;
            case 4:
                System.out.print(Main.getMessage(Messages.EDITION_MENU_REQUEST_PRODUCT_ADD_STOCK_AMOUNT));
                amount = InputController.requestUserInt(this.scan);
                if (productController.addStock(prodID, amount)) {
                    System.out.println(Main.getMessage(Messages.EDITION_MENU_PRODUCT_STOCK_ADDED));
                    menus.showEditionMenu(prodID);
                }
                break;
            case 5:
                System.out.print(Main.getMessage(Messages.EDITION_MENU_REQUEST_PRODUCT_REMOVE_STOCK_AMOUNT));
                amount = InputController.requestUserInt(this.scan);
                if (productController.removeStock(prodID, amount)) {
                    System.out.println(Main.getMessage(Messages.EDITION_MENU_PRODUCT_STOCK_REMOVED));
                } else {
                    System.err.println(Main.getMessage(Messages.INVALID_PRODUCT_FIELDS));
                }
                menus.showEditionMenu(prodID);
                break;
            case 6:
                System.out.println(Main.getMessage(Messages.BACK));
                menus.showManagerMenu();
                break;
            case 0:
                exit();
                break;
            default:
                System.out.println(INVALID_OPTION);
                break;
        }
    }

    public void languageMenu(Integer option) {
        ConfigController config = new ConfigController();
        if (option == 1) {
            Main.changeCurrentLocale(Locales.en);
            config.setLocale(Locales.en);
        } else {
            Main.changeCurrentLocale(Locales.br);
            config.setLocale(Locales.br);
        }
        System.out.println(Main.getMessage(Messages.LANGUAGE_MENU_LANGUAGE_SELECTED));
        menus.showConfigMenu();
    }

    public void confirmPurchaseMenu(Integer option) {
        CartController cartController = new CartController();
        switch (option) {
            case 1:
                cartController.checkout(cart);
                System.out.println(Main.getMessage(Messages.TOTAL_PAID) + cart.getTotal());
                menus.showClientMenu();
                break;
            case 2:
                menus.showClientMenu();
                break;
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
