package com.mylyrics.test;


import com.mylyrics.div.Cancion;
import com.mylyrics.div.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TestCancion {

    @Test
    public void comprobarFechaTest(){
        Cancion c = new Cancion();
        int año=1222;
        int mes=12;
        int dia=12;
        boolean respuesta= c.agregarFecha(año,mes,dia);
        Assertions.assertTrue(respuesta);

    }
    @Test
    public void comprobarSintaxisFechaTest(){
        Cancion c = new Cancion();
        int año=1223;
        int mes=12;
        int dia=12;
        boolean respuesta= c.agregarFecha(año,mes,dia);
        Assertions.assertTrue(respuesta);

    }
}
