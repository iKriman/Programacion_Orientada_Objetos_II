package model;

public abstract class DiscountDecorator implements IComponent {

    protected IComponent component;

    public DiscountDecorator(IComponent component) {
        this.component = component;
    }

    @Override
    public double applyDiscount(double originalPrice) {
        return component.applyDiscount(originalPrice);
    }

}
