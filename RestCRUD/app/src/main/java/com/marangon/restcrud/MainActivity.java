package com.marangon.restcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.marangon.restcrud.model.Alumno;
import com.marangon.restcrud.model.AlumnoRespuesta;
import com.marangon.restcrud.service.AlumnoService;
import com.marangon.restcrud.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;

    ArrayList<Alumno> listaAlumnos;
    ListView lstAlumnos;
    Button btnObtenerDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        listaAlumnos = new ArrayList<>();

        asignarReferencias();
        obtenerDatos();

    }

    private void obtenerDatos(){
        AlumnoService service = retrofit.create(AlumnoService.class);
        Call<AlumnoRespuesta> alumnoRespuestaCall = service.getAllAlumnos();

        alumnoRespuestaCall.enqueue(new Callback<AlumnoRespuesta>() {
            @Override
            public void onResponse(Call<AlumnoRespuesta> call, Response<AlumnoRespuesta> response) {
                if(response.isSuccessful()){
                    AlumnoRespuesta alumnoRespuesta = response.body();
                    listaAlumnos = alumnoRespuesta.getAlumnosLista();
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
    }

    public void actualizarDatos(View v){
        obtenerDatos();
    }

    public void asignarReferencias(){
        lstAlumnos = findViewById(R.id.lstAlumnos);
        btnObtenerDatos = findViewById(R.id.btnObtenerDatos);
    }

    public void nuevoAlumno(View v){
        Intent intent = new Intent(this, ActFormAlumno.class);
        startActivity(intent);
    }

    public void obtenerDatos(View v){
        CustomAdapter adapter = new CustomAdapter();
        lstAlumnos.setAdapter(adapter);
    }

    private class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return listaAlumnos.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = getLayoutInflater().inflate(R.layout.design_lista_alumnos, null);

            TextView txtId, txtNombre, txtEdad;

            txtId = v.findViewById(R.id.txtIdAlumno);
            txtNombre = v.findViewById(R.id.txtNombreAlumno);
            txtEdad = v.findViewById(R.id.txtEdadAlumno);

            txtId.setText(listaAlumnos.get(position).getId() + "");
            txtNombre.setText(listaAlumnos.get(position).getNombre() + " " + listaAlumnos.get(position).getApePat() + " " + listaAlumnos.get(position).getApeMat());
            txtEdad.setText(new SimpleDateFormat("dd/MM/yyyy").format(Utils.stringToDate(listaAlumnos.get(position).getFecNac())));
            return v;

        }

    }

}