package com.mylyrics.div;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class Usuario {
    private String password;
    private LocalDate fechaNacimiento;

    public Usuario() {

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

}
