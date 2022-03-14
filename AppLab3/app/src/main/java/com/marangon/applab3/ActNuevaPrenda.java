package com.marangon.applab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ActNuevaPrenda extends AppCompatActivity {

    EditText edtNom, edtPrecio;
    Spinner sprTalla, sprStock;
    ArrayList<Prenda> listaPrendas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_nueva_prenda);
        asignarReferencias();
        recuperarDatos();
    }

    private void asignarReferencias(){
        edtNom = findViewById(R.id.edtNom);
        edtPrecio = findViewById(R.id.edtPrecio);
        sprTalla = findViewById(R.id.sprTalla);
        sprStock = findViewById(R.id.sprStock);
    }

    private void recuperarDatos() {
        listaPrendas = (ArrayList<Prenda>) getIntent().getExtras().getSerializable("datos");
    }

    public long generarAleatorio(int min, int max){
        return (long)(Math.random() * (max - min + 1) + min);
    }

    public void limpiar(){
        edtNom.setText("");
        edtPrecio.setText("");
        sprTalla.setSelection(0);
        sprStock.setSelection(0);
    }

    public void registrarPrenda(View view){

        long cod = generarAleatorio(10000, 99999);
        String nom = edtNom.getText() + "";
        String talla = sprTalla.getSelectedItem() + "";
        double precio = Double.parseDouble(edtPrecio.getText() + "");
        String stock = "";
        String msj = "";

        switch(sprStock.getSelectedItemPosition()){
            case 1: stock = "Agotado"; break;
            case 2: stock = "Menos de 1000 unidades"; break;
            case 3: stock = "Entre 1000 y 10000 unidades"; break;
            case 4: stock = "Más de 10000 unidades";
        }

        if (sprTalla.getSelectedItemPosition() == 0){
            msj = "Seleccione una talla!!!";
        }else if(sprStock.getSelectedItemPosition() == 0){
            msj = "Seleccione un stock!!!";
        }else{
            Prenda objP = new Prenda(cod, nom, talla, precio, stock);
            listaPrendas.add(objP);
            msj = "Prenda registrada!!!";
            limpiar();
        }

        Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();

    }

    public void regresarMP(View view){
        Intent intent = new Intent(this, ActPrincipal.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("datos", listaPrendas);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}