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

public class agregar {
    public static void main(String[] args) {
        // Conexión
        conexion con = new conexion();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        // Datos a insertar
        String nombre = "Ana";
        String apellido = "Perez";
        String email = "ana.perez@gmail.com";
        String telefono = "3105551234";
        String direccion = "Carrera 30 #10-20";
        String ciudad = "Bogotá";
        String pais = "Colombia";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(agregar.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            cn = con.getConnection();
            if (cn != null) {
                st = cn.createStatement();

                // Verificar si el email ya existe
                String checkEmailSQL = "SELECT COUNT(*) FROM Clientes WHERE email = '" + email + "'";
                rs = st.executeQuery(checkEmailSQL);
                rs.next();
                int count = rs.getInt(1);

                if (count > 0) {
                    System.out.println("El email ya existe en la base de datos.");
                } else {
                    // Instrucción SQL para insertar datos
                    String sql = "INSERT INTO Clientes (nombre, apellido, email, telefono, direccion, ciudad, pais) " +
                                 "VALUES ('" + nombre + "', '" + apellido + "', '" + email + "', '" + telefono + "', '" + direccion + "', '" + ciudad + "', '" + pais + "')";
                    st.executeUpdate(sql);
                    System.out.println("Datos insertados correctamente.");
                }

                // Mostrar los datos actualizados de la tabla Clientes
                System.out.println("Datos actuales en la tabla Clientes:");
                String selectSQL = "SELECT * FROM Clientes";
                rs = st.executeQuery(selectSQL);
                while (rs.next()) {
                    System.out.println(rs.getInt("id_cliente") + ": " + rs.getString("nombre") + " " + rs.getString("apellido") + " - " + rs.getString("email") + " - " + rs.getString("telefono") + " - " + rs.getString("direccion") + " - " + rs.getString("ciudad") + " - " + rs.getString("pais"));
                }
            } else {
                System.out.println("No conectado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(agregar.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(agregar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
