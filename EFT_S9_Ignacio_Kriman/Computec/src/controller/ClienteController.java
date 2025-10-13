package controller;

import dao.ClienteDAO;
import modelo.Cliente;
import java.util.List;
import java.util.ArrayList;

public class ClienteController {

    private final ClienteDAO dao = new ClienteDAO();

    public void crear(Cliente c) {

        dao.crear(c);
    }

    public List<Cliente> listar() {

        try {
            return dao.listar();
        } catch (RuntimeException e) {
            System.err.println("Error al listar clientes: " + e.getMessage());

            return new ArrayList<>();
        }
    }

    public void actualizar(Cliente c) {
        dao.actualizar(c);
    }

    public void eliminar(String rut) {
        dao.eliminar(rut);
    }
}
