package modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Venta {

    private int id;
    private String rutCliente;
    private int idEquipo;
    private LocalDateTime fecha;
    private BigDecimal precioBase;
    private BigDecimal precioFinal;
    private String descuentoAplicado; 

    public Venta(int id, String rutCliente, int idEquipo, LocalDateTime fecha, BigDecimal precioBase, BigDecimal precioFinal, String descuentoAplicado) {
        this.id = id;
        this.rutCliente = rutCliente;
        this.idEquipo = idEquipo;
        this.fecha = fecha;
        this.precioBase = precioBase;
        this.precioFinal = precioFinal;
        this.descuentoAplicado = descuentoAplicado;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getRutCliente() { return rutCliente; }
    public void setRutCliente(String rutCliente) { this.rutCliente = rutCliente; }

    public int getIdEquipo() { return idEquipo; }
    public void setIdEquipo(int idEquipo) { this.idEquipo = idEquipo; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public BigDecimal getPrecioBase() { return precioBase; }
    public void setPrecioBase(BigDecimal precioBase) { this.precioBase = precioBase; }

    public BigDecimal getPrecioFinal() { return precioFinal; }
    public void setPrecioFinal(BigDecimal precioFinal) { this.precioFinal = precioFinal; }

    public String getDescuentoAplicado() { return descuentoAplicado; }
    public void setDescuentoAplicado(String descuentoAplicado) { this.descuentoAplicado = descuentoAplicado; }
}