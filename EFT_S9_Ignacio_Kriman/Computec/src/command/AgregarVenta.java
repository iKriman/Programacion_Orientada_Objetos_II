package command;

import controller.VentaController;
import modelo.Cliente;
import modelo.Equipo;
import service.Descuento;

public class AgregarVenta implements Command {

    private final VentaController controller;
    private final Cliente cliente;
    private final Equipo equipo;
    private final Descuento estrategiaDescuento; 

    public AgregarVenta(VentaController c, Cliente cli, Equipo eq, Descuento dscto) {
        this.controller = c;
        this.cliente = cli;
        this.equipo = eq;
        this.estrategiaDescuento = dscto;
    }

    @Override
    public void execute() {
        controller.registrarVenta(cliente, equipo, estrategiaDescuento);
    }
}
