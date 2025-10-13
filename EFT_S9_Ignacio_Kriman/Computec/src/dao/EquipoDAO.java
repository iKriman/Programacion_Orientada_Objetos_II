package dao;

import database.DatabaseConnection;
import java.math.BigDecimal;
import modelo.Equipo;
import modelo.Desktop;
import modelo.Laptop;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipoDAO {

    public void crear(Equipo e) {
        final String sql = "{CALL sp_equipo_insert(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

      
        Connection conn = DatabaseConnection.get();
        try (CallableStatement cs = conn.prepareCall(sql)) { 

            String tipo = (e instanceof Desktop) ? "Desktop" : "Laptop";

            cs.setString(1, tipo);
            cs.setString(2, e.getModelo());
            cs.setString(3, e.getCpu());
            cs.setInt(4, e.getDiscoDuroMB());
            cs.setInt(5, e.getRamGB());
            cs.setBigDecimal(6, e.getPrecio());

            if (e instanceof Desktop) {
                Desktop d = (Desktop) e;
                cs.setInt(7, d.getFuente());
                cs.setString(8, d.getFactorForma());
                cs.setNull(9, Types.DECIMAL);
                cs.setNull(10, Types.BOOLEAN);
                cs.setNull(11, Types.INTEGER);
            } else {
                Laptop l = (Laptop) e;
                cs.setNull(7, Types.INTEGER);
                cs.setNull(8, Types.VARCHAR);
                cs.setBigDecimal(9, l.getPantallaPulgadas());
                cs.setBoolean(10, l.isEsTouch());
                cs.setInt(11, l.getPuertosUSB());
            }
            cs.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Error al crear equipo", ex);
        }
    }

    public void eliminar(int id) {
        
        final String sql = "{CALL sp_equipo_delete(?)}";
        
        
        Connection conn = DatabaseConnection.get();
        
        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            cs.execute();
            
        } catch (SQLException ex) {
            
            throw new RuntimeException("Error al eliminar equipo: " + ex.getMessage(), ex);
        }
    }


  public List<Equipo> listar() {
        List<Equipo> out = new ArrayList<>();
        
        final String sql = "SELECT * FROM vw_equipos_detallados"; 

        Connection conn = DatabaseConnection.get(); 
        
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                
               
                String tipo = rs.getString("tipo");
                String modelo = rs.getString("modelo");
                String cpu = rs.getString("cpu");
                int disco = rs.getInt("disco_duro_mb");
                int ram = rs.getInt("ram_gb");
                BigDecimal precio = rs.getBigDecimal("precio");
                int id = rs.getInt("id_equipo");

                Equipo e = null;
                
               
                if ("Desktop".equalsIgnoreCase(tipo)) {
                    // Ahora esta columna EXISTE en la vista
                    int fuente = rs.getInt("potencia_fuente"); 
                    String factor = rs.getString("factor_forma");
                    e = new Desktop(modelo, cpu, disco, ram, precio, fuente, factor);
                } else if ("Laptop".equalsIgnoreCase(tipo)) {
                    BigDecimal pulgadas = rs.getBigDecimal("pantalla_pulgadas");
                    boolean touch = rs.getBoolean("es_touch");
                    // Usamos el alias 'puertos_usb' de la vista
                    int usb = rs.getInt("puertos_usb"); 
                    e = new Laptop(modelo, cpu, disco, ram, precio, pulgadas, touch, usb);
                }

                if (e != null) {
                    e.setIdEquipo(id);
                    out.add(e);
                }
            }

        } catch (SQLException ex) {
           
            throw new RuntimeException("Error al listar equipos: Fallo de mapeo de datos o BD. Mensaje: " + ex.getMessage(), ex);
        }

        return out;
    }
}