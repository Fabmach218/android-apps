package com.marangon.appexamenfinal.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.marangon.appexamenfinal.entity.Paciente;
import com.marangon.appexamenfinal.util.BDPaciente;
import com.marangon.appexamenfinal.util.ConstantesBD;

import java.util.ArrayList;

public class DAOPaciente {

    BDPaciente bdPaciente;
    SQLiteDatabase database;

    public DAOPaciente(Context c){
        bdPaciente = new BDPaciente(c);
    }

    public void openBD(){
        database = bdPaciente.getWritableDatabase();
    }

    public void closeBD(){
        bdPaciente.close();
        database.close();
    }

    public void registrarPaciente(Paciente p){

        try{

            ContentValues values = new ContentValues();
            values.put("dni", p.getDni());
            values.put("ape", p.getApe());
            values.put("nom", p.getNom());
            values.put("sintomas", p.getSintomas());
            values.put("antecedentes", p.getAntecedentes());

            database.insert(ConstantesBD.NOMBRETABLA, null, values);

        }catch (Exception e){

        }

    }

    public ArrayList<Paciente> getPacientes(){

        ArrayList<Paciente> lista = new ArrayList<>();

        try{

            Cursor c = database.rawQuery("SELECT * FROM " + ConstantesBD.NOMBRETABLA, null);

            while(c.moveToNext()){
                lista.add(new Paciente(c.getInt(0), c.getInt(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5)));
            }

        }catch (Exception e){

        }

        return lista;

    }

    public boolean existeDNI(int dni){

        ArrayList<Paciente> lista = getPacientes();

        for(Paciente p:lista){

            if(p.getDni() == dni){
                return true;
            }


        }

        return false;

    }

    public void modificarPaciente(Paciente p){

        try{

            ContentValues values = new ContentValues();
            values.put("dni", p.getDni());
            values.put("ape", p.getApe());
            values.put("nom", p.getNom());
            values.put("sintomas", p.getSintomas());
            values.put("antecedentes", p.getAntecedentes());

            database.update(ConstantesBD.NOMBRETABLA, values, "id=" + p.getId(), null);

        }catch (Exception e){

        }

    }

    public void eliminarPaciente(int id){

        try{
            database.delete(ConstantesBD.NOMBRETABLA, "id=" + id, null);
        } catch (Exception e){ }

    }



}
