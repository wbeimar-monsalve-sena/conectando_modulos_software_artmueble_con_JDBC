/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package catalogo_productos;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class eliminar {
    public static void main(String[] args) {
        // Conexión
        conexion con = new conexion();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        // ID del producto a eliminar
        int id_producto = 4;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(eliminar.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            cn = con.getConnection();
            if (cn != null) {
                // Eliminar el producto de la tabla Catalogo_Productos
                String sqlDelete = "DELETE FROM Catalogo_Productos WHERE id_producto = ?";
                pst = cn.prepareStatement(sqlDelete);
                pst.setInt(1, id_producto);

                int rowsDeleted = pst.executeUpdate();

                if (rowsDeleted > 0) {
                    System.out.println("Producto eliminado exitosamente.");
                } else {
                    System.out.println("No se encontró el producto con el ID especificado.");
                }

                // Consultar y mostrar los datos restantes de la tabla Catalogo_Productos
                String sqlSelect = "SELECT * FROM Catalogo_Productos";
                pst = cn.prepareStatement(sqlSelect);
                rs = pst.executeQuery();

                System.out.println("Datos de la tabla Catalogo_Productos:");
                while (rs.next()) {
                    System.out.println("ID Producto: " + rs.getInt("id_producto"));
                    System.out.println("Nombre: " + rs.getString("nombre"));
                    System.out.println("Descripción: " + rs.getString("descripcion"));
                    System.out.println("Precio: " + rs.getDouble("precio"));
                    System.out.println("Imagen: " + rs.getString("imagen"));
                    System.out.println("-----------------------");
                }
            } else {
                System.out.println("No conectado a la base de datos.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(eliminar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
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
