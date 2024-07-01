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

public class consultar {
    public static void main(String[] args) {
        // Tipo de reporte a buscar
        String tipo_reporte = "Reporte de Recursos Humanos";

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

                // Consulta SQL para obtener el reporte por tipo
                String sqlSelect = "SELECT * FROM Analisis_Reportes WHERE tipo_reporte = '" + tipo_reporte + "'";

                // Ejecutar la consulta de selección
                rs = st.executeQuery(sqlSelect);

                // Mostrar los resultados en la consola
                System.out.println("Resultados de la consulta:");
                if (rs.next()) {
                    System.out.println("ID Reporte: " + rs.getInt("id_reporte"));
                    System.out.println("Tipo Reporte: " + rs.getString("tipo_reporte"));
                    System.out.println("Fecha: " + rs.getDate("fecha"));
                    System.out.println("Descripción: " + rs.getString("descripcion"));
                    System.out.println("---------------------------");
                } else {
                    System.out.println("No se encontraron reportes con el tipo: " + tipo_reporte);
                }
            } else {
                System.out.println("No se pudo conectar a la base de datos.");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(consultar.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(consultar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

