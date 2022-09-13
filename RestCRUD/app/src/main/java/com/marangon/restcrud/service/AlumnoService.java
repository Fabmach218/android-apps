package com.marangon.restcrud.service;

import com.marangon.restcrud.model.Alumno;
import com.marangon.restcrud.model.AlumnoRespuesta;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AlumnoService {
    @GET("listar")
    Call<AlumnoRespuesta> getAllAlumnos();

    @POST("guardar")
    Call<AlumnoRespuesta> insertAlumno(@Body Alumno alumno);
}
