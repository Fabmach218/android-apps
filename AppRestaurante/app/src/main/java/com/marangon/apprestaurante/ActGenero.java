package com.marangon.apprestaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class ActGenero extends AppCompatActivity {

    RadioButton rdbM, rdbF, rdbO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_genero);
        asignarReferencias();
    }

    private void asignarReferencias() {
        rdbM = findViewById(R.id.rdbM);
        rdbF = findViewById(R.id.rdbF);
        rdbO = findViewById(R.id.rdbO);
    }

    public void mostrarGénero(View view){

        String g = "El género seleccionado es: \n";

        if (rdbM.isChecked()){
            g += rdbM.getText() + "";
        }else if(rdbF.isChecked()){
            g += rdbF.getText() + "";
        }else if(rdbO.isChecked()){
            g += rdbO.getText() + "";
        }else{
            g = "Por favor, seleccione un género!!!";
        }

        Toast.makeText(this, g, Toast.LENGTH_SHORT).show();

    }


}