/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

/**
 *
 * @author matias
 */
public class Boleta {

    private String nombreTienda;
    private String telefonoTienda;
    private String direccionTienda;
    private String nroBoleta;
    private String rutTienda;
    private String fechaEmision;
    private String total;

    public Boleta(String nombreTienda, String telefonoTienda, String direccionTienda, String nroBoleta, String rutTienda, String fechaEmision, String total) {
        this.nombreTienda = nombreTienda;
        this.telefonoTienda = telefonoTienda;
        this.direccionTienda = direccionTienda;
        this.nroBoleta = nroBoleta;
        this.rutTienda = rutTienda;
        this.fechaEmision = fechaEmision;
        this.total = total;
    }
    

    public String getNombreTienda() {
        return nombreTienda;
    }

    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }

    public String getTelefonoTienda() {
        return telefonoTienda;
    }

    public void setTelefonoTienda(String telefonoTienda) {
        this.telefonoTienda = telefonoTienda;
    }

    public String getDireccionTienda() {
        return direccionTienda;
    }

    public void setDireccionTienda(String direccionTienda) {
        this.direccionTienda = direccionTienda;
    }

    public String getNroBoleta() {
        return nroBoleta;
    }

    public void setNroBoleta(String nroBoleta) {
        this.nroBoleta = nroBoleta;
    }

    public String getRutTienda() {
        return rutTienda;
    }

    public void setRutTienda(String rutTienda) {
        this.rutTienda = rutTienda;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    private String items;

    public Boleta(String nombreTienda, String telefonoTienda, String direccionTienda, String nroBoleta, String rutTienda, String fechaEmision, String total, String items) {
        this.nombreTienda = nombreTienda;
        this.telefonoTienda = telefonoTienda;
        this.direccionTienda = direccionTienda;
        this.nroBoleta = nroBoleta;
        this.rutTienda = rutTienda;
        this.fechaEmision = fechaEmision;
        this.total = total;
        this.items = items;
    }

}
