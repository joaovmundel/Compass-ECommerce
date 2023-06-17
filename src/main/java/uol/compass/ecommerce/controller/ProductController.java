package uol.compass.ecommerce.controller;

import uol.compass.ecommerce.Main;
import uol.compass.ecommerce.model.Product;
import uol.compass.ecommerce.model.config.Messages;

import java.sql.*;
import java.util.TreeSet;

public class ProductController {
    private DatabaseController db;
    private static final String NONEXISTENT_PRODUCT = Main.getMessage(Messages.NONEXISTENT_PRODUCT_ERROR);

    public ProductController(DatabaseController databaseController) {
        this.db = databaseController;
    }

    public ProductController() {
        this.db = new DatabaseController();
    }

    //so povoa a tabela quando ela tiver 0 registros
    public void createDefaultProducts() {
        try (Connection con = db.getConnection(); Statement stmt = con.createStatement()) {
            if (isTableEmpty()) {
                String sqlSeeder = "insert into products (name, price, quantity) values ('Samsung Galaxy S35', 1299.99, 200), ('Samsung Galaxy Fold', 955.54, 250), ('Xiaomi Redmi Note 10', 1150.00, 100), ('Xiaomi Mi 10', 2450.99, 170), ('Apple IPhone 14', 5699.98, 100), ('Apple IPod Touch', 1780.50, 90), ('Apple SmartWatch Series 8', 7699.90, 400);";
                stmt.executeUpdate(sqlSeeder);
            }
        } catch (SQLException e) {
            System.err.println(Main.getMessage(Messages.CREATE_DEFAULT_PRODUCTS_ERROR) + "\n" + e.getMessage());
        }
    }

    public boolean hasStock(Integer productID) {
        String sql = "select quantity from products where id = ?";
        try (Connection con = db.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, productID);
            return pstmt.executeQuery().next();
        } catch (SQLException e) {
            System.err.println(Main.getMessage(Messages.SQL_ERROR) + "\n" + e.getMessage());
        }
        return false;
    }


    public Integer getStock(Integer productID) {
        ResultSet rs = null;
        int quantity = 0;
        String sql = "select quantity from products where id = ?";
        try (Connection con = db.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, productID);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                quantity = rs.getInt("quantity");
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            }
        } catch (SQLException e) {

            System.err.println(Main.getMessage(Messages.SQL_ERROR) + "\n" + e.getMessage());
        }
        return quantity;
    }

    public String getProductName(Integer productID) {
        String sql = "select name from products where id = ?";
        ResultSet rs = null;
        String name = "";
        try (Connection con = db.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, productID);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                name = rs.getString("name");
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            }
        } catch (SQLException e) {
            System.err.println(Main.getMessage(Messages.SQL_ERROR) + "\n" + e.getMessage());
        }

        return name;
    }


    public boolean setStock(Integer productID, Integer amount) {
        if (productExists(productID)) {
            String sql = "update products set quantity = ? where id = ?";
            try (Connection con = db.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setInt(1, amount);
                pstmt.setInt(2, productID);
                return pstmt.executeUpdate() > 0 ? true : false;
            } catch (SQLException e) {
                System.err.println(Main.getMessage(Messages.SQL_ERROR) + "\n" + e.getMessage());
            }
        } else {
            System.out.println(NONEXISTENT_PRODUCT);
        }
        return false;
    }

    public boolean addStock(Integer productID, Integer amount) {
        int quantityToAdd = getStock(productID) + amount;
        if (quantityToAdd >= 0) {
            return setStock(productID, quantityToAdd);
        }
        return false;
    }

    public boolean removeStock(Integer productID, Integer amount) {
        int currentQuantity = getStock(productID);
        int quantityAfterRemove = currentQuantity - amount;
        if (quantityAfterRemove >= 0) {
            return setStock(productID, quantityAfterRemove);
        }
        return false;
    }

    public boolean setPrice(Integer productID, Double newPrice) {
        if (productExists(productID)) {
            String sql = "update products set price = ? where id = ?;";
            try (Connection con = db.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setDouble(1, newPrice);
                pstmt.setInt(2, productID);
                return pstmt.executeUpdate() > 0 ? true : false;
            } catch (SQLException e) {
                System.err.println(Main.getMessage(Messages.SQL_ERROR) + "\n" + e.getMessage());
            }
        } else {
            System.out.println(NONEXISTENT_PRODUCT);
        }
        return false;
    }

    public boolean postProduct(Product product) {
        String sql = "insert into products (name, price, quantity) values (?, ?, ?);";
        if (product.isValid(product)) {
            try (Connection con = db.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, product.getName());
                pstmt.setDouble(2, product.getPrice());
                pstmt.setInt(3, product.getQuantity());
                return (pstmt.executeUpdate() > 0 ? true : false);
            } catch (SQLException e) {
                System.err.println(Main.getMessage(Messages.SQL_ERROR) + "\n" + e.getMessage());
            }
        } else {
            System.err.println(Main.getMessage(Messages.INVALID_PRODUCT_FIELDS));
            return false;
        }
        return false;
    }

    public Product getProduct(Integer productID) {
        String sql = "select * from products where id = ?";
        ResultSet rs = null;
        try (Connection con = db.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, productID);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            System.err.println(Main.getMessage(Messages.SQL_ERROR) + "\n" + e.getMessage());
        } finally {
            try {
                if (!rs.isClosed()) rs.close();
            } catch (SQLException e) {
                System.err.println(Main.getMessage(Messages.SQL_ERROR) + "\n" + e.getMessage());
            }
        }
        return new Product(-1, "", -1.0, -1);
    }

    public TreeSet<Product> getAllProducts() {
        String sql = "select * from products;";
        ResultSet rs = null;
        TreeSet<Product> products = new TreeSet<Product>();
        try (Connection con = db.getConnection(); Statement stmt = con.createStatement()) {
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int amount = rs.getInt("quantity");
                Product prod = new Product(id, name, price, amount);
                products.add(prod);
            }
        } catch (SQLException e) {
            System.err.println(Main.getMessage(Messages.SQL_ERROR) + "\n" + e.getMessage());
        }
        return products;
    }

    public boolean updateProduct(Integer productID, Product newProduct) {
        String sql = "update products set name = ?, price = ?, quantity = ? where id = ?;";
        try (Connection con = db.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, newProduct.getName());
            pstmt.setDouble(2, newProduct.getPrice());
            pstmt.setInt(3, newProduct.getQuantity());
            pstmt.setInt(4, productID);
            return pstmt.executeUpdate() > 0 ? true : false;
        } catch (SQLException e) {
            System.err.println(Main.getMessage(Messages.SQL_ERROR) + "\n" + e.getMessage());
        }
        return false;
    }

    public boolean deleteProduct(Integer productID) {
        String sql = "delete from products where id = ?;";
        try (Connection con = db.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, productID);
            return pstmt.executeUpdate() > 0 ? true : false;
        } catch (SQLException e) {
            System.err.println(Main.getMessage(Messages.SQL_ERROR) + "\n" + e.getMessage());
        }
        return false;
    }

    public boolean productExists(Integer productID) {
        String sql = "select id from products where id = ?";
        try (Connection con = db.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, productID);
            return pstmt.executeQuery().next();
        } catch (SQLException e) {
            System.err.println(Main.getMessage(Messages.SQL_ERROR) + "\n" + e.getMessage());
        }
        return false;
    }

    public boolean isTableEmpty() {
        try (Connection con = db.getConnection(); Statement stmt = con.createStatement()) {
            String sqlCount = "select count(*) from products;";
            ResultSet rs = stmt.executeQuery(sqlCount);
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
        } catch (SQLException e) {
            System.err.println(Main.getMessage(Messages.SQL_ERROR) + "\n" + e.getMessage());
        }
        return false;
    }

}
