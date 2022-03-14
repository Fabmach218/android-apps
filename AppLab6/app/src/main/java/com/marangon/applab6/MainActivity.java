package com.marangon.applab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Plato> listaPlatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recuperarDatos();
    }

    private void recuperarDatos() {

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            listaPlatos = (ArrayList<Plato>) bundle.getSerializable("datos");
        }else{
            listaPlatos = new ArrayList<>();
        }

    }

    public void registrarPlato(View view){
        Intent intent = new Intent(this, ActRegistrarPlato.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("datos", listaPlatos);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void listarPlatos(View view){
        Intent intent = new Intent(this, ActListarPlatos.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("datos", listaPlatos);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}