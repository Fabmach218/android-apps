package com.marangon.appproyfinalaedii.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDCarrito extends SQLiteOpenHelper {


    public BDCarrito(@Nullable Context context) {
        super(context, ConstantesBD.NOMBREBDCARRITO, null, ConstantesBD.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + ConstantesBD.NOMBRETABLACARRITO + " " +

                "(id integer primary key autoincrement," +
                "usuario text not null," +
                "producto text not null," +
                "precio double not null," +
                "cantidad integer not null," +
                "importe double not null);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
