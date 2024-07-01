/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proveedores;

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
        PreparedStatement psSelect = null;
        PreparedStatement psDelete = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = con.getConnection();

            if (cn != null) {
                // ID del proveedor a eliminar
                int id_proveedor = 11; // Cambiar por el ID del proveedor a eliminar

                // Mostrar datos del proveedor antes de eliminar
                String sqlSelect = "SELECT * FROM Proveedores WHERE id_proveedor = ?";
                psSelect = cn.prepareStatement(sqlSelect);
                psSelect.setInt(1, id_proveedor);

                rs = psSelect.executeQuery();

                if (rs.next()) {
                    System.out.println("Datos del proveedor antes de eliminar:");
                    System.out.println("ID: " + rs.getInt("id_proveedor")
                            + ", Nombre: " + rs.getString("nombre")
                            + ", Dirección: " + rs.getString("direccion")
                            + ", Teléfono: " + rs.getString("telefono")
                            + ", Email: " + rs.getString("email"));
                } else {
                    System.out.println("No se encontró el proveedor con el ID especificado.");
                }

                // Consulta preparada para eliminar el proveedor por ID
                String sqlDelete = "DELETE FROM Proveedores WHERE id_proveedor = ?";
                psDelete = cn.prepareStatement(sqlDelete);
                psDelete.setInt(1, id_proveedor);

                // Ejecutar la eliminación
                int rowsDeleted = psDelete.executeUpdate();

                if (rowsDeleted > 0) {
                    System.out.println("Proveedor eliminado correctamente.");
                } else {
                    System.out.println("No se encontró el proveedor con el ID especificado.");
                }

                // Mostrar datos del proveedor después de eliminar (si se desea)
                // Aquí podrías agregar una consulta similar a la anterior si deseas mostrar los datos actualizados

            } else {
                System.out.println("No se pudo establecer la conexión.");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(eliminar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) {
                    rs.close();
                }
                if (psSelect != null) {
                    psSelect.close();
                }
                if (psDelete != null) {
                    psDelete.close();
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
