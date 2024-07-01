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

public class editar {
    public static void main(String[] args) {
        // Conexión
        conexion con = new conexion();
        Connection cn = null;
        PreparedStatement psUpdate = null;
        PreparedStatement psSelect = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = con.getConnection();

            if (cn != null) {
                // ID del proveedor a editar (cambia esto según el proveedor que desees editar)
                int id_proveedor = 1;

                // Consulta preparada para actualizar un proveedor por ID
                String sqlUpdate = "UPDATE Proveedores SET nombre = ?, direccion = ?, telefono = ?, email = ? WHERE id_proveedor = ?";
                psUpdate = cn.prepareStatement(sqlUpdate);
                psUpdate.setString(1, "Maderas Renovadas LTDA"); // Nuevo nombre
                psUpdate.setString(2, "Calle 4 N° 20-15, Buga"); // Nueva dirección
                psUpdate.setString(3, "987654321"); // Nuevo teléfono
                psUpdate.setString(4, "maderenovadas@gmail.com"); // Nuevo email
                psUpdate.setInt(5, id_proveedor); // ID del proveedor a actualizar

                // Ejecutar la actualización
                int rowsUpdated = psUpdate.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Proveedor actualizado correctamente.");

                    // Consulta preparada para seleccionar el proveedor actualizado
                    String sqlSelect = "SELECT * FROM Proveedores WHERE id_proveedor = ?";
                    psSelect = cn.prepareStatement(sqlSelect);
                    psSelect.setInt(1, id_proveedor);

                    // Ejecutar la consulta
                    rs = psSelect.executeQuery();

                    // Mostrar resultado actualizado
                    if (rs.next()) {
                        System.out.println("Datos del proveedor actualizados:");
                        System.out.println("ID: " + rs.getInt("id_proveedor")
                                + ", Nombre: " + rs.getString("nombre")
                                + ", Dirección: " + rs.getString("direccion")
                                + ", Teléfono: " + rs.getString("telefono")
                                + ", Email: " + rs.getString("email"));
                    } else {
                        System.out.println("No se encontró el proveedor con el ID especificado.");
                    }
                } else {
                    System.out.println("No se realizó la actualización.");
                }
            } else {
                System.out.println("No se pudo establecer la conexión.");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(editar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) {
                    rs.close();
                }
                if (psUpdate != null) {
                    psUpdate.close();
                }
                if (psSelect != null) {
                    psSelect.close();
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
