package uol.compass.ecommerce.controller;


import uol.compass.ecommerce.Main;
import uol.compass.ecommerce.model.config.Config_Properties;
import uol.compass.ecommerce.model.config.Messages;

import java.sql.*;


public class DatabaseController {

    private ConfigController config = new ConfigController();
    private String host = config.getProperty(Config_Properties.MYSQL_HOST);
    private String port = config.getProperty(Config_Properties.MYSQL_PORT);
    private String user = config.getProperty(Config_Properties.MYSQL_USER);
    private String password = config.getProperty(Config_Properties.MYSQL_PASSWORD);
    private String database = config.getProperty(Config_Properties.MYSQL_DATABASE);

    public Connection getConnection() throws SQLException {
        reloadConfig();
        return DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
    }

    public void createDatabase(){
        String sql = "CREATE DATABASE IF NOT EXISTS ecommerce;";
        try(Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port, user, password);Statement stmt = con.createStatement()){
            stmt.executeUpdate(sql);
        }catch (SQLException e){
            System.err.println(Main.getMessage(Messages.DATABASE_CREATE_ERROR) + "\n" + e.getMessage());
        }


    }
    public void setupTables() {
        String sqlProducts = "CREATE TABLE IF NOT EXISTS products(id int not null primary key auto_increment, name varchar(50) not null, price double not null default 0, quantity int not null default 0);";
        try (Connection con = getConnection(); Statement statement = con.createStatement();) {
            statement.executeUpdate(sqlProducts);
        } catch (SQLException e) {
            System.err.println(Main.getMessage(Messages.CREATE_TABLE_ERROR)+ "\n" + e.getMessage());
        }
    }

    public void reloadConfig(){
        config = new ConfigController();
        host = config.getProperty(Config_Properties.MYSQL_HOST);
        port = config.getProperty(Config_Properties.MYSQL_PORT);
        user = config.getProperty(Config_Properties.MYSQL_USER);
        password = config.getProperty(Config_Properties.MYSQL_PASSWORD);
        database = config.getProperty(Config_Properties.MYSQL_DATABASE);
    }

}
