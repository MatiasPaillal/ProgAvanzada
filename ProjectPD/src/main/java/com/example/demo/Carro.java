/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import com.example.demo.Admin.modelo.ProductoModel;
import java.util.ArrayList;

/**
 *
 * @author matias
 */
public class Carro {
private ArrayList<ProductoModel> productos;

    public Carro(ArrayList<ProductoModel> productos) {
        this.productos = productos;
    }

    public ArrayList<ProductoModel> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<ProductoModel> productos) {
        this.productos = productos;
    }

    public void agregarProducto(ProductoModel producto){
        productos.add(producto);
    
    }
    public void quitarProducto(ProductoModel producto){
        productos.remove(producto);
    
    }

    public Carro() {
    }
    

}
