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
    private int semana;
    private String tipo;
    private double monto;
    private String modoDePago;
    private String detalles;
    
    public Registro(LocalDate f, String d, int s, String t, double mon, String modo, String details) {
        fecha = f;
        dia = d;
        semana = s;
        tipo = t;
        monto = mon;
        modoDePago = modo;
        detalles = details;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getFecha() {
        return this.fecha.toString();
    }

    public String getDia() {
        return dia;
    }

    public int getSemana() {
        return semana;
    }

    public String getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }

    public String getModoDePago() {
        return modoDePago;
    }

    public String getDetalles() {
        return detalles;
    }

    
    
    @Override
    public String toString() {
        return "Operacion{" + "id=" + id + ", fecha=" + fecha + ", dia=" + dia + ", semana=" + semana + ", tipo=" + tipo + ", monto=" + monto + ", modoDePago=" + modoDePago + ", detalles=" + detalles + '}';
    }
    
    
}
