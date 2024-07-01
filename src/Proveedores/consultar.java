/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proveedores;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class consultar {
    public static void main(String[] args) {
        // Conexión
        conexion con = new conexion();
        Connection cn = null;
        PreparedStatement psSelect = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = con.getConnection();

            if (cn != null) {
                // Consulta preparada para seleccionar proveedores cuya dirección comienza con "Calle"
                String sqlSelect = "SELECT * FROM Proveedores WHERE direccion LIKE ?";
                psSelect = cn.prepareStatement(sqlSelect);
                psSelect.setString(1, "Calle%");

                // Ejecutar la consulta
                rs = psSelect.executeQuery();

                // Mostrar resultados
                System.out.println("Proveedores encontrados con dirección comenzando por 'Calle':");
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id_proveedor")
                            + ", Nombre: " + rs.getString("nombre")
                            + ", Dirección: " + rs.getString("direccion")
                            + ", Teléfono: " + rs.getString("telefono")
                            + ", Email: " + rs.getString("email"));
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
                if (psSelect != null) {
                    psSelect.close();
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
