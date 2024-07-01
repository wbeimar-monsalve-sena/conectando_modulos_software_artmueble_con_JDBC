/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empleados;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class editar {
    public static void main(String[] args) {
        // Conexión
        conexion con = new conexion();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        // Datos para editar
        int id_empleado = 2; // ID del empleado a editar
        String nuevoCargo = "Jefe de Asistencia";
        double nuevoSalario = 2200.00;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(editar.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            cn = con.getConnection();
            if (cn != null) {
                st = cn.createStatement();

                // Editar el empleado
                String sqlUpdate = "UPDATE empleados SET cargo = '" + nuevoCargo + "', salario = " + nuevoSalario + " WHERE id_empleado = " + id_empleado;
                int rowsUpdated = st.executeUpdate(sqlUpdate);

                if (rowsUpdated > 0) {
                    System.out.println("Empleado actualizado exitosamente.");
                } else {
                    System.out.println("No se encontró el empleado con el ID especificado.");
                }

                // Consultar y mostrar todos los empleados
                String sqlSelect = "SELECT * FROM empleados";
                rs = st.executeQuery(sqlSelect);

                System.out.println("Datos actualizados de la tabla empleados:");
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
            Logger.getLogger(editar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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

