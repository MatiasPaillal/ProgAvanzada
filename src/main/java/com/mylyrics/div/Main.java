package com.mylyrics.div;

import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    public static final Scanner TECLADO = new Scanner(System.in);

    public static Object iniciarSesion() {
        boolean isAdmin = false;
        boolean login = false;
        String nombreUsuario;
        String password = "";
        String nombre = "";

        LocalDate fechaNacimiento = LocalDate.now();

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
                    }
                }
            } catch (Exception e) {
                System.err.println("Error al encontrar el usuario.");
            }
        } while (!login);

        return (isAdmin) ? new Administrador(nombre, password, nombreUsuario) : new Usuario(nombre, password, nombreUsuario, fechaNacimiento);
    }

    public static Usuario registrarUsuario() {
        Usuario newUser = new Usuario();

        newUser.guardarNombreUsuario();
        newUser.setPassword();
        newUser.setNombre();
        newUser.setFechaNacimiento();
        newUser.registrarUsuario();

        return newUser;
    }


    public static Object menu() {
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
                    Administrador.formularioAlbum();
                    break;
                case 2:
                    Administrador.formularioCancion();
                    break;
                case 3:
                    Administrador.formularioAutor();
                    break;
                case 4:
                    Administrador.cambiarTraduccion();
                    break;
                case 5:
                    Administrador.cambiarNombreAutor();
                    break;
                default:
                    exit = true;
                    break;
            }
        } while (!exit);
    }

    public static void menuUsuario(Usuario user) {

        int opcion = 0;
        boolean exit = false;

        do {
            do {
                System.out.println("Buscar canción por: ");
                System.out.println("1) Autor");
                System.out.println("2) Género");
                System.out.println("3) Nombre");
                System.out.println("4) Nombre del Albúm");
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
                        System.out.println(cancion.getId() + ") "+ cancion.getNombre());
                    }

                    break;

                default:
                    exit = true;
                    break;
            }
        } while (!exit);

    }

    public static void main(String[] args) {
        Object usuario = menu();

        if (usuario instanceof Administrador) {
            menuAdmin();
        } else {
            menuUsuario((Usuario) usuario);
        }
    }

}
