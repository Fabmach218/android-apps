package com.marangon.appproyfinalaedii.entity;

public class Carrito {

    private int id;
    private String usuario;
    private String producto;
    private double precio;
    private int cantidad;
    private double importe;

    public Carrito(int id, String usuario, String producto, double precio, int cantidad, double importe) {
        this.id = id;
        this.usuario = usuario;
        this.producto = producto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.importe = importe;
    }

    public Carrito(String usuario, String producto, double precio, int cantidad, double importe) {
        this.usuario = usuario;
        this.producto = producto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.importe = importe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }
}
