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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.marangon.appproyfinalaedii.R;
import com.marangon.appproyfinalaedii.entity.Producto;
import com.marangon.appproyfinalaedii.entity.Usuario;
import com.marangon.appproyfinalaedii.model.ProductoDAO;
import com.marangon.appproyfinalaedii.model.UsuarioDAO;

import java.util.ArrayList;

public class ActTienda extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ImageView imvFotoPerfilUsuario;
    TextView txvUsuario, txvEmail;
    Button btnSesion;

    UsuarioDAO daoUsuario;
    Usuario u;

    GridView grdProductos;

    ProductoDAO daoProducto;
    Producto p;

    ArrayList<Producto> listaProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_tienda);
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout1);
        findViewById(R.id.imageMenu1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = findViewById(R.id.navigationView1);
        navigationView.setNavigationItemSelectedListener(this);

        daoUsuario = new UsuarioDAO(this);
        daoUsuario.openBD();

        daoProducto = new ProductoDAO(this);
        daoProducto.openBD();
        listaProductos = daoProducto.getProductos();

        asignarReferencias();
        actualizarDatos();
        capturarEventos();

    }

    public void asignarReferencias(){
        grdProductos = findViewById(R.id.grdProductos);
        registerForContextMenu(grdProductos);
    }

    public void actualizarDatos(){

        NavigationView navigationView = findViewById(R.id.navigationView1);
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

    public void capturarEventos(){

        CustomAdapter customAdapter = new CustomAdapter();
        grdProductos.setAdapter(customAdapter);

        grdProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                p = listaProductos.get(position);

                Intent intent = new Intent(getApplicationContext(), ActDetalleProducto.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("id", p.getId());
                bundle.putSerializable("idComentario", 0);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

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

        DrawerLayout drawer = findViewById(R.id.drawerLayout1);
        drawer.closeDrawer(GravityCompat.START);
        return true;

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
            case R.id.modificarCantidad : modificarProducto(info.position); break;
            case R.id.eliminarItem: eliminarProducto(info.position); break;
        }

        return true;

    }

    public void modificarProducto(int pos){

        Producto p = listaProductos.get(pos);

        int id = p.getId();

        Intent intent = new Intent(this, ActRegistrarProducto.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("id", id);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    public void eliminarProducto(int pos){

        int id = listaProductos.get(pos).getId();

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setMessage("¿Seguro que desea eliminar el producto?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        daoProducto.eliminarProducto(id);
                        Toast.makeText(getApplicationContext(), "Se eliminó el producto con éxito!!!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), ActTienda.class));
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).create().setTitle("Confirmación");

        alerta.show();

    }

    private class CustomAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return listaProductos.size();
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

            View v = getLayoutInflater().inflate(R.layout.diseno_cuadricula, null);

            ImageView imgFoto = v.findViewById(R.id.imgFotoLista);
            TextView txtNom = v.findViewById(R.id.txtNomLista);

            Bitmap bitmap = BitmapFactory.decodeByteArray(listaProductos.get(position).getImagen(), 0, listaProductos.get(position).getImagen().length);
            imgFoto.setImageBitmap(bitmap);

            txtNom.setText(listaProductos.get(position).getNombre());

            return v;

        }
    }
}