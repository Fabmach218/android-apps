package com.marangon.appmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ActSubMenu extends AppCompatActivity {

    TextView txvM2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_sub_menu);
        txvM2 = findViewById(R.id.txvM2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sub_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.opc01_01: txvM2.setText("Usted ha seleccionado la opción 1.1!!!"); return true;
            case R.id.opc01_02: txvM2.setText("Usted ha seleccionado la opción 1.2!!!"); return true;
            case R.id.opc01_03: txvM2.setText("Usted ha seleccionado la opción 1.3!!!"); return true;
            case R.id.opc02_01: txvM2.setText("Usted ha seleccionado la opción 2.1!!!"); return true;
            case R.id.opc02_02: txvM2.setText("Usted ha seleccionado la opción 2.2!!!"); return true;
            case R.id.opc02_03: txvM2.setText("Usted ha seleccionado la opción 2.3!!!"); return true;
            case R.id.opc03_01: txvM2.setText("Usted ha seleccionado la opción 3.1!!!"); return true;
            case R.id.opc03_02: txvM2.setText("Usted ha seleccionado la opción 3.2!!!"); return true;
            case R.id.opc03_03: txvM2.setText("Usted ha seleccionado la opción 3.3!!!"); return true;
            default: return super.onOptionsItemSelected(item);
        }

    }

}