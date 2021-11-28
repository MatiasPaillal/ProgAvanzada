
package com.mylyrics.div;

import java.util.Scanner;

    public class Administrador extends Persona {
        public static final Scanner TECLADO = new Scanner(System.in);

        public Administrador(String nombre, String password, String nombreUsuario) {
            super(nombre, password, nombreUsuario);
        }

        public Administrador() {
            super();
        }
        public static void formularioAlbum(){

        System.out.print("\nIngrese nombre del autor: ");
        String nombreAutor = TECLADO.nextLine();
        Autor autor = new Autor(nombreAutor);
        System.out.print("\nIngrese nombre del album: ");
        String nombreAlbum = TECLADO.nextLine();

        Album album = new Album(nombreAlbum, autor);
        boolean bol = false;
        int anio;
        int mes;
        int dia;
        do {
            System.out.println("año");
            anio = TECLADO.nextInt();
            System.out.println("mes");
            mes = TECLADO.nextInt();
            System.out.println("dia");
            dia = TECLADO.nextInt();
            TECLADO.nextLine(); //Limpiar Buffer del Scanner
            bol = album.agregarFecha(anio, mes, dia);
        } while (!bol);

        album.ingresarAlbum();
    }

    public static void formularioAutor() {

        System.out.print("\nIngrese el nombre artístico del autor: ");
        String nombreAutor = TECLADO.nextLine();
        Autor autor = new Autor(nombreAutor);
        autor.registrarAutor();

    }

    public static void formularioCancion() {
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

    public static void cambiarNombreAutor() {
        try {
            System.out.print("\nIngrese el nombre del autor: ");
            String nombreAutor = TECLADO.nextLine();
            System.out.print("\nIngrese nuevo nombre del autor: ");
            String nuevoNombreAutor = TECLADO.nextLine();

            Autor autor = new Autor(nombreAutor);
            autor.editarNombre(nuevoNombreAutor);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}

