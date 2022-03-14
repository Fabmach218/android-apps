package com.marangon.appproyfinalaedii.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.marangon.appproyfinalaedii.R;
import com.marangon.appproyfinalaedii.entity.Usuario;
import com.marangon.appproyfinalaedii.model.UsuarioDAO;

import java.util.ArrayList;

public class ActInicio extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_inicio);
    }

    public void iniciarSesion(View v){
        startActivity(new Intent(this, ActIniciarSesion.class));
    }

}