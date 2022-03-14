package com.marangon.applab7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IPrenda{

    FgtLista fgtLista;
    FgtFoto fgtFoto;

    ArrayList<Prenda> listaPrendas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignarReferencias();
        recuperarDatos();
    }

    private void asignarReferencias() {
        fgtLista = (FgtLista) getSupportFragmentManager().findFragmentById(R.id.fgtLista);
        fgtFoto = (FgtFoto) getSupportFragmentManager().findFragmentById(R.id.fgtFoto);
    }

    private void recuperarDatos(){

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            listaPrendas = (ArrayList<Prenda>) bundle.getSerializable("carrito");
        }else{
            listaPrendas = new ArrayList<>();
        }

    }

    @Override
    public void seleccionarPrenda(Prenda p) {
        fgtFoto.mostrar(p);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.catalogo:

                Intent intent = new Intent(this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("carrito", listaPrendas);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;

            case R.id.carrito:



            default: return super.onOptionsItemSelected(item);
        }

    }

}