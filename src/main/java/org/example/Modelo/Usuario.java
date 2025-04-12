package org.example.Modelo;

import java.util.List;

public class Usuario {
    private String nombre;
    private String apellido;
    private String correo;
    private String contraseña;
    private List<String> preferencias;

    public Usuario(String nombre, String apellido, String correo, String contraseña, List<String> preferencias) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contraseña = contraseña;
        this.preferencias = preferencias;
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getCorreo() { return correo; }
    public String getContraseña() { return contraseña; }
    public List<String> getPreferencias() { return preferencias; }
}
