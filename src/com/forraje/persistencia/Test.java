package com.forraje.persistencia;

import java.time.LocalDate;
import java.sql.*;

/**
 *
 * @author dannita
 */
public class Test {

    public static void main(String[] args) {
        DbConexion.init();
    }

    public static void insertar(double monto, String modo, String details) {
        String sql = "INSERT INTO Registro (fecha, dia, semana, tipo_registro, monto, modo_pago, detalles) VALUES (?,?,?,?,?,?,?)";
        LocalDate hoy = LocalDate.now();
        String fecha = hoy.toString();
        String dia = hoy.getDayOfWeek().name().toLowerCase();
        int nro = 29;
        try (Connection conn = DbConexion.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            if (conn == null) {
                return;
            }
            ps.setString(1, fecha);
            ps.setString(2, dia);
            ps.setInt(3, nro);
            ps.setString(4, "Ingreso");
            ps.setDouble(5, monto);
            ps.setString(6, modo);
            ps.setString(8, details);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("[!] Registro creado con exito");
            } else {
                System.out.println("[!] Ocurrio un error a la hora de crear un registro");
            }
        } catch (SQLException err) {
            System.out.println("[!] Algo salio mal:" + err.getMessage());
        }
    }

}
