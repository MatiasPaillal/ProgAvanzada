package com.mylyrics.div;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.List;

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

    public static void menuAdmin(Administrador admin) {
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
                    admin.formularioAlbum();
                    break;
                case 2:
                    admin.formularioCancion();
                    break;
                case 3:
                    admin.formularioAutor();
                    break;
                case 4:
                    admin.cambiarTraduccion();
                    break;
                case 5:
                    admin.cambiarNombreAutor();
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

                    break;
                case 5:
                    List<Cancion> canciones = obtenerCancionesBD();
                    for (int i = 0; i < canciones.size(); i++) {
                        System.out.println(canciones.get(i).getNombre());
                        System.out.println(canciones.get(i).getGenero());
                        System.out.println(canciones.get(i).getLetra());
                        System.out.println(canciones.get(i).getNameAlbum());
                        System.out.println(canciones.get(i).getNameAutor());
                        System.out.println("<----------------------------->");
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
            menuAdmin((Administrador) usuario);
        } else {
            menuUsuario((Usuario) usuario);
        }
    }

    public static ArrayList<Cancion> obtenerCancionesBD() {

        try {
            ArrayList<Cancion> canciones = new ArrayList<Cancion>();
            ConexionBD bd = new ConexionBD();

            ConexionBD.setPs(bd.getConexion().prepareStatement("SELECT * FROM cancion"));
            ConexionBD.setRs(ConexionBD.getPs().executeQuery());
            Cancion cancion = new Cancion();

            while (ConexionBD.getRs().next()) {

                System.out.println("HOLIIIIII");
                String nombre = ConexionBD.getRs().getString("nombreCancion");
                String letra = ConexionBD.getRs().getString("letra");
                String letraTraducida = ConexionBD.getRs().getString("letraTraducida");
                int id = ConexionBD.getRs().getInt("id");
                int idAlbum = ConexionBD.getRs().getInt("idAlbum");
                int idAutor = ConexionBD.getRs().getInt("idAutor");
                int idGenero = ConexionBD.getRs().getInt("idGenero");

                //cancion.rellenarCancion(id, nombre, letra, letraTraducida, idAlbum, idAutor, idGenero);
                System.out.println(cancion.toString());
                //canciones.add(cancion);

            }
            return canciones;

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }


    }

}
