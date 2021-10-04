/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.util.ArrayList;

/**
 *
 * @author matias
 */
public class Carro {
    private ArrayList<Producto> items;
    private String dueño;

    public Carro(ArrayList<Producto> items, String dueño) {
        this.items = items;
        this.dueño = dueño;
    }

     

    public ArrayList<Producto> getItems() {
        return items;
    }

    public void setItems(ArrayList<Producto> items) {
        this.items = items;
    }
    public void guardarProducto(Producto producto){
        this.items.add(producto);
    
    }
}
