package com.marangon.appproyfinalaedii.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDProductos extends SQLiteOpenHelper {


    public BDProductos(@Nullable Context context) {
        super(context, ConstantesBD.NOMBREBDPRODUCTOS, null, ConstantesBD.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + ConstantesBD.NOMBRETABLAPRODUCTOS + " " +

            "(id integer primary key autoincrement," +
            "nombre text not null," +
            "precio double not null," +
            "imagen blob not null);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
