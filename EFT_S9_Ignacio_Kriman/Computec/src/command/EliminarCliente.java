package command;

import controller.ClienteController;

public class EliminarCliente implements Command {

    private final ClienteController manager;
    private final String rut; 

    public EliminarCliente(ClienteController m, String rut) {
        this.manager = m;
        this.rut = rut;
    }

    public void execute() {
        manager.eliminar(rut);
    }
}
