package com.mylyrics.div;

import java.util.ArrayList;

public class Autor {
    private int id;
    private String nombreArtistico;
    private ArrayList<Album> albums;

    public Autor(int id,String nombreArtistico) {
        this.id=id;
        this.nombreArtistico = nombreArtistico;
    }
    public Autor() {

    }

    public Autor(int id) {
        this.id = id;
        rellenarAutorNombre();
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

    public boolean editarNombre(String nombre) {
        ConexionBD bd = new ConexionBD();

        try {
            ConexionBD.setPs(bd.getConexion().prepareStatement("UPDATE autor SET nombreArtistico=? WHERE id=?"));
            ConexionBD.getPs().setString(1, nombre);
            ConexionBD.getPs().setString(2, String.valueOf(this.id));
            ConexionBD.getPs().executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;

        }

    }


    public void rellenarAutorNombre() {
        try {
            ConexionBD bd = new ConexionBD();

            ConexionBD.setPs(bd.getConexion().prepareStatement("SELECT * FROM autor WHERE id = ?"));
            ConexionBD.getPs().setInt(1, this.id);

            ConexionBD.setRs(ConexionBD.getPs().executeQuery());

            if (ConexionBD.getRs().next()) {
                this.nombreArtistico = ConexionBD.getRs().getString("nombreArtistico");

            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
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
