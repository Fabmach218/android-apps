package com.marangon.appmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txvM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txvM = findViewById(R.id.txvM);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu01, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.opc01: txvM.setText("Usted ha seleccionado la opción 1!!!"); return true;
            case R.id.opc02: txvM.setText("Usted ha seleccionado la opción 2!!!"); return true;
            case R.id.opc03: txvM.setText("Usted ha seleccionado la opción 3!!!"); return true;
            default: return super.onOptionsItemSelected(item);
        }

    }
}