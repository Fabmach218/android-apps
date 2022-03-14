package com.marangon.applab8.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.marangon.applab8.R;
import com.marangon.applab8.entity.Prenda;
import com.marangon.applab8.model.CuadroDialogo;
import com.marangon.applab8.model.PrendaDAO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtNombre, edtMarca, edtColor, edtPrecio;
    Spinner sprTalla;
    Button btnGuardar;
    ListView lstPrendas;

    ArrayList<Prenda> listaPrendas;
    PrendaDAO daoPrenda;

    public int id = 0;
    public String nombre, marca, talla, color;
    public double precio;

    Prenda p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaPrendas = new ArrayList<>();
        daoPrenda = new PrendaDAO(this);
        daoPrenda.openBD();
        asignarReferencias();
        listarPrendas();
    }

    public void asignarReferencias(){

        edtNombre = findViewById(R.id.edtNombre);
        edtMarca = findViewById(R.id.edtMarca);
        sprTalla = findViewById(R.id.sprTalla);
        edtColor = findViewById(R.id.edtColor);
        edtPrecio = findViewById(R.id.edtPrecio);
        btnGuardar = findViewById(R.id.btnGuardar);
        lstPrendas = findViewById(R.id.lstPrendas);
        capturarEventos();

    }

    public void capturarEventos(){

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                capturarDatos();
                daoPrenda.registrarPrenda(p);
                listarPrendas();
                limpiar();

            }
        });

        lstPrendas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FragmentManager fragmentManager = getSupportFragmentManager();

                p = listaPrendas.get(position);
                setId(p.getId());

                CuadroDialogo cuadro = new CuadroDialogo();

                AlertDialog.Builder builder = cuadro.getBuilder();

                builder.setTitle("Acción").setNeutralButton("Modificar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edtNombre.setText(p.getNombre());
                        edtMarca.setText(p.getMarca());
                        edtColor.setText(p.getColor());
                        edtPrecio.setText(p.getPrecio() + "");
                    }
                }).setNeutralButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        daoPrenda.eliminarPrenda(getId());
                        listarPrendas();
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                cuadro.show(fragmentManager, "tagAccion");

            }
        });



    }

    public void capturarDatos(){

        nombre = edtNombre.getText() + "";
        marca = edtMarca.getText() + "";
        talla = sprTalla.getSelectedItem() + "";
        color = edtColor.getText() + "";
        precio = Double.parseDouble(edtPrecio.getText() + "");

        String msj;

        if (sprTalla.getSelectedItemPosition() == 0){
            msj = "Debe seleccionar una talla!!!";
        }else{
            p = new Prenda(nombre, marca, talla, color, precio);
            msj = "Prenda registrada!!!";
        }

    }

    public void limpiar(){

        edtNombre.setText("");
        edtMarca.setText("");
        sprTalla.setSelection(0);
        edtColor.setText("");
        edtPrecio.setText("");
        edtNombre.requestFocus();

    }

    public void listarPrendas(){

        listaPrendas = daoPrenda.getPrendas();
        ArrayList<String> lista = new ArrayList<>();

        for(Prenda p:listaPrendas){
            lista.add(p.getNombre() + " " + p.getMarca() + " " + p.getTalla() + " " + p.getColor() + " " + p.getPrecio());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        lstPrendas.setAdapter(adapter);

    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }


}