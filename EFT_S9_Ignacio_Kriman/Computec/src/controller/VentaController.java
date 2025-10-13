package controller;

import modelo.Cliente;
import modelo.Equipo;
import modelo.Venta;
import service.Descuento;
import service.VentaService;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

public class VentaController {

    private final VentaService service = new VentaService();

    public void registrarVenta(Cliente cliente, Equipo equipo, Descuento estrategia) {
        BigDecimal precioBase = equipo.getPrecio();
        BigDecimal precioFinal = estrategia.aplicar(precioBase);

        
        String descuentoAplicado = estrategia.nombre(); 

        service.registrarVenta(
                cliente.getRut(),
                equipo.getIdEquipo(),
                precioBase,
                precioFinal,
                descuentoAplicado
        );

        System.out.println("Venta registrada para: " + cliente.getNombreCompleto() + ", Final: " + precioFinal);
    }

    public List<Venta> listar() {
        try {
            return service.listarVentas();
        } catch (RuntimeException e) {
            System.err.println("Error al listar Ventas: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}