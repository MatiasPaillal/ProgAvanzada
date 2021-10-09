package com.mylyrics.div;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Scanner;

public class Cancion {


    private LocalDate fecha;
    private String nombre;
    private String letra;

    public Cancion() {
        this.fecha = null;
        this.nombre = null;
        this.letra = null;
    }

    public boolean agregarFecha(int año, int mes, int dia) {


        if (año<LocalDate.now().getYear()&& mes<13 && dia<32) {
            LocalDate fechaIngresada = LocalDate.of(año, mes, dia);
            LocalDate fechaActual = LocalDate.now();

            if (fechaIngresada.isBefore(fechaActual)) {
                this.fecha=fechaIngresada;
                return true;

            } else {
                return false;
            }
        }else{
            return false;}

    }
    }
