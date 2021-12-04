package com.mylyrics.test;

import com.mylyrics.div.Persona;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;


class testUser {
    public static Persona user;

    @BeforeAll
    static void setUp() {
        user = new Persona();
    }

    @Test
    void setNombreTest() {

        Persona usuario = new Persona();
        boolean respuesta = usuario.guardarNombreUser("matias");
        Assertions.assertTrue(respuesta);
    }


    @Test
    void verificarLargoPassword() {
        //  La contraseña deberá tener a lo menos 8 caracteres.
        user.setPassword("holahola");
        int largoPass = user.getPassword().length();

        Assertions.assertTrue(largoPass >= 8);
    }

    @Test
    void verificarNumsPassword() {
        //  Debe tener a lo menos dos números.
        user.setPassword("holaasd12");

        int count = 0;
        for (Character x : user.getPassword().toCharArray()) {
            if (Character.isDigit(x)) {
                count++;
            }
        }

        Assertions.assertTrue(count >= 2);
    }

    @Test
    void verificarEdad() {
        //El Usuario debe tener más de 13 años.
        int anio, mes, dia, edad;
        anio = 2002;
        mes = 04;
        dia = 02;

        user.setFechaNacimiento(dia, mes, anio);
        LocalDate now = LocalDate.now();

        Period periodo = Period.between(user.getFechaNacimiento(), now);
        edad = periodo.getYears();

        Assertions.assertTrue(edad >= 13);
    }

    @Test
    void verificarFecha() {
        //Verificar que la fecha ingresada sea correcta.
        boolean isCorrect;
        int anio, mes, dia;
        anio = 2002;
        mes = 02;
        dia = 28;

        try {
            user.setFechaNacimiento(dia, mes, anio);
            isCorrect = true;
        } catch (DateTimeException e) {
            isCorrect = false;
        }

        Assertions.assertTrue(isCorrect);
    }

}
