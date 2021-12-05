package com.mylyrics.div;

import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    public static final Scanner TECLADO = new Scanner(System.in);
    public static Logger logger = Logger.getLogger(Main.class.getName());


    public static Persona iniciarSesion() {
        boolean isAdmin = false;
        boolean login = false;
        String nombreUsuario;
        String password = "";
        String nombre = "";
        LocalDate fechaNacimiento = null;

        do {
            System.out.print("\nIngrese su nombre de usuario: ");
            nombreUsuario = TECLADO.nextLine();

            System.out.print("\nIngrese su contraseña: ");
            password = TECLADO.nextLine();

            try {
                ConexionBD bd = new ConexionBD();
                ConexionBD.setPs(bd.getConexion().prepareStatement("SELECT * FROM persona WHERE nombreUsuario = ?"));
                ConexionBD.getPs().setString(1, nombreUsuario);

                ConexionBD.setRs(ConexionBD.getPs().executeQuery());

                if (ConexionBD.getRs().next()) {
                    isAdmin = ConexionBD.getRs().getBoolean("isAdmin");
                    nombre = ConexionBD.getRs().getString("nombre");
                    if (!isAdmin) {
                        fechaNacimiento = ConexionBD.getRs().getDate("fechaNacimiento").toLocalDate();
                    }

                    if (password.equals(ConexionBD.getRs().getString("password"))) {
                        login = true;
                    } else {
                        System.out.println("Contraseña Incorrecta.");
                        logger.warning("Se está intentando iniciar sesion a la cuenta del siguiente nombre de usuario: " + nombreUsuario + "\n");
                    }
                }
            } catch (Exception e) {
                logger.warning(e.getMessage() + " 'No se encuentra el usuario en la base de datos'\n");
            }

        } while (!login);

        return new Persona(nombre, password, nombreUsuario, fechaNacimiento, isAdmin);
    }

    public static Persona registrarUsuario() {
        Persona newUser = new Persona();

        newUser.guardarNombreUsuario();
        newUser.setPassword();
        newUser.setNombre();
        newUser.setFechaNacimiento();
        newUser.registrarUsuario();

        return newUser;
    }


    public static Persona menu() {
        System.out.println("1) Iniciar sesión.      2) Registrarse.");
        int opcion = TECLADO.nextInt();
        TECLADO.nextLine();

        if (opcion == 1) {
            return iniciarSesion();
        } else {
            return registrarUsuario();
        }

    }

    public static void menuAdmin() {
        int opcion = 0;
        boolean exit = false;
        String nombreAlbum, nombreCancion, nombreAutor;

        do {
            do {
                System.out.println("1) Agregar un Album");
                System.out.println("2) Agregar una Canción");
                System.out.println("3) Agregar un Autor");
                System.out.println("4) Agregar una Traducción");
                System.out.println("5) Cambiar nombre de un Autor");
                System.out.println("0) Cerrar Sesión");
                opcion = TECLADO.nextInt();
                TECLADO.nextLine();
            } while (opcion > 5 || opcion < 0);

            switch (opcion) {
                case 1:
                    System.out.print("\nIngrese nombre del autor: ");
                    nombreAutor = TECLADO.nextLine();
                    Autor autor = new Autor(nombreAutor);
                    System.out.print("\nIngrese nombre del album: ");
                    nombreAlbum = TECLADO.nextLine();
                    Album.formularioAlbum(nombreAutor, nombreAlbum);
                    break;
                case 2:
                    System.out.print("\nIngrese nombre del autor: ");
                    nombreAutor = TECLADO.nextLine();
                    System.out.print("\nIngrese nombre del album ");
                    nombreAlbum = TECLADO.nextLine();
                    System.out.print("\nIngrese nombre del genero ");
                    String nombreGenero = TECLADO.nextLine();
                    System.out.print("\nIngrese nombre de cancion: ");
                    nombreCancion = TECLADO.nextLine();
                    System.out.print("\nIngrese letra de la cancion: ");
                    String letraCancion = TECLADO.nextLine();
                    System.out.print("\nIngrese letra traducida de la cancion: ");
                    String traduccionCancion = TECLADO.nextLine();
                    Cancion.formularioCancion(nombreAutor, nombreAlbum, nombreGenero, nombreCancion, letraCancion, traduccionCancion);
                    break;
                case 3:
                    System.out.print("\nIngrese el nombre artístico del autor: ");
                    nombreAutor = TECLADO.nextLine();
                    Autor.formularioAutor(nombreAutor);
                    break;
                case 4:
                    System.out.print("\nIngrese el nombre del autor: ");
                    nombreAutor = TECLADO.nextLine();

                    System.out.print("\nIngrese el nombre de la cancion: ");
                    nombreCancion = TECLADO.nextLine();

                    System.out.print("\nIngrese traduccion de la letra ");
                    String letra = TECLADO.nextLine();
                    Cancion.cambiarTraduccion(nombreAutor, nombreCancion, letra);
                    break;
                case 5:
                    System.out.print("\nIngrese el nombre del autor: ");
                    nombreAutor = TECLADO.nextLine();
                    System.out.print("\nIngrese nuevo nombre del autor: ");
                    String nuevoNombreAutor = TECLADO.nextLine();
                    Autor.cambiarNombreAutor(nombreAutor, nuevoNombreAutor);
                    break;
                default:
                    exit = true;
                    break;
            }
        } while (!exit);
    }

    public static void menuUsuario(Persona user) {

        int opcion = 0;
        boolean exit = false;


        do {
            do {
                System.out.println("Buscar canción por: ");
                System.out.println("1) Autor");
                System.out.println("2) Género");
                System.out.println("3) Nombre");
                System.out.println("4) Nombre del Albúm");
                System.out.println("5) Canciones Favoritas");
                System.out.println("0) Cerrar Sesión");
                opcion = TECLADO.nextInt();
                TECLADO.nextLine();
            } while (opcion > 5 || opcion < 0);

            switch (opcion) {
                case 1:
                    user.mostrarAutores();
                    System.out.print("Ingrese el nombre del autor: ");
                    String nombreAutor = TECLADO.nextLine();
                    user.mostrarCancionesPorAutor(nombreAutor);

                    break;
                case 2:
                    user.mostrarGeneros();
                    System.out.println("Ingrese el género de la canción: ");
                    String generoSelect = TECLADO.nextLine();

                    user.mostrarCancionesPorGenero(generoSelect);

                    break;
                case 3:
                    System.out.print("Ingrese el nombre: ");
                    String nombreCancion = TECLADO.nextLine();

                    user.mostrarCancionesPorNombre(nombreCancion);
                    break;
                case 4:
                    user.mostrarAlbumes();
                    System.out.println("Ingrese el álbum de la canción: ");
                    String nombreAlbum = TECLADO.nextLine();

                    for (Cancion cancion : user.mostrarCancionesPorAlbum(nombreAlbum)) {
                        System.out.println(cancion.getId() + ") " + cancion.getNombre());
                    }

                    break;
                case 5:
                    user.traerFavoritos();
                    user.mostrarFavoritas();
                    break;

                default:
                    exit = true;
                    break;
            }
            System.out.println("Ingrese el número correspondiente a la canción que desee ver");
            int idCancionElegida = TECLADO.nextInt();
            TECLADO.nextLine(); //Limpiar Buffer
            Cancion cancionElegida = new Cancion(idCancionElegida);
            cancionElegida.mostrarInfoCancion();
        } while (!exit);

    }


    public static void main(String[] args) throws IOException {

        FileHandler fileLog = new FileHandler("mylog.log",true);
        SimpleFormatter formatter = new SimpleFormatter();
        fileLog.setFormatter(formatter);
        logger.addHandler(fileLog);

        Persona persona = menu();

        if (persona.isAdmin()) {
            menuAdmin();
        } else {
            menuUsuario(persona);
        }
    }

}
