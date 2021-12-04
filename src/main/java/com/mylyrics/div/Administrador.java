package com.mylyrics.div;

import java.util.Scanner;

public class Administrador extends Persona {
    public static final Scanner TECLADO = new Scanner(System.in);

    public Administrador() {
        super();
    }
    public boolean formularioCancion(String nombreAutor, String nombreAlbum, String nombreGenero, String nombreCancion, String letraCancion, String traduccionCancion) {

        Autor autor = new Autor(nombreAutor);
        Album album = new Album(nombreAlbum);
        Genero genero = Genero.buscarGenero(nombreGenero);
        Cancion cancion= new Cancion(nombreCancion, letraCancion, traduccionCancion, genero, autor, album);
        return cancion.ingresarCancion();
    }
    public void formularioCancionInicial() {
        System.out.print("\nIngrese nombre del autor: ");
        String nombreAutor = TECLADO.nextLine();
        Autor autor = new Autor(nombreAutor);

        System.out.print("\nIngrese nombre del album ");
        String nombreAlbum = TECLADO.nextLine();
        Album album = new Album(nombreAlbum);

        System.out.print("\nIngrese nombre del genero ");
        String nombreGenero = TECLADO.nextLine();
        Genero genero = Genero.buscarGenero(nombreGenero);

        System.out.print("\nIngrese nombre de cancion: ");
        String nombreCancion = TECLADO.nextLine();
        System.out.print("\nIngrese letra de la cancion: ");
        String letraCancion = TECLADO.nextLine();
        System.out.print("\nIngrese letra traducida de la cancion: ");
        String traduccionCancion = TECLADO.nextLine();


        Cancion cancion = new Cancion(nombreCancion, letraCancion, traduccionCancion, genero, autor, album);
        cancion.ingresarCancion();

    }

}