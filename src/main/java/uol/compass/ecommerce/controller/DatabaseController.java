package uol.compass.ecommerce.controller;

import java.sql.*;


public class DatabaseController {

    private ConfigController config = new ConfigController();
    private String host = config.getProperty("MYSQL_HOST");
    private String port = config.getProperty("MYSQL_PORT");
    private String user = config.getProperty("MYSQL_USER");
    private String password = config.getProperty("MYSQL_PASSWORD");
    private String database = config.getProperty("MYSQL_DATABASE");

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
    }

    public void setupTables() {
        String sqlProducts = "CREATE TABLE IF NOT EXISTS products(id int not null primary key auto_increment, name varchar(50) not null, price double not null default 0, quantity int not null default 0);";
        try (Connection con = getConnection(); Statement statement = con.createStatement();) {
            statement.execute(sqlProducts);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean hasStock(Integer productID) {
        String sql = "select quantity from products where id = ?";
        try (Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, productID);
            return pstmt.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public Integer getStock(Integer productID){
        String sql = "select quantity from products where id = ?";
        try(Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setInt(1, productID);
            return pstmt.executeQuery().getInt("quantity");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }


}
