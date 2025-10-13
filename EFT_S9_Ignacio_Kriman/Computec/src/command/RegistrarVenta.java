package command;

import controller.VentaController; 
import modelo.Cliente;
import modelo.Equipo;
import service.Descuento;

public class RegistrarVenta implements Command {

    private final VentaController controller;
    private final Cliente cliente;
    private final Equipo equipo;
    private final Descuento estrategia;

    
    public RegistrarVenta(VentaController controller, Cliente cliente, Equipo equipo, Descuento estrategia) {
        this.controller = controller;
        this.cliente = cliente;
        this.equipo = equipo;
        this.estrategia = estrategia;
    }

    @Override
    public void execute() {
    
        controller.registrarVenta(cliente, equipo, estrategia);
    }
}