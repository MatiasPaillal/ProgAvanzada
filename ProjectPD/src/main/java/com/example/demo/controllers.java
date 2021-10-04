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
    
    Producto PRO1= new Producto("1","2","3","5");
    Producto PRO2= new Producto("1","2","3","5");
    Producto PRO3= new Producto("1","2","3","5");
    Producto PRO4= new Producto("1","2","3","5");
    ArrayList<Producto> items= new ArrayList<Producto>();
    Carro carro= new Carro(items,"yo");
    
    Boleta boleta= new Boleta("a","a","a","a","a","a","a");

    @GetMapping("/")
    String Saludar() {
         
        return "Saludar";
    }

    @GetMapping("/fondo")
    String Cliente_Categorias() {
        return "Cliente_Categorias";
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
    @GetMapping("/Admin_Boletas")
    String mostrarBoletasAdmin() {
        return "Admin_Boletas";
    }
    @GetMapping("/Boletas")
    String  Boleta(@RequestParam (name="Boleta",required=false, defaultValue = "asda") Boleta boleta, Model modelo) {
        modelo.addAttribute(this.boleta);
        return "Boleta";
    }
    @GetMapping("/AgregarProducto")
    String  agregarProducto() {
        return "Admin_AgregarP";
    }
     

    @RequestMapping(value = "consultaAdmin", method = RequestMethod.POST)
    public String consultaAdmin(String name, String password) {
        if ("1".equals(name) && "1".equals(password)) {
            return "Admin_Opciones";}
         
            return "IngresarAdmin";
         
    }
    
    @RequestMapping(value = "buscarBoleta", method = RequestMethod.POST)
    public String buscarBoleta(String numero) {
        if ("11111".equals(numero)) {
            return "Boleta";}
         
            return "Admin_Boletas";
         
    }

}
