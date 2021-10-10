package com.mylyrics.div;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static Scanner teclado = new Scanner(System.in);

    public static Object iniciarSesion() {
        boolean isAdmin = false, login = false;
        String nombreUsuario, password, nombre = "";

        do {
            System.out.print("\nIngrese su nombre de usuario: ");
            nombreUsuario = teclado.nextLine();

            System.out.print("\nIngrese su contraseña: ");
            password = teclado.nextLine();

            try {
                ConexionBD bd = new ConexionBD();
                bd.setPs(bd.getConexion().prepareStatement("SELECT * FROM persona WHERE nombreUsuario = " + nombreUsuario));
                bd.setRs(bd.getPs().executeQuery());

                if (bd.getRs().next()) {
                    isAdmin = bd.getRs().getBoolean("isAdmin");
                    if (password.equals(bd.getRs().getString("password"))) {
                        login = true;
                    } else {
                        System.out.println("Contraseña Incorrecta.");
                    }
                }
            } catch (Exception e) {
                System.err.println("Error al encontrar el usuario.");
            }
        } while (!login);

        return (isAdmin) ? new Administrador(nombre, password, nombreUsuario) : new Usuario(nombre, password, nombreUsuario);
    }

    public static void registrarUsuario() {

    }

    public static void main(String[] args) {
        ConexionBD con = new ConexionBD();
        Scanner teclado = new Scanner(System.in);
        Usuario user1 = new Usuario();

        user1.guardarNombre();
        System.out.println(user1.getNombre());

    }
}
