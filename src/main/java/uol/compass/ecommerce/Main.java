package uol.compass.ecommerce;

import uol.compass.ecommerce.controller.ConfigController;
import uol.compass.ecommerce.controller.DatabaseController;
import uol.compass.ecommerce.model.config.Config_Properties;
import uol.compass.ecommerce.model.config.Locales;
import uol.compass.ecommerce.model.config.Messages;
import uol.compass.ecommerce.view.Menus;


import java.sql.SQLException;
import java.util.*;


public class Main {
    private static ConfigController config = new ConfigController();
    private static Locale locale = new Locale("en");

    public static void main(String[] args) throws InterruptedException {
        loadConfig();
        boolean running = true;
        try {
            startProgram();
        } catch (InputMismatchException ex) {
            System.err.println(getMessage(Messages.INPUT_MISMATCH_ERROR));
            Thread.sleep(100);
        }

    }

    public static void startProgram() {
        Scanner scan = new Scanner(System.in);
        Menus menus = new Menus(scan);
        if (config.createConfig()) {
            menus.showMainMenu();
        } else {
            System.err.println(getMessage(Messages.CONFIGURATION_CREATE_ERROR));
        }
    }

    public static void loadConfig() {
        config = new ConfigController();
    }


    public static String getMessage(Messages message) {
        try {
            locale = new Locale("en");
            if (config.createConfig()) {
                locale = new Locale(config.getProperty(Config_Properties.APP_LOCALE));
            }
            ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", locale);
            return resourceBundle.getString(message.toString());
        } catch (MissingResourceException e) {
            System.err.println("Invalid Locale error.");
            System.exit(0);
        }
        return "";
    }


    public static void changeCurrentLocale(Locales locale) {
        Main.locale = new Locale(locale.toString());
    }

    public static void testConnection() {
        DatabaseController db = new DatabaseController();
        try {
            db.getConnection();
            System.out.println(getMessage(Messages.SQL_CONNECTION_SUCCESS));
        } catch (SQLException e) {
            System.err.println(getMessage(Messages.SQL_ERROR) + "\n" + e.getMessage());
        }
    }

}
