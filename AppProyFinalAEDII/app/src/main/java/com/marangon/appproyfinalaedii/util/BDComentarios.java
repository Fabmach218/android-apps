package com.marangon.appproyfinalaedii.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDComentarios extends SQLiteOpenHelper {

    public BDComentarios(@Nullable Context context) {
        super(context, ConstantesBD.NOMBREBDCOMENTARIOS, null, ConstantesBD.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + ConstantesBD.NOMBRETABLACOMENTARIOS + " " +

                "(id integer primary key autoincrement," +
                "producto text not null," +
                "usuario text not null," +
                "comentario text not null," +
                "fecha integer not null);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
