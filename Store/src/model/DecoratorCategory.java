package model;

public class DecoratorCategory extends DiscountDecorator {

    private String category;
    private double discount;

    public DecoratorCategory(String category, double discount, IComponent component) {
        super(component);
        this.category = category;
        this.discount = discount;
    }

    // METODOS
    
    @Override
    public double applyDiscount(double originalPrice) {
        double price = super.applyDiscount(originalPrice);
    
        // iteramos apra verificar que aplique una categoria al producto
        if (component instanceof Product) {
            Product product = (Product) component;
            if (product.getCategory().equalsIgnoreCase(category))
                return price * (1 - discount);
        }
        
        return price;
    }

}
