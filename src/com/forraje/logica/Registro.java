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
    private String registro;
    private String tipoRegistro;
    private double monto;
    private String modoDePago;
    private String detalles;
    
    public Registro(){}
    public Registro(LocalDate f, String d, int s, String reg, String tipoReg, double mon, String modo, String details) {
        fecha = f;
        dia = d;
        semana = s;
        registro = reg;
        tipoRegistro = tipoReg;
        monto = mon;
        modoDePago = modo;
        detalles = details;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public void setSemana(int semana) {
        this.semana = semana;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setModoDePago(String modoDePago) {
        this.modoDePago = modoDePago;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
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

    public String getRegistro() {
        return registro;
    }
    
    public String getTipoRegistro() {
        return tipoRegistro;
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
        return "Registro{" + "id=" + id + ", fecha=" + fecha + ", dia=" + dia + ", semana=" + semana + ", registro=" + registro + ", tipo=" + tipoRegistro + ", monto=" + monto + ", modoDePago=" + modoDePago + ", detalles=" + detalles + '}';
    }
    
    
}
