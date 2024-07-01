/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package garantias;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class consultar {
    public static void main(String[] args) {
        // Conexión
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

                // Consulta para encontrar registros con fecha_vencimiento en el año 2026
                String sqlSelect = "SELECT * FROM Garantias WHERE YEAR(fecha_vencimiento) = 2026";
                rs = st.executeQuery(sqlSelect);

                System.out.println("Registros con fecha de vencimiento en el año 2026:");
                while (rs.next()) {
                    System.out.println("ID Garantía: " + rs.getInt("id_garantia"));
                    System.out.println("ID Producto: " + rs.getInt("id_producto"));
                    System.out.println("Fecha de Vencimiento: " + rs.getDate("fecha_vencimiento"));
                    System.out.println("Descripción: " + rs.getString("descripcion"));
                    System.out.println("-----------------------");
                }
            } else {
                System.out.println("No conectado a la base de datos.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(consultar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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

