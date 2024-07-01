/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedidos_ventas;

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
        // Datos del nuevo pedido
        int id_cliente = 2;  // Aquí colocar el ID del cliente asociado al pedido
        String fecha_pedido = "2024-07-02";  // Formato: "yyyy-MM-dd"
        double total_pedido = 180.75;
        String estado = "pendiente";  // Estado del pedido

        // Formateo de la fecha para MySQL (opcional, si se necesita)
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate;
        java.sql.Date sqlDate = null;
        try {
            parsedDate = formatter.parse(fecha_pedido);
            sqlDate = new java.sql.Date(parsedDate.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(agregar.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Conexión
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

                // Consulta SQL para insertar un nuevo pedido
                String sqlInsert = "INSERT INTO Pedidos_Ventas (id_cliente, fecha_pedido, total_pedido, estado) "
                                 + "VALUES (" + id_cliente + ", '" + sqlDate + "', " + total_pedido + ", '" + estado + "')";

                // Ejecutar la consulta de inserción
                int rowsInserted = st.executeUpdate(sqlInsert, Statement.RETURN_GENERATED_KEYS);

                if (rowsInserted > 0) {
                    System.out.println("Nuevo pedido agregado correctamente.");

                    // Obtener el ID generado automáticamente
                    rs = st.getGeneratedKeys();
                    if (rs.next()) {
                        int id_pedido = rs.getInt(1);

                        // Consulta SQL para obtener todos los pedidos
                        String sqlSelect = "SELECT * FROM Pedidos_Ventas";

                        // Ejecutar la consulta de selección
                        rs = st.executeQuery(sqlSelect);

                        // Mostrar los resultados en la consola
                        System.out.println("Lista de Pedidos:");
                        while (rs.next()) {
                            System.out.println("ID Pedido: " + rs.getInt("id_pedido"));
                            System.out.println("ID Cliente: " + rs.getInt("id_cliente"));
                            System.out.println("Fecha Pedido: " + rs.getDate("fecha_pedido"));
                            System.out.println("Total Pedido: " + rs.getDouble("total_pedido"));
                            System.out.println("Estado: " + rs.getString("estado"));
                            System.out.println("---------------------------");
                        }
                    }
                } else {
                    System.out.println("No se pudo agregar el nuevo pedido.");
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
