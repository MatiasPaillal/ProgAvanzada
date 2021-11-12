package com.mylyrics.div;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Scanner;

public class Cancion {

    private String nombre;
    private String letra;
    private String letraTraducida;
    private Genero genero;
    private Autor autor;
    private Album album;

    public Cancion() {

    }

    public Cancion(String nombre, String letra, String letraTraducida, Genero genero, Autor autor, Album album) {
        this.nombre = nombre;
        this.letra = letra;
        this.letraTraducida = letraTraducida;
        this.genero = genero;
        this.autor = autor;
        this.album = album;
    }

    public String getNombre() {

        return this.nombre;
    }

    public String getLetra() {

        return this.letra;
    }

    public Genero getGenero() {

        return this.genero;
    }

    public String getNameAutor() {

        return this.autor.getNombreArtistico();
    }

    public String getNameAlbum() {

        return this.album.getNombre();
    }

    public boolean ingresarCancion() {
        ConexionBD bd = new ConexionBD();

        try {
            ConexionBD.setPs(bd.getConexion().prepareStatement("INSERT INTO cancion (nombreCancion, letra, letraTraducida,idAlbum,idAutor,idGenero) VALUES(?,?,?,?,?,?)"));

            ConexionBD.getPs().setString(1, this.nombre);
            ConexionBD.getPs().setString(2, this.letra);
            ConexionBD.getPs().setString(3, this.letraTraducida);
            ConexionBD.getPs().setInt(4, this.album.getId());
            ConexionBD.getPs().setInt(5, this.autor.getId());
            ConexionBD.getPs().setInt(6, this.genero.getId());


            ConexionBD.getPs().executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;

        }


    }


}