package command;

import controller.EquipoController;

public class EliminarEquipo implements Command {

    private final EquipoController controller;
    private final int id;

    public EliminarEquipo(EquipoController c, int id) {
        this.controller = c;
        this.id = id;
    }

    public void execute() {
        controller.eliminar(id);
    }
}
