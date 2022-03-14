package com.marangon.appintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edtNum1, edtNum2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignarReferencias();
    }

    private void asignarReferencias() {
        edtNum1 = findViewById(R.id.edtNum1);
        edtNum2 = findViewById(R.id.edtNum2);
    }

    public void enviarNumeros(View view){

        String num1, num2;

        num1 = edtNum1.getText() + "";
        num2 = edtNum2.getText() + "";

        Intent intent = new Intent(this, ActResultado.class);

        Bundle bundle = new Bundle();

        bundle.putString("num1", num1);
        bundle.putString("num2", num2);

        intent.putExtras(bundle);
        startActivity(intent);

    }

}