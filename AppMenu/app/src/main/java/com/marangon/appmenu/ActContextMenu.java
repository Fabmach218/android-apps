package com.marangon.appmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ActContextMenu extends AppCompatActivity {

    ImageView imgFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_context_menu);
        imgFoto = findViewById(R.id.imgFoto);
        imgFoto.setImageResource(R.drawable.bandera_peru);
        registerForContextMenu(imgFoto);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu01,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.opc01: Toast.makeText(this, "Usted ha seleccionado la opción 1!!!", Toast.LENGTH_LONG).show(); return true;
            case R.id.opc02: Toast.makeText(this, "Usted ha seleccionado la opción 2!!!", Toast.LENGTH_LONG).show(); return true;
            case R.id.opc03: Toast.makeText(this, "Usted ha seleccionado la opción 3!!!", Toast.LENGTH_LONG).show(); return true;
            default: return super.onOptionsItemSelected(item);
        }

    }

}