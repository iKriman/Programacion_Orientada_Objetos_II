package controller;

import java.util.ArrayList;
import java.util.List;
import model.Order;
import model.Product;
import model.IComponent;
import model.DecoratorApplyDiscount;
import model.DecoratorCategory;
import view.CartView;

// controlador que gestiona el carrito de compras
public class OrderController {

    private Order order;
    private CartView view;

    public OrderController(Order order, CartView view) {
        this.order = order;
        this.view = view;
    }

    // METODOS
    public void viewCart() {
        // obtenemos la lista de productos para el carrito
        List<Product> cartProducts = order.cartViewer();

        // creamos otra lista para almacenar los precios con descuentos
        List<Product> DiscountedPrices = new ArrayList<>();

        // aplicamos los descuentos a cada producto
        for (Product product : cartProducts) {
            IComponent decoratedProduct = product;
            // verificamos que sea de la categoria con descuento
            if (product.getCategory().equalsIgnoreCase("Footwear")) {
                decoratedProduct = new DecoratorCategory("Footwear", 0.20, decoratedProduct);
            }

            // aplicamos descuento general de la tienda
            decoratedProduct = new DecoratorApplyDiscount(0.15, decoratedProduct);

            // creamos un ultimo producto con el precio final
            Product finalProduct = new Product(decoratedProduct.applyDiscount(product.getPrice()), product.getCategory(), product.getName());
            DiscountedPrices.add(finalProduct);
        }

        view.displatCart(DiscountedPrices);
    }

}
