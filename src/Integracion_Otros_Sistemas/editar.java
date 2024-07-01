/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integracion_otros_sistemas;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class editar {
    public static void main(String[] args) {
        // ID del sistema a editar y nueva descripción
        int idSistemaEditar = 2;
        String nuevaDescripcion = "Nueva descripción actualizada para el sistema CRM";

        // Conexión
        conexion con = new conexion();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión
            cn = con.getConnection();

            if (cn != null) {
                st = cn.createStatement();

                // Consulta SQL para actualizar la descripción del sistema
                String sqlUpdate = "UPDATE Integracion_Otros_Sistemas SET descripcion = '" + nuevaDescripcion + "' WHERE id_integracion = " + idSistemaEditar;

                // Ejecutar la consulta de actualización
                int rowsUpdated = st.executeUpdate(sqlUpdate);

                if (rowsUpdated > 0) {
                    System.out.println("Descripción actualizada correctamente para el sistema con ID: " + idSistemaEditar);

                    // Consulta SQL para obtener todos los sistemas después de la actualización
                    String sqlSelect = "SELECT * FROM Integracion_Otros_Sistemas";

                    // Ejecutar la consulta de selección
                    rs = st.executeQuery(sqlSelect);

                    // Mostrar los resultados en la consola
                    System.out.println("Lista de Sistemas de Integración:");
                    while (rs.next()) {
                        System.out.println("ID Integración: " + rs.getInt("id_integracion"));
                        System.out.println("Nombre del Sistema: " + rs.getString("nombre_sistema"));
                        System.out.println("Descripción: " + rs.getString("descripcion"));
                        System.out.println("---------------------------");
                    }
                } else {
                    System.out.println("No se pudo actualizar la descripción del sistema con ID: " + idSistemaEditar);
                }
            } else {
                System.out.println("No se pudo conectar a la base de datos.");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(editar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) {
                    rs.close();
                }
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
