package com.mylyrics.div;

import java.util.ArrayList;
import java.util.Arrays;

public class Cancion {
    private int id;
    private String nombre;
    private String letra;
    private String letraTraducida;
    private Genero genero;
    private Autor autor;
    private Album album;

    public Cancion() {

    }

    public static boolean formularioCancion(String nombreAutor, String nombreAlbum, String nombreGenero, String nombreCancion, String letraCancion, String traduccionCancion) {

        Autor autor = new Autor(nombreAutor);
        Album album = new Album(nombreAlbum);
        Genero genero = Genero.buscarGenero(nombreGenero);
        Cancion cancion = new Cancion(nombreCancion, letraCancion, traduccionCancion, genero, autor, album);
        return cancion.ingresarCancion();
    }

    public Cancion(int id) {
        try {
            ConexionBD bd = new ConexionBD();

            ConexionBD.setPs(bd.getConexion().prepareStatement("SELECT * FROM cancion WHERE id = ?"));
            ConexionBD.getPs().setInt(1, id);
            ConexionBD.setRs(ConexionBD.getPs().executeQuery());

            ConexionBD.getRs().next();

            this.nombre = ConexionBD.getRs().getString("nombreCancion");
            this.letra = ConexionBD.getRs().getString("letra");
            this.letraTraducida = ConexionBD.getRs().getString("letraTraducida");

            int idAutor = ConexionBD.getRs().getInt("idAutor");
            int idGenero = ConexionBD.getRs().getInt("idGenero");
            int idAlbum = ConexionBD.getRs().getInt("idAlbum");

            rellenarCancion(id, nombre, letra, letraTraducida, idAlbum, idAutor, idGenero);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public Cancion(String nombre, Autor autor) {
        this.nombre = nombre;
        this.autor = autor;
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

    public int getId() {
        return id;
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

    public boolean editarTraduccion(String letra) {
        ConexionBD bd = new ConexionBD();

        try {
            ConexionBD.setPs(bd.getConexion().prepareStatement("UPDATE cancion SET letraTraducida=? WHERE nombreCancion=? AND idAutor=?"));
            ConexionBD.getPs().setString(1, letra);
            ConexionBD.getPs().setString(2, this.nombre);
            ConexionBD.getPs().setString(3, String.valueOf(this.autor.getId()));

            ConexionBD.getPs().executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;

        }

    }

    public void rellenarCancion(int id, String nombre, String letra, String letraTraducida, int idAlbum, int idAutor, int idGenero) {

        this.id = id;
        this.nombre = nombre;
        this.letra = letra;
        this.letraTraducida = letraTraducida;

        Autor autorRecibido = new Autor(idAutor);

        Album albumRecibido = new Album(idAlbum);

        Genero generoRecibido = Genero.buscarGeneroXid(idGenero);

        this.autor = autorRecibido;
        this.album = albumRecibido;
        this.genero = generoRecibido;

    }


    public static void cambiarTraduccion(String nombreAutor, String nombreCancion, String letra) {
        try {
            Autor autor = new Autor(nombreAutor);


            Cancion cancion = new Cancion(nombreCancion, autor);
            cancion.editarTraduccion(letra);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", letra='" + letra + '\'' +
                ", letraTraducida='" + letraTraducida + '\'' +
                ", genero=" + genero +
                ", autor=" + autor +
                ", album=" + album +
                '}';
    }

    public void mostrarInfoCancion() {
        System.out.print("Nombre: " + this.nombre + "\n" +
                "Autor: " + this.autor.getNombreArtistico() + "\n" +
                "Album: " + this.album.getNombre() + "\n" +
                "Genero: " + this.genero + "\n"
        );
        System.out.println("\nLetra:");
        dividirLetras(this.letra);
        System.out.println("\nLetraTraducida:");
        dividirLetras(this.letraTraducida);

    }

    public void dividirLetras(String letra) {
        ArrayList<String> letrasplit = new ArrayList<>(Arrays.asList(letra.split(" ")));

        for (int i = 0; i < letrasplit.size(); i++) {
            if (i % 5 == 0 && i != 0) {
                System.out.println(" ");
            }
            System.out.print(letrasplit.get(i) + " ");
        }
        System.out.println("");


    }
}