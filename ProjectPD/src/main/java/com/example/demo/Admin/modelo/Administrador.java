/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Admin.modelo;

 
import javax.persistence.*;
 

/**
 *
 * @author matias
 */
@Entity
public class Administrador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String usuario;
    @Column
    private String password;
    @Column
    private String nombre;

    public Administrador(String usuario, String password, String nombre) {
        
        this.usuario = usuario;
        this.password = password;
        this.nombre = nombre;
    }

    
    public Administrador() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

       
    
}
