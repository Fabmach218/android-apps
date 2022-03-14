package com.marangon.appproyfinalaedii.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDUsuarios extends SQLiteOpenHelper {


    public BDUsuarios(@Nullable Context context) {
        super(context, ConstantesBD.NOMBREBDUSUARIOS, null, ConstantesBD.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + ConstantesBD.NOMBRETABLAUSUARIOS + " " +

                "(id integer primary key autoincrement," +
                "foto blob not null," +
                "nom text not null," +
                "ape text not null," +
                "email text not null," +
                "password text not null," +
                "login integer);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
