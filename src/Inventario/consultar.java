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

public class consultar {
    public static void main(String[] args) {
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

                // Consulta SQL para seleccionar las cajoneras
                String sqlSelect = "SELECT * FROM Inventario WHERE nombre = 'cajoneras'";

                // Ejecutar la consulta de selección
                rs = st.executeQuery(sqlSelect);

                // Mostrar los resultados en la consola
                System.out.println("Datos de las cajoneras:");
                while (rs.next()) {
                    int idInventario = rs.getInt("id_inventario");
                    String nombre = rs.getString("nombre");
                    String descripcion = rs.getString("descripcion");
                    int cantidadDisponible = rs.getInt("cantidad_disponible");
                    double precioUnitario = rs.getDouble("precio_unitario");
                    String ubicacion = rs.getString("ubicacion");

                    System.out.println("ID Inventario: " + idInventario);
                    System.out.println("Nombre: " + nombre);
                    System.out.println("Descripción: " + descripcion);
                    System.out.println("Cantidad Disponible: " + cantidadDisponible);
                    System.out.println("Precio Unitario: " + precioUnitario);
                    System.out.println("Ubicación: " + ubicacion);
                    System.out.println("---------------------------");
                }
            } else {
                System.out.println("No se pudo conectar a la base de datos.");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(consultar.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(consultar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
