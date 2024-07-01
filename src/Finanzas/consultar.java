/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finanzas;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class consultar {
    public static void main(String[] args) {
        // Conexión
        conexion con = new conexion();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = con.getConnection();

            if (cn != null) {
                st = cn.createStatement();

                // Consulta SQL
                String sqlQuery = "SELECT * FROM Finanzas";

                // Ejecutar consulta
                rs = st.executeQuery(sqlQuery);

                // Mostrar resultados en consola
                System.out.println("Datos en la tabla Finanzas:");
                while (rs.next()) {
                    int idTransaccion = rs.getInt("id_transaccion");
                    String tipoTransaccion = rs.getString("tipo_transaccion");
                    String fecha = rs.getString("fecha");
                    double cantidad = rs.getDouble("cantidad");
                    String descripcion = rs.getString("descripcion");

                    System.out.println("ID: " + idTransaccion
                            + ", Tipo: " + tipoTransaccion
                            + ", Fecha: " + fecha
                            + ", Cantidad: " + cantidad
                            + ", Descripción: " + descripcion);
                }

            } else {
                System.out.println("No se pudo establecer la conexión.");
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
