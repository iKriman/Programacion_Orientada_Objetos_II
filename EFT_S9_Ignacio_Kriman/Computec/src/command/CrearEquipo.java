package command;

import controller.EquipoController;
import modelo.Equipo;

public class CrearEquipo implements Command {

    private final EquipoController controller;
    private final Equipo equipo;

    public CrearEquipo(EquipoController c, Equipo e) {
        this.controller = c;
        this.equipo = e;
    }

    @Override
    public void execute() {
        controller.crear(equipo);
    }
}
