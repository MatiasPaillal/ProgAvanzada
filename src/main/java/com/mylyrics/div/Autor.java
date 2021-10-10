package com.mylyrics.div;

import java.util.ArrayList;

public class Autor {
    private int id;
    private String nombreArtistico;
    private ArrayList<Album> albums;

    public Autor() {

    }


    public void agregarId() {

        try {
            ConexionBD bd = new ConexionBD();
            bd.setPs(bd.getConexion().prepareStatement("SELECT * FROM autor WHERE nombreArtistico = " + this.nombreArtistico));
            bd.setRs(bd.getPs().executeQuery());

            if (bd.getRs().next()) {
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
