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

public class agregar {
    public static void main(String[] args) {
        // Datos de sistemas de seguridad a agregar
        String[] nombres = {
            "Sistema de Video Vigilancia",
            "Sistema de Control de Acceso",
            "Sistema de Alarmas de Incendio",
            "Sistema de Seguridad Perimetral",
            "Sistema de Gestión de Inventario",
            "Sistema de Control de Producción",
            "Sistema de Protección de Datos"
        };

        String[] descripciones = {
            "Sistema de cámaras de seguridad para monitorear las instalaciones y prevenir robos.",
            "Sistema para controlar y restringir el acceso a áreas sensibles de la fábrica.",
            "Sistema de detección y alarmas contra incendios para proteger las instalaciones y el personal.",
            "Sistema de cercas y sensores para proteger el perímetro de la fábrica.",
            "Sistema para controlar y monitorear el inventario de materiales y productos terminados.",
            "Sistema para supervisar y asegurar el cumplimiento de los estándares de producción.",
            "Sistema para proteger los datos sensibles y la información confidencial de la empresa."
        };

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

                // Agregar los registros a la tabla Sistemas_Seguridad
                for (int i = 0; i < nombres.length; i++) {
                    String sqlInsert = "INSERT INTO Sistemas_Seguridad (nombre, descripcion) VALUES ('" +
                            nombres[i] + "', '" + descripciones[i] + "')";

                    // Ejecutar la consulta de inserción
                    int rowsInserted = st.executeUpdate(sqlInsert);

                    if (rowsInserted > 0) {
                        System.out.println("Registro insertado correctamente para " + nombres[i]);
                    } else {
                        System.out.println("Error al insertar el registro para " + nombres[i]);
                    }
                }
            } else {
                System.out.println("No se pudo conectar a la base de datos.");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(agregar.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(agregar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

