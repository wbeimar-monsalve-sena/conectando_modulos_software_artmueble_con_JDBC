/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
    Connection con;

    public conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/base_artmueble", "root", "Luciana.2012");
            System.out.println("Conectado a la base de datos");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("No conectado");
        }
    }

    public Connection getConnection() {
        return con;
    }
}

