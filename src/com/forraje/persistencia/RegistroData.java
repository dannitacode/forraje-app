package com.forraje.persistencia;

import com.forraje.logica.Registro;
import java.sql.*;
/**
 *
 * @author dannita
 */
public class RegistroData {
    
    public void crearRegistro(String fecha, String dia, int semana, String tipo, double monto, String modo, String details){
        String sql = "INSERT INTO Registro (fecha, dia, semana, tipo_registro, monto, modo_pago, detalles) VALUES (?,?,?,?,?,?,?)";
        try(Connection conn = DbConexion.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)){
            if(conn == null) {
                return;
            } else {
                DbConexion.init();
            }
            ps.setString(1, fecha);
            ps.setString(2, dia);
            ps.setInt(3, semana);
            ps.setString(4, tipo);
            ps.setDouble(5, monto);
            ps.setString(6, modo);
            ps.setString(7, details);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("[!] Se creo el registro con exito");
            } else {
                System.out.println("[!] No se pudo crear el registro");
            }
        } catch(SQLException err){
            System.out.println("[!] Ocurrio un error inesperado: " + err.getMessage());
        }
    }
    
}
