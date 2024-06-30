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

public class eliminar {
    public static void main(String[] args) {
        // Conexión
        conexion con = new conexion();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        // ID del cliente a eliminar
        int id_cliente = 1; // Aquí colocar el ID del cliente que quieres eliminar

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(eliminar.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            cn = con.getConnection();
            if (cn != null) {
                st = cn.createStatement();

                // Desactivar temporalmente la verificación de claves externas
                st.execute("SET FOREIGN_KEY_CHECKS = 0");

                // Eliminar el cliente de la tabla Clientes
                String sqlDelete = "DELETE FROM Clientes WHERE id_cliente = " + id_cliente;
                int rowsDeleted = st.executeUpdate(sqlDelete);

                if (rowsDeleted > 0) {
                    System.out.println("Cliente eliminado exitosamente.");
                } else {
                    System.out.println("No se encontró el cliente con el ID especificado.");
                }

                // Activar la verificación de claves externas nuevamente
                st.execute("SET FOREIGN_KEY_CHECKS = 1");

                // Consultar y mostrar los datos actualizados de la tabla Clientes
                String sqlSelect = "SELECT * FROM Clientes";
                rs = st.executeQuery(sqlSelect);

                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id_cliente"));
                    System.out.println("Nombre: " + rs.getString("nombre"));
                    System.out.println("Apellido: " + rs.getString("apellido"));
                    System.out.println("Email: " + rs.getString("email"));
                    System.out.println("Teléfono: " + rs.getString("telefono"));
                    System.out.println("Dirección: " + rs.getString("direccion"));
                    System.out.println("Ciudad: " + rs.getString("ciudad"));
                    System.out.println("País: " + rs.getString("pais"));
                    System.out.println("------------------------");
                }
            } else {
                System.out.println("No conectado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(eliminar.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(eliminar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
