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

public class consultar {
    public static void main(String[] args) {
        // Conexión
        conexion con = new conexion();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(consultar.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            cn = con.getConnection();
            if (cn != null) {
                // Consultar empleados cuyo nombre empieza por 'M'
                String sqlSelect = "SELECT * FROM empleados WHERE nombre LIKE 'M%'";
                st = cn.createStatement();
                rs = st.executeQuery(sqlSelect);

                System.out.println("Empleados cuyo nombre empieza por 'M':");
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
            Logger.getLogger(consultar.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(consultar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

