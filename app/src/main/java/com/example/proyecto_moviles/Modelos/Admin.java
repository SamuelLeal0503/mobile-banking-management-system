package com.example.proyecto_moviles.Modelos;

import java.io.Serializable;

public class Admin implements Serializable {
    private String id;
    private String cedula;
    private String nombreUsuario;
    private String contrasena;

    public Admin() {}
    public Admin(String id, String cedula, String nombreUsuario, String contrasena) {
        this.id = id;
        this.cedula = cedula;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
