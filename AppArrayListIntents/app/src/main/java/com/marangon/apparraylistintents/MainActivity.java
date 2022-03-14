package com.marangon.apparraylistintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Cliente> listaClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recuperarDatos();
    }

    private void recuperarDatos() {
        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            listaClientes = (ArrayList<Cliente>) bundle.getSerializable("datos");
        }else{
            listaClientes = new ArrayList<>();
        }

    }

    public void registrar(View view){

        Intent intent = new Intent(this, ActRegistrar.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("datos", listaClientes);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    public void listar(View view){

        Intent intent = new Intent(this, ActListar.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("datos", listaClientes);
        intent.putExtras(bundle);
        startActivity(intent);

    }



}