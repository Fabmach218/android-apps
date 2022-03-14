package com.marangon.appexamenfinal.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDPaciente extends SQLiteOpenHelper {
    public BDPaciente(@Nullable Context context) {
        super(context, ConstantesBD.NOMBREBD, null, ConstantesBD.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + ConstantesBD.NOMBRETABLA + " " +
                "(id integer primary key autoincrement," +
                "dni integer not null," +
                "ape text not null," +
                "nom text not null," +
                "sintomas text not null," +
                "antecedentes text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
