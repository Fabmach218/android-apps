package com.marangon.ejercicio1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edtApe, edtNom, edtProm, edtRes;
    Spinner sprCursos;
    ArrayList<String> apellidos;
    ArrayList<String> nombres;
    ArrayList<String> cursos;
    ArrayList<Double> promedios;
    ArrayList<Integer> codigos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignarReferencias();
    }

    private void asignarReferencias() {
        edtApe = findViewById(R.id.edtApe);
        edtNom = findViewById(R.id.edtNom);
        edtProm = findViewById(R.id.edtProm);
        edtRes = findViewById(R.id.edtRes);
        sprCursos = findViewById(R.id.sprCursos);
        apellidos = new ArrayList<>();
        nombres = new ArrayList<>();
        cursos = new ArrayList<>();
        promedios = new ArrayList<>();
        codigos = new ArrayList<>();
    }

    public void ingresar(View view){

        String ape = edtApe.getText() + "";
        String nom = edtNom.getText() + "";
        String curso = sprCursos.getSelectedItem() + "";
        double prom = Double.parseDouble(edtProm.getText() + "");
        int ale = generarAleatorio(10000, 99999);
        String msj;

        if (sprCursos.getSelectedItemPosition() == 0){
            msj = "Elija un curso!!!";
        }else if(prom < 0 || prom > 20){
            msj = "Escriba un promedio válido!!!";
            edtProm.setText("");
        }else{
            apellidos.add(ape);
            nombres.add(nom);
            cursos.add(curso);
            promedios.add(prom);
            codigos.add(ale);

            msj = "Datos ingresados con éxito!!!";

            edtApe.setText("");
            edtNom.setText("");
            sprCursos.setSelection(0);
            edtProm.setText("");
        }

        Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();

    }

    public void mostrar(View view){

        String titulo = "Código\tApellido\tNombre\tCurso\tPromedio\n";
        edtRes.setText(titulo);

        for (int i = 0; i < apellidos.size(); i++){
            edtRes.append(codigos.get(i) + "\t" + apellidos.get(i) + "\t" + nombres.get(i) + "\t" + cursos.get(i) + "\t" + promedios.get(i) + "\n");
        }

    }

    public int generarAleatorio(int min, int max){
        return (int)(Math.random() * (max - min + 1) + min);
    }

}