package com.mylyrics.div;

import java.time.LocalDate;
import java.util.ArrayList;

public class Album {
    private int id;
    private String nombre;
    private Autor autor;
    private ArrayList<Cancion> canciones;
    private LocalDate fecha;


    public Album() {
    }

    public Album(int id) {
        this.id = id;
        rellenarAlbumNombre();
    }

    public Album(String nombre, Autor autor) {
        this.nombre = nombre;
        this.autor = autor;
        this.fecha = null;
    }

    public Album(String nombre) {
        this.id = 0;
        this.nombre = nombre;
    }

    public boolean agregarFecha(int anio, int mes, int dia) {

        if (anio < LocalDate.now().getYear() && mes < 13 && dia < 32) {
            LocalDate fechaIngresada = LocalDate.of(anio, mes, dia);

            if (fechaIngresada.isBefore(LocalDate.now())) {
                this.fecha = fechaIngresada;
                return true;
            }
        }
        return false;
    }

    public String getNombre() {
        return this.nombre;
    }

    public boolean ingresarAlbum() {
        try {
            ConexionBD bd = new ConexionBD();
            System.out.println(this.nombre);
            bd.setPs(bd.getConexion().prepareStatement("INSERT INTO album (nombreAlbum, fechaEstreno, idAutor) VALUES(?,?,?)"));

            bd.getPs().setString(1, this.nombre);
            bd.getPs().setString(2, String.valueOf(this.fecha));
            bd.getPs().setInt(3, this.autor.getId());


            bd.getPs().executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;

        }

    }

    public void agregarId() {

        try {
            ConexionBD bd = new ConexionBD();
            System.out.println(this.nombre);
            bd.setPs(bd.getConexion().prepareStatement("SELECT * FROM album WHERE nombreAlbum = ?"));
            bd.getPs().setString(1, this.nombre);
            bd.setRs(bd.getPs().executeQuery());
            if (bd.getRs().next()) {
                System.out.println(bd.getRs().getInt("id"));
                this.id = bd.getRs().getInt("id");
            }

        } catch (Exception e) {

        }

    }

    public void rellenarAlbumNombre() {
        try {
            ConexionBD bd = new ConexionBD();

            ConexionBD.setPs(bd.getConexion().prepareStatement("SELECT * FROM album WHERE id = ?"));
            ConexionBD.getPs().setInt(1, this.id);

            ConexionBD.setRs(ConexionBD.getPs().executeQuery());

            if (ConexionBD.getRs().next()) {
                this.nombre = ConexionBD.getRs().getString("nombreAlbum");
                this.fecha = LocalDate.parse(ConexionBD.getRs().getString("fechaEstreno"));

            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public int getId() {
        return id;
    }
}
