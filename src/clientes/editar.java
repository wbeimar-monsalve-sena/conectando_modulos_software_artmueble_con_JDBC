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

public class editar {
    public static void main(String[] args) {
        // Conexión
        conexion con = new conexion();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        // Datos a editar
        int id_cliente = 1; // ID del cliente a editar
        String campoEditar = "nombre"; // Campo a editar (puedes cambiar a "apellido", "email", etc.)
        String nuevoValor = "Luciana Cadavid Actualizada"; // Nuevo valor del campo

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(editar.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            cn = con.getConnection();
            if (cn != null) {
                st = cn.createStatement();

                // Instrucción SQL para actualizar datos
                String sqlUpdate = "UPDATE Clientes SET " + campoEditar + " = '" + nuevoValor + "' WHERE id_cliente = " + id_cliente;
                
                int rowsUpdated = st.executeUpdate(sqlUpdate); // Actualizar datos
                if (rowsUpdated > 0) {
                    System.out.println("Datos actualizados exitosamente.");

                    // Consulta SQL para obtener los datos actualizados
                    String sqlSelect = "SELECT * FROM Clientes WHERE id_cliente = " + id_cliente;
                    rs = st.executeQuery(sqlSelect);

                    // Mostrar los datos actualizados en la consola
                    while (rs.next()) {
                        System.out.println("ID Cliente: " + rs.getInt("id_cliente"));
                        System.out.println("Nombre: " + rs.getString("nombre"));
                        System.out.println("Apellido: " + rs.getString("apellido"));
                        System.out.println("Email: " + rs.getString("email"));
                        System.out.println("Teléfono: " + rs.getString("telefono"));
                        System.out.println("Dirección: " + rs.getString("direccion"));
                        System.out.println("Ciudad: " + rs.getString("ciudad"));
                        System.out.println("País: " + rs.getString("pais"));
                        System.out.println("--------------------------");
                    }
                } else {
                    System.out.println("No se encontró el cliente con el ID especificado.");
                }
            } else {
                System.out.println("No conectado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(editar.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(editar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
