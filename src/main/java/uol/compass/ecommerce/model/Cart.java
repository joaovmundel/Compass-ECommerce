package uol.compass.ecommerce.model;

import uol.compass.ecommerce.controller.DatabaseController;

import java.util.ArrayList;
import java.util.HashMap;

public class Cart {
    private HashMap<Integer, Integer> products;
    private Double total;


    public boolean hasProduct(Integer productID){
        return this.products.containsKey(productID) && this.products.get(productID) > 0;
    }
    public void addProduct(Integer productID, Integer quantity) {
        DatabaseController db = new DatabaseController();
        if (db.hasStock(productID) && db.getStock(productID) >= quantity) {
            if (hasProduct(productID)) {
                this.products.put(productID, products.get(productID) + quantity);
            } else {
                this.products.put(productID, quantity);
            }
            System.out.println("Produto adicionado ao carrinho com sucesso.");
            System.out.println("Atualmente voce tem " + this.products.get(productID) + " desse produto no carrinho.");
        } else {
            System.out.println("Nao temos estoque o suficiente para essa quantidade do produto.");
            System.out.println("Temos apenas " + db.getStock(productID) + " no estoque.");
        }
    }

    public void removeProduct(Integer productID, Integer quantity) {
        if (hasProduct(productID)) {
            if (this.products.get(productID) >= quantity) {
                this.products.put(productID, this.products.get(productID) - quantity);
                System.out.println("Foram removidos " + quantity + " do carrinho.");
                System.out.println("Restam " + this.products.get(productID) + " agora");
            } else {
                System.out.println("Voce nao pode remover mais produtos do que voce tem no carrinho.");
            }
        } else {
            System.out.println("Esse produto nao esta no carrinho.");
        }
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
