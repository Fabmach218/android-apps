package com.marangon.restcrud.model;

import java.util.ArrayList;

public class AlumnoRespuesta {
    private ArrayList<Alumno> alumnosLista;
    private String mensaje;

    public ArrayList<Alumno> getAlumnosLista() {
        return alumnosLista;
    }

    public void setAlumnosLista(ArrayList<Alumno> alumnosLista) {
        this.alumnosLista = alumnosLista;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
