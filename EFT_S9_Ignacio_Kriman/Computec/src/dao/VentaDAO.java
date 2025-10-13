package dao;

import database.DatabaseConnection;
import modelo.Venta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VentaDAO {

    public void crear(Venta v) {

        String sql = "INSERT INTO ventas (rut_cliente, id_equipo, fecha_hora, precio_base, precio_final, descuento_aplicado) VALUES (?, ?, ?, ?, ?, ?)";

        Connection conn = DatabaseConnection.get();

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, v.getRutCliente());
            ps.setInt(2, v.getIdEquipo());
            
            ps.setTimestamp(3, Timestamp.valueOf(v.getFecha()));

           
            ps.setBigDecimal(4, v.getPrecioBase());
            ps.setBigDecimal(5, v.getPrecioFinal());

            ps.setString(6, v.getDescuentoAplicado());

            ps.executeUpdate();

           
        } catch (SQLException ex) {
            
            throw new RuntimeException("Error al registrar venta: " + ex.getMessage(), ex);
        }
    }

    public List<Venta> listar() {
        List<Venta> lista = new ArrayList<>();

        
        String sql = "SELECT * FROM ventas ORDER BY fecha_hora DESC";

        
        Connection conn = DatabaseConnection.get();

        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_venta");
                String rut = rs.getString("rut_cliente");
                int idEquipo = rs.getInt("id_equipo");

                
                LocalDateTime fecha = rs.getTimestamp("fecha_hora").toLocalDateTime();

                BigDecimal base = rs.getBigDecimal("precio_base");
                BigDecimal finalPrecio = rs.getBigDecimal("precio_final");
                String descuentoAplicado = rs.getString("descuento_aplicado");

                lista.add(new Venta(id, rut, idEquipo, fecha, base, finalPrecio, descuentoAplicado));
            }

        } catch (SQLException ex) {
            
            throw new RuntimeException("Error al listar ventas: Falló la consulta SQL o el mapeo. Mensaje: " + ex.getMessage(), ex);
        }
        return lista;
    }

}
