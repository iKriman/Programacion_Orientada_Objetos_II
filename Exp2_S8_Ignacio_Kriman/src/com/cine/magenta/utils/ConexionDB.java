package com.cine.magenta.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 * Clase simple para conectar a MySQL
 */
public class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/Cine_DB";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "1234";

   
    public static Connection conectar() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("Conexión exitosa");

        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al conectar: " + e.getMessage());
        }
        return conexion;
    }


    public static void cerrar(Connection conexion) {
        try {
            if (conexion != null) {
                conexion.close();
                System.out.println("Conexión cerrada");
            }
        } catch (Exception e) {
            System.out.println("Error al cerrar: " + e.getMessage());
        }
    }
}