package com.marangon.appproyfinalaedii.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.marangon.appproyfinalaedii.entity.Producto;
import com.marangon.appproyfinalaedii.util.BDProductos;
import com.marangon.appproyfinalaedii.util.ConstantesBD;

import java.util.ArrayList;

public class ProductoDAO {

    BDProductos bdProductos;
    SQLiteDatabase database;

    public ProductoDAO(Context c){
        bdProductos = new BDProductos(c);
    }

    public void openBD(){
        database = bdProductos.getWritableDatabase();
    }

    public void closeBD(){
        bdProductos.close();
        database.close();
    }

    public void registrarProducto(Producto p){

        try{

            ContentValues values = new ContentValues();
            values.put("nombre", p.getNombre());
            values.put("precio", p.getPrecio());
            values.put("imagen", p.getImagen());
            database.insert(ConstantesBD.NOMBRETABLAPRODUCTOS, null, values);

        }catch (Exception e){

        }

    }

    public ArrayList<Producto> getProductos(){

        ArrayList<Producto> lista = new ArrayList<>();

        try{

            Cursor c = database.rawQuery("SELECT * FROM " + ConstantesBD.NOMBRETABLAPRODUCTOS, null);

            while(c.moveToNext()){
                lista.add(new Producto(c.getInt(0), c.getString(1), c.getDouble(2), c.getBlob(3)));
            }

        }catch (Exception e){

        }

        return lista;

    }

    public void modificarProducto(Producto p){

        try{

            ContentValues values = new ContentValues();
            values.put("nombre", p.getNombre());
            values.put("precio", p.getPrecio());
            values.put("imagen", p.getImagen());
            database.update(ConstantesBD.NOMBRETABLAPRODUCTOS, values, "id=" + p.getId(), null);

        }catch (Exception e){

        }

    }

    public void eliminarProducto(int id){

        try{
            database.delete(ConstantesBD.NOMBRETABLAPRODUCTOS, "id=" + id, null);
        }catch (Exception e){

        }


    }

    public Producto buscarPorID(int id){

        ArrayList<Producto> lista = getProductos();

        for(Producto p:lista){

            if(id == p.getId()){
                return p;
            }


        }

        return null;

    }

    public boolean existeProductoRegistrado(String nom){

        ArrayList<Producto> lista = getProductos();

        for(Producto p:lista){

            if(nom.equalsIgnoreCase(p.getNombre())){
                return true;
            }


        }

        return false;

    }

    //Método autodestructor, usar en casos de extrema urgencia
    public void reiniciarTabla(){

        try{

            Cursor c = database.rawQuery("SELECT * FROM " + ConstantesBD.NOMBRETABLAPRODUCTOS, null);

            while(c.moveToNext()){
                database.delete(ConstantesBD.NOMBRETABLAPRODUCTOS, "id=" + c.getInt(0), null);
            }


        }catch (Exception e){

        }


    }


}
