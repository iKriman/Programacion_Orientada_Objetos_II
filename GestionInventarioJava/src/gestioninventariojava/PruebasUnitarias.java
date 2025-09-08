package gestioninventariojava;

import java.util.List;

/*
 * creamos una clase espeacilizada con un metodo main para realizar todas las
 * pruebas unitarias de nuestro codigo. ademas, usamos assert para tener una
 * verificacion mas precisa 
 */
public class PruebasUnitarias {

    public static void main(String[] args) {
        probarClaseProducto();
        probarClaseInventario();
    }

    private static void probarClaseProducto() {
        System.out.println("=== PRUEBAS UNITARIAS: CLASE PRODUCTO ===");

        // prueba 1: Creacion y verificacion de atributos
        System.out.println(" ");
        System.out.println("1. Probando la creacion del producto...");
        Producto p = new Producto("1234", "Iphone 15", "Telefono inteligente", 800000, 10);
        assert p.getCodigo().equals("1234") : "Error en Codigo";
        assert p.getNombre().equals("Iphone 15") : "Error en Nombre";
        assert p.getPrecio() == 800000 : "Error en Precio";
        System.out.println("Producto creado correctamente.");
        System.out.println(" ");
        // prueba 2: actualizacion de atributos
        System.out.println("2. Probando la actualizacion de precio y stock...");
        p.actualizarPrecio(600000);
        p.setStock(8);
        assert p.getPrecio() == 600000 : "Error al actualizar precio";
        assert p.getStock() == 8 : "Error al actualizar stock";
        System.out.println("Atributos actualizados correctamente.");
        System.out.println(" ");
        System.out.println("TODAS LAS PRUEBAS DE PRODUCTO PASARON");
    }

    private static void probarClaseInventario() {
        System.out.println("\n=== PRUEBAS UNITARIAS: CLASE INVENTARIO ===");
        Inventario inventario = new Inventario();

        Producto p1 = new Producto("1234", "Iphone 15", "Telefono inteligente", 800000, 50);
        Producto p2 = new Producto("4321", "Samsung s25", "Telefono inteligente", 600000, 100);

        // prueba 1: agregar producto
        System.out.println(" ");
        System.out.println("1. Probando agregar un producto...");
        inventario.agregarProducto(p1);
        assert inventario.listarTodosLosProductos().size() == 1 : "Error al agregar producto";
        System.out.println("Producto agregado correctamente.");

        // prueba 2: eliminar producto
        System.out.println(" ");
        System.out.println("2. Probando eliminar un producto...");
        inventario.agregarProducto(p2);
        boolean eliminado = inventario.eliminarProducto("P001");
        assert eliminado : "Error al eliminar producto";
        assert inventario.listarTodosLosProductos().size() == 1 : "Error en conteo despues de eliminar";
        System.out.println("Producto eliminado correctamente.");

        // prueba 3: buscar producto por nombre
        System.out.println(" ");
        System.out.println("3. Probando buscar un producto por nombre...");
        List<Producto> resultados = inventario.buscarProductosPorNombre("Mouse");
        assert resultados.size() == 1 : "Error al buscar por nombre";
        assert resultados.get(0).getNombre().equals("Iphone 15") : "Error, producto no encontrado";
        System.out.println("Busqueda por nombre funciona correctamente.");

        // prueba 4: eliminar un producto inexistente
        System.out.println(" ");
        System.out.println("4. Probando eliminar un producto inexistente...");
        boolean noEliminado = inventario.eliminarProducto("0987");
        assert !noEliminado : "Error, se elimino un producto inexistente";
        System.out.println("Se maneja correctamente la eliminacion de un producto inexistente.");
        System.out.println(" ");
        System.out.println("TODAS LAS PRUEBAS DE INVENTARIO PASARON");
    }
}
