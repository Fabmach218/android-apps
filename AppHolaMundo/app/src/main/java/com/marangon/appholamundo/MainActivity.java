package com.marangon.appholamundo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edtApe, edtNom, etmDatos;
    Button btnMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignarReferencias();
    }

    private void asignarReferencias(){
        edtApe = findViewById(R.id.edtApe);
        edtNom = findViewById(R.id.edtNom);
        etmDatos = findViewById(R.id.etmResultado);
    }

    public void mostrarDatos(View view){

        String nom, ape;

        ape = edtApe.getText()+"";
        nom = edtNom.getText()+"";
        etmDatos.setText("Apellidos: " + ape + "\n" + "Nombres: " + nom);

    }

}