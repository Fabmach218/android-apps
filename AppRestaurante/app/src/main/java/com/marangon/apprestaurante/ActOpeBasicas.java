package com.marangon.apprestaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ActOpeBasicas extends AppCompatActivity {

    Spinner sprOpe;
    EditText edtN1, edtN2;
    String operaciones[] = {"- Elija una opción -", "Suma", "Resta", "Producto", "División"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_ope_basicas);
        asignarReferencias();
        cargarDataSpinner();
    }

    private void cargarDataSpinner() {
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, operaciones);
        sprOpe.setAdapter(adapter);
    }

    private void asignarReferencias() {
        sprOpe = findViewById(R.id.sprOpe);
        edtN1 = findViewById(R.id.edtN1);
        edtN2 = findViewById(R.id.edtN2);
    }

    public void calcularOperacion(View view){

        String msj;
        int ope;
        double n1, n2, res;

        n1 = Double.parseDouble(edtN1.getText().toString());
        n2 = Double.parseDouble(edtN2.getText().toString());
        ope = sprOpe.getSelectedItemPosition();

        switch(ope){
            case 1:
                res = n1 + n2;
                msj = "La suma es: " + res;
                break;
            case 2:
                res = n1 - n2;
                msj = "La resta es: " + res;
                break;
            case 3:
                res = n1 * n2;
                msj = "El producto es: " + res;
                break;
            case 4:
                res = n1 * 1.0 / n2;
                res = Math.rint(res * 100) / 100;
                msj = "El cociente es: " + res;
                break;
            default: msj = "Elija una operación!!!";
        }

        Toast.makeText(this, msj, Toast.LENGTH_LONG).show();


    }

}