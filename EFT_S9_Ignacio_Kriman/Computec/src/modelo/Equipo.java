package modelo;

import java.math.BigDecimal;

public class Equipo {
    private int idEquipo; 
    private String tipo; 
    private String modelo;
    private String cpu;
    private int discoDuroMB;
    private int ramGB;
    private BigDecimal precio; 

 
    public Equipo(String tipo, String modelo, String cpu, int discoDuroMB, int ramGB, BigDecimal precio) {
        this.tipo = tipo;
        this.modelo = modelo;
        this.cpu = cpu;
        this.discoDuroMB = discoDuroMB;
        this.ramGB = ramGB;
        this.precio = precio;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public int getDiscoDuroMB() {
        return discoDuroMB;
    }

    public void setDiscoDuroMB(int discoDuroMB) {
        this.discoDuroMB = discoDuroMB;
    }

    public int getRamGB() {
        return ramGB;
    }

    public void setRamGB(int ramGB) {
        this.ramGB = ramGB;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }


}
