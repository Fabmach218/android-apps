package com.marangon.appproyfinalaedii.entity;

public class Comentario {

    private int id;
    private String producto;
    private String usuario;
    private String comentario;
    private int fecha;

    public Comentario(int id, String producto, String usuario, String comentario, int fecha) {
        this.id = id;
        this.producto = producto;
        this.usuario = usuario;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    public Comentario(String producto, String usuario, String comentario, int fecha) {
        this.producto = producto;
        this.usuario = usuario;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }
}
