package com.forraje.logica;

import java.time.LocalDate;

/**
 *
 * @author dannita
 */
public class Registro {
    
    private int id;
    private LocalDate fecha;
    private String dia;
    private String semana;
    private String tipo;
    private double monto;
    private String modoDePago;
    private String detalles;
    
    public Registro(LocalDate f, String d, String s, String t, double mon, String modo, String details) {
        fecha = f;
        dia = d;
        semana = s;
        tipo = t;
        monto = mon;
        modoDePago = modo;
        detalles = details;
    }

    @Override
    public String toString() {
        return "Operacion{" + "id=" + id + ", fecha=" + fecha + ", dia=" + dia + ", semana=" + semana + ", tipo=" + tipo + ", monto=" + monto + ", modoDePago=" + modoDePago + ", detalles=" + detalles + '}';
    }
    
    
}
