package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection conn;

    private DatabaseConnection() {}

    public static Connection get() {
        try {
            if (conn == null || conn.isClosed()) {
                String url = "jdbc:mysql://localhost:3306/computecdb"; // ajusta tu BD
                String user = "root";
                String pass = "1234";

                conn = DriverManager.getConnection(url, user, pass);
                System.out.println("Conexión establecida a la BD.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar con la BD", e);
        }
        return conn;
    }
}
