package controller;

import model.DiscountCommand;
import model.DecoratorApplyDiscount;
import model.DecoratorCategory;
import model.Product;
import model.Order;
import view.DiscountView;

// controlador responsable de aplicar descuentos atraves del decorator
public class DiscountController {

    private DiscountView view;
    private Order order;

    public DiscountController(DiscountView view, Order order) {
        this.view = view;
        this.order = order;
    }

    // METODOS
    // instanciamos el decorador para aplicar descuento
    public void applyDiscount(Product product, double percentage) {
        DecoratorApplyDiscount decorator = new DecoratorApplyDiscount(percentage, product);

        // ahora dejamos listo el comando ejecutar 
        DiscountCommand command = new DiscountCommand(order, product, decorator);
        command.Ejecutar();
    }

    // misma instancia pero para el descuento por categoria
    public void applyDiscountCategory(Product product, String category, double percentage) {
        DecoratorCategory decorator = new DecoratorCategory(category, percentage, product);

        DiscountCommand command = new DiscountCommand(order, product, decorator);
        command.Ejecutar();
    }
}
