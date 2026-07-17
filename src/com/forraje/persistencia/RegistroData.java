package com.forraje.persistencia;

import com.forraje.logica.Registro;
import java.sql.*;
/**
 *
 * @author dannita
 */
public class RegistroData {
    
    public boolean crearRegistro(Registro r){
        String sql = "INSERT INTO Registro (fecha, dia, semana, tipo_registro, monto, modo_pago, detalles) VALUES (?,?,?,?,?,?,?)";
        try(Connection conn = DbConexion.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            if(conn == null) return false;
            ps.setString(1, r.getFecha());
            ps.setString(2, r.getDia());
            ps.setInt(3, r.getSemana());
            ps.setString(4, r.getTipo());
            ps.setDouble(5, r.getMonto());
            ps.setString(6, r.getModoDePago());
            ps.setString(7, r.getDetalles());
            int filas = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (filas > 0) {
                System.out.println("[!] Se creo el registro con exito");
            } else {
                System.out.println("[!] No se pudo crear el registro");
            }
            if (rs.next()) {
                r.setId(rs.getInt(1));
            } else {
                System.out.println("[!] No se pudo obtener el id del registro");
            }
        } catch(SQLException err){
            System.out.println("[!] Ocurrio un error inesperado: " + err.getMessage());
        }
        return true;
    }
    
}
