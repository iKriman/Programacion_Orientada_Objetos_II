package modelo;

import java.math.BigDecimal;

public class Desktop extends Equipo {

    private int fuente;
    private String factorForma; // ATX, Micro-ATX, etc
                 
    public Desktop(String modelo, String cpu, int discoDuroMB, int ramGB, BigDecimal precio, int fuente, String factorForma) {
        super("Desktop", modelo, cpu, discoDuroMB, ramGB, precio);
        this.fuente = fuente;
        this.factorForma = factorForma;
    }



    public int getFuente() {
        return fuente;
    }

    public void setFuente(int potenciaFuente) {
        this.fuente = potenciaFuente;
    }

    public String getFactorForma() {
        return factorForma;
    }

    public void setFactorForma(String factorForma) {
        this.factorForma = factorForma;
    }

}
