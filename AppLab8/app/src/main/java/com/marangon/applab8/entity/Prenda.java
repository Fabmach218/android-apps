package com.marangon.applab8.entity;

public class Prenda {

    private int id;
    private String nombre, marca, talla, color;
    private double precio;

    public Prenda(int id, String nombre, String marca, String talla, String color, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.talla = talla;
        this.color = color;
        this.precio = precio;
    }

    public Prenda(String nombre, String marca, String talla, String color, double precio) {
        this.nombre = nombre;
        this.marca = marca;
        this.talla = talla;
        this.color = color;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
