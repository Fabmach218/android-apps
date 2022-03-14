package com.marangon.applab3;

import java.io.Serializable;

public class Prenda implements Serializable {

    private long cod;
    private String nom;
    private String talla;
    private double precio;
    private String stock;

    public Prenda(long cod, String nom, String talla, double precio, String stock) {
        this.cod = cod;
        this.nom = nom;
        this.talla = talla;
        this.precio = precio;
        this.stock = stock;
    }

    public long getCod() {
        return cod;
    }

    public void setCod(long cod) {
        this.cod = cod;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

}
