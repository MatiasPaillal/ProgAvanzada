/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 *
 * @author matias
 */
@Controller
public class saludo {
    @GetMapping("/")
        String Saludar() {
        return "Saludar";
    }
    @GetMapping("/fondo")
    String Cliente_Categorias(){
        return "Cliente_Categorias";
    }
    
    @GetMapping("/Ingresar")
    String Ingresar(){
        return "Ingresar";
    }
}

    

