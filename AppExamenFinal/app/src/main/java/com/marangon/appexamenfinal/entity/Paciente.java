package com.marangon.appexamenfinal.entity;

public class Paciente {

    private int id;
    private int dni;
    private String ape;
    private String nom;
    private String sintomas;
    private String antecedentes;

    public Paciente(int id, int dni, String ape, String nom, String sintomas, String antecedentes) {
        this.id = id;
        this.dni = dni;
        this.ape = ape;
        this.nom = nom;
        this.sintomas = sintomas;
        this.antecedentes = antecedentes;
    }

    public Paciente(int dni, String ape, String nom, String sintomas, String antecedentes) {
        this.dni = dni;
        this.ape = ape;
        this.nom = nom;
        this.sintomas = sintomas;
        this.antecedentes = antecedentes;
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

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }
}
