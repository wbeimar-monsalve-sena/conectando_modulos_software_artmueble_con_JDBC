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

public class eliminar {
    public static void main(String[] args) {
        // ID del reporte a eliminar
        int id_reporte = 4;

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

                // Consulta SQL para eliminar el reporte
                String sqlDelete = "DELETE FROM Analisis_Reportes WHERE id_reporte = " + id_reporte;

                // Ejecutar la consulta de eliminación
                int rowsDeleted = st.executeUpdate(sqlDelete);

                if (rowsDeleted > 0) {
                    System.out.println("Reporte eliminado correctamente.");
                } else {
                    System.out.println("No se encontró el reporte con ID: " + id_reporte);
                }

                // Mostrar los datos de la tabla Analisis_Reportes después de la eliminación
                String sqlSelect = "SELECT * FROM Analisis_Reportes";
                rs = st.executeQuery(sqlSelect);

                System.out.println("\nDatos de la tabla Analisis_Reportes:");
                while (rs.next()) {
                    int idReporte = rs.getInt("id_reporte");
                    String tipoReporte = rs.getString("tipo_reporte");
                    java.sql.Date fecha = rs.getDate("fecha");
                    String descripcion = rs.getString("descripcion");

                    System.out.println("ID: " + idReporte);
                    System.out.println("Tipo de Reporte: " + tipoReporte);
                    System.out.println("Fecha: " + fecha);
                    System.out.println("Descripción: " + descripcion);
                    System.out.println("-------------------------");
                }
            } else {
                System.out.println("No se pudo conectar a la base de datos.");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(eliminar.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(eliminar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

