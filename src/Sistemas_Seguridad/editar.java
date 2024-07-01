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

public class editar {
    public static void main(String[] args) {
        // Datos del sistema a actualizar
        String sistemaActualizar = "Sistema de Video Vigilancia";
        String nuevaDescripcion = "Sistema mejorado de cámaras para monitoreo y prevención de robos.";

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

                // Consulta SQL para actualizar la descripción del sistema de seguridad
                String sqlUpdate = "UPDATE Sistemas_Seguridad SET descripcion = '" + nuevaDescripcion + "' " +
                                   "WHERE nombre = '" + sistemaActualizar + "'";

                // Ejecutar la consulta de actualización
                int rowsUpdated = st.executeUpdate(sqlUpdate);

                if (rowsUpdated > 0) {
                    System.out.println("Se actualizó correctamente la descripción del sistema: " + sistemaActualizar);
                } else {
                    System.out.println("No se encontró el sistema: " + sistemaActualizar + " o no se pudo actualizar.");
                }
            } else {
                System.out.println("No se pudo conectar a la base de datos.");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(editar.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(editar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

