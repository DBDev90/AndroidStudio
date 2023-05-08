package com.example.projeto_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Banco extends SQLiteOpenHelper {

    public Banco(Context context) {
        super(context, "despesas", null, 1);
    }

    public long insereGasto(String local, String data, String valor){
        SQLiteDatabase banco = this.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("local", local);
        registro.put("data", data);
        registro.put("valor", valor);
        long id = banco.insert("gasto", null, registro);
        banco.close();
        return id;
    }

    public ArrayList<String> consulta(){
        SQLiteDatabase banco = this.getReadableDatabase();
        String sql = "SELECT * FROM gasto";
        ArrayList<String> resultado = null;

        Cursor c = banco.rawQuery(sql, null);
        if(c.moveToFirst()){
            resultado = new ArrayList<String>();
            do{
                String local = c.getString(1);
                String data = c.getString(2);
                String valor = c.getString(3);
                resultado.add("\n Local : " + local +
                              "\n Data : " + data +
                              "\n Valor : " + valor);
            }while (c.moveToNext());
        }
        banco.close();
        return resultado;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE gasto (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                     "local TEXT," +
                     "data TEXT," +
                     "valor TEXT )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
