package uol.compass.ecommerce.controller;

import uol.compass.ecommerce.Main;
import uol.compass.ecommerce.model.Cart;
import uol.compass.ecommerce.model.Product;
import uol.compass.ecommerce.model.config.Messages;

import java.text.DecimalFormat;
import java.util.HashMap;

public class CartController {
    private DatabaseController db = new DatabaseController();
    private ProductController productController = new ProductController(db);


    public void setProductAmount(Integer productID, Integer quantity, Cart cart) {
        if (productController.productExists(productID)) {
            if (quantity >= 0) {
                if (productController.hasStock(productID) && productController.getStock(productID) >= quantity) {
                    cart.getProducts().put(productID, quantity);
                    System.out.println(Main.getMessage(Messages.CART_PRODUCT_AMOUNT).replace("@quantity", cart.getProducts().get(productID) + ""));
                } else {
                    System.out.println(Main.getMessage(Messages.MENU_CLIENT_PRODUCT_NO_STOCK));
                    System.out.println(Main.getMessage(Messages.QUANTITY_IN_STOCK).replace("@quantity", productController.getStock(productID) + ""));
                }
            } else {
                System.out.println(Main.getMessage(Messages.INVALID_PRODUCT_AMOUNT));
            }
        } else {
            System.out.println(Main.getMessage(Messages.NONEXISTENT_PRODUCT_ERROR));
        }
    }


    public void addProduct(Integer productID, Integer quantity, Cart cart) {
        if (productController.productExists(productID)) {
            int stock = productController.getStock(productID);
            int cartAmount = 0;
            if(cart.getProducts().containsKey(productID)){
                cartAmount = cart.getProducts().get(productID);
            }
            if (quantity >= 0) {
                if (productController.hasStock(productID) && stock >= quantity && (quantity + cartAmount) <= (stock-cartAmount)) {
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
                System.out.println(Main.getMessage(Messages.INVALID_PRODUCT_AMOUNT));
            }
        } else {
            System.out.println(Main.getMessage(Messages.NONEXISTENT_PRODUCT_ERROR));
        }
    }

    public void removeProduct(Integer productID, Integer quantity, Cart cart) {
        if (cart.hasProduct(productID)) {
            if (cart.getProducts().get(productID) >= quantity && quantity >= 0) {
                if (cart.getProducts().get(productID) - quantity == 0) {
                    cart.getProducts().remove(productID);
                } else {
                    cart.getProducts().put(productID, cart.getProducts().get(productID) - quantity);
                }
                System.out.println(Main.getMessage(Messages.CART_PRODUCT_REMOVED));
                System.out.println(Main.getMessage(Messages.CART_PRODUCT_AMOUNT).replace("@quantity", (cart.getProducts().get(productID) != null ? cart.getProducts().get(productID) : 0) + ""));

            } else {
                System.out.println(Main.getMessage(Messages.CART_PRODUCT_AMOUNT_INVALID));
            }
        } else {
            System.out.println(Main.getMessage(Messages.CART_PRODUCT_NOT_FOUND));
        }
    }

    public boolean checkout(Cart cart) {
        HashMap<Integer, Integer> products = cart.getProducts();
        HashMap<Integer, Boolean> unavailableProducts = new HashMap<>();
        if(!products.isEmpty()) {
            for (Integer id : products.keySet()) {
                if (!productController.hasStock(id)) {
                    unavailableProducts.put(id, true);
                }
            }

            if (unavailableProducts.size() == 0) {
                for (Integer id : products.keySet()) {
                    productController.removeStock(id, products.get(id));
                }
                cart.getProducts().clear();
                System.out.println(Main.getMessage(Messages.MENU_CLIENT_CHECKOUT_SUCCESS));
                return true;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("|");
                for (Integer unvaliableProductID : unavailableProducts.keySet()) {
                    sb.append(productController.getProductName(unvaliableProductID) + "|");
                }
                System.out.println(Main.getMessage(Messages.CART_UNVALIABLE_PRODUCTS) + sb);
            }
        }else{
            System.out.println(Main.getMessage(Messages.CART_EMPTY));
        }
        return false;
    }

    public void showProducts(Cart cart) {
        HashMap<Integer, Integer> products = cart.getProducts();
        DecimalFormat f = new DecimalFormat("##.00");
        double total = 0;
        int largerName = 0;
        if (!products.isEmpty()) {
            for (Integer id : products.keySet()) {
                Product prod = productController.getProduct(id);
                if (prod.getName().length() > largerName) {
                    largerName = prod.getName().length();
                }
            }
            String hyphen = placeText(largerName, "-");
            System.out.println("------------" + hyphen + "-----------------------------------");
            System.out.println("|   ID   | " + Main.getMessage(Messages.SHOW_PRODUCTS_NAME) + placeText(largerName - 4, " ") + "|      " + Main.getMessage(Messages.SHOW_PRODUCTS_PRICE) + "      |   " + Main.getMessage(Messages.SHOW_PRODUCTS_QUANTITY) + "   |");
            for (Integer id : products.keySet()) {
                Product prod = productController.getProduct(id);
                double prodTotalPrice = prod.getPrice() * products.get(id);
                total += prodTotalPrice;
                System.out.println("|   " + prod.getId() + "   | " + prod.getName() + placeText(largerName - prod.getName().length(), " ") + "| " + f.format(prodTotalPrice) + placeText(16 - (String.valueOf(f.format(prodTotalPrice)).length()), " ") + "| " + products.get(id) + placeText(15 - (String.valueOf(products.get(id)).length()), " ") + "|");
            }
            System.out.println("------------" + hyphen + "-----------------------------------");
            System.out.println("Total: " + f.format(total));
        } else {
            System.out.println(Main.getMessage(Messages.CART_EMPTY));
        }
    }

    public String placeText(Integer size, String textToPlace) {
        if (size >= 0) {
            return new String(new char[size]).replace("\0", textToPlace);
        } else {
            return "";
        }
    }
}
