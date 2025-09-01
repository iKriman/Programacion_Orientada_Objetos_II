package model;

public class DiscountCommand implements ICommand {

    private Order order;
    private Product product;
    private IComponent discountDecorator;

    public DiscountCommand(Order order, Product product, IComponent discountDecorator) {
        this.order = order;
        this.product = product;
        this.discountDecorator = discountDecorator;
    }

    @Override
    public void Ejecutar() {
        double finalPrice = discountDecorator.applyDiscount(product.getPrice());
        System.out.println("Se ha aplicado el descuento.");
    }

}
