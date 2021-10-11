package com.mylyrics.div;


import java.sql.*;

/**
 * @author matias
 */


class ConexionBD {

    public static final String usuario = "root";
    public static final String clave = "";
    public static final String url = "jdbc:mysql://localhost:3306/mylyrics";
    public static final String driver = "com.mysql.jdbc.Driver";
    public static Connection con;
    public static PreparedStatement ps;
    public static ResultSet rs;


    public ConexionBD() {
        con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, usuario, clave);

        } catch (ClassNotFoundException | SQLException e) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", usuario, clave);
                ps = con.prepareStatement("CREATE DATABASE mylyrics");
                ps.executeUpdate();
                con = DriverManager.getConnection(url, usuario, clave);
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
                        "id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "nombreArtistico VARCHAR(50) NOT NULL)");
                ps.executeUpdate();
                ps = con.prepareStatement("" +
                        "CREATE TABLE album (" +
                        "id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY," +
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
                        "id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY," +
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
                System.out.println(ex.getMessage());
                System.err.println("Error al crear la base de datos");
            }
        }
    }

    public Connection getConexion() {
        return con;
    }

    public void desconectar() {
        con = null;
        if (con == null) {
            System.out.println("conexion terminada");
        }
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
