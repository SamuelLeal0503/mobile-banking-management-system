package com.example.proyecto_moviles.Modelos;

public class Ahorro {
    private long id;
    private long idCliente;
    private double monto;
    private double cuotaMensual;
    private String tipo;

    private boolean activo;

    public Ahorro(long id, long idCliente, double monto, double cuotaMensual, String tipo, boolean activo) {
        this.id = id;
        this.idCliente = idCliente;
        this.monto = monto;
        this.cuotaMensual = cuotaMensual;
        this.tipo = tipo;
        this.activo = activo;

    }

    public Ahorro(){}

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

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
