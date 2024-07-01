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

public class consultar {
    public static void main(String[] args) {
        // Conexión a la base de datos
        conexion con = new conexion();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(consultar.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            cn = con.getConnection();
            if (cn != null) {
                st = cn.createStatement();

                // Consultar todos los proyectos
                String sql = "SELECT * FROM Proyectos";
                rs = st.executeQuery(sql);

                // Mostrar los resultados en consola
                System.out.println("Listado de Proyectos:");
                System.out.println("--------------------");
                while (rs.next()) {
                    System.out.printf("ID: %d, Nombre: %s, Descripción: %s, Fecha Inicio: %s, Fecha Fin: %s, Estado: %s%n",
                            rs.getInt("id_proyecto"), rs.getString("nombre"), rs.getString("descripcion"),
                            rs.getDate("fecha_inicio"), rs.getDate("fecha_fin"), rs.getString("estado"));
                }
            } else {
                System.out.println("No se pudo conectar a la base de datos.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(consultar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cerrar ResultSet, Statement y Connection en el orden inverso
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(consultar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
