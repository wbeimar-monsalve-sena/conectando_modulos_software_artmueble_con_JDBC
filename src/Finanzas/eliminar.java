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

public class eliminar {
    public static void main(String[] args) {
        // Conexión
        conexion con = new conexion();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        // ID de la transacción a eliminar
        int id_transaccion = 1; // Aquí colocar el ID de la transacción que deseas eliminar

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(eliminar.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            cn = con.getConnection();
            if (cn != null) {
                // Consultar los datos antes de eliminar
                String sqlSelectBefore = "SELECT * FROM Finanzas WHERE id_transaccion = ?";
                pst = cn.prepareStatement(sqlSelectBefore);
                pst.setInt(1, id_transaccion);
                rs = pst.executeQuery();

                if (rs.next()) {
                    System.out.println("Datos antes de eliminar:");
                    System.out.println("ID Transacción: " + rs.getInt("id_transaccion"));
                    System.out.println("Tipo: " + rs.getString("tipo_transaccion"));
                    System.out.println("Fecha: " + rs.getDate("fecha"));
                    System.out.println("Cantidad: " + rs.getDouble("cantidad"));
                    System.out.println("Descripción: " + rs.getString("descripcion"));
                } else {
                    System.out.println("No se encontró la transacción con el ID especificado.");
                    return;
                }

                // Eliminar la transacción
                String sqlDelete = "DELETE FROM Finanzas WHERE id_transaccion = ?";
                pst = cn.prepareStatement(sqlDelete);
                pst.setInt(1, id_transaccion);
                int rowsDeleted = pst.executeUpdate();

                if (rowsDeleted > 0) {
                    System.out.println("Transacción eliminada exitosamente.");

                    // Consultar y mostrar los datos después de la eliminación
                    String sqlSelectAfter = "SELECT * FROM Finanzas WHERE id_transaccion = ?";
                    pst = cn.prepareStatement(sqlSelectAfter);
                    pst.setInt(1, id_transaccion);
                    rs = pst.executeQuery();

                    if (rs.next()) {
                        System.out.println("Datos después de eliminar:");
                        System.out.println("ID Transacción: " + rs.getInt("id_transaccion"));
                        System.out.println("Tipo: " + rs.getString("tipo_transaccion"));
                        System.out.println("Fecha: " + rs.getDate("fecha"));
                        System.out.println("Cantidad: " + rs.getDouble("cantidad"));
                        System.out.println("Descripción: " + rs.getString("descripcion"));
                    } else {
                        System.out.println("La transacción con ID " + id_transaccion + " ha sido eliminada.");
                    }
                } else {
                    System.out.println("No se encontró la transacción con el ID especificado.");
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

