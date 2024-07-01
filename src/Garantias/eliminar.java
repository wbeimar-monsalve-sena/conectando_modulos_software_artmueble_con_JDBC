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

public class eliminar {
    public static void main(String[] args) {
        // Conexión
        conexion con = new conexion();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        // ID de la garantía a eliminar
        int id_garantia = 5; // Aquí colocar el ID de la garantía que quieres eliminar

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(eliminar.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            cn = con.getConnection();
            if (cn != null) {
                st = cn.createStatement();

                // Eliminar la garantía de la tabla Garantias
                String sqlDelete = "DELETE FROM Garantias WHERE id_garantia = " + id_garantia;
                int rowsDeleted = st.executeUpdate(sqlDelete);

                if (rowsDeleted > 0) {
                    System.out.println("Garantía eliminada exitosamente.");
                } else {
                    System.out.println("No se encontró la garantía con el ID especificado.");
                }

                // Consulta para mostrar todos los registros actualizados
                String sqlSelect = "SELECT * FROM Garantias";
                rs = st.executeQuery(sqlSelect);

                System.out.println("Datos de la tabla Garantias:");
                while (rs.next()) {
                    System.out.println("ID Garantía: " + rs.getInt("id_garantia"));
                    System.out.println("ID Producto: " + rs.getInt("id_producto"));
                    System.out.println("Fecha de Vencimiento: " + rs.getDate("fecha_vencimiento"));
                    System.out.println("Descripción: " + rs.getString("descripcion"));
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

