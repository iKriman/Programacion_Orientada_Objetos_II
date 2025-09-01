package model;

public class Product implements IComponent {

    private double price;
    private String productName;
    private String category;

    public Product(double price, String productName, String category) {
        this.price = price;
        this.productName = productName;
        this.category = category;
    }

    // METODOS
    
    public String getName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }
    
    public String getCategory() {
        return category;
    }

    // esta es la logica base la cual no aplica descuento
    // el decorador se encargara de aplicarlos dependiendo del caso
    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice;
    }
}
