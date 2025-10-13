package service;

import dao.VentaDAO;
import modelo.Venta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class VentaService {

    private final VentaDAO dao = new VentaDAO();

   
    public void registrarVenta(String rutCliente, int idEquipo, BigDecimal precioBase, BigDecimal precioFinal, String descuentoAplicado) {
        Venta v = new Venta(0, rutCliente, idEquipo, LocalDateTime.now(), precioBase, precioFinal, descuentoAplicado);
        
    
        dao.crear(v); 
    }

    public List<Venta> listarVentas() {
        return dao.listar();
    }
}