package model;

public class RemoveProductCommand implements ICommand {

    private Order order;
    private Product product;

    public RemoveProductCommand(Order order, Product product) {
        this.order = order;
        this.product = product;
    }

    // aplicamos el metodo de la interfaz ICommand
    // este ejecutara la orden de anadir un nuevo producto
    @Override
    public void Ejecutar() {
        order.removeProduct(product);
    }

}
