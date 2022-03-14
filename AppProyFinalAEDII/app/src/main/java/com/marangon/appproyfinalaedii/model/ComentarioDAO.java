package com.marangon.appproyfinalaedii.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.core.content.ContextCompat;

import com.marangon.appproyfinalaedii.entity.Comentario;
import com.marangon.appproyfinalaedii.entity.Producto;
import com.marangon.appproyfinalaedii.util.BDComentarios;
import com.marangon.appproyfinalaedii.util.ConstantesBD;

import java.util.ArrayList;

public class ComentarioDAO {

    BDComentarios bdComentarios;
    SQLiteDatabase database;

    public ComentarioDAO(Context c){
        bdComentarios = new BDComentarios(c);
    }

    public void openBD(){
        database = bdComentarios.getWritableDatabase();
    }

    public void closeBD(){
        bdComentarios.close();
        database.close();
    }

    public void registrarComentario(Comentario c) {

        try{

            ContentValues values = new ContentValues();
            values.put("producto", c.getProducto());
            values.put("usuario", c.getUsuario());
            values.put("comentario", c.getComentario());
            values.put("fecha", c.getFecha());

            database.insert(ConstantesBD.NOMBRETABLACOMENTARIOS, null, values);

        }catch (Exception e){

        }

    }

    public ArrayList<Comentario> getListaGeneral(){

        ArrayList<Comentario> lista = new ArrayList<>();

        try{

            Cursor c = database.rawQuery("SELECT * FROM " + ConstantesBD.NOMBRETABLACOMENTARIOS, null);

            while(c.moveToNext()){
                lista.add(new Comentario(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getInt(4)));
            }

        }catch (Exception e){

        }

        return lista;

    }

    public ArrayList<Comentario> mostrarComentarios(Producto p){

        ArrayList<Comentario> listaGeneral = getListaGeneral();
        ArrayList<Comentario> lista = new ArrayList<>();

        for (Comentario c:listaGeneral) {

            if(p.getNombre().equals(c.getProducto())){
                lista.add(c);
            }

        }

        Comentario aux;

        for(int i = 0; i < lista.size() - 1; i++){
            for(int j = i + 1; j < lista.size(); j++){

                if(lista.get(i).getFecha() < lista.get(j).getFecha()){
                    aux = lista.get(i);
                    lista.set(i, lista.get(j));
                    lista.set(j, aux);
                }

            }
        }


        return lista;

    }

    public void modificarComentario(Comentario c){

        try{

            ContentValues values = new ContentValues();
            values.put("comentario", c.getComentario());
            values.put("fecha", c.getFecha());

            database.update(ConstantesBD.NOMBRETABLACOMENTARIOS, values, "id=" + c.getId(), null);

        }catch (Exception e){

        }
    }

    public void eliminarComentario(int id){

        try{
            database.delete(ConstantesBD.NOMBRETABLACOMENTARIOS, "id=" + id, null);
        }catch (Exception e){

        }

    }

    public int getCantidadComentarios(Producto p){
        return mostrarComentarios(p).size();
    }

    public Comentario buscarPorID(int id){

        ArrayList<Comentario> listaGeneral = getListaGeneral();

        for(Comentario c:listaGeneral){

            if(id == c.getId()){
                return c;
            }

        }

        return null;

    }


}
