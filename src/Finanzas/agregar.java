/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finanzas;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class agregar {
    public static void main(String[] args) {
        // Datos de las transacciones a agregar
        Object[][] datosTransacciones = {
            { "Ingreso", "2024-06-25", 1000.00, "Venta de productos" },
            { "Gasto", "2024-06-26", 500.00, "Compra de insumos" },
            { "Ingreso", "2024-06-27", 1500.00, "Consultoría" }
        };

        // Conexión
        conexion con = new conexion();
        Connection cn = null;
        PreparedStatement psInsert = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = con.getConnection();

            if (cn != null) {
                // Insertar datos en la tabla Finanzas
                String sqlInsert = "INSERT INTO Finanzas (tipo_transaccion, fecha, cantidad, descripcion) VALUES (?, ?, ?, ?)";
                psInsert = cn.prepareStatement(sqlInsert);

                for (Object[] transaccion : datosTransacciones) {
                    psInsert.setString(1, (String) transaccion[0]); // tipo_transaccion
                    psInsert.setString(2, (String) transaccion[1]); // fecha
                    psInsert.setDouble(3, (Double) transaccion[2]); // cantidad
                    psInsert.setString(4, (String) transaccion[3]); // descripcion

                    // Ejecutar la inserción
                    int rowsInserted = psInsert.executeUpdate();

                    if (rowsInserted > 0) {
                        System.out.println("Transacción agregada correctamente.");
                    } else {
                        System.out.println("No se pudo agregar la transacción.");
                    }
                }

                // Mostrar datos de la tabla después de la inserción
                String sqlSelect = "SELECT * FROM Finanzas";
                st = cn.createStatement();
                rs = st.executeQuery(sqlSelect);

                System.out.println("\nDatos actuales en la tabla Finanzas:");
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id_transaccion")
                            + ", Tipo: " + rs.getString("tipo_transaccion")
                            + ", Fecha: " + rs.getDate("fecha")
                            + ", Cantidad: " + rs.getDouble("cantidad")
                            + ", Descripción: " + rs.getString("descripcion"));
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
                if (st != null) {
                    st.close();
                }
                if (psInsert != null) {
                    psInsert.close();
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

