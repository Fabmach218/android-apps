package com.marangon.appproyfinalaedii.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.marangon.appproyfinalaedii.R;
import com.marangon.appproyfinalaedii.model.UsuarioDAO;

public class ActIniciarSesion extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    UsuarioDAO daoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_iniciar_sesion);
        daoUsuario = new UsuarioDAO(this);
        daoUsuario.openBD();
        asignarReferencias();
    }

    public void asignarReferencias(){
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
    }

    public void ingresar(View v){

        String email, password;

        email = edtEmail.getText() + "";
        password = edtPassword.getText() + "";

        if(!daoUsuario.existeUsuario(email)){
            Toast.makeText(this, "El usuario no se encuentra registrado en la base de datos!!!", Toast.LENGTH_SHORT).show();
        }else if(!daoUsuario.validarCredenciales(email, password)){
            Toast.makeText(this, "Contraseña incorrecta!!!", Toast.LENGTH_SHORT).show();
        }else{
            daoUsuario.iniciarSesion(daoUsuario.buscarUsuario(email, password));
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }

    public void registro(View v){
        Intent intent = new Intent(this, ActRegistro.class);
        startActivity(intent);
    }
}