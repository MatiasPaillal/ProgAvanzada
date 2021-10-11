package com.mylyrics.div;

import java.util.ArrayList;

public enum Genero {
    JAZZ(1), BLUES(2), REGGAETON(3), POP(4), ROCK(5), PUNK(6), K_POP(7),
    METAL(8), ELECTRONICA(9), DUBSTEP(10), RAP(11), HIP_HOP(12), INDEPENDIENTE(13),
    COUNTRY(14), REGGAE(15), TRAP(16);

    public int id;

    Genero(int id) {
        this.id = id;
    }

    int getId() {
        return id;
    }


    public Genero buscarGenero(String nombreGenero) {
        Genero[] generos = Genero.values();
        for (Genero genero : generos) {
            //Comparing
            if (genero.toString().equals(nombreGenero)) {
                return genero;
            }
        }
        return null;
    }
}

