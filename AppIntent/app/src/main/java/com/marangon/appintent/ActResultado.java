package com.marangon.appintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ActResultado extends AppCompatActivity {

    EditText edtRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_resultado);
        asignarReferencia();
        mostrarResultado();
    }

    private void asignarReferencia() {
        edtRes = findViewById(R.id.edtRes);
    }

    private void mostrarResultado() {

        double num1, num2, res;

        Intent i = getIntent();
        Bundle b = i.getExtras();

        num1 = Double.parseDouble(b.getString("num1"));
        num2 = Double.parseDouble(b.getString("num2"));

        res = num1 * num2;
        res = redondear(res, 2);

        edtRes.setText("El resultado es: " + res);

    }

    public double redondear(double num, int qDec){
        return Math.rint(num * Math.pow(10, qDec)) / Math.pow(10, qDec);
    }

    public void regresar(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}