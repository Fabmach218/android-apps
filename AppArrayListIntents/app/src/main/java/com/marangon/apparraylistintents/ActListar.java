package com.marangon.apparraylistintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ActListar extends AppCompatActivity {

    ListView lstClientes;
    ArrayList<Cliente> listaClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_listar);
        asignarReferencia();
        recuperarDatos();
        listarClientes();
    }

    private void asignarReferencia() {
        lstClientes = findViewById(R.id.lstClientes);
    }

    private void recuperarDatos() {
        listaClientes = (ArrayList<Cliente>) getIntent().getExtras().getSerializable("datos");
    }

    public void listarClientes(){

        ArrayList<String> lista = new ArrayList<>();

        lista.add("APELLIDOS\tNOMBRES\tNÚMERO TELEFÓNICO\tEMAIL");

        for (Cliente objC : listaClientes) {
            lista.add(objC.getApe() + "\t" + objC.getNom() + "\t" + objC.getNumTelf() + "\t" + objC.getEmail());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        lstClientes.setAdapter(adapter);

    }

    public void irMenuP(View view){
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("datos", listaClientes);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}