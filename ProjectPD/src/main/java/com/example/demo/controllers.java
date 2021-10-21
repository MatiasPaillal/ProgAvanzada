/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author matias
 */
@Controller
public class controllers {

    Boleta boleta = new Boleta("a", "a", "a", "a", "a", "a", "a");

    @GetMapping("/")
    String LocalHost() {

        return "Ingresar";
    }

    @GetMapping("/Cliente_Categorias")
    String Cliente_Categorias() {
        return "Cliente_Categorias";
    }

    @GetMapping("/Cliente_Productos")
    String Cliente_Productos() {
        return "Cliente_Productos";
    }

    @GetMapping("/Cliente_ProductoSeleccionado")
    String Cliente_ProductoSeleccionado() {
        return "Cliente_ProductoSeleccionado";
    }

    @GetMapping("/Cliente_Boleta")
    String Cliente_Boleta() {
        return "Cliente_Boleta";
    }

    @GetMapping("/Ingresar")
    String Ingresar() {
        return "Ingresar";
    }

    @GetMapping("/Admin")
    String IngresarAdmin() {
        return "IngresarAdmin";
    }

    @GetMapping("/Admin_Categorias")
    String desplegarOpcionesAdmin() {
        return "Admin_Categorias";
    }

    @GetMapping("/Admin_BuscarBoletas")
    String mostrarBoletasAdmin() {
        return "Admin_BuscarBoletas";
    }

    @GetMapping("/Boletas")
    String Boleta(@RequestParam(name = "Boleta", required = false, defaultValue = "asda") Boleta boleta, Model modelo) {
        modelo.addAttribute(this.boleta);
        return "Boleta";
    }

    @GetMapping("/AgregarProducto")
    String agregarProducto() {
        return "Admin_AgregarP";
    }

    @GetMapping("/Admin_Productos")
    String mostrarProductoAdmin() {
        return "Admin_Productos";
    }
    @GetMapping("/Admin_ProductoSeleccionado")
    String mostrarProductoSeleccionadoAdmin(Model modelo) {
         
        return "Admin_ProductoSeleccionado";
    }

    @RequestMapping(value = "consultaAdmin", method = RequestMethod.POST)
    public String consultaAdmin(String name, String password) {
        if ("1".equals(name) && "1".equals(password)) {
            return "Admin_Opciones";
        }

        return "IngresarAdmin";

    }

    @RequestMapping(value = "buscarBoleta", method = RequestMethod.POST)
    public String buscarBoleta(String numero) {
        if ("11111".equals(numero)) {
            return "Admin_Boleta";
        }

        return "Admin_BuscarBoletas";

    }

    @RequestMapping(value = "opciones")
    public String guardarProducto(String nombre) {

        return "Admin_Opciones";

    }

    @RequestMapping(value = "productos")
    public String verProductoAdmin(String nombre) {

        return "Admin_Productos";

    }

}
