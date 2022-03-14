package com.marangon.apprestaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ActDepartamentos extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView lstDepartamentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_departamentos);
        asignarReferencias();
    }

    public void asignarReferencias(){
        lstDepartamentos = findViewById(R.id.lstDepartamentos);
        lstDepartamentos.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String dpto = ((TextView) view).getText().toString();
        Toast.makeText(this, "El departamento es: " + dpto + "\n El índice es: " + position, Toast.LENGTH_LONG).show();
    }
}