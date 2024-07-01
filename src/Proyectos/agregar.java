/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectos;

import conexion.conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class agregar {
    public static void main(String[] args) {
        // Datos del proyecto a agregar
        String nombreProyecto = "proyecto D";
        String descripcion = "Oficinas ejecutivas";
        String fechaInicio = "2024-04-01";
        String fechaFin = "2024-09-30";
        String estado = "en progreso";

        // Conexión a la base de datos
        conexion con = new conexion();
        Connection cn = null;
        Statement st = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(agregar.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            cn = con.getConnection();
            if (cn != null) {
                st = cn.createStatement();

                // Query para insertar el nuevo proyecto
                String sqlInsert = "INSERT INTO Proyectos (nombre, descripcion, fecha_inicio, fecha_fin, estado) VALUES ('"
                        + nombreProyecto + "', '" + descripcion + "', '" + fechaInicio + "', '" + fechaFin + "', '" + estado + "')";

                // Ejecutar la inserción
                int rowsInserted = st.executeUpdate(sqlInsert);
                if (rowsInserted > 0) {
                    System.out.println("Proyecto agregado correctamente.");
                } else {
                    System.out.println("Error al agregar el proyecto.");
                }

                // Mostrar todos los proyectos después de la inserción
                System.out.println("Listado de Proyectos:");
                System.out.println("--------------------");
                String sqlSelect = "SELECT * FROM Proyectos";
                var rs = st.executeQuery(sqlSelect);
                while (rs.next()) {
                    System.out.printf("ID: %d, Nombre: %s, Descripción: %s, Fecha Inicio: %s, Fecha Fin: %s, Estado: %s%n",
                            rs.getInt("id_proyecto"), rs.getString("nombre"), rs.getString("descripcion"),
                            rs.getDate("fecha_inicio"), rs.getDate("fecha_fin"), rs.getString("estado"));
                }

            } else {
                System.out.println("No se pudo conectar a la base de datos.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(agregar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (st != null) st.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(agregar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

