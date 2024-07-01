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

public class agregar {
    public static void main(String[] args) {
        // Conexión
        conexion con = new conexion();
        Connection cn = null;
        PreparedStatement psInsert = null;
        PreparedStatement psSelect = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = con.getConnection();

            if (cn != null) {
                // Consulta preparada para la inserción
                String sqlInsert = "INSERT INTO Proveedores (nombre, direccion, telefono, email) VALUES (?, ?, ?, ?)";
                psInsert = cn.prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);

                // Datos de los proveedores a agregar
                String[][] proveedores = {
                    {"Maderas LTDA", "Calle 4 N°  20-15, Buga", "123456789", "madegbug@hotmail.com"},
                    {"textlis lacontex", "Ayacucho la candelaria, Medellin", "5117271", "textlacont@gmail.com"},
                    {"Antioqueña de Pinturas", "Av. 80 #44-08, La Floresta, Medellín", "4446399", "atioqueñapintu@hotmail.com"},
                    {"Nuevo Proveedor", "Calle Nueva 123", "987654321", "nuevoproveedor@example.com"}
                };

                for (String[] proveedor : proveedores) {
                    try {
                        // Asignar valores a la consulta preparada de inserción
                        psInsert.setString(1, proveedor[0]);
                        psInsert.setString(2, proveedor[1]);
                        psInsert.setString(3, proveedor[2]);
                        psInsert.setString(4, proveedor[3]);

                        // Ejecutar la consulta de inserción
                        int rowsAffected = psInsert.executeUpdate();

                        if (rowsAffected > 0) {
                            System.out.println("Proveedor '" + proveedor[0] + "' agregado correctamente.");
                        } else {
                            System.out.println("Error al agregar el proveedor '" + proveedor[0] + "'.");
                        }

                        // Obtener el ID generado para el nuevo proveedor
                        rs = psInsert.getGeneratedKeys();
                        if (rs.next()) {
                            int idProveedor = rs.getInt(1);
                            System.out.println("ID generado para el proveedor '" + proveedor[0] + "': " + idProveedor);
                        }
                    } catch (SQLException ex) {
                        // Omitir mensaje si es una excepción de clave duplicada
                        if (!(ex instanceof java.sql.SQLIntegrityConstraintViolationException)) {
                            Logger.getLogger(agregar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                // Consulta para mostrar los proveedores actuales
                String sqlSelect = "SELECT * FROM Proveedores";
                psSelect = cn.prepareStatement(sqlSelect);
                rs = psSelect.executeQuery();

                System.out.println("\nProveedores actuales:");
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id_proveedor")
                            + ", Nombre: " + rs.getString("nombre")
                            + ", Dirección: " + rs.getString("direccion")
                            + ", Teléfono: " + rs.getString("telefono")
                            + ", Email: " + rs.getString("email"));
                }

            } else {
                System.out.println("No se pudo establecer la conexión.");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(agregar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) {
                    rs.close();
                }
                if (psInsert != null) {
                    psInsert.close();
                }
                if (psSelect != null) {
                    psSelect.close();
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
