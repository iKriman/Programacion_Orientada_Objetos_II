package view;

import model.Product;

// esta vista se encarga de mostrar info relaciona a un producto
public class ProductView {
    
    public void displayDetails(Product product) {
        System.out.println("--- Detalles Del Producto ---");
        System.out.println("Nombre: " + product.getName());
        System.out.println("Precio: $" + product.getPrice());
        System.out.println("-----------------------------");
    }

}
