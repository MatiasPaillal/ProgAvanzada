package com.mylyrics.div;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Usuario {

    private String nombre;
    private String contraseña;
    private String nombreUsuario;


    public Usuario()  {
        this.nombre = null;
        this.contraseña = null;
        this.nombreUsuario = null;
    }
    public boolean cambiarNombre(String nombreUsuario){
        boolean ejecucion=false;
        Pattern pat = Pattern.compile("^[a-zA-Z0-9]*$");
        Matcher mat = pat.matcher(nombreUsuario);
        if (mat.matches()) {
            this.nombre=nombreUsuario;
            return true;

        } else {
            return false;
        }
  }
  /*
    public boolean cambiarNombre2(){
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese nuevo Nombre");
        String nombreUsuario= teclado.next();

        boolean ejecucion=false;
        do {
            Pattern pat = Pattern.compile("^[a-zA-Z0-9]*$");
            Matcher mat = pat.matcher(nombreUsuario);
            if (mat.matches()) {
                this.nombre = nombreUsuario;
                return true;

            }
        }while(ejecucion==false);
        return ejecucion;
    }

*/



}
