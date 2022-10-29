package com.example.ejercicio1_3.Controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ejercicio1_3.Modelo.Persona;
import com.example.ejercicio1_3.Conexion.SQliteHelper;

import java.util.ArrayList;

public class PersonaControlador {
    private SQliteHelper sqliteHelper;
    private String TABLA_NAME = "persona";

    public PersonaControlador(Context contexto) {
        sqliteHelper = new SQliteHelper(contexto);
    }


    public int eliminar(Persona persona) {
        SQLiteDatabase db = sqliteHelper.getWritableDatabase();
        String[] argumento = {
                String.valueOf(persona.getId())
        };

        return db.delete(TABLA_NAME, "id = ?", argumento);
    }

    public long crear(Persona persona) {
        SQLiteDatabase db = sqliteHelper.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nombre", persona.getNombre());
        valores.put("apellido", persona.getApellido());
        valores.put("edad", persona.getEdad());
        valores.put("correo", persona.getCorreo());
        valores.put("direccion", persona.getDireccion());

        return db.insert(TABLA_NAME, null, valores);
    }

    public int actualizar(Persona personaActualizar) {
        SQLiteDatabase baseDeDatos = sqliteHelper.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nombre", personaActualizar.getNombre());
        valores.put("apellido", personaActualizar.getApellido());
        valores.put("edad", personaActualizar.getEdad());
        valores.put("correo", personaActualizar.getCorreo());
        valores.put("direccion", personaActualizar.getDireccion());

        String queryId = "id = ?";

        String[] argumento = {
                String.valueOf(personaActualizar.getId())
        };

        return baseDeDatos.update(TABLA_NAME, valores, queryId, argumento);
    }

    public ArrayList<Persona> leer() {
        ArrayList<Persona> persona = new ArrayList<>();

        SQLiteDatabase db = sqliteHelper.getReadableDatabase();

        String[] queryConsulta = {"nombre", "apellido", "edad", "correo", "direccion", "id"};
        Cursor cursor = db.query(
                TABLA_NAME,
                queryConsulta,
                null,
                null,
                null,
                null,
                null
        );

        // Regresamos el array persona si se produce un error al leer la lista
        if (cursor == null) {
            return persona;
        }

        // Regresamos el mismo array si no hay datos
        if (!cursor.moveToFirst()) return persona;

        do {
            String nombre = cursor.getString(0);
            String apellido = cursor.getString(1);
            int edad = cursor.getInt(2);
            String correo = cursor.getString(3);
            String direccion = cursor.getString(4);
            long id = cursor.getLong(5);

            Persona persona_db = new Persona(nombre, apellido, edad, correo, direccion, id);
            persona.add(persona_db);

        } while (cursor.moveToNext());

        cursor.close();
        return persona;
    }
}
