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

public class agregar {
    public static void main(String[] args) {
        // Datos del nuevo sistema de integración
        String nombre_sistema = "Sistema de Recursos Humanos";
        String descripcion = "Integración con el sistema de recursos humanos de la empresa";

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

                // Consulta SQL para insertar un nuevo sistema de integración
                String sqlInsert = "INSERT INTO Integracion_Otros_Sistemas (nombre_sistema, descripcion) " +
                                   "VALUES ('" + nombre_sistema + "', '" + descripcion + "')";

                // Ejecutar la consulta de inserción
                int rowsInserted = st.executeUpdate(sqlInsert, Statement.RETURN_GENERATED_KEYS);

                if (rowsInserted > 0) {
                    System.out.println("Nuevo sistema de integración agregado correctamente.");

                    // Obtener el ID generado automáticamente
                    rs = st.getGeneratedKeys();
                    if (rs.next()) {
                        int id_integracion = rs.getInt(1);

                        // Consulta SQL para obtener todos los sistemas de integración
                        String sqlSelect = "SELECT * FROM Integracion_Otros_Sistemas";

                        // Ejecutar la consulta de selección
                        rs = st.executeQuery(sqlSelect);

                        // Mostrar los resultados en la consola
                        System.out.println("\nDatos de la tabla Integracion_Otros_Sistemas:");
                        while (rs.next()) {
                            System.out.println("ID Integración: " + rs.getInt("id_integracion"));
                            System.out.println("Nombre del Sistema: " + rs.getString("nombre_sistema"));
                            System.out.println("Descripción: " + rs.getString("descripcion"));
                            System.out.println("---------------------------");
                        }
                    }
                } else {
                    System.out.println("No se pudo agregar el nuevo sistema de integración.");
                }
            } else {
                System.out.println("No se pudo conectar a la base de datos.");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(agregar.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(agregar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

