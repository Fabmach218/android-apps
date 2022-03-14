package com.marangon.applab5;

public class Trabajador {

    private String ape;
    private String nom;
    private char seg;
    private char gen;
    private double sBasico;
    private double sNeto;

    public Trabajador(String ape, String nom, char seg, char gen, double sBasico, double sNeto) {
        this.ape = ape;
        this.nom = nom;
        this.seg = seg;
        this.gen = gen;
        this.sBasico = sBasico;
        this.sNeto = sNeto;
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

    public char getSeg() {
        return seg;
    }

    public void setSeg(char seg) {
        this.seg = seg;
    }

    public char getGen() {
        return gen;
    }

    public void setGen(char gen) {
        this.gen = gen;
    }

    public double getsBasico() {
        return sBasico;
    }

    public void setsBasico(double sBasico) {
        this.sBasico = sBasico;
    }

    public double getsNeto() {
        return sNeto;
    }

    public void setsNeto(double sNeto) {
        this.sNeto = sNeto;
    }
}
