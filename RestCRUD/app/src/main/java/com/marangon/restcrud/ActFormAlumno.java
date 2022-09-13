package com.marangon.restcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.marangon.restcrud.model.Alumno;
import com.marangon.restcrud.model.AlumnoRespuesta;
import com.marangon.restcrud.service.AlumnoService;
import com.marangon.restcrud.utils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActFormAlumno extends AppCompatActivity {

    private Retrofit retrofit;

    TextView txvId;
    EditText edtNombre, edtApePat, edtApeMat, edtFecNac, edtCorreo;
    Spinner sprSexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_form_alumno);

        retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        asignarReferencias();
    }

    public void asignarReferencias(){
        txvId = findViewById(R.id.txvId);
        edtNombre = findViewById(R.id.edtNombre);
        edtApePat = findViewById(R.id.edtApePat);
        edtApeMat = findViewById(R.id.edtApeMat);
        edtFecNac = findViewById(R.id.edtFecNac);
        sprSexo = findViewById(R.id.sprSexo);
        edtCorreo = findViewById(R.id.edtCorreo);

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        edtFecNac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(ActFormAlumno.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {

                        month = month + 1;

                        String fecha = "";

                        if(day < 10){
                            fecha += "0" + day;
                        }else{
                            fecha += day;
                        }

                        fecha += "/";

                        if(month < 10){
                            fecha += "0" + month;
                        }else{
                            fecha += month;
                        }

                        fecha += "/" + year;
                        edtFecNac.setText(fecha);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

    }

    public void regresarLista(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void guardarAlumno(View v){

        int id = Integer.parseInt(txvId.getText() + "");
        String nombre = edtNombre.getText() + "";
        String apePat = edtApePat.getText() + "";
        String apeMat = edtApeMat.getText() + "";
        Date fecNac = new Date();

        try {
            fecNac = new SimpleDateFormat("dd/MM/yyyy").parse(edtFecNac.getText() + "");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String sexo = "";

        switch(sprSexo.getSelectedItemPosition()){
            case 1: sexo = "M"; break;
            case 2: sexo = "F"; break;
            default: sexo = "";
        }

        String correo = edtCorreo.getText() + "";

        String validaciones = "";

        if(nombre.equals("")){
            validaciones += "El nombre es obligatorio!!!\n";
        }

        if(apePat.equals("")){
            validaciones += "El apellido paterno es obligatorio!!!\n";
        }

        if(apeMat.equals("")){
            validaciones += "El apellido materno es obligatorio!!!\n";
        }

        if((edtFecNac.getText() + "").equals("")){
            validaciones += "La fecha de nacimiento es obligatoria!!!\n";
        }

        if(sexo.equals("")){
            validaciones += "El sexo es obligatorio!!!\n";
        }

        if(correo.equals("")){
            validaciones += "El correo es obligatorio!!!\n";
        }

        if(validaciones.equals("")){
            Alumno alumno = new Alumno(id, nombre, apePat, apeMat, Utils.dateToString(fecNac), sexo, correo);
            Toast.makeText(this, "Alumno creado!!!", Toast.LENGTH_LONG).show();
            saveAlumno(alumno);
        }else{
            Toast.makeText(this, validaciones, Toast.LENGTH_LONG).show();
        }

    }

    private void saveAlumno(Alumno alumno){
        AlumnoService service = retrofit.create(AlumnoService.class);
        if(alumno.getId() == 0){

            Call<AlumnoRespuesta> alumnoRespuestaCall = service.insertAlumno(alumno);

            alumnoRespuestaCall.enqueue(new Callback<AlumnoRespuesta>() {
                @Override
                public void onResponse(Call<AlumnoRespuesta> call, Response<AlumnoRespuesta> response) {
                    if(response.isSuccessful()){
                        AlumnoRespuesta alumnoRespuesta = response.body();
                        Toast.makeText(getApplicationContext(), alumnoRespuesta.getMensaje(),Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Error desconocido!!!",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<AlumnoRespuesta> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "El servicio no está disponible!!!",Toast.LENGTH_LONG).show();
                }
            });

        }else{

        }

    }

}