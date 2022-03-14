package com.marangon.applab7;

import java.io.Serializable;

public class Prenda implements Serializable {

    private String nom;
    private int idFoto;
    private String talla;
    private double precio;
    private int stock;

    public Prenda(String nom, int idFoto, String talla, double precio, int stock) {
        this.nom = nom;
        this.idFoto = idFoto;
        this.talla = talla;
        this.precio = precio;
        this.stock = stock;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
