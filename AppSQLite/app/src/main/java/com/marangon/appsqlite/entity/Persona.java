package com.marangon.appsqlite.entity;

public class Persona {

    private int id, dni;
    private String ape, nom, email;

    public Persona(int id, int dni, String ape, String nom, String email) {
        this.id = id;
        this.dni = dni;
        this.ape = ape;
        this.nom = nom;
        this.email = email;
    }

    public Persona(int dni, String ape, String nom, String email) {
        this.dni = dni;
        this.ape = ape;
        this.nom = nom;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getApe() {
        return ape;
    }

    public void setApe(String ape) {
        this.ape = ape;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
