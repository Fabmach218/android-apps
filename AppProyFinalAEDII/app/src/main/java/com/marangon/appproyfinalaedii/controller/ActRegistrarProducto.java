package com.marangon.appproyfinalaedii.controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.marangon.appproyfinalaedii.R;
import com.marangon.appproyfinalaedii.entity.Producto;
import com.marangon.appproyfinalaedii.entity.Usuario;
import com.marangon.appproyfinalaedii.model.ProductoDAO;
import com.marangon.appproyfinalaedii.model.UsuarioDAO;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ActRegistrarProducto extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageView imgFoto;
    EditText edtNombreProducto, edtPrecio;
    Button btnSubirFoto, btnRegistrarProducto;

    ProductoDAO daoProducto;

    String nombre;
    double precio;
    final int REQUEST_CODE_GALLERY = 999;
    Producto p;

    Usuario u;
    UsuarioDAO daoUsuario;

    ImageView imvFotoPerfilUsuario;
    TextView txvUsuario, txvEmail;
    Button btnSesion;

    TextView txtTamano;

    int idProducto = 0;

    String nombreAnterior = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_registrar_producto);
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout2);
        findViewById(R.id.imageMenu2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = findViewById(R.id.navigationView2);
        navigationView.setNavigationItemSelectedListener(this);

        daoUsuario = new UsuarioDAO(this);
        daoUsuario.openBD();

        daoProducto = new ProductoDAO(this);
        daoProducto.openBD();
        asignarReferencias();
        actualizarDatos();
        recuperarDatos();
        capturarEventos();
    }


    public void asignarReferencias(){
        imgFoto = findViewById(R.id.imgFoto);
        edtNombreProducto = findViewById(R.id.edtNombreProducto);
        edtPrecio = findViewById(R.id.edtPrecio);
        btnSubirFoto = findViewById(R.id.btnSubirFoto);
        btnRegistrarProducto = findViewById(R.id.btnRegistrarProducto);
        txtTamano = findViewById(R.id.txtTamano);
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

    public void actualizarDatos(){
        NavigationView navigationView = findViewById(R.id.navigationView2);
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

    public void recuperarDatos(){

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){

            idProducto = (int) bundle.getSerializable("id");

            p = daoProducto.buscarPorID(idProducto);

            nombreAnterior = p.getNombre();

            Bitmap bitmap = BitmapFactory.decodeByteArray(p.getImagen(), 0, p.getImagen().length);

            edtNombreProducto.setText(p.getNombre());
            edtPrecio.setText(p.getPrecio() + "");
            imgFoto.setImageBitmap(bitmap);

            btnRegistrarProducto.setText("MODIFICAR");

        }

    }

    public void capturarEventos(){

        btnSubirFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturarFoto();
            }
        });

        btnRegistrarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(imgFotoToView(imgFoto).length < 1500000){

                    crearProducto();

                    if(idProducto == 0){

                        if(!daoProducto.existeProductoRegistrado(p.getNombre())){
                            daoProducto.registrarProducto(p);
                            Toast.makeText(getApplicationContext(), "Se registró el producto con éxito!!!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Producto ya registrado, no se puede repetir!!!", Toast.LENGTH_SHORT).show();
                        }

                    }else{

                        if(!daoProducto.existeProductoRegistrado(p.getNombre()) || p.getNombre().equalsIgnoreCase(nombreAnterior)){
                            p.setId(idProducto);
                            daoProducto.modificarProducto(p);
                            Toast.makeText(getApplicationContext(), "Se modificó el producto con éxito!!!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Existe otro producto con el mismo nombre, no se pudo modificar!!!", Toast.LENGTH_SHORT).show();
                        }

                        idProducto = 0;
                    }

                    limpiar();

                }else{
                    Toast.makeText(getApplicationContext(), "Archivo muy pesado, no se puede registrar!!!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    public void capturarFoto(){
        ActivityCompat.requestPermissions(ActRegistrarProducto.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_GALLERY);
    }

    public void crearProducto(){
        nombre = edtNombreProducto.getText() + "";
        precio = Double.parseDouble(edtPrecio.getText() + "");
        p = new Producto(nombre, precio, imgFotoToView(imgFoto));
    }

    public byte[] imgFotoToView(ImageView view){
        Bitmap bitmap = ((BitmapDrawable)view.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return  byteArray;
    }

    public void limpiar(){
        edtNombreProducto.setText("");
        edtPrecio.setText("");
        edtNombreProducto.requestFocus();
        imgFoto.setImageResource(R.drawable.logo);
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
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.menuTienda : startActivity(new Intent(this, ActTienda.class)); break;
            case R.id.menuRegistro : startActivity(new Intent(this, ActRegistrarProducto.class)); break;
            case R.id.menuCarrito : startActivity(new Intent(this, ActCarrito.class)); break;
            case R.id.menuPerfil : startActivity(new Intent(this, ActPerfil.class)); break;

        }

        DrawerLayout drawer = findViewById(R.id.drawerLayout2);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}