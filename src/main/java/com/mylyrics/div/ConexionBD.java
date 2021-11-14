package com.mylyrics.div;

import java.sql.*;

class ConexionBD {

    private static final String USUARIO = "root";
    private static final String CLAVE = "";
    private static final String URL = "jdbc:mysql://localhost:3306/mylyrics";
    private static final String COMMON_ID = "id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,";
    private static Connection con = null;
    private static PreparedStatement ps;
    private static ResultSet rs;


    public ConexionBD() {
        try {
            con = DriverManager.getConnection(URL, USUARIO, CLAVE);

        } catch (SQLException e) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", USUARIO, CLAVE);
                ps = con.prepareStatement("CREATE DATABASE mylyrics");
                ps.executeUpdate();
                con = DriverManager.getConnection(URL, USUARIO, CLAVE);
                ps = con.prepareStatement("" +
                        "CREATE TABLE persona (" +
                        "nombreUsuario VARCHAR(50) PRIMARY KEY NOT NULL," +
                        "password VARCHAR(50) NOT NULL," +
                        "nombre VARCHAR(50) NOT NULL," +
                        "fechaNacimiento DATE," +
                        "isAdmin BIT NOT NULL)");
                ps.executeUpdate();
                ps = con.prepareStatement("" +
                        "CREATE TABLE autor (" +
                        COMMON_ID +
                        "nombreArtistico VARCHAR(50) NOT NULL)");
                ps.executeUpdate();
                ps = con.prepareStatement("" +
                        "CREATE TABLE album (" +
                        COMMON_ID +
                        "nombreAlbum VARCHAR(50) NOT NULL," +
                        "fechaEstreno DATE NOT NULL," +
                        "idAutor BIGINT UNSIGNED NOT NULL," +
                        "CONSTRAINT fk_Autor FOREIGN KEY (idAutor) REFERENCES autor (id))");
                ps.executeUpdate();
                ps = con.prepareStatement("" +
                        "CREATE TABLE genero (" +
                        "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "nombreGenero VARCHAR(50) NOT NULL)");
                ps.executeUpdate();
                ps = con.prepareStatement("" +
                        "CREATE TABLE cancion (" +
                        COMMON_ID +
                        "nombreCancion VARCHAR(50) NOT NULL," +
                        "letra MEDIUMTEXT NOT NULL," +
                        "letraTraducida MEDIUMTEXT," +
                        "idAlbum BIGINT UNSIGNED NOT NULL," +
                        "idAutor BIGINT UNSIGNED NOT NULL," +
                        "idGenero INT NOT NULL," +
                        "CONSTRAINT fk_Album FOREIGN KEY (idAlbum) REFERENCES album (id)," +
                        "CONSTRAINT fk_Autor2 FOREIGN KEY (idAutor) REFERENCES autor (id)," +
                        "CONSTRAINT fk_Genero FOREIGN KEY (idGenero) REFERENCES genero (id))");
                ps.executeUpdate();
                ps = con.prepareStatement("" +
                        "CREATE TABLE cancionesFavoritas (" +
                        "nombreUsuarioPersona VARCHAR(50) NOT NULL," +
                        "idCancion BIGINT UNSIGNED NOT NULL," +
                        "PRIMARY KEY (nombreUsuarioPersona, idCancion)," +
                        "CONSTRAINT fk_Persona FOREIGN KEY (nombreUsuarioPersona) REFERENCES persona (nombreUsuario)," +
                        "CONSTRAINT fk_Cancion FOREIGN KEY (idCancion) REFERENCES cancion (id))");
                ps.executeUpdate();
                ps = con.prepareStatement("" +
                        "INSERT INTO genero VALUES(NULL,'JAZZ'),(NULL,'BLUES'),(NULL,'REGGAETON'),(NULL,'POP'),(NULL,'ROCK')," +
                        "(NULL,'PUNK'),(NULL,'K-POP'),(NULL,'METAL'),(NULL,'ELECTRONICA'),(NULL,'DUBSTEP'),(NULL,'RAP'),(NULL,'HIP-HOP')," +
                        "(NULL,'INDEPENDIENTE'),(NULL,'COUNTRY'),(NULL,'REGGAE'),(NULL,'TRAP')");
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                System.err.println("Error al crear la base de datos");
            }
        }
    }

    public Connection getConexion() {
        return con;
    }

    public void desconectar() {
        con = null;
        System.out.println("Conexión Terminada");
    }

    public static PreparedStatement getPs() {
        return ps;
    }

    public static void setPs(PreparedStatement ps) {
        ConexionBD.ps = ps;
    }

    public static ResultSet getRs() {
        return rs;
    }

    public static void setRs(ResultSet rs) {
        ConexionBD.rs = rs;
    }
}
