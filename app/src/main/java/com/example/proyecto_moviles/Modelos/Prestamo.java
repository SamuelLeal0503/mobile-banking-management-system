package com.example.proyecto_moviles.Modelos;
import java.io.Serializable;
public class Prestamo implements Serializable{
    private long id;
    private long idCliente;
    private double monto;
    private double interes;
    private int plazo;
    private double cuotaMensual;
    private String tipo;

    public Prestamo(long id, long idCliente, double monto, double interes, int plazo, double cuotaMensual, String tipo) {
        this.id = id;
        this.idCliente = idCliente;
        this.monto = monto;
        this.interes = interes;
        this.plazo = plazo;
        this.cuotaMensual = cuotaMensual;
        this.tipo = tipo;
    }

    public Prestamo(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public double getCuotaMensual() {
        return cuotaMensual;
    }

    public void setCuotaMensual(double cuotaMensual) {
        this.cuotaMensual = cuotaMensual;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double calcularPromedioCredito(String tipo){
        switch (tipo) {
            case "Hipotecario":
                return 0.075;
            case "Educacion":
                return 0.08;
            case "Personal":
                return 0.1;
            case "Viajes":
                return 0.12;
            default:
                return 0;
        }
    }
}
