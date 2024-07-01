/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empleados;

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
        // Conexión
        conexion con = new conexion();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        // Datos del nuevo empleado
        String nombre = "Carlos Perez";
        String cargo = "Contador";
        double salario = 2200.00;
        String fecha_contratacion = "2024-07-01"; // Formato: YYYY-MM-DD

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(agregar.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            cn = con.getConnection();
            if (cn != null) {
                // Insertar el nuevo empleado en la tabla empleados
                String sqlInsert = "INSERT INTO empleados (nombre, cargo, salario, fecha_contratacion) VALUES (?, ?, ?, ?)";
                pst = cn.prepareStatement(sqlInsert);
                pst.setString(1, nombre);
                pst.setString(2, cargo);
                pst.setDouble(3, salario);
                pst.setString(4, fecha_contratacion);

                int rowsInserted = pst.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("Empleado agregado exitosamente.");
                } else {
                    System.out.println("No se pudo agregar el empleado.");
                }

                // Consultar y mostrar los datos de la tabla empleados
                String sqlSelect = "SELECT * FROM empleados";
                Statement st = cn.createStatement();
                rs = st.executeQuery(sqlSelect);

                System.out.println("Datos de la tabla empleados:");
                while (rs.next()) {
                    System.out.println("ID Empleado: " + rs.getInt("id_empleado"));
                    System.out.println("Nombre: " + rs.getString("nombre"));
                    System.out.println("Cargo: " + rs.getString("cargo"));
                    System.out.println("Salario: " + rs.getDouble("salario"));
                    System.out.println("Fecha de Contratación: " + rs.getDate("fecha_contratacion"));
                    System.out.println("-----------------------");
                }
            } else {
                System.out.println("No conectado a la base de datos.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(agregar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
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

