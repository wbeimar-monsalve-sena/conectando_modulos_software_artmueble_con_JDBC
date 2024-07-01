/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedidos_ventas;

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
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión
            cn = con.getConnection();

            if (cn != null) {
                st = cn.createStatement();

                // ID del pedido a eliminar (por ejemplo, el pedido con ID 1)
                int id_pedido = 1;

                // Consulta SQL para eliminar el pedido
                String sqlDelete = "DELETE FROM Pedidos_Ventas WHERE id_pedido = " + id_pedido;

                // Ejecutar la eliminación
                int rowsDeleted = st.executeUpdate(sqlDelete);

                if (rowsDeleted > 0) {
                    System.out.println("Pedido eliminado exitosamente.");
                } else {
                    System.out.println("No se encontró el pedido con el ID especificado.");
                }

                // Consulta SQL para obtener todos los pedidos restantes
                String sqlSelect = "SELECT * FROM Pedidos_Ventas";

                // Ejecutar la consulta para obtener los datos actualizados
                rs = st.executeQuery(sqlSelect);

                // Mostrar los resultados en la consola
                System.out.println("Pedidos restantes en la tabla:");
                while (rs.next()) {
                    System.out.println("ID Pedido: " + rs.getInt("id_pedido"));
                    System.out.println("ID Cliente: " + rs.getInt("id_cliente"));
                    System.out.println("Fecha Pedido: " + rs.getDate("fecha_pedido"));
                    System.out.println("Total Pedido: " + rs.getDouble("total_pedido"));
                    System.out.println("Estado: " + rs.getString("estado"));
                    System.out.println("---------------------------");
                }
            } else {
                System.out.println("No se pudo conectar a la base de datos.");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(eliminar.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(eliminar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

