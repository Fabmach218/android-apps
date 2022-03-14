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
import android.widget.Button;
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

public class ActModificar extends AppCompatActivity {

    Usuario u;
    UsuarioDAO daoUsuario;

    final int REQUEST_CODE_GALLERY = 999;

    ImageView imgFoto;
    TextView txtTamano;
    EditText edtNom, edtApe, edtPassword;
    Button btnModificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_modificar);
        daoUsuario = new UsuarioDAO(this);
        daoUsuario.openBD();
        asignarReferencias();
    }

    public void asignarReferencias(){

        imgFoto = findViewById(R.id.imgModificarFotoPerfil);
        txtTamano = findViewById(R.id.txtTamanoModificarFotoPerfil);
        edtNom = findViewById(R.id.edtNom2);
        edtApe = findViewById(R.id.edtApe2);
        edtPassword = findViewById(R.id.edtPassword2);
        btnModificar = findViewById(R.id.btnModificarUsuario);
        u = daoUsuario.buscarUsuarioActivo();
        Bitmap bitmap = BitmapFactory.decodeByteArray(u.getFoto(), 0, u.getFoto().length);
        imgFoto.setImageBitmap(bitmap);
        edtNom.setText(u.getNom());
        edtApe.setText(u.getApe());

    }

    public void capturarFoto(View v){
        ActivityCompat.requestPermissions(ActModificar.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_GALLERY);
    }

    public void modificar(View v){

        String newNom, newApe, password, msj = "";
        byte[] newFoto;

        newNom = edtNom.getText() + "";
        newApe = edtApe.getText() + "";
        newFoto = imgFotoToView(imgFoto);

        password = edtPassword.getText() + "";

        if(imgFotoToView(imgFoto).length < 1500000) {

            if (newNom.equals(u.getNom()) && newApe.equals(u.getApe()) && newFoto == u.getFoto()) {
                msj = "Los datos no han sido cambiados!!!";
            } else if (!daoUsuario.validarCredenciales(u.getEmail(), password)) {
                msj = "Contraseña incorrecta, no se pudo modificar!!!";
            } else {
                u.setNom(newNom);
                u.setApe(newApe);
                u.setFoto(newFoto);
                daoUsuario.modificarUsuario(u);
                msj = "Datos modificados exitosamente!!!";
                startActivity(new Intent(this, ActPerfil.class));
            }

        }else{
            msj = "Archivo muy pesado, no se puede registrar!!!";
        }

        Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();

    }

    public byte[] imgFotoToView(ImageView imageView){

        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return  byteArray;

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