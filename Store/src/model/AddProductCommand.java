package model;

public class AddProductCommand implements ICommand {

    private Order order;
    private Product product;

    public AddProductCommand(Order order, Product product) {
        this.order = order;
        this.product = product;
    }

    // aplicamos el metodo de la interfaz ICommand
    // este ejecutara la orden de anadir un nuevo producto
    @Override
    public void Ejecutar() {
        order.addProduct(product);
    }

}
