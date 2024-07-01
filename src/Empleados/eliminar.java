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

public class eliminar {
    public static void main(String[] args) {
        // Conexión
        conexion con = new conexion();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        // ID del empleado a eliminar
        int id_empleado = 4; // Aquí colocar el ID del empleado que quieres eliminar

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(eliminar.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            cn = con.getConnection();
            if (cn != null) {
                st = cn.createStatement();

                // Eliminar el empleado de la tabla empleados
                String sqlDelete = "DELETE FROM empleados WHERE id_empleado = " + id_empleado;
                int rowsDeleted = st.executeUpdate(sqlDelete);

                if (rowsDeleted > 0) {
                    System.out.println("Empleado eliminado exitosamente.");
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
            Logger.getLogger(eliminar.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(eliminar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

