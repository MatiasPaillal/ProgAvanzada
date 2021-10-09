package com.mylyrics.div;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author matias
 */


class ConexionBD {

    public static final String usuario = "root";
    public static final String clave = "";
    public static final String url = "jdbc:mysql://localhost:3306/mylyrics";
    public static final String driver = "com.mysql.jdbc.Driver";
    public static Connection con;


    public ConexionBD() {
        con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, usuario, clave);
            if (con != null) {
                System.out.println("conexion establecida");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("error al conectar");
        }
    }


    public Connection getConexion() {

        return con;

    }

    public void desconectar() {
        con = null;
        if (con == null) {
            System.out.println("conexion termindad");
        }
    }
}
