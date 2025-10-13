package command;

import controller.ClienteController;
import modelo.Cliente;
import java.util.List;

public class ListarClientes implements Command {

    private final ClienteController controller;
    private List<Cliente> resultado;

    public ListarClientes(ClienteController c) {
        this.controller = c;
    }

    @Override
    public void execute() {
        this.resultado = controller.listar();
    }

    public List<Cliente> getResultado() {
        return resultado;
    }
}
