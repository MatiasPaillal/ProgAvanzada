package com.mylyrics.div;

public class Persona {
    protected String nombre;
    protected String password;
    protected String nombreUsuario;

    public Persona(String nombre,String password,String nombreUsuario) {
        this.nombre = nombre;
        this.password = password;
        this.nombreUsuario = nombreUsuario;
    }

    public Persona() {
    }
}
