package com.marangon.appproyfinalaedii.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.marangon.appproyfinalaedii.entity.Usuario;
import com.marangon.appproyfinalaedii.util.BDUsuarios;
import com.marangon.appproyfinalaedii.util.ConstantesBD;

import java.util.ArrayList;

public class UsuarioDAO {

    BDUsuarios bdUsuarios;
    SQLiteDatabase database;

    public UsuarioDAO(Context c){
        bdUsuarios = new BDUsuarios(c);
    }

    public void openBD(){
        database = bdUsuarios.getWritableDatabase();
    }

    public void closeBD(){
        bdUsuarios.close();
        database.close();
    }

    public void registrarUsuario(Usuario u){

        try{

            ContentValues values = new ContentValues();
            values.put("nom", u.getNom());
            values.put("foto", u.getFoto());
            values.put("ape", u.getApe());
            values.put("email", u.getEmail());
            values.put("password", u.getPassword());
            values.put("login", u.getLog());

            database.insert(ConstantesBD.NOMBRETABLAUSUARIOS, null, values);

        }catch (Exception e){

        }

    }

    public void iniciarSesion(Usuario u){

        try{

            ContentValues values = new ContentValues();
            values.put("login", 1);

            database.update(ConstantesBD.NOMBRETABLAUSUARIOS, values, "id=" + u.getId(), null);

        }catch (Exception e){

        }

    }

    public void cerrarSesion(Usuario u){

        try{

            ContentValues values = new ContentValues();
            values.put("login", 0);

            database.update(ConstantesBD.NOMBRETABLAUSUARIOS, values, "id=" + u.getId(), null);

        }catch (Exception e){

        }

    }

    public ArrayList<Usuario> getUsuarios(){

        ArrayList<Usuario> lista = new ArrayList<>();

        try{

            Cursor c = database.rawQuery("SELECT * FROM " + ConstantesBD.NOMBRETABLAUSUARIOS, null);

            while(c.moveToNext()){
                lista.add(new Usuario(c.getInt(0), c.getBlob(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getInt(6)));
            }

        }catch (Exception e){

        }

        return lista;

    }

    public boolean existeUsuario(String email){

        ArrayList<Usuario> lista = getUsuarios();
        boolean existe = false;

        for (Usuario u:lista) {
            if(u.getEmail().equals(email)){
                existe = true;
            }
        }

        return existe;

    }

    public boolean validarCredenciales(String email, String password){

        ArrayList<Usuario> lista = getUsuarios();
        boolean datosCorrectos = false;

        for(Usuario u:lista){

            if(u.getEmail().equals(email) && u.getPassword().equals(password)){
                datosCorrectos = true;
            }

        }

        return datosCorrectos;

    }

    public Usuario buscarUsuario(String email, String password){

        ArrayList<Usuario> lista = getUsuarios();
        Usuario usuario = null;

        for(Usuario u:lista){

            if(u.getEmail().equals(email) && u.getPassword().equals(password)){
                usuario = u;
            }

        }

        return usuario;

    }

    public boolean existeSesionActiva(){

        ArrayList<Usuario> lista = getUsuarios();
        boolean sesionActiva = false;

        for (Usuario u:lista) {
            if(u.getLog() == 1){
                sesionActiva = true;
            }
        }

        return sesionActiva;

    }

    public Usuario buscarUsuarioActivo(){

        ArrayList<Usuario> lista = getUsuarios();
        Usuario uActivo = null;

        for (Usuario u:lista) {

            if(u.getLog() == 1){
                uActivo = u;
            }

        }

        return uActivo;

    }

    public void modificarUsuario(Usuario u){

        try{

            ContentValues values = new ContentValues();
            values.put("foto", u.getFoto());
            values.put("nom", u.getNom());
            values.put("ape", u.getApe());

            database.update(ConstantesBD.NOMBRETABLAUSUARIOS, values, "id=" + u.getId(), null);

        }catch (Exception e){

        }

    }

    public void eliminarUsuario(Usuario u){

        try{
            database.delete(ConstantesBD.NOMBRETABLAUSUARIOS, "id=" + u.getId(), null);
        } catch (Exception e){ }

    }

    public Usuario buscarUsuarioPorEmail(String email){

        ArrayList<Usuario> lista = getUsuarios();

        for(Usuario u:lista){

            if(email.equals(u.getEmail())){
                return u;
            }

        }

        return null;

    }


}
