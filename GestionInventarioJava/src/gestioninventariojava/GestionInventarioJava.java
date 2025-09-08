package gestioninventariojava;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionInventarioJava {

    public static void main(String[] args) {
        Inventario inventario = new Inventario();

        Scanner sc = new Scanner(System.in);
        boolean anotherOperation = true;
        int menuOption;

        do {
            while (true) {
                MenuPrincipal.mostrarMenu();
                try {
                    menuOption = sc.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Ingrese una opcion valida.");
                    sc.nextLine();
                }
            }

            sc.nextLine();

            switch (menuOption) {
                case 1:
                    // AGREGAR PRODUCTO
                    MenuPrincipal.agregarProducto(inventario, sc);
                    break;

                case 2:
                    // ELIMINAR PRODUCTO
                    MenuPrincipal.eliminarProducto(inventario, sc);
                    break;

                case 3:
                    // BUSCAR PRODUCTO
                    MenuPrincipal.buscarProducto(inventario, sc);
                    break;

                case 4:
                    // LISTAR PRODUCTOS
                    MenuPrincipal.listarProductos(inventario);
                    break;
                case 5:
                    System.out.println("Gracias por usar nuestro sistema!.");
                    anotherOperation = false;
                    break;

                default:
                    System.out.println("Opcion invalida vuelva a intentarlo.");
                    break;
            }

            if (menuOption != 4) {
                int continueOption;
                do {
                    System.out.println("\nDesea realizar otra operacion?");
                    System.out.println("1. Si");
                    System.out.println("2. No");
                    System.out.print("Eliga: ");

                    try {
                        continueOption = sc.nextInt();
                        if (continueOption == 1) {
                            anotherOperation = true;
                        } else if (continueOption == 2) {
                            System.out.println("Gracias por usar el sistema.");
                            anotherOperation = false;
                        } else {
                            System.out.println("Ingrese una opcion valida.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Error, ingrese un numero valido.");
                        sc.nextLine();
                        continueOption = 0;
                    }
                } while (continueOption != 1 && continueOption != 2);
            }
        } while (anotherOperation);

        sc.close();
    }
}
