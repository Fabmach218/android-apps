package com.marangon.appproyfinalaedii.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.marangon.appproyfinalaedii.entity.Carrito;
import com.marangon.appproyfinalaedii.entity.Producto;
import com.marangon.appproyfinalaedii.entity.Usuario;
import com.marangon.appproyfinalaedii.util.BDCarrito;
import com.marangon.appproyfinalaedii.util.BDProductos;
import com.marangon.appproyfinalaedii.util.ConstantesBD;

import java.util.ArrayList;

public class CarritoDAO {

    BDCarrito bdCarrito;
    SQLiteDatabase database;

    public CarritoDAO(Context c){
        bdCarrito = new BDCarrito(c);
    }

    public void openBD(){
        database = bdCarrito.getWritableDatabase();
    }

    public void closeBD(){
        bdCarrito.close();
        database.close();
    }

    public void registrarProducto(Usuario u, Producto p, int cantidad){

        Carrito item = new Carrito(u.getEmail(), p.getNombre(), p.getPrecio(), cantidad, p.getPrecio() * cantidad);

        try{

            ContentValues values = new ContentValues();
            values.put("usuario", item.getUsuario());
            values.put("producto", item.getProducto());
            values.put("precio", item.getPrecio());
            values.put("cantidad", item.getCantidad());
            values.put("importe", item.getImporte());

            database.insert(ConstantesBD.NOMBRETABLACARRITO, null, values);

        }catch (Exception e){

        }

    }

    public void modificarCantidad(Carrito item, int cantidad){

        try{

            ContentValues values = new ContentValues();
            values.put("cantidad", cantidad);
            values.put("importe", item.getPrecio() * cantidad);

            database.update(ConstantesBD.NOMBRETABLACARRITO, values, "id=" + item.getId(), null);

        }catch (Exception e){

        }

    }

    public void eliminarProducto(Carrito item){

        try{
            database.delete(ConstantesBD.NOMBRETABLACARRITO, "id=" + item.getId(), null);
        }catch (Exception e){

        }

    }

    public ArrayList<Carrito> getListaGeneral(){

        ArrayList<Carrito> lista = new ArrayList<>();

        try{

            Cursor c = database.rawQuery("SELECT * FROM " + ConstantesBD.NOMBRETABLACARRITO, null);

            while (c.moveToNext()){
                lista.add(new Carrito(c.getInt(0), c.getString(1), c.getString(2), c.getDouble(3), c.getInt(4), c.getDouble(5)));
            }

        }catch (Exception e){

        }

        return lista;

    }

    public ArrayList<Carrito> getListaComprasUsuario(Usuario u){

        ArrayList<Carrito> listaGeneral = getListaGeneral();
        ArrayList<Carrito> listaCompras = new ArrayList<>();

        for(Carrito c:listaGeneral){

            if(u.getEmail().equals(c.getUsuario())){
                listaCompras.add(c);
            }

        }

        return listaCompras;

    }

    public double getImporte(Usuario u){

        ArrayList<Carrito> listaCompras = getListaComprasUsuario(u);
        double suma = 0;

        for(Carrito c:listaCompras){
            suma += c.getImporte();
        }

        return suma;

    }

    public void comprar(Usuario u){

        try{

            Cursor c = database.rawQuery("SELECT * FROM " + ConstantesBD.NOMBRETABLACARRITO, null);

            while (c.moveToNext()){

                if(c.getString(1).equals(u.getEmail())){
                    database.delete(ConstantesBD.NOMBRETABLACARRITO, "id=" + c.getInt(0), null);
                }

            }

        }catch (Exception e){

        }

    }


}
