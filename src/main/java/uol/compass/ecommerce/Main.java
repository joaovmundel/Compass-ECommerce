package uol.compass.ecommerce;

import uol.compass.ecommerce.controller.DatabaseController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        DatabaseController database = new DatabaseController();
        try(Connection con = database.getConnection()){
            System.out.println("Conectado!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void init(DatabaseController databaseController){
        try {
            databaseController.createConfig();
            databaseController.setupTables();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
