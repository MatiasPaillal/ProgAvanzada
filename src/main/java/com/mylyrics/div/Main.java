package com.mylyrics.div;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args){
        ConexionBD con= new ConexionBD();
        Scanner teclado = new Scanner(System.in);
        Usuario user1= new Usuario();

        user1.guardarNombre();
        System.out.println(user1.getNombre());






}}
