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

    public boolean crearRegistro(Registro r) {
        String sql = "INSERT INTO Registro (fecha, dia, semana, registro, tipo_registro, monto, modo_pago, detalles) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection conn = DbConexion.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            if (conn == null) {
                return false;
            }
            ps.setString(1, r.getFecha());
            ps.setString(2, r.getDia());
            ps.setInt(3, r.getSemana());
            ps.setString(4, r.getRegistro());
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
        } catch (SQLException err) {
            System.out.println("[!] Ocurrio un error inesperado: " + err.getMessage());
        }
        return true;
    }

    public List<Registro> listarRegistros() {
        List<Registro> listR = new ArrayList<>();
        String sql = "SELECT * FROM Registro";
        try (Connection conn = DbConexion.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            if (conn == null) {
                return null;
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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
        } catch (SQLException err) {
            System.out.println("[!] Ocurrio un error inesperado: " + err.getMessage());
        }
        return listR;
    }

    public boolean eliminarRegistro(int id) {
        String sql = "DELETE FROM Registro WHERE id = ?";
        try (Connection conn = DbConexion.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            if (conn == null) {
                return false;
            }
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("[!] Se eliminó el registro con id " + id + " con exito");
            } else {
                System.out.println("[!] No se pudo eliminar el registro con id " + id);
            }
        } catch (SQLException err) {
            System.out.println("[!] Ocurrio un error inesperado: " + err.getMessage());
        }
        return true;
    }

    public boolean actualizarRegistro(int id, Registro r) {
        String sql = "UPDATE Registro SET fecha=?, dia=?, semana=?, registro=?, tipo_registro=?, monto=?, modo_pago=?, detalles=? WHERE id=?";
        try (Connection conn = DbConexion.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            if (conn == null) {
                return false;
            }
            ps.setString(1, r.getFecha());
            ps.setString(2, r.getDia());
            ps.setInt(3, r.getSemana());
            ps.setString(4, r.getRegistro());
            ps.setString(5, r.getTipoRegistro());
            ps.setDouble(6, r.getMonto());
            ps.setString(7, r.getModoDePago());
            ps.setString(8, r.getDetalles());
            ps.setInt(9, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("[!] Se actualizó el registro con exito");
            } else {
                System.out.println("[!] No se pudo actualizar el registro con el id " + r.getId());
            }
        } catch (SQLException err) {
            System.out.println("[!] Ocurrio un error inesperado: " + err.getMessage());
        }
        return true;
    }
    
    public Registro buscarRegistro(int id) {
        String sql = "SELECT * FROM Registro WHERE id = ?";
        try(Connection conn = DbConexion.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            if (conn == null) {
                return null;
            }
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                r.setId(rs.getInt("id"));
                String fechaStr = rs.getString("fecha");
                LocalDate fechaParsed = LocalDate.parse(fechaStr);
                r.setFecha(fechaParsed);
                r.setDia(rs.getString("dia"));
                r.setSemana(rs.getInt("semana"));
                r.setRegistro(rs.getString("registro"));
                r.setTipoRegistro(rs.getString("tipo_registro"));
                r.setMonto(rs.getDouble("monto"));
                r.setModoDePago(rs.getString("modo_pago"));
                r.setDetalles(rs.getString("detalles"));
            } else {
                System.out.println("[!] No se pudo encontrar el registro con id " + id);
            }
        } catch (SQLException err) {
            System.out.println("[!] Ocurrio un error inesperado: " + err.getMessage());
        }
        return r;
    }

    public double obtenerSaldoPorModoPago(String modoPago) {
        String sql = "SELECT SUM(CASE WHEN registro = 'Ingreso' THEN monto ELSE -monto END) AS saldo FROM Registro WHERE modo_pago = ?";
        double saldo = 0.0;
        try (Connection conn = DbConexion.establecerConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, modoPago);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                saldo = rs.getDouble("saldo");
            }
        } catch (SQLException e) {
            System.out.println("[!] Error al calcular saldo de " + modoPago + ": " + e.getMessage());
        }
        return saldo;
    }
}
