package uol.compass.ecommerce.model;

import uol.compass.ecommerce.Main;
import uol.compass.ecommerce.controller.DatabaseController;
import uol.compass.ecommerce.controller.ProductController;
import uol.compass.ecommerce.model.config.Messages;

import java.util.ArrayList;
import java.util.HashMap;

public class Cart {
    private HashMap<Integer, Integer> products;
    private Double total;
    private DatabaseController db;

    public Cart(HashMap<Integer, Integer> products, Double total, DatabaseController db) {
        this.products = products;
        this.total = total;
        this.db = db;
    }

    public Cart(DatabaseController db){
        this.products = new HashMap<>();
        this.total = 0.0;
        this.db = db;
    }

    public Cart(){
        this.db = new DatabaseController();
        this.total = 0.0;
        this.products = new HashMap<>();
    }

    public boolean hasProduct(Integer productID){
        return this.products.containsKey(productID) && this.products.get(productID) > 0;
    }

    public HashMap<Integer, Integer> getProducts() {
        return products;
    }

    public void setProducts(HashMap<Integer, Integer> products) {
        this.products = products;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
