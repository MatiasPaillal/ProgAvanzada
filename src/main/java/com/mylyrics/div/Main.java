package com.mylyrics.div;

import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    public static Scanner teclado = new Scanner(System.in);

    public static Object iniciarSesion() {
        boolean isAdmin = false, login = false;
        String nombreUsuario, password = "", nombre = "";
        LocalDate fechaNacimiento = LocalDate.now();

        do {
            System.out.print("\nIngrese su nombre de usuario: ");
            nombreUsuario = teclado.nextLine();

            System.out.print("\nIngrese su contraseña: ");
            password = teclado.nextLine();

            try {
                ConexionBD bd = new ConexionBD();
                bd.setPs(bd.getConexion().prepareStatement("SELECT * FROM persona WHERE nombreUsuario = ?"));
                bd.getPs().setString(1, nombreUsuario);
                bd.setRs(bd.getPs().executeQuery());

                if (bd.getRs().next()) {
                    isAdmin = bd.getRs().getBoolean("isAdmin");
                    nombre = bd.getRs().getString("nombre");
                    if (!isAdmin) {
                        fechaNacimiento = bd.getRs().getDate("fechaNacimiento").toLocalDate();
                    }

                    if (password.equals(bd.getRs().getString("password"))) {
                        login = true;
                    } else {
                        System.out.println("Contraseña Incorrecta.");
                    }
                }
            } catch (Exception e) {
                System.out.println("Error al encontrar el usuario.");
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
        int opcion = teclado.nextInt();
        teclado.nextLine();

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
                System.out.println("1) Agregar un album");
                System.out.println("2) Agregar una canción");
                System.out.println("3) Agregar un Autor");
                System.out.println("4) Agregar una Traducción");
                System.out.println("5) Cambiar nombre de un Autor");
                System.out.println("0) Cerrar Sesión");
                opcion = TECLADO.nextInt();
                TECLADO.nextLine();
            } while (opcion > 5 || opcion < 0);

            switch (opcion) {
                case 0:
                    exit = true;
                    break;
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


    }

    public static void main(String[] args) {
        Object usuario = menu();

        if (usuario instanceof Administrador) {
            menuAdmin((Administrador) usuario);
        } else {
            menuUsuario((Usuario) usuario);
        }
    }
}
