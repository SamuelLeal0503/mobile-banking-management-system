package com.example.proyecto_moviles.Modelos;

import java.io.Serializable;
import java.time.LocalDate;

public class Cliente implements Serializable {
    private long id;
    private String cedula;
    private String nombre;
    private String contrasena;
    private double salario;
    private String telefono;
    private String fechaNacimiento;
    private String estadoCivil;
    private String direccion;


    public Cliente(long id, String cedula, String nombre, String contrasena, double salario, String telefono, String fechaNacimiento, String estadoCivil, String direccion) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.salario = salario;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.estadoCivil = estadoCivil;
        this.direccion = direccion;
    }

    public Cliente() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
