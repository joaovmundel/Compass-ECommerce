package uol.compass.ecommerce.controller;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseController {

    private static final String CONFIG_NAME = "config.properties";
    private String host = getProperty("MYSQL_HOST");
    private String user = getProperty("MYSQL_USER");
    private String password = getProperty("MYSQL_PASSWORD");
    private String database = getProperty("MYSQL_DATABASE");

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://" + host + "/" + database, user, password);
    }

    public void setupTables() {
        String sqlProducts = "CREATE TABLE IF NOT EXISTS products(id int not null primary key, name varchar(50) not null, price double not null default 0, quantity int not null default 0);";
        try (Connection con = getConnection(); Statement statement = con.createStatement();) {
            statement.execute(sqlProducts);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createConfig() throws IOException {
        File appConfig = new File(CONFIG_NAME);
        if (!appConfig.exists()) {
            appConfig.createNewFile();
            try (Writer inputStream = new FileWriter(appConfig); FileInputStream propsInput = new FileInputStream(CONFIG_NAME)) {

                Properties prop = new Properties();

                prop.load(propsInput);

                // Setting the properties.
                prop.setProperty("MYSQL_HOST", "127.0.0.1:3306");
                prop.setProperty("MYSQL_USER", "root");
                prop.setProperty("MYSQL_PASSWORD", "fatec123*");
                prop.setProperty("MYSQL_DATABASE", "ecommerce");

                // Storing the properties in the file with a heading comment.
                prop.store(inputStream, "MYSQL CONFIGURATION");


            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public String getProperty(String key) {
        Properties prop = new Properties();
        try (FileInputStream propsInput = new FileInputStream(CONFIG_NAME)) {
            prop.load(propsInput);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop.getProperty(key);
    }
}
