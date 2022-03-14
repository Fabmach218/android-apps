package com.marangon.apparraylistintents;

import java.io.Serializable;

public class Cliente implements Serializable {

    private String ape;
    private String nom;
    private long numTelf;
    private String email;

    public Cliente(String ape, String nom, long numTelf, String email) {
        this.ape = ape;
        this.nom = nom;
        this.numTelf = numTelf;
        this.email = email;
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

    public long getNumTelf() {
        return numTelf;
    }

    public void setNumTelf(long numTelf) {
        this.numTelf = numTelf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
