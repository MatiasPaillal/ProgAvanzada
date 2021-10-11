package com.mylyrics.div;

public enum Genero {
    ROCK(1), POP(2);

    private int id;

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

