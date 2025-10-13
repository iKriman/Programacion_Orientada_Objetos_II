package computec;

import database.DatabaseConnection;
import javax.swing.JOptionPane;
import view.JFrameComputec;

public class app {

    public static void main(String[] args) {
        if (DatabaseConnection.get() != null) {
            System.out.println("Conectado a la BD");

            
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new JFrameComputec().setVisible(true); 
                }
            });
        } else {
            System.out.println("Error al conectar con la BD");
            
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos. Revise DatabaseConnection.", "Error Crítico", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
}
