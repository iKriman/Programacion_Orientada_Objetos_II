package model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Product> products = new ArrayList<>();
    
    public void addProduct (Product product) {
        products.add(product);
        System.out.println(product.getName() + " agregado al carrito");
    }
    
    public void removeProduct (Product product) {
        products.remove(product);
        System.out.println(product.getName() + " eliminado del carrito");
    }
    
    public List<Product> cartViewer() {
        return products;
    }
}
