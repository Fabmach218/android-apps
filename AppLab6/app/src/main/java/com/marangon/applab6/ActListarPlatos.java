package com.marangon.applab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class ActListarPlatos extends AppCompatActivity implements IPlato{

    FgtListaPlatos fgtLista;
    FgtDetallePlatos fgtDetalle;
    ArrayList<Plato> listaPlatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_listar_platos);
        asignarReferencias();
        recuperarDatos();
    }

    private void asignarReferencias() {
        fgtLista = (FgtListaPlatos) (getSupportFragmentManager().findFragmentById(R.id.fgtLista));
        fgtDetalle = (FgtDetallePlatos) (getSupportFragmentManager().findFragmentById(R.id.fgtDetalle));
    }

    private void recuperarDatos(){
        listaPlatos = (ArrayList<Plato>) getIntent().getExtras().getSerializable("datos");
    }

    @Override
    public void seleccionarPlato(Plato p) {
        fgtDetalle.mostrar(p);
    }

    public void irMenuP(View view){
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("datos", listaPlatos);
        intent.putExtras(bundle);
        startActivity(intent);
    }


}