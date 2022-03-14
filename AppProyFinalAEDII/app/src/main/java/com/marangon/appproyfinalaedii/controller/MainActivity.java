package com.marangon.appproyfinalaedii.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.marangon.appproyfinalaedii.R;
import com.marangon.appproyfinalaedii.entity.Usuario;
import com.marangon.appproyfinalaedii.model.UsuarioDAO;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ImageView imvFotoPerfilUsuario;
    TextView txvUsuario, txvEmail;
    Button btnSesion;

    UsuarioDAO daoUsuario;
    Usuario u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        daoUsuario = new UsuarioDAO(this);
        daoUsuario.openBD();

        actualizarDatos();

    }

    public void actualizarDatos(){

        NavigationView navigationView = findViewById(R.id.navigationView);
        View headerView = navigationView.getHeaderView(0);

        imvFotoPerfilUsuario = headerView.findViewById(R.id.imvFotoPerfilUsuario);
        txvUsuario = headerView.findViewById(R.id.txvUsuario);
        txvEmail = headerView.findViewById(R.id.txvEmail);
        btnSesion = headerView.findViewById(R.id.btnSesion);

        if(daoUsuario.existeSesionActiva()){
            u = daoUsuario.buscarUsuarioActivo();
            Bitmap bitmap = BitmapFactory.decodeByteArray(u.getFoto(), 0, u.getFoto().length);
            imvFotoPerfilUsuario.setImageBitmap(bitmap);
            txvUsuario.setText(u.getNom() + " " + u.getApe());
            txvEmail.setText(u.getEmail());
            btnSesion.setText("CERRAR SESIÓN");
        }else{
            txvUsuario.setText("Usuario invitado");
            btnSesion.setText("INICIAR SESIÓN");
        }

    }

    public void sesion(View v){

        if(daoUsuario.existeSesionActiva()){

            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setMessage("¿Desea cerrar su sesión?")
                    .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            daoUsuario.cerrarSesion(u);
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog dialogo = alerta.create();
            dialogo.show();

        }else{
            startActivity(new Intent(this, ActInicio.class));
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.menuTienda : startActivity(new Intent(this, ActTienda.class)); break;
            case R.id.menuRegistro : startActivity(new Intent(this, ActRegistrarProducto.class)); break;
            case R.id.menuCarrito : startActivity(new Intent(this, ActCarrito.class)); break;
            case R.id.menuPerfil : startActivity(new Intent(this, ActPerfil.class)); break;

        }

        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}