/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemas_seguridad;

import conexion.conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class eliminar {
    public static void main(String[] args) {
        // IDs de los sistemas a eliminar
        int[] idsAEliminar = {8, 9, 10, 11, 12, 13, 14};

        // Conexión a la base de datos
        conexion con = new conexion();
        Connection cn = null;
        Statement st = null;

        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión
            cn = con.getConnection();

            if (cn != null) {
                st = cn.createStatement();

                // Eliminar registros de la tabla Sistemas_Seguridad
                for (int id : idsAEliminar) {
                    String sqlDelete = "DELETE FROM Sistemas_Seguridad WHERE id_sistema = " + id;

                    // Ejecutar la consulta de eliminación
                    int rowsDeleted = st.executeUpdate(sqlDelete);

                    if (rowsDeleted > 0) {
                        System.out.println("Se eliminó correctamente el sistema con ID: " + id);
                    } else {
                        System.out.println("No se encontró el sistema con ID: " + id + " o no se pudo eliminar.");
                    }
                }
            } else {
                System.out.println("No se pudo conectar a la base de datos.");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(eliminar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cerrar recursos
            try {
                if (st != null) {
                    st.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(eliminar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

