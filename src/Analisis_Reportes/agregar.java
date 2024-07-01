/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analisis_reportes;

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
        // Datos del nuevo reporte
        String tipo_reporte = "Reporte de Recursos Humanos";
        String fecha_reporte = "2024-06-28";  // Formato: "yyyy-MM-dd"
        String descripcion = "Análisis de recursos humanos del mes de junio";

        // Formateo de la fecha para MySQL (opcional, si se necesita)
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate;
        java.sql.Date sqlDate = null;
        try {
            parsedDate = formatter.parse(fecha_reporte);
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

                // Consulta SQL para insertar un nuevo reporte
                String sqlInsert = "INSERT INTO Analisis_Reportes (tipo_reporte, fecha, descripcion) "
                                 + "VALUES ('" + tipo_reporte + "', '" + sqlDate + "', '" + descripcion + "')";

                // Ejecutar la consulta de inserción
                int rowsInserted = st.executeUpdate(sqlInsert, Statement.RETURN_GENERATED_KEYS);

                if (rowsInserted > 0) {
                    System.out.println("Nuevo reporte agregado correctamente.");

                    // Obtener el ID generado automáticamente
                    rs = st.getGeneratedKeys();
                    if (rs.next()) {
                        int id_reporte = rs.getInt(1);

                        // Consulta SQL para obtener todos los reportes
                        String sqlSelect = "SELECT * FROM Analisis_Reportes";

                        // Ejecutar la consulta de selección
                        rs = st.executeQuery(sqlSelect);

                        // Mostrar los resultados en la consola
                        System.out.println("Lista de Reportes:");
                        while (rs.next()) {
                            System.out.println("ID Reporte: " + rs.getInt("id_reporte"));
                            System.out.println("Tipo Reporte: " + rs.getString("tipo_reporte"));
                            System.out.println("Fecha: " + rs.getDate("fecha"));
                            System.out.println("Descripción: " + rs.getString("descripcion"));
                            System.out.println("---------------------------");
                        }
                    }
                } else {
                    System.out.println("No se pudo agregar el nuevo reporte.");
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

