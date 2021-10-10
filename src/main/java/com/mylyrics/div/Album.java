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

    public boolean agregarFecha(int año, int mes, int dia) {


        if (año < LocalDate.now().getYear() && mes < 13 && dia < 32) {
            LocalDate fechaIngresada = LocalDate.of(año, mes, dia);
            LocalDate fechaActual = LocalDate.now();

            if (fechaIngresada.isBefore(fechaActual)) {
                this.fecha = fechaIngresada;
                return true;

            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    public String getNombre() {
        return this.nombre;
    }

    public boolean ingresarAlbum() {
        try {
            ConexionBD bd = new ConexionBD();
            bd.setPs(bd.getConexion().prepareStatement("INSERT INTO album (nombreAlbum, fechaEstreno, idAutor) VALUES(?,?,?)"));

            bd.getPs().setString(1, this.nombre);
            bd.getPs().setString(2, String.valueOf(this.fecha));
            bd.getPs().setInt(3, this.autor.getId());


            bd.getPs().executeUpdate();
            return true;
        } catch (Exception e) {
            return false;

        }

    }

    public void agregarId() {

        try {
            ConexionBD bd = new ConexionBD();
            bd.setPs(bd.getConexion().prepareStatement("SELECT * FROM album WHERE nombreAlbum = " + this.nombre));

            if (bd.getRs().next()) {
                this.id = bd.getRs().getInt("id");
            }

        } catch (Exception e) {

        }

    }

    public int getId() {
        return id;
    }
}
