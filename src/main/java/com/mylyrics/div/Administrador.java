package com.mylyrics.div;

import java.util.Scanner;

public class Administrador extends Persona {
    public static Scanner teclado = new Scanner(System.in);

    public Administrador(String nombre, String password, String nombreUsuario) {
        super(nombre, password, nombreUsuario);
    }

    public Administrador() {
        super();
    }

    public static Genero buscarGenero(String nombreGenero) {
        for (Genero genero : Genero.values()) {
            if (genero.toString().equals(nombreGenero)) {
                return genero;
            }
        }
        return null;
    }

    public static void formularioAlbum() {
        System.out.print("\nIngrese nombre del autor: ");
        String nombreAutor = teclado.nextLine();
        Autor autor = new Autor(nombreAutor);
        autor.agregarId();
        System.out.print("\nIngrese nombre del album: ");
        String nombreAlbum = teclado.nextLine();

        Album album = new Album(nombreAlbum, autor);
        boolean bol = false;
        int anio, mes, dia;
        do {
            System.out.println("año");
            anio = teclado.nextInt();
            System.out.println("mes");
            mes = teclado.nextInt();
            System.out.println("dia");
            dia = teclado.nextInt();
            bol = album.agregarFecha(anio, mes, dia);
        } while (!bol);

        album.ingresarAlbum();
    }

    public static void formularioAutor() {
        System.out.print("\nIngrese el nombre artístico del autor: ");
        String nombreAutor = teclado.nextLine();
        Autor autor = new Autor(nombreAutor);
        autor.registrarAutor();

    }

    public static void formularioCancion() {
        System.out.print("\nIngrese nombre del autor: ");
        String nombreAutor = teclado.nextLine();
        Autor autor = new Autor(nombreAutor);
        autor.agregarId();

        System.out.print("\nIngrese nombre del album ");
        String nombreAlbum = teclado.nextLine();
        Album album = new Album(nombreAlbum);
        album.agregarId();

        System.out.print("\nIngrese nombre del genero ");
        String nombreGenero = teclado.nextLine();
        Genero genero = buscarGenero(nombreGenero);

        System.out.print("\nIngrese nombre de cancion: ");
        String nombreCancion = teclado.nextLine();
        System.out.print("\nIngrese letra de la cancion: ");
        String letraCancion = teclado.nextLine();
        System.out.print("\nIngrese letra traducida de la cancion: ");
        String traduccionCancion = teclado.nextLine();


        Cancion cancion = new Cancion(nombreCancion, letraCancion, traduccionCancion, genero, autor, album);
        cancion.ingresarCancion();

    }

    public static void cambiarTraduccion() {
        ConexionBD bd = new ConexionBD();
        try {
            System.out.print("\nIngrese el nombre del autor: ");
            String nombreAutor = teclado.nextLine();

            System.out.print("\nIngrese el nombre de la cancion: ");
            String nombreCancion = teclado.nextLine();


            Autor autor =new Autor(nombreAutor);
            autor.agregarId();
            System.out.print("\nIngrese traduccion de la letra ");
            String letra = teclado.nextLine();

            Cancion cancion = new Cancion(nombreCancion, autor);
            cancion.editarTraduccion(letra);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void cambiarNombreAutor() {
        ConexionBD bd = new ConexionBD();
        try {
            System.out.print("\nIngrese el nombre del autor: ");
            String nombreAutor = teclado.nextLine();
            System.out.print("\nIngrese nuevo nombre del autor: ");
            String nuevoNombreAutor = teclado.nextLine();

            Autor autor =new Autor(nombreAutor);
            autor.agregarId();
            autor.editarNombre(nuevoNombreAutor);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


}
