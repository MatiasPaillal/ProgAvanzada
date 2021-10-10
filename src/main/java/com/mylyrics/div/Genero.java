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
}
