package com.marangon.appfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements IPresidente{

    FragmentLista fgtLista;
    FragmentDetalle fgtDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignarReferencias();
    }

    private void asignarReferencias() {
        fgtLista = (FragmentLista) (getSupportFragmentManager().findFragmentById(R.id.fgtLista));
        fgtDetalle = (FragmentDetalle) (getSupportFragmentManager().findFragmentById(R.id.fgtDetalle));
    }


    @Override
    public void seleccionarPresidente(Presidente p) {
        fgtDetalle.mostrar(p);
    }
}