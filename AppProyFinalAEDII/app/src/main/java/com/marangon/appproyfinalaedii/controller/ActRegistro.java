package com.marangon.appproyfinalaedii.controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.marangon.appproyfinalaedii.R;
import com.marangon.appproyfinalaedii.entity.Usuario;
import com.marangon.appproyfinalaedii.model.UsuarioDAO;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class ActRegistro extends AppCompatActivity {

    ImageView imgFoto;
    TextView txtTamano;
    EditText edtNom, edtApe, edtEmail, edtPassword, edtConfirmar;

    final int REQUEST_CODE_GALLERY = 999;

    ArrayList<Usuario> listaUsuarios;
    UsuarioDAO daoUsuario;

    String nom, ape, email, password;
    Usuario u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_registro);
        listaUsuarios = new ArrayList<>();
        daoUsuario = new UsuarioDAO(this);
        daoUsuario.openBD();
        asignarReferencias();
        listaUsuarios = daoUsuario.getUsuarios();
    }

    public void asignarReferencias() {
        imgFoto = findViewById(R.id.imgFotoPerfil);
        txtTamano = findViewById(R.id.txtTamanoFotoPerfil);
        edtNom = findViewById(R.id.edtNom);
        edtApe = findViewById(R.id.edtApe);
        edtEmail = findViewById(R.id.edtEmail1);
        edtPassword = findViewById(R.id.edtPassword1);
        edtConfirmar = findViewById(R.id.edtConfirmar);
    }

    public void capturarFoto(View v){
        ActivityCompat.requestPermissions(ActRegistro.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_GALLERY);
    }


    public void registrarse(View v){

        String msj = "";

        capturarDatos();

        if(imgFotoToView(imgFoto).length < 1500000) {

            if ((edtConfirmar.getText() + "").equals(password)) {

                if (!daoUsuario.existeUsuario(email)) {
                    u = new Usuario(imgFotoToView(imgFoto) ,nom, ape, email, password, 0);
                    daoUsuario.registrarUsuario(u);
                    limpiar();
                    msj = "Usuario registrado exitosamente!!!";
                } else {
                    msj = "Ya existe un usuario registrado con ese correo!!!";
                }

            } else {
                msj = "Las contraseñas no coinciden!!!";
            }

        }else{
            msj = "Archivo muy pesado, no se puede registrar!!!";
        }

        Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();

    }

    public void volver(View v){

        Intent intent = new Intent(this, ActInicio.class);
        startActivity(intent);

    }

    public void capturarDatos(){

        nom = edtNom.getText() + "";
        ape = edtApe.getText() + "";
        email = edtEmail.getText() + "";
        password = edtPassword.getText() + "";

    }

    public byte[] imgFotoToView(ImageView imageView){

        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return  byteArray;

    }

    public void limpiar(){

        edtNom.setText("");
        edtApe.setText("");
        edtEmail.setText("");
        edtPassword.setText("");
        edtConfirmar.setText("");
        imgFoto.setImageResource(R.drawable.logo);
        edtNom.requestFocus();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){

            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_GALLERY);

            }else{

                Toast.makeText(getApplicationContext(),"No tienes permiso para seleccionar",Toast.LENGTH_SHORT).show();

            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){

            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgFoto.setImageBitmap(bitmap);

                int tamano = imgFotoToView(imgFoto).length;

                if(tamano > 1500000){
                    txtTamano.setTextColor(ContextCompat.getColor(this, R.color.design_default_color_error));
                    txtTamano.setText("Archivo muy pesado, la aplicación no lo soportará!!!");
                }else{
                    txtTamano.setText("");
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }


}