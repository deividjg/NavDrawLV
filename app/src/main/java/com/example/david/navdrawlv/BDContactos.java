package com.example.david.navdrawlv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by David on 02/12/2016.
 */

public class BDContactos extends SQLiteOpenHelper {

    private static final int VERSION_BASEDATOS = 1;
    private static final String NOMBRE_BASEDATOS = "Contactos.db";
    private static final String ins = "CREATE TABLE CONTACTOS (idContacto INTEGER PRIMARY KEY, Nombre VARCHAR(50))";

    public BDContactos(Context context) {
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ins);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS CONTACTOS");
        onCreate(db);
    }

    public long insertarContacto(long idContacto, String nombre){
        long nreg_afectados = -1;
        SQLiteDatabase db = getWritableDatabase();

        if (db != null) {
            ContentValues valores = new ContentValues();
            valores.put("idContacto", idContacto);
            valores.put("Nombre", nombre);
            nreg_afectados = db.insert("CONTACTOS", null, valores);
        }
        db.close();
        return nreg_afectados;
    }

    public ArrayList<Contacto> consultarContactos(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Contacto> contactos = new ArrayList();
        Contacto contacto;
        long idContacto;
        String nombre;

        if (db != null) {
            String[] campos = {"idContacto", "Nombre"};
            Cursor c = db.query("CONTACTOS", campos, null, null, null, null, "Nombre ASC");
            c.moveToFirst();
            for(int i=0; i<c.getCount(); i++){
                idContacto = c.getLong(0);
                nombre = c.getString(1);
                contacto = new Contacto(idContacto, nombre);
                contactos.add(contacto);
                c.moveToNext();
            }
            c.close();
        }
        db.close();
        return contactos;
    }

    public long nuevaID(){
        SQLiteDatabase db = getReadableDatabase();
        long nContactos;
        if (db != null) {
            String[] campos = {"idContacto"};
            Cursor c = db.query("CONTACTOS", campos, null, null, null, null, "idContacto DESC");
            if(c.moveToFirst()){
                nContactos = Long.parseLong(c.getString(0));
            }else{
                nContactos = 0;
            }
            c.close();
            db.close();
            return nContactos + 1;
        }
        return -1;
    }
}
