package com.mylyrics.test;


import com.mylyrics.div.Album;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TestCancion {

    @Test
    public void comprobarFechaTest(){
        Album c = new Album();
        int anio=1222;
        int mes=12;
        int dia=12;
        boolean respuesta= c.agregarFecha(anio,mes,dia);
        Assertions.assertTrue(respuesta);

    }
    @Test
    public void comprobarSintaxisFechaTest(){
        Album c = new Album();
        int anio=1223;
        int mes=13;
        int dia=12;
        boolean respuesta= c.agregarFecha(anio,mes,dia);
        Assertions.assertTrue(respuesta);

    }
}
