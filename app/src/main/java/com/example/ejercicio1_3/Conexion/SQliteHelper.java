package com.example.ejercicio1_3.Conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQliteHelper extends SQLiteOpenHelper {

    private static final String BD_NAME = "persona",
            TABLA_NAME = "persona";
    private static final int VERSION_BD = 1;

    public SQliteHelper(Context context) {
        super(context, BD_NAME, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS " + TABLA_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellido TEXT, edad INTEGER, correo TEXT, direccion TEXT)"));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
