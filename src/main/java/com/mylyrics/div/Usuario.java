package com.mylyrics.div;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;


public class Usuario extends Persona {
    public static final Scanner TECLADO = new Scanner(System.in);
    private LocalDate fechaNacimiento;
    private ArrayList<Cancion> favoritos;

    public Usuario(String nombre, String password, String nombreUsuario, LocalDate fechaNacimiento) {
        super(nombre, password, nombreUsuario);
        this.fechaNacimiento = fechaNacimiento;
    }

    public Usuario() {
        super();
    }

    public boolean registrarUsuario() {
        try {
            ConexionBD bd = new ConexionBD();
            ConexionBD.setPs(bd.getConexion().prepareStatement("INSERT INTO persona (nombreUsuario, password, nombre,fechaNacimiento,isAdmin) VALUES(?,?,?,?,?)"));

            ConexionBD.getPs().setString(1, this.nombreUsuario);
            ConexionBD.getPs().setString(2, this.password);
            ConexionBD.getPs().setString(3, this.nombre);
            ConexionBD.getPs().setString(4, this.fechaNacimiento.toString());
            ConexionBD.getPs().setBoolean(5, false);
            ConexionBD.getPs().executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean guardarNombreUser(String nombreUsuario) {
        Pattern pat = Pattern.compile("^[a-zA-Z0-9]*$");
        Matcher mat = pat.matcher(nombreUsuario);
        if (mat.matches()) {
            this.nombre = nombreUsuario;
            return true;

        } else {
            return false;
        }
    }


    public void guardarNombreUsuario() {
        boolean ejecucion = false;
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("Ingrese un nombre de usuario");
            String nombreUsuario = teclado.nextLine();

            Pattern pat = Pattern.compile("^[a-zA-Z0-9]*$");
            Matcher mat = pat.matcher(nombreUsuario);
            if (mat.matches()) {
                this.nombreUsuario = nombreUsuario;
                ejecucion = true;
            }
            if (!ejecucion) {
                System.out.println("No ingrese caracteres raros, solo alfanumericos (a-z, A-Z, 0-9).");
            }
        } while (!ejecucion);

    }

    public void setPassword() {
        String pass;
        int count = 0;
        Scanner teclado = new Scanner(System.in);

        do {
            System.out.println("Ingrese la contraseña: ");
            pass = teclado.nextLine();

            if (pass.length() < 8) {
                System.out.println("La contraseña debe tener a lo menos 8 caracteres.");
            }

            for (Character x : pass.toCharArray()) {
                if (Character.isDigit(x)) {
                    count++;
                }
            }
            if (count < 2) {
                System.out.println("La contraseña debe tener a lo menos 2 números.");
            }

        } while (pass.length() < 8 || count < 2);

        this.password = pass;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setFechaNacimiento() {
        Scanner teclado = new Scanner(System.in);
        int dia;
        int mes;
        int anio;
        int edad = 0;

        do {

            System.out.println("Ingrese su fecha de nacimiento (Ej. 08/10/2021)");
            System.out.print("Día: ");
            dia = teclado.nextInt();

            System.out.print("Mes: ");
            mes = teclado.nextInt();

            System.out.print("Año: ");
            anio = teclado.nextInt();

            try {
                LocalDate nacimientoFecha = LocalDate.of(anio, mes, dia);
                this.fechaNacimiento = nacimientoFecha;
                LocalDate now = LocalDate.now();

                Period periodo = Period.between(nacimientoFecha, now);
                edad = periodo.getYears();
            } catch (DateTimeException e) {
                System.out.println("La fecha ingresada es incorrecta.");
            }

        } while (edad < 13);

    }

    public void setFechaNacimiento(int dia, int mes, int anio) {
        this.fechaNacimiento = LocalDate.of(anio, mes, dia);
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getStringFechaNacimiento() {
        int dia = this.fechaNacimiento.getDayOfMonth();
        int mes = this.fechaNacimiento.getMonthValue();
        int anio = this.fechaNacimiento.getYear();

        return dia + "-" + mes + "-" + anio;
    }

    public void mostrarFavoritas() {
        this.favoritos.stream().forEach(cancion ->
                System.out.println(cancion.getNombre() +
                        cancion.getNameAutor() +
                        cancion.getGenero() +
                        cancion.getNameAlbum())
        );
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNombre() {
        System.out.print("\nIngrese su nombre: ");
        this.nombre = new Scanner(System.in).nextLine();
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Cancion> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<Cancion> favoritos) {
        this.favoritos = (ArrayList<Cancion>) favoritos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void mostrarAutores() {
        try {
            ConexionBD bd = new ConexionBD();

            ConexionBD.setPs(bd.getConexion().prepareStatement("SELECT * FROM autor"));
            ConexionBD.setRs(ConexionBD.getPs().executeQuery());
            System.out.println("<------Autores------>");
            while (ConexionBD.getRs().next()) {
                System.out.println(ConexionBD.getRs().getString("nombreArtistico"));
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public void mostrarGeneros() {
        for (Genero genero : Genero.values()) {
            System.out.println(genero);
        }
    }

    public void mostrarAlbumes() {
        try {
            ConexionBD bd = new ConexionBD();

            ConexionBD.setPs(bd.getConexion().prepareStatement("SELECT * FROM album"));
            ConexionBD.setRs(ConexionBD.getPs().executeQuery());
            System.out.println("<------Albumes------>");
            while (ConexionBD.getRs().next()) {
                System.out.println(ConexionBD.getRs().getString("nombreAlbum"));
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public void mostrarCancionesPorNombre(String nombreCancion) {
        try {
            ConexionBD bd = new ConexionBD();

            ConexionBD.setPs(bd.getConexion().prepareStatement("SELECT * FROM cancion WHERE nombreCancion LIKE ?"));
            ConexionBD.getPs().setString(1, "%" + nombreCancion + "%");
            ConexionBD.setRs(ConexionBD.getPs().executeQuery());
            System.out.println("<------Canciones------>");
            while (ConexionBD.getRs().next()) {
                System.out.println(ConexionBD.getRs().getInt("id") + ") " + ConexionBD.getRs().getString("nombreCancion"));
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void mostrarCancionesPorAutor(String nombreAutor) {

        Autor autor = new Autor(nombreAutor);

        try {
            ConexionBD bd = new ConexionBD();

            ConexionBD.setPs(bd.getConexion().prepareStatement("SELECT * FROM cancion WHERE idAutor=?"));
            ConexionBD.getPs().setInt(1, autor.getId());
            ConexionBD.setRs(ConexionBD.getPs().executeQuery());
            System.out.println("<------Canciones------>");
            while (ConexionBD.getRs().next()) {
                System.out.println(ConexionBD.getRs().getInt("id") + ") " + ConexionBD.getRs().getString("nombreCancion"));
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public void mostrarCancionesPorGenero(String generoSelect) {
        Genero genero = Genero.buscarGenero(generoSelect);

        try {
            ConexionBD bd = new ConexionBD();

            ConexionBD.setPs(bd.getConexion().prepareStatement("SELECT * FROM cancion WHERE idGenero=?"));
            ConexionBD.getPs().setInt(1, genero.getId());

            ConexionBD.setRs(ConexionBD.getPs().executeQuery());
            System.out.println("<------Canciones------>");
            while (ConexionBD.getRs().next()) {
                System.out.println(ConexionBD.getRs().getInt("id") + ") " + ConexionBD.getRs().getString("nombreCancion"));
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public List<Cancion> mostrarCancionesPorAlbum(String albumSelect) {
        Album album = new Album(albumSelect);
        List<Cancion> listaCanciones = new ArrayList<>();

        try {
            ConexionBD bd = new ConexionBD();

            ConexionBD.setPs(bd.getConexion().prepareStatement("SELECT * FROM cancion WHERE idAlbum=?"));
            ConexionBD.getPs().setInt(1, album.getId());

            ConexionBD.setRs(ConexionBD.getPs().executeQuery());
            System.out.println("<------Canciones------>");
            while (ConexionBD.getRs().next()) {
                int id = ConexionBD.getRs().getInt("id");
                String nombre = ConexionBD.getRs().getString("nombreCancion");
                String letra = ConexionBD.getRs().getString("letra");
                String letraTraducida = ConexionBD.getRs().getString("letraTraducida");
                int idAlbum = ConexionBD.getRs().getInt("idAlbum");
                int idAutor = ConexionBD.getRs().getInt("idAutor");
                int idGenero = ConexionBD.getRs().getInt("idGenero");

                Cancion cancion = new Cancion();
                cancion.rellenarCancion(id, nombre, letra, letraTraducida, idAlbum, idAutor, idGenero);
                listaCanciones.add(cancion);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return listaCanciones;

    }

}
