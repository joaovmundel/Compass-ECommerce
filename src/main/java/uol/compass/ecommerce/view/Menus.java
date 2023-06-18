package uol.compass.ecommerce.view;

import uol.compass.ecommerce.Main;
import uol.compass.ecommerce.controller.InputController;
import uol.compass.ecommerce.controller.MenuController;
import uol.compass.ecommerce.model.Cart;
import uol.compass.ecommerce.model.config.Messages;

import java.util.Scanner;

public class Menus {

    private Scanner scan;
    private Integer option;
    private MenuController controller;
    private static final String HEADER = "--------------[ECommerce Manager]---------------";
    private static final String FOOTER = "----------------------------------";
    public Menus(Scanner scan){
        this.scan = scan;
        this.controller = new MenuController(this, scan);
    }

    public void showMainMenu(){
        this.option = -1;
        System.out.println("");
        System.out.println(HEADER);
        System.out.println("1- " + Main.getMessage(Messages.VIEWS_MAIN_MENU_OPTION_1));
        System.out.println("2- " + Main.getMessage(Messages.VIEWS_MAIN_MENU_OPTION_2));
        System.out.println("3- " + Main.getMessage(Messages.VIEWS_MAIN_MENU_OPTION_3));
        System.out.println("0- " + Main.getMessage(Messages.VIEWS_OPTION_EXIT));
        System.out.println(FOOTER);
        System.out.print(Main.getMessage(Messages.REQUEST_USER_INPUT));
        this.option = InputController.requestUserInt(this.scan);
        this.controller.mainMenu(option);
    }

    public void showConfigMenu(){
        this.option = -1;
        System.out.println("");
        System.out.println(HEADER);
        System.out.println("1- " + Main.getMessage(Messages.VIEWS_CONFIG_MENU_OPTION_1));
        System.out.println("2- " + Main.getMessage(Messages.VIEWS_CONFIG_MENU_OPTION_2));
        System.out.println("3- " + Main.getMessage(Messages.VIEWS_CONFIG_MENU_OPTION_3));
        System.out.println("4- " + Main.getMessage(Messages.VIEWS_CONFIG_MENU_OPTION_4));
        System.out.println("5- " + Main.getMessage(Messages.VIEWS_CONFIG_MENU_OPTION_5));
        System.out.println("6- " + Main.getMessage(Messages.VIEWS_CONFIG_MENU_OPTION_6));
        System.out.println("7- " + Main.getMessage(Messages.VIEWS_CONFIG_MENU_OPTION_7));
        System.out.println("8- " + Main.getMessage(Messages.VIEWS_OPTION_BACK));
        System.out.println("0- " + Main.getMessage(Messages.VIEWS_OPTION_EXIT));
        System.out.println(FOOTER);
        System.out.print(Main.getMessage(Messages.REQUEST_USER_INPUT));
        this.option = InputController.requestUserInt(this.scan);
        this.controller.configMenu(option);
    }

    public void showManagerMenu(){
        this.option = -1;
        System.out.println("");
        System.out.println(HEADER);
        System.out.println("1- " + Main.getMessage(Messages.VIEWS_MANAGER_MENU_OPTION_1));
        System.out.println("2- " + Main.getMessage(Messages.VIEWS_MANAGER_MENU_OPTION_2));
        System.out.println("3- " + Main.getMessage(Messages.VIEWS_MANAGER_MENU_OPTION_3));
        System.out.println("4- " + Main.getMessage(Messages.VIEWS_MANAGER_MENU_OPTION_4));
        System.out.println("5- " + Main.getMessage(Messages.VIEWS_OPTION_BACK));
        System.out.println("0- " + Main.getMessage(Messages.VIEWS_OPTION_EXIT));
        System.out.println(FOOTER);
        System.out.print(Main.getMessage(Messages.REQUEST_USER_INPUT));
        this.option = InputController.requestUserInt(this.scan);
        this.controller.managerMenu(this.option);
    }

    public void showClientMenu(){
        this.option = -1;
        System.out.println("");
        System.out.println(HEADER);
        System.out.println("1- " + Main.getMessage(Messages.VIEWS_CLIENT_MENU_OPTION_1));
        System.out.println("2- " + Main.getMessage(Messages.VIEWS_CLIENT_MENU_OPTION_2));
        System.out.println("3- " + Main.getMessage(Messages.VIEWS_CLIENT_MENU_OPTION_3));
        System.out.println("4- " + Main.getMessage(Messages.VIEWS_CLIENT_MENU_OPTION_4));
        System.out.println("5- " + Main.getMessage(Messages.VIEWS_CLIENT_MENU_OPTION_5));
        System.out.println("6- " + Main.getMessage(Messages.VIEWS_CLIENT_MENU_OPTION_6));
        System.out.println("7- " + Main.getMessage(Messages.VIEWS_CLIENT_MENU_OPTION_7));
        System.out.println("8- " + Main.getMessage(Messages.VIEWS_OPTION_BACK));
        System.out.println("0- " + Main.getMessage(Messages.VIEWS_OPTION_EXIT));
        System.out.println(FOOTER);
        System.out.print(Main.getMessage(Messages.REQUEST_USER_INPUT));
        this.option = InputController.requestUserInt(this.scan);
        this.controller.clientMenu(this.option);
    }

    public void showCheckoutConfirmationMenu(){
        this.option = -1;
        System.out.println("");
        System.out.println(HEADER);
        System.out.println(Main.getMessage(Messages.MENU_CLIENT_CHECKOUT_CONFIRM_TITLE));
        System.out.println("1- " + Main.getMessage(Messages.MENU_CLIENT_CHECKOUT_CONFIRM_OPTION_1));
        System.out.println("2- " + Main.getMessage(Messages.MENU_CLIENT_CHECKOUT_CONFIRM_OPTION_2));
        System.out.println(FOOTER);
        System.out.print(Main.getMessage(Messages.REQUEST_USER_INPUT));
        this.option = InputController.requestUserInt(this.scan);
        this.controller.confirmPurchaseMenu(option);
    }

    public void showEditionMenu(Integer productID){
        this.option = -1;
        System.out.println("");
        System.out.println(HEADER);
        System.out.println("1- " + Main.getMessage(Messages.VIEWS_EDITION_MENU_OPTION_1));
        System.out.println("2- " + Main.getMessage(Messages.VIEWS_EDITION_MENU_OPTION_2));
        System.out.println("3- " + Main.getMessage(Messages.VIEWS_EDITION_MENU_OPTION_3));
        System.out.println("4- " + Main.getMessage(Messages.VIEWS_EDITION_MENU_OPTION_4));
        System.out.println("5- " + Main.getMessage(Messages.VIEWS_EDITION_MENU_OPTION_5));
        System.out.println("6- " + Main.getMessage(Messages.VIEWS_OPTION_BACK));
        System.out.println("0- " + Main.getMessage(Messages.VIEWS_OPTION_EXIT));
        System.out.println(FOOTER);
        System.out.print(Main.getMessage(Messages.REQUEST_USER_INPUT));
        this.option = InputController.requestUserInt(this.scan);
        this.controller.editMenu(option, productID);
    }

    public void showLanguageMenu(){
        this.option = -1;
        System.out.println(Main.getMessage(Messages.VIEWS_LANGUAGE_MENU_TITLE));
        System.out.println("1- " + Main.getMessage(Messages.VIEWS_LANGUAGE_MENU_OPTION_1));
        System.out.println("2- " + Main.getMessage(Messages.VIEWS_LANGUAGE_MENU_OPTION_2));
        System.out.print(Main.getMessage(Messages.REQUEST_USER_INPUT));
        this.option = InputController.requestUserInt(this.scan);
        this.controller.languageMenu(option);
    }




}
