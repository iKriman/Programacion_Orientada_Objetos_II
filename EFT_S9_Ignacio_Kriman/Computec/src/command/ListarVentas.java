package command;

import service.VentaService;
import modelo.Venta;
import java.util.List;

public class ListarVentas implements Command {
    private final VentaService servicio;
    private List<Venta> resultado;

    public ListarVentas(VentaService servicio) {
        this.servicio = servicio;
    }

    @Override
    public void execute() {
        resultado = servicio.listarVentas();
    }

    public List<Venta> getResultado() {
        return resultado;
    }
}