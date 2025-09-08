package gestioninventariojava;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {

    // METODOS
    public static void mostrarMenu() {
        System.out.println("=== Gestion de Inventario ===");
        System.out.println("1. Agregar Producto.");
        System.out.println("2. Eliminar Producto.");
        System.out.println("3. Buscar Producto.");
        System.out.println("4. Ver Lista de Productos.");
        System.out.println("5. Salir.");
        System.out.print("Eliga una Opcion: ");
    }
    // metodo para agregar un producto al inventario

    public static void agregarProducto(Inventario inventario, Scanner scanner) {
        try {
            System.out.println("--- Agregar Nuevo Producto ---");
            System.out.print("Ingrese el codigo del producto: ");
            String codigo = scanner.nextLine();
            System.out.print("Ingrese el nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese la descripcion: ");
            String descripcion = scanner.nextLine();
            System.out.print("Ingrese el precio: ");
            double precio = scanner.nextDouble();
            System.out.print("Ingrese la cantidad en stock: ");
            int stock = scanner.nextInt();
            scanner.nextLine();

            Producto nuevoProducto = new Producto(codigo, nombre, descripcion, precio, stock);
            inventario.agregarProducto(nuevoProducto);
            System.out.println("Producto '" + nombre + "' agregado exitosamente.");
        } catch (InputMismatchException e) {
            System.out.println("Ingrese una opcion valida, el producto no fue agregado.");
            scanner.nextLine();
        }
    }

    // busqueda de producto por codigo o nombre
    public static void buscarProducto(Inventario inventario, Scanner scanner) {
        System.out.println("--- Buscar Producto ---");
        System.out.print("Ingrese el codigo o nombre del producto a buscar: ");
        String busqueda = scanner.nextLine();

        // Buscar por codigo primero 
        Producto productoEncontrado = inventario.buscarProductoPorId(busqueda);
        if (productoEncontrado != null) {
            System.out.println("\nProducto encontrado por codigo:");
            System.out.println("Codigo: " + productoEncontrado.getCodigo());
            System.out.println("Nombre: " + productoEncontrado.getNombre());
            System.out.println("Descripcion: " + productoEncontrado.getDescripcion());
            System.out.println("Precio: " + productoEncontrado.getPrecio());
            System.out.println("Stock: " + productoEncontrado.getStock());
            return;
        }

        // Si no se encuentra por codigo, buscar por nombre
        List<Producto> resultados = inventario.buscarProductosPorNombre(busqueda);
        if (!resultados.isEmpty()) {
            System.out.println("\nProductos encontrados por nombre:");
            for (Producto p : resultados) {
                System.out.println("-----------------------------------");
                System.out.println("Codigo: " + p.getCodigo());
                System.out.println("Nombre: " + p.getNombre());
                System.out.println("Precio: " + p.getPrecio());
                System.out.println("Stock: " + p.getStock());
            }
            System.out.println("-----------------------------------");
        } else {
            System.out.println("No se encontraron productos con ese codigo o nombre.");
        }
    }

    // eliminacion de producto 
    public static void eliminarProducto(Inventario inventario, Scanner scanner) {
        System.out.println("--- Eliminar Producto ---");
        System.out.print("Ingrese el codigo del producto a eliminar: ");
        String codigo = scanner.nextLine();

        if (inventario.eliminarProducto(codigo)) {
            System.out.println("Producto con codigo " + codigo + " eliminado exitosamente.");
        } else {
            System.out.println("No se encontro un producto con el codigo " + codigo + ".");
        }
    }

    // listado completo de productos en stock
    public static void listarProductos(Inventario inventario) {
        System.out.println("--- Listado de Productos en Inventario ---");
        List<Producto> todosLosProductos = inventario.listarTodosLosProductos();

        if (todosLosProductos.isEmpty()) {
            System.out.println("El inventario esta vacio.");
        } else {
            for (Producto p : todosLosProductos) {
                System.out.println("-----------------------------------");
                System.out.println("Codigo: " + p.getCodigo());
                System.out.println("Nombre: " + p.getNombre());
                System.out.println("Descripcion: " + p.getDescripcion());
                System.out.println("Precio: " + p.getPrecio());
                System.out.println("Stock: " + p.getStock());
            }
            System.out.println("-----------------------------------");
        }
    }

}
