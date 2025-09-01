package controller;

import model.Order;
import model.Product;
import model.AddProductCommand;
import model.RemoveProductCommand;
import view.ProductView;

// controlador encargado de mostrar productos y agregar/eliminar del carrito
public class ProductController {

    private ProductView view;
    private Order order;

    public ProductController(ProductView view, Order order) {
        this.view = view;
        this.order = order;
    }

    public void addProduct(Product product) {
        AddProductCommand command = new AddProductCommand(order, product);
        command.Ejecutar();
    }

    public void removeProduct(Product product) {
        if (order.cartViewer().contains(product)) {
        RemoveProductCommand command = new RemoveProductCommand(order, product);
        command.Ejecutar();
        } else {
            System.out.println("El producto " + "\"" + product.getName()+ "\"" + " no esta en el carrito.");
        }
    }

}
