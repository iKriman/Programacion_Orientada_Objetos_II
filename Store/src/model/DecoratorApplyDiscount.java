package model;

public class DecoratorApplyDiscount extends DiscountDecorator {

    private double percentage;

    public DecoratorApplyDiscount(double percentage, IComponent component) {
        super(component);
        this.percentage = percentage;

    }

    // sobreescribimos el metodo para aplicar un porcentaje de descuento aplicable
    @Override
    public double applyDiscount(double originalPrice) {
        double price = super.applyDiscount(originalPrice);
        return price * (1 - percentage);
    }

}
