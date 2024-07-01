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

public class consultar {
    public static void main(String[] args) {
        // Nombre del sistema a consultar
        String nombreSistemaConsultar = "Sistema ERP";

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

                // Consulta SQL para obtener los datos del sistema específico
                String sqlSelect = "SELECT * FROM Integracion_Otros_Sistemas WHERE nombre_sistema = '" + nombreSistemaConsultar + "'";

                // Ejecutar la consulta de selección
                rs = st.executeQuery(sqlSelect);

                // Mostrar los resultados en la consola
                if (rs.next()) {
                    System.out.println("ID Integración: " + rs.getInt("id_integracion"));
                    System.out.println("Nombre del Sistema: " + rs.getString("nombre_sistema"));
                    System.out.println("Descripción: " + rs.getString("descripcion"));
                } else {
                    System.out.println("No se encontró un sistema con el nombre: " + nombreSistemaConsultar);
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

