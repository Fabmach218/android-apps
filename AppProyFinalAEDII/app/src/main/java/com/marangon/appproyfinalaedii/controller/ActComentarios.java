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
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.marangon.appproyfinalaedii.R;
import com.marangon.appproyfinalaedii.entity.Comentario;
import com.marangon.appproyfinalaedii.entity.Producto;
import com.marangon.appproyfinalaedii.entity.Usuario;
import com.marangon.appproyfinalaedii.model.ComentarioDAO;
import com.marangon.appproyfinalaedii.model.ProductoDAO;
import com.marangon.appproyfinalaedii.model.UsuarioDAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ActComentarios extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageView imvFotoPerfilUsuario;
    TextView txvUsuario, txvEmail;
    Button btnSesion;

    UsuarioDAO daoUsuario;
    Usuario u;

    ComentarioDAO daoComentario;

    int idProducto;
    ProductoDAO daoProducto;
    Producto p;

    int idComentario;

    ArrayList<Comentario> listaComentarios;
    ListView lstComentarios;
    TextView txtNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_comentarios);
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout6);
        findViewById(R.id.imageMenu6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = findViewById(R.id.navigationView6);
        navigationView.setNavigationItemSelectedListener(this);

        daoUsuario = new UsuarioDAO(this);
        daoUsuario.openBD();

        daoProducto = new ProductoDAO(this);
        daoProducto.openBD();

        daoComentario = new ComentarioDAO(this);
        daoComentario.openBD();

        actualizarDatos();
        recuperarDatos();

    }

    public void actualizarDatos(){

        NavigationView navigationView = findViewById(R.id.navigationView6);
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

        lstComentarios = findViewById(R.id.lstComentarios);
        registerForContextMenu(lstComentarios);
        txtNum = findViewById(R.id.txtNumComentarios);

        Bundle bundle = getIntent().getExtras();
        idProducto = (int) bundle.getSerializable("idProducto");
        p = daoProducto.buscarPorID(idProducto);

        listaComentarios = daoComentario.mostrarComentarios(p);
        txtNum.setText(daoComentario.getCantidadComentarios(p) + "");

        CustomAdapter customAdapter = new CustomAdapter();
        lstComentarios.setAdapter(customAdapter);

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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_carrito, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.modificarCantidad : modificarComentario(info.position); break;
            case R.id.eliminarItem: eliminarComentario(info.position); break;
        }

        return true;

    }

    public void modificarComentario(int pos){

        Comentario c = listaComentarios.get(pos);

        if(daoUsuario.existeSesionActiva()){

            if(u.getEmail().equals(c.getUsuario())){

                idComentario = c.getId();

                Intent intent = new Intent(this, ActDetalleProducto.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("idComentario", idComentario);
                bundle.putSerializable("id", idProducto);
                intent.putExtras(bundle);
                startActivity(intent);

            }else{
                Toast.makeText(this, "No puede modificar el comentario de otro usuario!!!", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Usuario invitado, no puede interactuar con los comentarios!!!", Toast.LENGTH_SHORT).show();
        }

    }

    public void eliminarComentario(int pos){

        Comentario c = listaComentarios.get(pos);

        if(daoUsuario.existeSesionActiva()){

            if(u.getEmail().equals(c.getUsuario())){

                int id = c.getId();

                AlertDialog.Builder alerta = new AlertDialog.Builder(this);
                alerta.setMessage("¿Seguro que desea eliminar este comentario?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                daoComentario.eliminarComentario(id);
                                Toast.makeText(getApplicationContext(), "Se eliminó el comentario!!!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), ActComentarios.class));
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create().setTitle("Confirmación de eliminación");

                alerta.show();

            }else{
                Toast.makeText(this, "No puede eliminar el comentario de otro usuario!!!", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Usuario invitado, no puede interactuar con los comentarios!!!", Toast.LENGTH_SHORT).show();
        }

    }

    public void volver(View v){

        Intent intent = new Intent(this, ActDetalleProducto.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("id", idProducto);
        bundle.putSerializable("idComentario", 0);
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

        DrawerLayout drawer = findViewById(R.id.drawerLayout6);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }


    private class CustomAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return listaComentarios.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = getLayoutInflater().inflate(R.layout.diseno_comentarios, null);

            ImageView imgFoto = v.findViewById(R.id.imgFotoComentario);
            TextView txtUsuario = v.findViewById(R.id.txtUsuarioComentario);
            TextView txtComentario = v.findViewById(R.id.txtComentario);
            TextView txtFecha = v.findViewById(R.id.txtFechaComentario);

            Usuario u = daoUsuario.buscarUsuarioPorEmail(listaComentarios.get(position).getUsuario());

            Bitmap bitmap = BitmapFactory.decodeByteArray(u.getFoto(), 0, u.getFoto().length);
            imgFoto.setImageBitmap(bitmap);
            txtUsuario.setText(u.getNom() + " " + u.getApe());
            txtComentario.setText(listaComentarios.get(position).getComentario());

            Date date = new Date(listaComentarios.get(position).getFecha() * 1000L);
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String fecha = formato.format(date);
            txtFecha.setText(fecha);

            return v;

        }
    }

}