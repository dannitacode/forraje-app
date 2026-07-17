package com.forraje;

import com.forraje.igu.JFrameHome;
import com.forraje.persistencia.DbConexion;

/**
 *
 * @author dannita
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("[!] Inicializando persistencia");
        DbConexion.init();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrameHome home = new JFrameHome();
                home.setLocationRelativeTo(null);
                home.setVisible(true);
            }
        });
    }
}
