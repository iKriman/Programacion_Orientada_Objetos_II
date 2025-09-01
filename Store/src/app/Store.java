// Creacion de aplicacion de venta de ropa con aplicacion de descuento dinamica
// y uso de diseno arquitectonico MVC y uso de decoradores y singleton

package app;

import java.util.InputMismatchException;
import java.util.Scanner;
import model.Order;
import model.Product;
import model.Catalog;
import view.ProductView;
import view.CartView;
import view.DiscountView;
import controller.ProductController;
import controller.OrderController;
import controller.DiscountController;
import java.util.ArrayList;
import java.util.List;

public class Store {
    
    public static void main(String[] args) {

        // instanciamos MODEL
        Catalog catalog = new Catalog();
        List<Product> catalogList = catalog.getProducts();
        Order carrito = new Order();

        // instanciamos VIEW
        ProductView productView = new ProductView();
        CartView cartView = new CartView();
        DiscountView discountView = new DiscountView();

        // instanciamos CONTROLLER
        ProductController productController = new ProductController(productView, carrito);
        OrderController orderController = new OrderController(carrito, cartView);
        DiscountController discountController = new DiscountController(discountView, carrito);

        // metodos operacionales 
        Scanner sc = new Scanner(System.in);
        boolean anotherOperation = true;
        int menuOption;
        
        do {
            while (true) { // menu de bienvenida
                System.out.println("=== Bienvenido a Ropantastica ===");
                System.out.println("---------------------------------");
                System.out.println("         Solo por hoy!           ");
                System.out.println("Descuento del 15% en toda la tienda");
                System.out.println("20% de descuento adicional en calzado");
                System.out.println("---------------------------------");
                System.out.println("1. Agregar Producto.");
                System.out.println("2. Eliminar Producto Del Carrito.");
                System.out.println("3. Ver Carrito.");
                System.out.println("4. Salir.");
                System.out.print("Eliga una opcion: ");
                
                try {
                    menuOption = sc.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Eliga una opcion correcta.");
                    sc.nextLine();
                }
            }
            
            sc.nextLine();
            
            switch (menuOption) {
                case 1:
                    // AGREGAR PRODUCTOS
                    System.out.println("Catalogo: ");
                    // itereamos una lista para hacer visible la lista de Catalog
                    for (int i = 0; i < catalogList.size(); i++) {
                        Product p = catalogList.get(i);
                        System.out.println((i + 1) + ". " + p.getName() + " - $" + p.getPrice());
                    }
                    System.out.println("Eliga un producto: ");

                    // utilizamos un trycatch para manejo de excepciones
                    try {
                        // creamos variable para almacenar seleccion del usuario
                        int productIndex = sc.nextInt();
                        // si la seleccion es mayor a cero y no supera el tamano de la lista
                        if (productIndex > 0 && productIndex <= catalogList.size()) {
                            // ajustamos indice
                            Product selectedProduct = catalogList.get(productIndex - 1);
                            // agregamos el producto al carrito 
                            productController.addProduct(selectedProduct);
                        } else {
                            System.out.println("Opcion de producto invalida.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada no valida. ingrese un numero.");
                        sc.nextLine(); // limpiamos el buffer
                    }
                    break;
                
                case 2:
                    // ELIMINAR UN PRODUCTO DEL CARRITO
                    System.out.println("Que producto desea eliminar? ");
                    // itereamos una lista para hacer visible la lista de Catalog
                    for (int i = 0; i < catalogList.size(); i++) {
                        Product p = catalogList.get(i);
                        System.out.println((i + 1) + ". " + p.getName() + " - $" + p.getPrice());
                    }
                    System.out.println("Eliga un producto: ");

                    // utilizamos un trycatch para manejo de excepciones
                    try {
                        // creamos variable para almacenar seleccion del usuario
                        int productIndex = sc.nextInt();
                        // si la seleccion es mayor a cero y no supera el tamano de la lista
                        if (productIndex > 0 && productIndex <= catalogList.size()) {
                            // ajustamos indice
                            Product selectedProduct = catalogList.get(productIndex - 1);
                            // quitamos el producto al carrito 
                            productController.removeProduct(selectedProduct);
                        } else {
                            System.out.println("Opcion de producto invalida.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada no valida. ingrese un numero.");
                        sc.nextLine(); // limpiamos el buffer
                    }
                    break;
                
                case 3:
                    // VER CARRITO
                    orderController.viewCart();
                    break;
                
                case 4:
                    System.out.println("Grcias por visitar nuestra tienda!.");
                    anotherOperation = false;
                    break;
                
                default:
                    System.out.println("Opcion invalida, vuelva a intentarlo.");
                    break;
            }
            
            if (menuOption != 4) {
                int continueOption;
                do {
                    System.out.println("\nDesea hacer otra operacion?");
                    System.out.println("1. Si");
                    System.out.println("2. No");
                    System.out.print("Eliga: ");
                    
                    try {
                        continueOption = sc.nextInt();
                        if (continueOption == 1) {
                            anotherOperation = true;
                        } else if (continueOption == 2) {
                            System.out.println("Gracias por visitar nuestra tienda!.");
                            anotherOperation = false;
                        } else {
                            System.out.println("Ingrese una opcion valida.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Error, ingrese una opcion valida.");
                        sc.nextLine();
                        continueOption = 0; // reset to stay in the loop
                    }
                } while (continueOption != 1 && continueOption != 2);
            }
        } while (anotherOperation);
        
        sc.close();
    }
    
}
//ik
