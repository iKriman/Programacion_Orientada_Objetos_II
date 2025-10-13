package modelo;

import java.math.BigDecimal;

public class Laptop extends Equipo {

    private BigDecimal pantallaPulgadas;
    private boolean esTouch;
    private int puertosUSB;

    public Laptop(String modelo, String cpu, int discoDuroMB, int ramGB, BigDecimal precio, BigDecimal pantallaPulgadas, boolean esTouch, int cantidadPuertosUSB) {
        // Llama al constructor base y establece el tipo
        super("Laptop", modelo, cpu, discoDuroMB, ramGB, precio);
        this.pantallaPulgadas = pantallaPulgadas;
        this.esTouch = esTouch;
        this.puertosUSB = cantidadPuertosUSB;
    }

    public BigDecimal getPantallaPulgadas() {
        return pantallaPulgadas;
    }

    public void setPantallaPulgadas(BigDecimal pantallaPulgadas) {
        this.pantallaPulgadas = pantallaPulgadas;
    }

    public boolean isEsTouch() {
        return esTouch;
    }

    public void setEsTouch(boolean esTouch) {
        this.esTouch = esTouch;
    }

    public int getPuertosUSB() {
        return puertosUSB;
    }

    public void setCantidadPuertosUSB(int cantidadPuertosUSB) {
        this.puertosUSB = cantidadPuertosUSB;
    }




}
