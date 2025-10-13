package modelo;

public class Cliente {

    private int id;
    private String rut;
    private String nombreCompleto;
    private String direccion;
    private String comuna;
    private String correo;
    private String telefono;

    public Cliente(String rut, String nombreCompleto, String direccion,
            String comuna, String correo, String telefono) {
        this.rut = rut;
        this.nombreCompleto = nombreCompleto;
        this.direccion = direccion;
        this.comuna = comuna;
        this.correo = correo;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "RUT: " + rut + " | Nombre: " + nombreCompleto + " | Correo: " + correo;
    }
}
