package com.marangon.apprestaurante;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    CheckBox chkAP, chkAG, chkLS;
    Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignarReferencias();

    }

    private void asignarReferencias() {
        chkAP = findViewById(R.id.chkAP);
        chkAG = findViewById(R.id.chkAG);
        chkLS = findViewById(R.id.chkLS);
        btnCalcular = findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(this);
    }

    public void calcularMonto(){

        String m = "Lista de pedidos: \n\n";
        double monto = 0;

        if (chkAP.isChecked()){
            m += chkAP.getText() + " = S/. 20\n";
            monto += 20;
        }

        if (chkAG.isChecked()){
            m += chkAG.getText() + " = S/. 12\n";
            monto += 12;
        }

        if (chkLS.isChecked()){
            m += chkLS.getText() + " = S/. 15\n";
            monto += 15;
        }

        Toast.makeText(this,m + "\nEl monto total a pagar es: S/. " + monto, Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onClick(View view) {

        if (view == btnCalcular){
            calcularMonto();
        }

    }
}