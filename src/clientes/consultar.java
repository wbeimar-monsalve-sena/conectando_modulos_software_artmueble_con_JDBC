/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientes;

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
                // Instrucción SQL para consultar datos
                String sql = "SELECT * FROM Clientes";
                rs = st.executeQuery(sql);

                // Mostrar los datos de la tabla Clientes
                System.out.println("Datos en la tabla Clientes:");
                while (rs.next()) {
                    System.out.println(rs.getInt("id_cliente") + ": " + rs.getString("nombre") + " " + rs.getString("apellido") + " - " + rs.getString("email") + " - " + rs.getString("telefono") + " - " + rs.getString("direccion") + " - " + rs.getString("ciudad") + " - " + rs.getString("pais"));
                }
            } else {
                System.out.println("No conectado");
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

