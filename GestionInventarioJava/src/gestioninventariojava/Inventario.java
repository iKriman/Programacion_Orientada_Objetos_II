package gestioninventariojava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Inventario {

    private HashMap<String, Producto> productos;

    public Inventario() {
        productos = new HashMap<>();
    }

    // METODOS
    public void agregarProducto(Producto producto) {
        productos.put(producto.getCodigo(), producto);
    }

    public boolean eliminarProducto(String codigo) {
        if (productos.containsKey(codigo)) {
            productos.remove(codigo);
            return true;
        }
        return false;
    }

    public Producto buscarProductoPorId(String codigo) {
        return productos.get(codigo);
    }

    public List<Producto> buscarProductosPorNombre(String nombre) {
        List<Producto> resultados = new ArrayList<>();
        for (Producto producto : productos.values()) {
            if (producto.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                resultados.add(producto);
            }
        }
        return resultados;
    }

    public List<Producto> listarTodosLosProductos() {
        return new ArrayList<>(productos.values());
    }

}
