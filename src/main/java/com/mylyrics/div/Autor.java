package com.mylyrics.div;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Autor {
    public static final Scanner TECLADO = new Scanner(System.in);
    private int id;
    private String nombreArtistico;
    private ArrayList<Album> albums;

    public Autor(int id, String nombreArtistico) {
        this.id = id;
        this.nombreArtistico = nombreArtistico;
    }

    public Autor() {

    }

    public Autor(int id) {
        this.id = id;
        rellenarAutorNombre();
    }

    public Autor(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
        agregarId();
    }

    public boolean registrarAutor() {
        try {
            ConexionBD bd = new ConexionBD();

            ConexionBD.setPs(bd.getConexion().prepareStatement("INSERT INTO autor (nombreArtistico) VALUES(?)"));
            ConexionBD.getPs().setString(1, this.nombreArtistico);

            ConexionBD.getPs().executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }

    }

    public void agregarId() {
        try {
            ConexionBD bd = new ConexionBD();

            ConexionBD.setPs(bd.getConexion().prepareStatement("SELECT * FROM autor WHERE nombreArtistico = ?"));
            ConexionBD.getPs().setString(1, this.nombreArtistico);

            ConexionBD.setRs(ConexionBD.getPs().executeQuery());

            if (ConexionBD.getRs().next()) {
                System.out.println(ConexionBD.getRs().getInt("id"));
                this.id = ConexionBD.getRs().getInt("id");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
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

    public static void formularioAutor(String nombreAutor) {


        Autor autor = new Autor(nombreAutor);
        autor.registrarAutor();

    }
    public static void cambiarNombreAutor(String nombreAutor,String nuevoNombreAutor) {
        try {
            Autor autor = new Autor(nombreAutor);
            autor.editarNombre(nuevoNombreAutor);
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

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = (ArrayList<Album>) albums;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
