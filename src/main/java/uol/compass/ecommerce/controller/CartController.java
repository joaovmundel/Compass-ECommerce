package uol.compass.ecommerce.controller;

import uol.compass.ecommerce.Main;
import uol.compass.ecommerce.model.Cart;
import uol.compass.ecommerce.model.Product;
import uol.compass.ecommerce.model.config.Messages;

import java.util.HashMap;

public class CartController {


    public void addProduct(Integer productID, Integer quantity, Cart cart) {
        DatabaseController db = new DatabaseController();
        ProductController productController = new ProductController(db);
        if (productController.productExists(productID)) {
            if (productController.hasStock(productID) && productController.getStock(productID) >= quantity) {
                if (cart.hasProduct(productID)) {
                    cart.getProducts().put(productID, cart.getProducts().get(productID) + quantity);
                } else {
                    cart.getProducts().put(productID, quantity);
                }
                System.out.println(Main.getMessage(Messages.CART_PRODUCT_ADDED));
                System.out.println(Main.getMessage(Messages.CART_PRODUCT_AMOUNT).replace("@quantity", cart.getProducts().get(productID) + ""));
            } else {
                System.out.println(Main.getMessage(Messages.MENU_CLIENT_PRODUCT_NO_STOCK));
                System.out.println(Main.getMessage(Messages.QUANTITY_IN_STOCK).replace("@quantity", productController.getStock(productID) + ""));
            }
        } else {
            System.out.println(Main.getMessage(Messages.NONEXISTENT_PRODUCT_ERROR));
        }
    }

    public void removeProduct(Integer productID, Integer quantity, Cart cart) {
        if (cart.hasProduct(productID)) {
            if (cart.getProducts().get(productID) >= quantity) {
                cart.getProducts().put(productID, cart.getProducts().get(productID) - quantity);
                System.out.println(Main.getMessage(Messages.CART_PRODUCT_REMOVED));
                System.out.println(Main.getMessage(Messages.CART_PRODUCT_AMOUNT).replace("@quantity", cart.getProducts().get(productID) + ""));
            } else {
                System.out.println(Main.getMessage(Messages.CART_PRODUCT_AMOUNT_INVALID));
            }
        } else {
            System.out.println(Main.getMessage(Messages.CART_PRODUCT_NOT_FOUND));
        }
    }

    public boolean checkout(Cart cart) {
        HashMap<Integer, Integer> products = cart.getProducts();
        ProductController pc = new ProductController();
        HashMap<Integer, Boolean> unavailableProducts = new HashMap<>();
        for (Integer id : products.keySet()) {
            if (!pc.hasStock(id)) {
                unavailableProducts.put(id, true);
            }
        }

        if (unavailableProducts.size() == 0) {
            for (Integer id : products.keySet()) {
                pc.removeStock(id, products.get(id));
            }
            System.out.println(Main.getMessage(Messages.MENU_CLIENT_CHECKOUT_SUCCESS));
            return true;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("|");
            for (Integer unvaliableProductID : unavailableProducts.keySet()) {
                sb.append(pc.getProductName(unvaliableProductID) + "|");
            }
            System.out.println(Main.getMessage(Messages.CART_UNVALIABLE_PRODUCTS) + sb);
        }

        return false;
    }

    public void showProducts(Cart cart) {
        HashMap<Integer, Integer> products = cart.getProducts();
        ProductController pc = new ProductController();
        double total = 0;
        int largerName = 0;

        for (Integer id : products.keySet()) {
            Product prod = pc.getProduct(id);
            if (prod.getName().length() > largerName) {
                largerName = prod.getName().length();
            }
        }
        String hyphen = placeText(largerName, "-");
        System.out.println("------------" + hyphen + "-----------------------------------");
        System.out.println("|   ID   | " + Main.getMessage(Messages.SHOW_PRODUCTS_NAME) + placeText(largerName - 4, " ") + "|      " + Main.getMessage(Messages.SHOW_PRODUCTS_PRICE) + "      |   " + Main.getMessage(Messages.SHOW_PRODUCTS_QUANTITY) + "   |");
        for (Integer id : products.keySet()) {
            Product prod = pc.getProduct(id);
            total += prod.getPrice() * prod.getQuantity();
            System.out.println("|   " + prod.getId() + "   | " + prod.getName() + placeText(largerName - prod.getName().length(), " ") + "| " + prod.getPrice() * prod.getPrice() + placeText(16 - (String.valueOf(prod.getPrice()).length()), " ") + "| " + prod.getQuantity() + placeText(15 - (String.valueOf(prod.getQuantity()).length()), " ") + "|");
        }
        System.out.println("------------" + hyphen + "-----------------------------------");
        System.out.println("Total: " + total);
    }

    public String placeText(Integer size, String textToPlace) {

        return new String(new char[size]).replace("\0", textToPlace);
    }
}
