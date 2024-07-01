/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectos;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class eliminar {
    public static void main(String[] args) {
        // Conexión a la base de datos
        conexion con = new conexion();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(eliminar.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            cn = con.getConnection();
            if (cn != null) {
                st = cn.createStatement();

                // ID del proyecto a eliminar
                int id_proyecto = 2;  // Aquí colocar el ID del proyecto que deseas eliminar

                // Instrucción SQL para eliminar el proyecto
                String sqlDelete = "DELETE FROM Proyectos WHERE id_proyecto = " + id_proyecto;
                int rowsDeleted = st.executeUpdate(sqlDelete);

                if (rowsDeleted > 0) {
                    System.out.println("Proyecto eliminado correctamente.");

                    // Consulta para mostrar todos los proyectos restantes
                    String sqlSelect = "SELECT * FROM Proyectos";
                    rs = st.executeQuery(sqlSelect);

                    // Mostrar los datos en la consola
                    System.out.println("Tabla de Proyectos actualizada:");
                    while (rs.next()) {
                        int id = rs.getInt("id_proyecto");
                        String nombre = rs.getString("nombre");
                        String descripcion = rs.getString("descripcion");
                        String fecha_inicio = rs.getString("fecha_inicio");
                        String fecha_fin = rs.getString("fecha_fin");
                        String estado = rs.getString("estado");

                        System.out.println("ID: " + id + ", Nombre: " + nombre + ", Descripción: " + descripcion
                                + ", Fecha Inicio: " + fecha_inicio + ", Fecha Fin: " + fecha_fin + ", Estado: " + estado);
                    }
                } else {
                    System.out.println("No se encontró el proyecto con el ID especificado.");
                }
            } else {
                System.out.println("No se pudo conectar a la base de datos.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(eliminar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cerrar ResultSet, Statement y Connection en el orden inverso
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(eliminar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

