package com.forraje.persistencia;

import com.forraje.logica.Registro;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author dannita
 */
public class RegistroData {
    
    private Registro r;
    
    public boolean crearRegistro(Registro r){
        String sql = "INSERT INTO Registro (fecha, dia, semana, registro, tipo_registro, monto, modo_pago, detalles) VALUES (?,?,?,?,?,?,?,?)";
        try(Connection conn = DbConexion.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            if(conn == null) return false;
            ps.setString(1, r.getFecha());
            ps.setString(2, r.getDia());
            ps.setInt(3, r.getSemana());
            ps.setString (4, r.getRegistro());
            ps.setString(5, r.getTipoRegistro());
            ps.setDouble(6, r.getMonto());
            ps.setString(7, r.getModoDePago());
            ps.setString(8, r.getDetalles());
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
    
    public List<Registro> listarRegistros() {
        List<Registro> listR = new ArrayList<>();
        String sql = "SELECT * FROM Registro";
        try(Connection conn = DbConexion.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            if(conn == null) return null;
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String fechaStr = rs.getString("fecha");
                LocalDate fechaParsed = LocalDate.parse(fechaStr);
                String dia = rs.getString("dia");
                int semana = rs.getInt("semana");
                String registro = rs.getString("registro");
                String tipo = rs.getString("tipo_registro");
                double monto = rs.getDouble("monto");
                String modo = rs.getString("modo_pago");
                String detalles = rs.getString("detalles");
                r = new Registro(fechaParsed, dia, semana, registro, tipo, monto, modo, detalles);
                r.setId(rs.getInt("id"));
                listR.add(r);
            }
        } catch(SQLException err){
            System.out.println("[!] Ocurrio un error inesperado: " + err.getMessage());
        }
        return listR;
    }
}
