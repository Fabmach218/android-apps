package com.marangon.applab6;

import java.io.Serializable;

public class Plato implements Serializable {

    private int id, idFoto, stock;
    private String nom, desc;
    private double precio;

    public Plato(int id, int idFoto, int stock, String nom, String desc, double precio) {
        this.id = id;
        this.idFoto = idFoto;
        this.stock = stock;
        this.nom = nom;
        this.desc = desc;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
