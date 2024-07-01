/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finanzas;

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

        // Datos a actualizar
        int id_transaccion = 1; // ID de la transacción a actualizar
        String nuevoTipo = "Egreso";
        double nuevaCantidad = 1200.00;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(editar.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            cn = con.getConnection();
            if (cn != null) {
                // Query SQL para actualizar
                String sqlUpdate = "UPDATE Finanzas SET tipo_transaccion = ?, cantidad = ? WHERE id_transaccion = ?";
                
                // Preparar la declaración
                pst = cn.prepareStatement(sqlUpdate);
                pst.setString(1, nuevoTipo);
                pst.setDouble(2, nuevaCantidad);
                pst.setInt(3, id_transaccion);
                
                // Ejecutar la actualización
                int rowsUpdated = pst.executeUpdate();
                
                if (rowsUpdated > 0) {
                    System.out.println("Transacción financiera actualizada exitosamente.");
                    
                    // Consultar y mostrar los datos actualizados
                    String sqlSelect = "SELECT * FROM Finanzas WHERE id_transaccion = ?";
                    pst = cn.prepareStatement(sqlSelect);
                    pst.setInt(1, id_transaccion);
                    rs = pst.executeQuery();
                    
                    if (rs.next()) {
                        System.out.println("Datos actualizados:");
                        System.out.println("ID Transacción: " + rs.getInt("id_transaccion"));
                        System.out.println("Tipo: " + rs.getString("tipo_transaccion"));
                        System.out.println("Fecha: " + rs.getDate("fecha"));
                        System.out.println("Cantidad: " + rs.getDouble("cantidad"));
                        System.out.println("Descripción: " + rs.getString("descripcion"));
                    } else {
                        System.out.println("No se encontró la transacción con el ID especificado.");
                    }
                } else {
                    System.out.println("No se encontró la transacción con el ID especificado.");
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
