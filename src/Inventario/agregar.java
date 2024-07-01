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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class agregar {
    public static void main(String[] args) {
        // Datos del nuevo inventario
        String[] nombres = {"cajoneras", "Closets", "cetros de entretenimiento"};
        String[] descripciones = {"colores planos", "de 3 cuerpos modulares", "Naturales en teka"};
        int[] cantidadesDisponibles = {100, 150, 200};
        double[] preciosUnitarios = {50.00, 75.00, 100.00};
        String[] ubicaciones = {"almacen 1", "Almacén 2", "Almacén 3"};

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

                // Agregar los registros a la tabla Inventario
                for (int i = 0; i < nombres.length; i++) {
                    String sqlInsert = "INSERT INTO Inventario (nombre, descripcion, cantidad_disponible, precio_unitario, ubicacion) "
                                     + "VALUES ('" + nombres[i] + "', '" + descripciones[i] + "', " + cantidadesDisponibles[i] + ", "
                                     + preciosUnitarios[i] + ", '" + ubicaciones[i] + "')";

                    // Ejecutar la consulta de inserción
                    int rowsInserted = st.executeUpdate(sqlInsert, Statement.RETURN_GENERATED_KEYS);

                    if (rowsInserted > 0) {
                        System.out.println("Registro insertado correctamente para " + nombres[i]);

                        // Mostrar los datos insertados
                        rs = st.getGeneratedKeys();
                        if (rs.next()) {
                            int idInventario = rs.getInt(1);
                            System.out.println("ID Inventario: " + idInventario);
                            System.out.println("Nombre: " + nombres[i]);
                            System.out.println("Descripción: " + descripciones[i]);
                            System.out.println("Cantidad Disponible: " + cantidadesDisponibles[i]);
                            System.out.println("Precio Unitario: " + preciosUnitarios[i]);
                            System.out.println("Ubicación: " + ubicaciones[i]);
                            System.out.println("---------------------------");
                        }
                    } else {
                        System.out.println("Error al insertar el registro para " + nombres[i]);
                    }
                }

                // Mostrar los datos de la tabla Inventario después de la inserción
                String sqlSelect = "SELECT * FROM Inventario";

                // Ejecutar la consulta de selección
                rs = st.executeQuery(sqlSelect);

                // Mostrar los resultados en la consola
                System.out.println("Datos de la tabla Inventario:");
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
            Logger.getLogger(agregar.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(agregar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
