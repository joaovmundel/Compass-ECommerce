package uol.compass.ecommerce;

import uol.compass.ecommerce.controller.ConfigController;
import uol.compass.ecommerce.controller.DatabaseController;
import uol.compass.ecommerce.view.Menus;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        ConfigController config = new ConfigController();
        try {
            if(config.createConfig()){
                Scanner scan = new Scanner(System.in);
                new Menus(scan).showMainMenu();
            }
        }catch (IOException e){
            System.out.println("Houve um erro ao criar o arquivo de configuracao.");
            e.printStackTrace();
        }

    }



    public static void testConnection(){
        DatabaseController db = new DatabaseController();
        try {
            db.getConnection();
            System.out.println("Conexao realizada com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
