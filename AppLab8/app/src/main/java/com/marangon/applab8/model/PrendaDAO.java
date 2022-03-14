package com.marangon.applab8.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.marangon.applab8.entity.Prenda;
import com.marangon.applab8.util.BDPrenda;
import com.marangon.applab8.util.ConstantesBD;

import java.util.ArrayList;

public class PrendaDAO {

    BDPrenda bdPrenda;
    SQLiteDatabase database;
    int indDB;

    public PrendaDAO(Context c){
        bdPrenda = new BDPrenda(c);
    }

    public void openBD(){
        database = bdPrenda.getWritableDatabase();
    }

    public void closeBD(){
        bdPrenda.close();
        database.close();
    }

    public void registrarPrenda(Prenda p){

        try{

            ContentValues values = new ContentValues();
            values.put("nombre", p.getNombre());
            values.put("marca", p.getMarca());
            values.put("talla", p.getTalla());
            values.put("color", p.getColor());
            values.put("precio", p.getPrecio());

            database.insert(ConstantesBD.NOMBRETABLA, null, values);

        }catch (Exception e){ }

    }

    public void modificarDatos(Prenda p){

        try{

            ContentValues values = new ContentValues();
            values.put("nombre", p.getNombre());
            values.put("marca", p.getMarca());
            values.put("talla", p.getTalla());
            values.put("color", p.getColor());
            values.put("precio", p.getPrecio());

            database.update(ConstantesBD.NOMBRETABLA, values, "id=" + p.getId(), null);

        }catch (Exception e){ }

    }

    public void eliminarPrenda(int id){

        try{
            database.delete(ConstantesBD.NOMBRETABLA, "id=" + id, null);
        } catch (Exception e){ }

    }

    public ArrayList<Prenda> getPrendas(){

        ArrayList<Prenda> lista = new ArrayList<>();

        try{

            Cursor c = database.rawQuery("SELECT * FROM " + ConstantesBD.NOMBRETABLA, null);

            while(c.moveToNext()){
                lista.add(new Prenda(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getDouble(5)));
                indDB = c.getInt(0);
            }


        }catch (Exception e){ }

        return lista;

    }

    public int getIndDB(){
        return indDB;
    }

}
