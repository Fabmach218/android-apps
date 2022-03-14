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
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.text.InputType;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.marangon.appproyfinalaedii.R;
import com.marangon.appproyfinalaedii.entity.Carrito;
import com.marangon.appproyfinalaedii.entity.Usuario;
import com.marangon.appproyfinalaedii.model.CarritoDAO;
import com.marangon.appproyfinalaedii.model.UsuarioDAO;

import java.util.ArrayList;

public class ActCarrito extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ImageView imvFotoPerfilUsuario;
    TextView txvUsuario, txvEmail;
    Button btnSesion;

    UsuarioDAO daoUsuario;
    Usuario u;

    CarritoDAO daoCarrito;
    ArrayList<Carrito> listaProductos;

    ListView lstCarrito;
    TextView txtImporte;
    Button btnPagar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_carrito);
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout3);
        findViewById(R.id.imageMenu3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = findViewById(R.id.navigationView3);
        navigationView.setNavigationItemSelectedListener(this);

        daoUsuario = new UsuarioDAO(this);
        daoUsuario.openBD();

        daoCarrito = new CarritoDAO(this);
        daoCarrito.openBD();

        asignarReferencias();
        actualizarDatos();
        obtenerProductos(u);
        registerForContextMenu(lstCarrito);

    }

    public void asignarReferencias(){
        lstCarrito = findViewById(R.id.lstCarrito);
        txtImporte = findViewById(R.id.txtImporte);
        btnPagar = findViewById(R.id.btnPagar);
    }

    public void actualizarDatos(){

        NavigationView navigationView = findViewById(R.id.navigationView3);
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
            txtImporte.setText("Importe a pagar: S/. " + daoCarrito.getImporte(u));
        }else{
            txvUsuario.setText("Usuario invitado");
            btnSesion.setText("INICIAR SESIÓN");
        }

    }

    public void obtenerProductos(Usuario u){

        listaProductos = daoCarrito.getListaComprasUsuario(u);
        CustomAdapter adapter = new CustomAdapter();
        lstCarrito.setAdapter(adapter);

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
            case R.id.modificarCantidad : modificarCantidad(info.position); break;
            case R.id.eliminarItem: eliminarItem(info.position); break;
        }

        return true;

    }

    public void modificarCantidad(int pos){

        Carrito item = listaProductos.get(pos);

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        EditText edtCantidad = new EditText(this);
        edtCantidad.setInputType(InputType.TYPE_CLASS_NUMBER);
        alerta.setView(edtCantidad);
        alerta.setMessage("Ingrese la nueva cantidad:")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        int cantidad;
                        String msj;
                        cantidad = Integer.parseInt(edtCantidad.getText() + "");

                        if(cantidad == item.getCantidad()){
                            msj = "Las cantidades son las mismas, no se modificó!!!";
                        }else{
                            daoCarrito.modificarCantidad(item, cantidad);
                            msj = "Cantidad modificada con éxito!!!";
                            startActivity(new Intent(getApplicationContext(), ActCarrito.class));
                        }

                        Toast.makeText(getApplicationContext(), msj, Toast.LENGTH_SHORT).show();

                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialogo = alerta.create();
        dialogo.setTitle("Confirmación");
        dialogo.show();

    }

    public void eliminarItem(int pos){

        Carrito item = listaProductos.get(pos);

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);

        alerta.setMessage("¿Seguro que desea eliminar el ítem?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        daoCarrito.eliminarProducto(item);
                        startActivity(new Intent(getApplicationContext(), ActCarrito.class));
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialogo = alerta.create();
        dialogo.setTitle("Confirmación");
        dialogo.show();

    }

    public void pagar(View v){

        if(daoUsuario.existeSesionActiva()){

            if(daoCarrito.getImporte(u) != 0){
                AlertDialog.Builder alerta = new AlertDialog.Builder(this);

                alerta.setMessage("¿Pagar S/. " + daoCarrito.getImporte(u) + "?").setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        daoCarrito.comprar(u);
                        Toast.makeText(getApplicationContext(), "Compra realizada con éxito!!!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialogo = alerta.create();
                dialogo.setTitle("Confirmación de pago");
                dialogo.show();
            }else{
                Toast.makeText(this, "No hay ningún ítem en el carrito de compras!!!", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "No existe sesión activa!!!", Toast.LENGTH_SHORT).show();
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

        DrawerLayout drawer = findViewById(R.id.drawerLayout3);
        drawer.closeDrawer(GravityCompat.START);
        return true;

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

            View v = getLayoutInflater().inflate(R.layout.diseno_lista_carrito, null);

            TextView txtNom, txtPrecioUnitario, txtCantidad, txtImporte;

            txtNom = v.findViewById(R.id.txtNomItem);
            txtPrecioUnitario = v.findViewById(R.id.txtPrecioUnitarioItem);
            txtCantidad = v.findViewById(R.id.txtCantidadItem);
            txtImporte = v.findViewById(R.id.txtImporteItem);

            txtNom.setText(listaProductos.get(position).getProducto());
            txtPrecioUnitario.setText("S/. " + listaProductos.get(position).getPrecio());
            txtCantidad.setText(listaProductos.get(position).getCantidad() + "");
            txtImporte.setText("S/. " + listaProductos.get(position).getImporte());

            return v;

        }

    }

}