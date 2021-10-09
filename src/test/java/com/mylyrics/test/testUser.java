package com.mylyrics.test;

import com.mylyrics.div.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class testUser {

@Test
public void setNombreTest(){

    Usuario usuario= new Usuario();
    boolean nose= usuario.cambiarNombre("holaaaa");
    Assertions.assertTrue(nose);
}



}
