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

public class agregar {
    public static void main(String[] args) {
        // Datos del nuevo producto a agregar
        String nombre = "Sillas";
        String descripcion = "Sillas ergonómicas";
        double precio = 65.00;
        String imagen = "imagen_silla.jpg";

        // Conexión
        conexion con = new conexion();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(agregar.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            cn = con.getConnection();
            if (cn != null) {
                // Insertar el nuevo producto en la tabla Catalogo_Productos
                String sqlInsert = "INSERT INTO Catalogo_Productos (nombre, descripcion, precio, imagen) VALUES (?, ?, ?, ?)";
                pst = cn.prepareStatement(sqlInsert);
                pst.setString(1, nombre);
                pst.setString(2, descripcion);
                pst.setDouble(3, precio);
                pst.setString(4, imagen);

                int rowsInserted = pst.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("Producto agregado exitosamente.");
                } else {
                    System.out.println("No se pudo agregar el producto.");
                }

                // Consultar y mostrar todos los productos después de la inserción
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
            Logger.getLogger(agregar.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(agregar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
