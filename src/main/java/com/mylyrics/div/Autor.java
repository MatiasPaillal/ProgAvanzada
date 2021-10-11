package com.mylyrics.div;

import java.util.ArrayList;

public class Autor {
    private int id;
    private String nombreArtistico;
    private ArrayList<Album> albums;

    public Autor() {

    }

    public Autor(String nombreArtistico) {
        this.id = 0;
        this.nombreArtistico = nombreArtistico;
        this.albums = null;

    }

    public boolean registrarAutor() {
        try {
            ConexionBD bd = new ConexionBD();

            bd.setPs(bd.getConexion().prepareStatement("INSERT INTO autor (nombreArtistico) VALUES(?)"));

            bd.getPs().setString(1, this.nombreArtistico);

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
            bd.setPs(bd.getConexion().prepareStatement("SELECT * FROM autor WHERE nombreArtistico = ?"));
            bd.getPs().setString(1, this.nombreArtistico);
            bd.setRs(bd.getPs().executeQuery());

            if (bd.getRs().next()) {
                System.out.println(bd.getRs().getInt("id"));
                this.id = bd.getRs().getInt("id");

            }

        } catch (Exception e) {

        }

    }


    public String getNombreArtistico() {
        return nombreArtistico;
    }

    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
