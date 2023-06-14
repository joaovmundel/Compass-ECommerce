package uol.compass.ecommerce.controller;

import uol.compass.ecommerce.model.Product;

import java.sql.*;

public class ProductController {
    private DatabaseController db;
    private static final String NONEXISTENT_PRODUCT = "Esse produto nao esta cadastrado.";

    public ProductController(DatabaseController databaseController){
        this.db = databaseController;
    }

//so povoa a tabela quando ela tiver 0 registros
    public void createDefaultProducts(){
        try(Connection con = db.getConnection(); Statement stmt = con.createStatement()){
            String sqlCount = "select count(*) from products;";
            if(stmt.executeQuery(sqlCount).getInt(1) > 0){
                String sqlSeeder = "insert into products (name, price, quantity) values (Samsung Galaxy S35, 1299.99, 200), (Samsung Galaxy Fold, 955.54, 250), (Xiaomi Redmi Note 10, 1150.00, 100), (Xiaomi Mi 10, 2450.99, 170), (Apple IPhone 14, 5699.98, 100), (Apple IPod Touch, 1780.50, 90), (Apple SmartWatch Series 8, 7699.90, 400);";
                stmt.executeQuery(sqlSeeder);
            }
        } catch (SQLException e) {
            System.out.println("Cheque as configuracoes de banco de dados.");
            e.printStackTrace();
        }
    }
    public boolean hasStock(Integer productID) {
        String sql = "select quantity from products where id = ?";
        try (Connection con = db.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, productID);
            return pstmt.executeQuery().next();
        } catch (SQLException e) {
            System.out.println("Cheque as configuracoes de banco de dados.");
            e.printStackTrace();
        }
        return false;
    }


    public Integer getStock(Integer productID){
        String sql = "select quantity from products where id = ?";
        try(Connection con = db.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setInt(1, productID);
            return pstmt.executeQuery().getInt("quantity");
        }catch (SQLException e){
            System.out.println("Cheque as configuracoes de banco de dados.");
            e.printStackTrace();
        }
        return 0;
    }

    public void setStock(Integer productID, Integer amount){
        if(productExists(productID)){
            String sql = "update products set quantity = ? where id = ?";
            try(Connection con = db.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)){
                pstmt.setInt(1, amount);
                pstmt.setInt(2, productID);
                pstmt.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }else {
            System.out.println(NONEXISTENT_PRODUCT);
        }
    }

    public void addStock(Integer productID, Integer amount){
        setStock(productID, getStock(productID) + amount);
    }

    public void removeStock(Integer productID, Integer amount){
        setStock(productID, getStock(productID) - amount);
    }

    public void postProduct(Product product){
        String sql = "insert into products (name, price, quantity) values (?, ?, ?);";
        try(Connection con = db.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setInt(3, product.getQuantity());
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public Product getProduct(Integer productID){
        String sql = "select * from products where id = ?";
        ResultSet rs = null;
        try(Connection con = db.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setInt(1, productID);
            rs = pstmt.executeQuery();
            return new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getInt("quantity"));
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (!rs.isClosed()) rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return new Product(-1, "", -1.0, -1);
    }

    public void updateProduct(Integer productID, Product newProduct){
        String sql = "update products set name = ?, price = ?, quantity = ? where id = ?;";
        try(Connection con = db.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, newProduct.getName());
            pstmt.setDouble(2, newProduct.getPrice());
            pstmt.setInt(3, newProduct.getQuantity());
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteProduct(Integer productID){
        String sql = "delete from products where id = ?;";
        try(Connection con = db.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setInt(1, productID);
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean productExists(Integer productID){
        String sql = "select id from products where id = ?";
        try(Connection con = db.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setInt(1, productID);
            return pstmt.executeQuery().next();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
