package uol.compass.ecommerce.controller;


import java.sql.*;


public class DatabaseController {

    private static ConfigController config = new ConfigController();
    private String host = config.getProperty("MYSQL_HOST");
    private String port = config.getProperty("MYSQL_PORT");
    private String user = config.getProperty("MYSQL_USER");
    private String password = config.getProperty("MYSQL_PASSWORD");
    private String database = config.getProperty("MYSQL_DATABASE");

    public Connection getConnection() throws SQLException {
        reloadConfig();
        return DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
    }

    public void setupTables() {
        String sqlProducts = "CREATE TABLE IF NOT EXISTS products(id int not null primary key auto_increment, name varchar(50) not null, price double not null default 0, quantity int not null default 0);";
        try (Connection con = getConnection(); Statement statement = con.createStatement();) {
            statement.executeUpdate(sqlProducts);
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }

    public void reloadConfig(){
        config = new ConfigController();
        host = config.getProperty("MYSQL_HOST");
        port = config.getProperty("MYSQL_PORT");
        user = config.getProperty("MYSQL_USER");
        password = config.getProperty("MYSQL_PASSWORD");
        database = config.getProperty("MYSQL_DATABASE");
    }

}
