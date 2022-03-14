package com.marangon.applab7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActDetalle extends AppCompatActivity {

    ImageView imgFoto;
    TextView txtNombre, txtPrecio, txtStock;
    Spinner sprTalla;

    ArrayList<Prenda> listaPrendas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_detalle);
        asignarReferencias();
    }

    private void asignarReferencias() {

        imgFoto = findViewById(R.id.imgFoto2);
        txtNombre = findViewById(R.id.txtNombre2);
        sprTalla = findViewById(R.id.sprTalla);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtStock = findViewById(R.id.txtStock);

    }

    public void mostrar(Prenda p){
        imgFoto.setImageResource(p.getIdFoto());
        txtNombre.setText(p.getNom());
        txtPrecio.setText(p.getPrecio() + "");
        txtStock.setText(p.getStock() + "");
    }

    public void comprar(View v){

        String nom = txtNombre.getText() + "";
        int idFoto = imgFoto.getId();
        String talla = sprTalla.getSelectedItem() + "";
        double precio = Double.parseDouble(txtPrecio.getText() + "");
        int stock = Integer.parseInt(txtStock.getText() + "");
        String msj = "";

        if(sprTalla.getSelectedItemPosition() == 0){
            msj = "Seleccione una talla!!!";
        }else{
            listaPrendas.add(new Prenda(nom, idFoto, talla, precio, stock));
            msj = "Producto registrado con éxito!!!";
        }

        Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();

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