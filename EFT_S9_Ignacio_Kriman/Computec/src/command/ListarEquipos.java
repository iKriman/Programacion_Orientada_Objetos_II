
package command;

import controller.EquipoController;
import modelo.Equipo;
import java.util.List;


public class ListarEquipos implements Command {

    private final EquipoController controller;
    private List<Equipo> resultado;

    public ListarEquipos(EquipoController c) {
        this.controller = c;
    }

  @Override
    public void execute() {
        this.resultado = controller.listar();
    }

    public List<Equipo> getResultado() {
        return resultado;
    }
}
