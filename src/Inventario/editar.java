/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventario;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class editar {
    public static void main(String[] args) {
        // Datos del inventario a editar
        int idInventario = 1;  // ID del inventario a editar
        String nuevoNombre = "Cajoneras Modificadas";
        String nuevaDescripcion = "Colores variados";
        int nuevaCantidad = 120;
        double nuevoPrecio = 55.00;
        String nuevaUbicacion = "Almacén 1";

        // Conexión a la base de datos
        conexion con = new conexion();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión
            cn = con.getConnection();

            if (cn != null) {
                st = cn.createStatement();

                // Consulta SQL para actualizar el inventario
                String sqlUpdate = "UPDATE Inventario SET "
                                 + "nombre = '" + nuevoNombre + "', "
                                 + "descripcion = '" + nuevaDescripcion + "', "
                                 + "cantidad_disponible = " + nuevaCantidad + ", "
                                 + "precio_unitario = " + nuevoPrecio + ", "
                                 + "ubicacion = '" + nuevaUbicacion + "' "
                                 + "WHERE id_inventario = " + idInventario;

                // Ejecutar la consulta de actualización
                int rowsUpdated = st.executeUpdate(sqlUpdate);

                if (rowsUpdated > 0) {
                    System.out.println("Datos de inventario actualizados correctamente.");
                } else {
                    System.out.println("No se encontró el inventario con ID " + idInventario);
                }

                // Mostrar los datos actualizados
                String sqlSelect = "SELECT * FROM Inventario WHERE id_inventario = " + idInventario;
                rs = st.executeQuery(sqlSelect);

                while (rs.next()) {
                    int id = rs.getInt("id_inventario");
                    String nombre = rs.getString("nombre");
                    String descripcion = rs.getString("descripcion");
                    int cantidad = rs.getInt("cantidad_disponible");
                    double precio = rs.getDouble("precio_unitario");
                    String ubicacion = rs.getString("ubicacion");

                    System.out.println("ID: " + id);
                    System.out.println("Nombre: " + nombre);
                    System.out.println("Descripción: " + descripcion);
                    System.out.println("Cantidad Disponible: " + cantidad);
                    System.out.println("Precio Unitario: " + precio);
                    System.out.println("Ubicación: " + ubicacion);
                    System.out.println("---------------------------");
                }
            } else {
                System.out.println("No se pudo conectar a la base de datos.");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(editar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cerrar recursos
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
                Logger.getLogger(editar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

