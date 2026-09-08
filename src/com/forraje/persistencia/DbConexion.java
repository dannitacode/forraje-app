package com.forraje.persistencia;

import java.sql.*;

/**
 *
 * @author dannita
 */
public class DbConexion {

    private static final String DB_NAME = "forraje.db";
    private static final String URL = "jdbc:sqlite:" + DB_NAME;

    public static Connection establecerConexion() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("[!] Conexion establecida");
        } catch (SQLException err) {
            System.out.println("[!] No se pudo establecer la conexion: " + err.getMessage());
        }
        return conn;
    }

    public static void init() {
        String sql = "CREATE TABLE IF NOT EXISTS Registro ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "fecha TEXT NOT NULL,"
                + "dia TEXT NOT NULL,"
                + "semana INTEGER NOT NULL,"
                + "registro TEXT NOT NULL,"
                + "tipo_registro TEXT NOT NULL,"
                + "monto REAL NOT NULL,"
                + "modo_pago TEXT NOT NULL,"
                + "detalles TEXT"
                + ");";
        try (Connection conn = establecerConexion(); Statement s = conn.createStatement()) {
            if (conn != null) {
                s.execute(sql);
                System.out.println("[!] Tabla creada con exito");
            }
        } catch (SQLException err) {
            System.out.println("[!] Ocurrio un error con crear la tabla: " + err.getMessage());
        }
    }

}
