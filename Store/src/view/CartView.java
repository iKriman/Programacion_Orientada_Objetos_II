package view;

import java.util.List;
import model.Product;

// vista dedicada a mostrar los productos del carrito de compras y total a pagar
public class CartView {

    public void displatCart(List<Product> products) {
        System.out.println("--- Carrito De Compras ---");

        if (products.isEmpty()) {
            System.out.println("El carrito esta vacio.");
            return;
        }
        double total = 0.0; // creamos variable para que vaya acumulando el valor en la iteracion
        for (Product product : products) {
            System.out.println(" - " + product.getName() + " - $" + product.getPrice());
            total += product.getPrice();
        }
        System.out.println("---------------------------");
        System.out.println("Total: $" + total);
        System.out.println("---------------------------");
    }

}
