package com.marangon.appproyfinalaedii.entity;

public class Usuario {

    private int id;
    private byte[] foto;
    private String nom;
    private String ape;
    private String email;
    private String password;
    private int log;

    public Usuario(int id, byte[] foto, String nom, String ape, String email, String password, int log) {
        this.id = id;
        this.foto = foto;
        this.nom = nom;
        this.ape = ape;
        this.email = email;
        this.password = password;
        this.log = log;
    }

    public Usuario(byte[] foto, String nom, String ape, String email, String password, int log) {
        this.foto = foto;
        this.nom = nom;
        this.ape = ape;
        this.email = email;
        this.password = password;
        this.log = log;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getApe() {
        return ape;
    }

    public void setApe(String ape) {
        this.ape = ape;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLog() {
        return log;
    }

    public void setLog(int log) {
        this.log = log;
    }
}