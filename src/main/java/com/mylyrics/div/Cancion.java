package com.mylyrics.div;

import java.sql.SQLException;
import java.util.Scanner;

public class Cancion {
    public static final Scanner TECLADO = new Scanner(System.in);
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
        Cancion cancion= new Cancion(nombreCancion, letraCancion, traduccionCancion, genero, autor, album);
        return cancion.ingresarCancion();
    }

    public Cancion(String nombre) {
        this.nombre = nombre;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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




    public static void cambiarTraduccion() {
        try {
            System.out.print("\nIngrese el nombre del autor: ");
            String nombreAutor = TECLADO.nextLine();

            System.out.print("\nIngrese el nombre de la cancion: ");
            String nombreCancion = TECLADO.nextLine();


            Autor autor = new Autor(nombreAutor);
            System.out.print("\nIngrese traduccion de la letra ");
            String letra = TECLADO.nextLine();

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
}