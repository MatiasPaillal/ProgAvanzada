package com.mylyrics.div;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;


public class Usuario extends Persona {
    private LocalDate fechaNacimiento;
    private ArrayList<Cancion> favoritos;

    public Usuario(String nombre, String password, String nombreUsuario, LocalDate fechaNacimiento, ArrayList<Cancion> favoritos) {
        super(nombre, password, nombreUsuario);
        this.fechaNacimiento = fechaNacimiento;
        this.favoritos = favoritos;
    }

    public Usuario(String nombre, String password, String nombreUsuario) {
        super(nombre, password, nombreUsuario);
    }

    public Usuario() {
        super();
    }

    public boolean cambiarNombre(String nombreUsuario) {
        boolean ejecucion = false;
        Pattern pat = Pattern.compile("^[a-zA-Z0-9]*$");
        Matcher mat = pat.matcher(nombreUsuario);
        if (mat.matches()) {
            this.nombre = nombreUsuario;
            return true;

        } else {
            return false;
        }
    }

    public void guardarNombre() {
        boolean ejecucion = false;
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("Ingrese nuevo Nombre");
            String nombreUsuario = teclado.next();

            Pattern pat = Pattern.compile("^[a-zA-Z0-9]*$");
            Matcher mat = pat.matcher(nombreUsuario);
            if (mat.matches()) {
                this.nombre = nombreUsuario;
                ejecucion = true;

            } else {
                ejecucion = false;
            }
        } while (ejecucion == false);

    }


    public void setPassword() {
        String pass;
        int count = 0;
        Scanner teclado = new Scanner(System.in);

        do {
            System.out.println("Ingrese la contraseña: ");
            pass = teclado.nextLine();

            if (pass.length() < 8) {
                System.out.println("La contraseña debe tener a lo menos 8 caracteres.");
            }

            for (Character x : this.getPassword().toCharArray()) {
                if (Character.isDigit(x)) {
                    count++;
                }
            }
            if (count < 2) {
                System.out.println("La contraseña debe tener a lo menos 2 números.");
            }

        } while (pass.length() < 8 && count < 2);

        this.password = pass;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setFechaNacimiento() {
        Scanner teclado = new Scanner(System.in);
        int dia, mes, anio, edad = 0;

        do {

            System.out.println("Ingrese su fecha de nacimiento (Ej. 08/10/2021)");
            System.out.print("Día: ");
            dia = teclado.nextInt();

            System.out.print("Mes: ");
            mes = teclado.nextInt();

            System.out.print("Año: ");
            anio = teclado.nextInt();

            try {
                LocalDate fechaNacimiento = LocalDate.of(anio, mes, dia);
                LocalDate now = LocalDate.now();

                Period periodo = Period.between(fechaNacimiento, now);
                edad = periodo.getYears();
            } catch (DateTimeException e) {
                System.out.println("La fecha ingresada es incorrecta.");
            }


        } while (edad >= 13);

    }

    public void setFechaNacimiento(int dia, int mes, int anio) {
        this.fechaNacimiento = LocalDate.of(anio, mes, dia);
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getStringFechaNacimiento() {
        int dia, mes, anio;
        dia = this.fechaNacimiento.getDayOfMonth();
        mes = this.fechaNacimiento.getMonthValue();
        anio = this.fechaNacimiento.getYear();
        return dia + "/" + mes + "/" + anio;
    }


    public void MostrarPlaylist() {
        this.favoritos.stream().forEach((p) -> {
            System.out.println(p.getNombre() + p.getNameAutor() + p.getGenero() + p.getNameAlbum());
        });

    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public ArrayList<Cancion> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(ArrayList<Cancion> favoritos) {
        this.favoritos = favoritos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }


}
