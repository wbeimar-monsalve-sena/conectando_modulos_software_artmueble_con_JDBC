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

public class editar {
    public static void main(String[] args) {
        // Conexión
        conexion con = new conexion();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        // Datos a editar
        int id_producto = 2; // ID del producto a editar
        String nuevoNombre = "Cocinas Modernas"; // Nuevo nombre
        String nuevaDescripcion = "Cocinas en RH con diseño moderno"; // Nueva descripción
        double nuevoPrecio = 80.00; // Nuevo precio
        String nuevaImagen = "imagen_cocina_moderna.jpg"; // Nueva imagen

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(editar.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            cn = con.getConnection();
            if (cn != null) {
                // Editar el producto en la tabla Catalogo_Productos
                String sqlUpdate = "UPDATE Catalogo_Productos SET nombre = ?, descripcion = ?, precio = ?, imagen = ? WHERE id_producto = ?";
                pst = cn.prepareStatement(sqlUpdate);
                pst.setString(1, nuevoNombre);
                pst.setString(2, nuevaDescripcion);
                pst.setDouble(3, nuevoPrecio);
                pst.setString(4, nuevaImagen);
                pst.setInt(5, id_producto);

                int rowsUpdated = pst.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Producto editado exitosamente.");
                } else {
                    System.out.println("No se encontró el producto con el ID especificado.");
                }

                // Consultar y mostrar los datos actualizados de la tabla Catalogo_Productos
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
            Logger.getLogger(editar.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(editar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

