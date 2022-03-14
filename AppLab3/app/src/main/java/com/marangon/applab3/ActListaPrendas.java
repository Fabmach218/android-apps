package com.marangon.applab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ActListaPrendas extends AppCompatActivity {

    ListView lstPrendas;
    ArrayList<Prenda> listaPrendas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_lista_prendas);
        asignarReferencia();
        recuperarDatos();
        listarPrendas();
    }

    private void asignarReferencia(){
        lstPrendas = findViewById(R.id.lstPrendas);
    }

    private void recuperarDatos(){
        listaPrendas = (ArrayList<Prenda>) getIntent().getExtras().getSerializable("datos");
    }

    private void listarPrendas(){

        ArrayList<String> lista = new ArrayList<>();

        lista.add("CODIGO\tNOMBRE\tTALLA\tPRECIO\tSTOCK");

        for (Prenda objP : listaPrendas) {
            lista.add(objP.getCod() + "\t" + objP.getNom() + "\t" + objP.getTalla() + "\t" + objP.getPrecio() + "\t" + objP.getStock());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        lstPrendas.setAdapter(adapter);

    }

    public void regresarMP(View view){
        Intent intent = new Intent(this, ActPrincipal.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("datos", listaPrendas);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}