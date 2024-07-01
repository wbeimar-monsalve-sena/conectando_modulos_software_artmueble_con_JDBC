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

public class agregar {
    public static void main(String[] args) {
        // Conexión
        conexion con = new conexion();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        // Datos de la nueva garantía
        int id_producto = 1; // Cambiar a un ID de producto existente
        String fecha_vencimiento = "2026-01-01"; // Fecha de vencimiento de la garantía
        String descripcion = "Garantía de 1 año para el nuevo producto"; // Descripción de la garantía

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(agregar.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            cn = con.getConnection();
            if (cn != null) {
                st = cn.createStatement();

                // Verificar si el id_producto existe en la tabla Catalogo_Productos
                String sqlCheck = "SELECT COUNT(*) AS count FROM Catalogo_Productos WHERE id_producto = " + id_producto;
                rs = st.executeQuery(sqlCheck);
                rs.next();
                int count = rs.getInt("count");

                if (count == 0) {
                    System.out.println("El id_producto especificado no existe en la tabla Catalogo_Productos.");
                } else {
                    // Insertar nueva garantía
                    String sqlInsert = "INSERT INTO Garantias (id_producto, fecha_vencimiento, descripcion) VALUES ("
                            + id_producto + ", '" + fecha_vencimiento + "', '" + descripcion + "')";
                    int rowsInserted = st.executeUpdate(sqlInsert);

                    if (rowsInserted > 0) {
                        System.out.println("Garantía agregada exitosamente.");
                    } else {
                        System.out.println("No se pudo agregar la garantía.");
                    }

                    // Consultar y mostrar todos los registros de la tabla Garantias
                    String sqlSelect = "SELECT * FROM Garantias";
                    rs = st.executeQuery(sqlSelect);

                    System.out.println("Datos actualizados de la tabla Garantias:");
                    while (rs.next()) {
                        System.out.println("ID Garantía: " + rs.getInt("id_garantia"));
                        System.out.println("ID Producto: " + rs.getInt("id_producto"));
                        System.out.println("Fecha de Vencimiento: " + rs.getDate("fecha_vencimiento"));
                        System.out.println("Descripción: " + rs.getString("descripcion"));
                        System.out.println("-----------------------");
                    }
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
