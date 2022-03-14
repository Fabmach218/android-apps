package com.marangon.appsqlite.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.marangon.appsqlite.entity.Persona;
import com.marangon.appsqlite.util.BDPersona;
import com.marangon.appsqlite.util.ConstantesBD;

import java.util.ArrayList;

public class PersonaDAO {

    BDPersona bdPersona;
    SQLiteDatabase database;

    public PersonaDAO(Context c){
        bdPersona = new BDPersona(c);
    }

    public void openBD(){
        database = bdPersona.getWritableDatabase();
    }

    public void closeBD(){
        bdPersona.close();
        database.close();
    }

    public void registrarPersona(Persona p){

        try{

            ContentValues values = new ContentValues();
            values.put("dni", p.getDni());
            values.put("ape", p.getApe());
            values.put("nom", p.getNom());
            values.put("email", p.getEmail());

            database.insert(ConstantesBD.NOMBRETABLA, null, values);

        }catch (Exception e){ }

    }

    public void modificarDatos(Persona p){

        try{

            ContentValues values = new ContentValues();
            values.put("dni", p.getDni());
            values.put("ape", p.getApe());
            values.put("nom", p.getNom());
            values.put("email", p.getEmail());

            database.update(ConstantesBD.NOMBRETABLA, values, "id=" + p.getId(), null);

        }catch (Exception e){ }

    }

    public void eliminarPersona(int id){

        try{
            database.delete(ConstantesBD.NOMBRETABLA, "id=" + id, null);
        } catch (Exception e){ }

    }

    public ArrayList<Persona> getPersonas(){

        ArrayList<Persona> lista = new ArrayList<>();

        try{

            Cursor c = database.rawQuery("SELECT * FROM " + ConstantesBD.NOMBRETABLA, null);

            while(c.moveToNext()){
                lista.add(new Persona(c.getInt(0), c.getInt(1), c.getString(2), c.getString(3), c.getString(4)));
            }


        }catch (Exception e){ }

        return lista;

    }

}
