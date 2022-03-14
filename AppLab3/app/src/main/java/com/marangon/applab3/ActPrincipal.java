package com.marangon.applab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

public class ActPrincipal extends AppCompatActivity {

    ArrayList<Prenda> listaPrendas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_principal);
        recuperarDatos();
    }

    private void recuperarDatos() {

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            listaPrendas = (ArrayList<Prenda>) bundle.getSerializable("datos");
        }else{
            listaPrendas = new ArrayList<>();
        }

    }

    public void registrarNuevaPrenda(View view){
        Intent intent = new Intent(this, ActNuevaPrenda.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("datos", listaPrendas);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void listarPrendas(View view){
        Intent intent = new Intent(this, ActListaPrendas.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("datos", listaPrendas);
        intent.putExtras(bundle);
        startActivity(intent);
    }


}