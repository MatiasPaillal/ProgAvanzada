package com.mylyrics.test;


import com.mylyrics.div.Album;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class TestCancion {

    @Test
    void comprobarFechaTest() {
        Album c = new Album();
        int anio = 1222;
        int mes = 12;
        int dia = 12;
        boolean respuesta = c.agregarFecha(anio, mes, dia);
        Assertions.assertTrue(respuesta);

    }

    @Test
    void comprobarSintaxisFechaTest() {
        Album c = new Album();
        int anio = 1223;
        int mes = 12;
        int dia = 12;
        boolean respuesta = c.agregarFecha(anio, mes, dia);
        Assertions.assertTrue(respuesta);

    }
}
