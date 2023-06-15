package uol.compass.ecommerce;

import uol.compass.ecommerce.controller.ConfigController;
import uol.compass.ecommerce.controller.DatabaseController;
import uol.compass.ecommerce.view.Menus;


import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    private static ConfigController config = new ConfigController();


    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        Menus menus = new Menus(scan);
        loadConfig();
        try {
            if(config.createConfig()){
                menus.showMainMenu();
            }
        }catch (IOException e){
            System.out.println("Houve um erro ao criar o arquivo de configuracao.");
            e.printStackTrace();
        }catch (InputMismatchException ex){
            System.err.println("A opcao digitada e invalida.");
            Thread.sleep(100);
            main(args);
        }

    }

    public static void loadConfig(){
        config = new ConfigController();
    }

    public static void testConnection(){
        DatabaseController db = new DatabaseController();
        try {
            db.getConnection();
            System.out.println("Conexao realizada com sucesso.");
        } catch (SQLException e) {
            System.err.println("Houve um erro de conexao: " + e.getMessage());
        }
    }

}
