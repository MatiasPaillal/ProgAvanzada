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
            bd.setPs(bd.getConexion().prepareStatement("INSERT INTO album (nombreCancion, letra, letraTraducida,idAlbum,idAutor,idGenero) VALUES(?,?,?,?,?)"));

            bd.getPs().setString(1, this.nombre);
            bd.getPs().setString(1, this.letra);
            bd.getPs().setString(1, this.letraTraducida);
            bd.getPs().setInt(3, this.album.getId());
            bd.getPs().setInt(3, this.autor.getId());
            bd.getPs().setInt(3, this.genero.getId());


            bd.getPs().executeUpdate();
            return true;
        } catch (Exception e) {
            return false;

        }


    }


}