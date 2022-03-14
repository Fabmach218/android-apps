package com.marangon.applab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ActRegistrarPlato extends AppCompatActivity {

    EditText edtStock, edtPrecio, edtDesc;
    Spinner sprPlatos;
    ArrayList<Plato> listaPlatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_registrar_plato);
        asignarReferencias();
        recuperarDatos();
    }

    private void asignarReferencias() {
        sprPlatos = findViewById(R.id.sprPlatos);
        edtStock = findViewById(R.id.edtStock);
        edtPrecio = findViewById(R.id.edtPrecio);
        edtDesc = findViewById(R.id.edtDesc);
    }

    private void recuperarDatos(){
        listaPlatos = (ArrayList<Plato>) getIntent().getExtras().getSerializable("datos");
    }

    public void registrar(View view){

        int id = 0, idFoto = 0, stock;
        String nom, desc, msj = "";
        double precio;
        boolean existe = false;

        switch(sprPlatos.getSelectedItemPosition()){
            case 1: id = 1; idFoto = R.drawable.aji_de_gallina; break;
            case 2: id = 2; idFoto = R.drawable.chaufa; break;
            case 3: id = 3; idFoto = R.drawable.carapulcra; break;
            case 4: id = 4; idFoto = R.drawable.ceviche; break;
            case 5: id = 5; idFoto = R.drawable.lomo_saltado; break;
            case 6: id = 6; idFoto = R.drawable.pachamanca;
        }

        nom = sprPlatos.getSelectedItem() + "";
        stock = Integer.parseInt(edtStock.getText() + "");
        precio = Double.parseDouble(edtPrecio.getText() + "");
        desc = edtDesc.getText() + "";

        for (Plato objP:listaPlatos) {
            if(objP.getId() == sprPlatos.getSelectedItemPosition()){
                existe = true;
            }
        }

        if(sprPlatos.getSelectedItemPosition() == 0){
            msj = "Debe seleccionar un plato!!!";
        }else if(existe){
            msj = "El plato ya está registrado!!!";
        }else{
            Plato p = new Plato(id, idFoto, stock, nom, desc, precio);
            listaPlatos.add(p);
            msj = "Plato registrado!!!";
            limpiar();
        }

        Toast.makeText(this, msj, Toast.LENGTH_LONG).show();

    }

    private void limpiar() {
        sprPlatos.setSelection(0);
        edtStock.setText("");
        edtPrecio.setText("");
        edtDesc.setText("");
    }

    public void irMenuP(View view){
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("datos", listaPlatos);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}