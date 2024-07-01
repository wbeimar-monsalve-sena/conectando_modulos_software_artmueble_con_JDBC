/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analisis_reportes;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class editar {
    public static void main(String[] args) {
        // ID del reporte a editar
        int id_reporte = 3;
        // Nueva descripción para el reporte
        String nuevaDescripcion = "Estado actualizado del inventario a fin de junio";

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

                // Consulta SQL para actualizar el reporte
                String sqlUpdate = "UPDATE Analisis_Reportes SET descripcion = '" + nuevaDescripcion + "' WHERE id_reporte = " + id_reporte;

                // Ejecutar la consulta de actualización
                int rowsUpdated = st.executeUpdate(sqlUpdate);

                if (rowsUpdated > 0) {
                    System.out.println("Reporte actualizado correctamente.");

                    // Mostrar el reporte actualizado
                    String sqlSelect = "SELECT * FROM Analisis_Reportes WHERE id_reporte = " + id_reporte;
                    rs = st.executeQuery(sqlSelect);
                    if (rs.next()) {
                        System.out.println("ID Reporte: " + rs.getInt("id_reporte"));
                        System.out.println("Tipo Reporte: " + rs.getString("tipo_reporte"));
                        System.out.println("Fecha: " + rs.getDate("fecha"));
                        System.out.println("Descripción: " + rs.getString("descripcion"));
                        System.out.println("---------------------------");
                    }
                } else {
                    System.out.println("No se encontró el reporte con ID: " + id_reporte);
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
