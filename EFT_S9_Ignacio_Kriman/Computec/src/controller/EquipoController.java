package controller;

import dao.EquipoDAO;
import java.util.List;
import modelo.Equipo;

public class EquipoController {

    private final EquipoDAO dao = new EquipoDAO();

    public void crear(Equipo e) {
        dao.crear(e);
    }

    public List<Equipo> listar() {
        return dao.listar();
    }

    public void eliminar(int id) {
        dao.eliminar(id);
    }

}
