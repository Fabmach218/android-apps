package com.marangon.appfragment;

public class Presidente {

    private int id;
    private String nom, partido, periodo;
    private int idFoto;

    public Presidente(int id, String nom, String partido, String periodo, int idFoto) {
        this.id = id;
        this.nom = nom;
        this.partido = partido;
        this.periodo = periodo;
        this.idFoto = idFoto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }

}
