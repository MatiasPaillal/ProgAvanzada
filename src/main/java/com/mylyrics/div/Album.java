package com.mylyrics.div;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Album {
    public static final Scanner TECLADO = new Scanner(System.in);
    private int id;
    private String nombre;
    private Autor autor;
    private ArrayList<Cancion> canciones = new ArrayList<>();
    private LocalDate fecha;


    public Album() {
    }

    public Album(int id) {
        this.id = id;
        rellenarAlbumNombre();
    }

    public Album(String nombre, Autor autor) {
        this.nombre = nombre;
        this.autor = autor;
        this.fecha = null;
    }

    public Album(String nombre) {
        this.nombre = nombre;
        agregarId();
    }

    public boolean agregarFecha(int anio, int mes, int dia) {

        if (anio <= LocalDate.now().getYear() && mes < 13 && dia < 32) {
            LocalDate fechaIngresada = LocalDate.of(anio, mes, dia);

            if (fechaIngresada.isBefore(LocalDate.now())) {
                this.fecha = fechaIngresada;
                return true;
            }
        }
        return false;
    }

    public String getNombre() {
        return this.nombre;
    }

    public boolean ingresarAlbum() {
        try {
            ConexionBD bd = new ConexionBD();

            ConexionBD.setPs(bd.getConexion().prepareStatement("INSERT INTO album (nombreAlbum, fechaEstreno, idAutor) VALUES(?,?,?)"));

            ConexionBD.getPs().setString(1, this.nombre);
            ConexionBD.getPs().setString(2, String.valueOf(this.fecha));
            ConexionBD.getPs().setInt(3, this.autor.getId());


            ConexionBD.getPs().executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }

    }

    public void agregarId() {

        try {
            ConexionBD bd = new ConexionBD();

            ConexionBD.setPs(bd.getConexion().prepareStatement("SELECT * FROM album WHERE nombreAlbum = ?"));
            ConexionBD.getPs().setString(1, this.nombre);

            ConexionBD.setRs(ConexionBD.getPs().executeQuery());

            if (ConexionBD.getRs().next()) {
                this.id = ConexionBD.getRs().getInt("id");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public void rellenarAlbumNombre() {
        try {
            ConexionBD bd = new ConexionBD();

            bd.setPs(bd.getConexion().prepareStatement("SELECT * FROM album WHERE id = ?"));
            bd.getPs().setInt(1, this.id);

            bd.setRs(bd.getPs().executeQuery());

            if (bd.getRs().next()) {
                this.nombre = bd.getRs().getString("nombreAlbum");
                this.fecha = LocalDate.parse(bd.getRs().getString("fechaEstreno"));
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void formularioAlbum(String nombreAutor, String nombreAlbum) {

        Autor autor = new Autor(nombreAutor);
        Album album = new Album(nombreAlbum, autor);
        boolean bol = false;
        int anio;
        int mes;
        int dia;
        do {
            System.out.println("año");
            anio = TECLADO.nextInt();
            System.out.println("mes");
            mes = TECLADO.nextInt();
            System.out.println("dia");
            dia = TECLADO.nextInt();
            TECLADO.nextLine(); //Limpiar Buffer del Scanner
            bol = album.agregarFecha(anio, mes, dia);
        } while (!bol);

        album.ingresarAlbum();
    }

    public int getId() {
        return id;
    }

}
