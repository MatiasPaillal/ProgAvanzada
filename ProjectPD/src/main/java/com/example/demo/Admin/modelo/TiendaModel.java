package com.example.demo.Admin.modelo;

import javax.persistence.*;

@Entity
@Table(name = "tienda")
public class TiendaModel {

    @Id
    @Column(length = 10)
    private String rutTienda;

    @Column(length = 45, nullable = false)
    private String nombre;

    @Column(length = 45, nullable = false)
    private String direccion;

    @Column(nullable = false)
    private Integer telefono;

    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "usuario")
    private Administrador administrador_usuario;

    public String getRutTienda() {
        return this.rutTienda;
    }

    public void setRutTienda(String rutTienda) {
        this.rutTienda = rutTienda;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getTelefono() {
        return this.telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Administrador getAdministrador_usuario() {
        return this.administrador_usuario;
    }

    public void setAdministrador_usuario(Administrador administrador_usuario) {
        this.administrador_usuario = administrador_usuario;
    }

}
