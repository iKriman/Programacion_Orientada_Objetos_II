package command;

import command.Command;
import controller.ClienteController;
import modelo.Cliente;

public class CrearCliente implements Command {

    private final ClienteController controller;

    private final Cliente cliente;

    public CrearCliente(ClienteController c, Cliente cli) {
        this.controller = c;
        this.cliente = cli;
    }

    @Override
    public void execute() {

        controller.crear(cliente);
    }
}
