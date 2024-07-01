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

public class consultar {
    public static void main(String[] args) {
        // Conexión
        conexion con = new conexion();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(consultar.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            cn = con.getConnection();
            if (cn != null) {
                // Consultar los productos cuyo nombre empiece por 'C'
                String sqlSelect = "SELECT * FROM Catalogo_Productos WHERE nombre LIKE 'C%'";
                pst = cn.prepareStatement(sqlSelect);
                rs = pst.executeQuery();

                System.out.println("Datos de la tabla Catalogo_Productos cuyos nombres empiezan por 'C':");
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
            Logger.getLogger(consultar.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(consultar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

