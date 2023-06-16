package uol.compass.ecommerce.model;

import uol.compass.ecommerce.Main;
import uol.compass.ecommerce.model.config.Messages;

public class Product implements Comparable<Product> {
    private Integer id;
    private String name;
    private Double price;
    private Integer quantity;

    public Product(String name, Double price, Integer quantity) {
        if (isValid(name, price, quantity)) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        } else {
            System.err.println(Main.getMessage(Messages.INVALID_PRODUCT_FIELDS));
            this.id = -1;
            this.name = "-1";
            this.price = -1.0;
            this.quantity = -1;
        }
    }

    public Product(Integer id, String name, Double price, Integer quantity) {
        if (isValid(name, price, quantity)) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        } else {
            System.err.println(Main.getMessage(Messages.INVALID_PRODUCT_FIELDS));
            this.id = -1;
            this.name = "-1";
            this.price = -1.0;
            this.quantity = -1;
        }
    }

    public boolean isValid(String name, Double price, Integer quantity) {
        return (name != null && name.length() > 0 && price != null && price >= 0 && quantity != null && quantity >= 0);
    }

    public boolean isValid(Product product) {

        return (product.name != null && product.name.length() > 0 && product.price != null && product.price >= 0 && product.quantity != null && product.quantity >= 0);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && name.length() > 0) {
            this.name = name;
        } else {
            System.err.println(Main.getMessage(Messages.INVALID_PRODUCT_NAME));
        }
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        if (price >= 0 && !(price.isNaN())) {
            this.price = price;
        } else {
            System.err.println(Main.getMessage(Messages.INVALID_PRODUCT_PRICE));
        }
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        if (quantity != null && quantity >= 0) {
            this.quantity = quantity;
        } else {
            System.err.println(Main.getMessage(Messages.INVALID_PRODUCT_AMOUNT));

        }
    }

    @Override
    public int compareTo(Product other) {
        if (other == null) {
            return 1;
        }
        if (this.id > other.id) return 1;
        else if (this.id < other.id) return -1;
        else return 0;
    }

}
