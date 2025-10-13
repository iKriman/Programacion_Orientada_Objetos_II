package dao;

import database.DatabaseConnection;
import modelo.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void crear(Cliente c) {
        final String sql = "{CALL sp_cliente_insert(?,?,?,?,?,?)}";
        try (CallableStatement cs = DatabaseConnection.get().prepareCall(sql)) {

            cs.setString(1, c.getRut());
            cs.setString(2, c.getNombreCompleto());
            cs.setString(3, c.getDireccion());
            cs.setString(4, c.getComuna());
            cs.setString(5, c.getCorreo());
            cs.setString(6, c.getTelefono());
            cs.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar sp_cliente_insert", e);
        }
    }

    public void eliminar(String rut) {

        try (CallableStatement cs = DatabaseConnection.get().prepareCall("{CALL sp_cliente_delete(?)}")) {
            cs.setString(1, rut);
            cs.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar cliente con RUT: " + rut, e);
        }
    }

    public void actualizar(Cliente c) {

        try (CallableStatement cs = DatabaseConnection.get().prepareCall("{CALL sp_cliente_update(?,?,?,?,?,?)}")) {

            cs.setString(1, c.getRut());
            cs.setString(2, c.getNombreCompleto());
            cs.setString(3, c.getDireccion());
            cs.setString(4, c.getComuna());
            cs.setString(5, c.getCorreo());
            cs.setString(6, c.getTelefono());

            cs.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar cliente con RUT: " + c.getRut(), e);
        }
    }

    // Lista todos los clientes
    public List<Cliente> listar() {
        List<Cliente> out = new ArrayList<>();
        final String sql = "{CALL sp_cliente_listar()}";

        try (Connection conn = DatabaseConnection.get(); CallableStatement cs = conn.prepareCall(sql)) {

            if (cs.execute()) { // Ejecuta y verifica si hay un ResultSet
                try (ResultSet rs = cs.getResultSet()) {
                    while (rs.next()) {
                        Cliente c = new Cliente(
                                rs.getString("rut"),
                                rs.getString("nombre_completo"),
                                rs.getString("direccion"),
                                rs.getString("comuna"),
                                rs.getString("correo"),
                                rs.getString("telefono")
                        );
                        out.add(c);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar clientes: " + e.getMessage());
            throw new RuntimeException("Error en la DB al listar clientes", e);
        }
        return out;
    }

}
