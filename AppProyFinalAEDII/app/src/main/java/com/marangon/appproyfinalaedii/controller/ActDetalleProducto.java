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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.marangon.appproyfinalaedii.R;
import com.marangon.appproyfinalaedii.entity.Comentario;
import com.marangon.appproyfinalaedii.entity.Producto;
import com.marangon.appproyfinalaedii.entity.Usuario;
import com.marangon.appproyfinalaedii.model.CarritoDAO;
import com.marangon.appproyfinalaedii.model.ComentarioDAO;
import com.marangon.appproyfinalaedii.model.ProductoDAO;
import com.marangon.appproyfinalaedii.model.UsuarioDAO;

import java.util.Date;

public class ActDetalleProducto extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ImageView imvFotoPerfilUsuario;
    TextView txvUsuario, txvEmail;
    Button btnSesion;

    UsuarioDAO daoUsuario;
    Usuario u;

    ProductoDAO daoProducto;
    Producto p;

    CarritoDAO daoCarrito;

    ComentarioDAO daoComentario;
    int idComentario = 0;

    int idProducto = 0;

    ImageView imgFoto;
    TextView txtNom, txtPrecio;
    EditText edtCantidad;

    Comentario c;
    ImageView imgFotoComentario;
    EditText edtComentario;

    Button btnComentar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_detalle_producto);
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout5);
        findViewById(R.id.imageMenu5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = findViewById(R.id.navigationView5);
        navigationView.setNavigationItemSelectedListener(this);

        daoUsuario = new UsuarioDAO(this);
        daoUsuario.openBD();

        daoProducto = new ProductoDAO(this);
        daoProducto.openBD();

        daoCarrito = new CarritoDAO(this);
        daoCarrito.openBD();

        daoComentario = new ComentarioDAO(this);
        daoComentario.openBD();

        asignarReferencias();
        recuperarDatos();
        actualizarDatos();
        mostrarProductoSeleccionado();
    }

    public void asignarReferencias(){
        imgFoto = findViewById(R.id.imgFotoProducto);
        txtNom = findViewById(R.id.txtNomProducto);
        txtPrecio = findViewById(R.id.txtPrecioProducto);
        edtCantidad = findViewById(R.id.edtCantidadProducto);
        imgFotoComentario = findViewById(R.id.imgFotoPublicarComentario);
        edtComentario = findViewById(R.id.edtComentario);
        btnComentar = findViewById(R.id.btnComentar);
    }

    public void recuperarDatos(){

        Bundle bundle = getIntent().getExtras();

        idComentario = (int) bundle.getSerializable("idComentario");

        if(idComentario != 0){
            c = daoComentario.buscarPorID(idComentario);
            edtComentario.setText(c.getComentario());
            btnComentar.setText("MODIFICAR");
        }

    }

    public void actualizarDatos(){

        NavigationView navigationView = findViewById(R.id.navigationView5);
        View headerView = navigationView.getHeaderView(0);

        imvFotoPerfilUsuario = headerView.findViewById(R.id.imvFotoPerfilUsuario);
        txvUsuario = headerView.findViewById(R.id.txvUsuario);
        txvEmail = headerView.findViewById(R.id.txvEmail);
        btnSesion = headerView.findViewById(R.id.btnSesion);

        if(daoUsuario.existeSesionActiva()){
            u = daoUsuario.buscarUsuarioActivo();
            Bitmap bitmap = BitmapFactory.decodeByteArray(u.getFoto(), 0, u.getFoto().length);
            imvFotoPerfilUsuario.setImageBitmap(bitmap);
            imgFotoComentario.setImageBitmap(bitmap);
            txvUsuario.setText(u.getNom() + " " + u.getApe());
            txvEmail.setText(u.getEmail());
            btnSesion.setText("CERRAR SESIÓN");
        }else{
            txvUsuario.setText("Usuario invitado");
            btnSesion.setText("INICIAR SESIÓN");
        }

    }

    public void mostrarProductoSeleccionado(){

        Bundle bundle = getIntent().getExtras();

        idProducto = (int) bundle.getSerializable("id");
        p = daoProducto.buscarPorID(idProducto);

        txtNom.setText(p.getNombre());
        txtPrecio.setText("Precio: S/. " + p.getPrecio());
        Bitmap bitmap = BitmapFactory.decodeByteArray(p.getImagen(), 0, p.getImagen().length);
        imgFoto.setImageBitmap(bitmap);

    }

    public void comprar(View v) {

        int cantidad;
        String msj;

        if (daoUsuario.existeSesionActiva()) {

            cantidad = Integer.parseInt(edtCantidad.getText() + "");

            if (cantidad <= 0) {
                msj = "Ingrese una cantidad válida!!!";
            } else {

                if(daoCarrito.getListaComprasUsuario(u).size() < 10){
                    daoCarrito.registrarProducto(u, p, cantidad);
                    msj = "Producto agregado al carrito!!!";
                    edtCantidad.setText("");
                }else{
                    msj = "Solo puede agregar al carrito un máximo de 10 productos!!!";
                    edtCantidad.setText("");
                }

            }

        } else {
            msj = "Inicie sesión o regístrese para continuar!!!";
        }

        Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();

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

    public void comentar(View v){

        String comentario;
        int fecha;
        Date date = new Date(new Date().getTime());

        if(daoUsuario.existeSesionActiva()){

            comentario = edtComentario.getText() + "";
            fecha = (int) (date.getTime() / 1000L);
            c = new Comentario(p.getNombre(), u.getEmail(), comentario, fecha);

            if(comentario.length() < 160){

                if(idComentario == 0){
                    daoComentario.registrarComentario(c);
                    Toast.makeText(this, "Comentario registrado exitosamente!!!", Toast.LENGTH_SHORT).show();
                }else{
                    c.setId(idComentario);
                    daoComentario.modificarComentario(c);
                    Toast.makeText(this, "Comentario modificado exitosamente!!!", Toast.LENGTH_SHORT).show();
                }

                edtComentario.setText("");

                Intent intent = new Intent(this, ActComentarios.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("idProducto", idProducto);
                intent.putExtras(bundle);
                startActivity(intent);

            }else{
                Toast.makeText(this, "Límite de caracteres sobrepasado, no se puede registrar!!!", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Inicie sesión o regístrese para comentar!!!", Toast.LENGTH_SHORT).show();
        }

    }

    public void abrirComentarios(View v){
        Intent intent = new Intent(this, ActComentarios.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("idProducto", idProducto);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.menuTienda : startActivity(new Intent(this, ActTienda.class)); break;
            case R.id.menuRegistro : startActivity(new Intent(this, ActRegistrarProducto.class)); break;
            case R.id.menuCarrito : startActivity(new Intent(this, ActCarrito.class)); break;
            case R.id.menuPerfil : startActivity(new Intent(this, ActPerfil.class)); break;

        }

        DrawerLayout drawer = findViewById(R.id.drawerLayout5);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }


}