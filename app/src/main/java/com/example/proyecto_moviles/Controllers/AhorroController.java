package com.example.proyecto_moviles.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.proyecto_moviles.BaseDeDatos.DBHelper;
import com.example.proyecto_moviles.Modelos.Ahorro;

import java.util.ArrayList;
import java.util.List;

public class AhorroController {

    private static final String TABLE_NAME = "Ahorros";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ID_CLIENTE = "idCliente";
    private static final String COLUMN_MONTO = "monto";
    private static final String COLUMN_CUOTA_MENSUAL = "cuotaMensual";
    private static final String COLUMN_TIPO = "tipoAhorro";
    private static final String COLUMN_ACTIVO = "activo";
    private final DBHelper dbHelper;

    public AhorroController(Context context) {
        dbHelper = new DBHelper(context);
    }

    // Get all ahorros
    public List<Ahorro> getAllAhorros() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                COLUMN_ID,
                COLUMN_ID_CLIENTE,
                COLUMN_MONTO,
                COLUMN_CUOTA_MENSUAL,
                COLUMN_TIPO,
                COLUMN_ACTIVO
        };
        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        List<Ahorro> ahorros = new ArrayList<>();
        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID));
            long idCliente = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID_CLIENTE));
            double monto = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_MONTO));
            double cuotaMensual = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_CUOTA_MENSUAL));
            String tipo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIPO));
            boolean activo = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ACTIVO)) == 0;
            Ahorro ahorro = new Ahorro(id, idCliente, monto, cuotaMensual, tipo, activo);
            ahorros.add(ahorro);
        }
        cursor.close();
        return ahorros;
    }

    // Get all ahorros of a cliente
    public List<Ahorro> getAhorrosByCliente(long clienteId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                COLUMN_ID,
                COLUMN_ID_CLIENTE,
                COLUMN_MONTO,
                COLUMN_CUOTA_MENSUAL,
                COLUMN_TIPO,
                COLUMN_ACTIVO
        };
        String selection = COLUMN_ID_CLIENTE + " = ?";
        String[] selectionArgs = { String.valueOf(clienteId) };
        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        List<Ahorro> ahorros = new ArrayList<>();
        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID));
            long idCliente = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID_CLIENTE));
            double monto = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_MONTO));
            double cuotaMensual = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_CUOTA_MENSUAL));
            String tipo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIPO));
            boolean activo = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ACTIVO)) == 0;
            Ahorro ahorro = new Ahorro(id, idCliente, monto, cuotaMensual, tipo, activo);
            ahorros.add(ahorro);
        }
        cursor.close();
        return ahorros;
    }

    // Get a single ahorro by ID
    public Ahorro getAhorroById(long ahorroId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                COLUMN_ID,
                COLUMN_ID_CLIENTE,
                COLUMN_MONTO,
                COLUMN_CUOTA_MENSUAL,
                COLUMN_TIPO,
                COLUMN_ACTIVO
        };
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = { String.valueOf(ahorroId) };
        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        Ahorro ahorro = null;
        if (cursor.moveToFirst()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID));
            long idCliente = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID_CLIENTE));
            double monto = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_MONTO));
            double cuotaMensual = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_CUOTA_MENSUAL));
            String tipo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIPO));
            boolean activo = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ACTIVO)) == 0;
            ahorro = new Ahorro(id, idCliente, monto, cuotaMensual, tipo,activo);
        }
        cursor.close();
        return ahorro;
    }

    // Add a new ahorro
    public long addAhorro(Ahorro ahorro) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_CLIENTE, ahorro.getIdCliente());
        values.put(COLUMN_MONTO, ahorro.getMonto());
        values.put(COLUMN_CUOTA_MENSUAL, ahorro.getCuotaMensual());
        values.put(COLUMN_TIPO, ahorro.getTipo());
        values.put(COLUMN_ACTIVO,ahorro.getActivo());
        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }

    // Update an existing ahorro
    public int updateAhorro(Ahorro ahorro) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_CLIENTE, ahorro.getIdCliente());
        values.put(COLUMN_MONTO, ahorro.getMonto());
        values.put(COLUMN_CUOTA_MENSUAL, ahorro.getCuotaMensual());
        values.put(COLUMN_TIPO, ahorro.getTipo());
        values.put(COLUMN_ACTIVO,ahorro.getActivo());
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = { String.valueOf(ahorro.getId()) };
        int count = db.update(TABLE_NAME, values, selection, selectionArgs);
        db.close();
        return count;
    }
    public int updateMonto(Ahorro ahorro) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MONTO, ahorro.getMonto());
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = { String.valueOf(ahorro.getId()) };
        int count = db.update(TABLE_NAME, values, selection, selectionArgs);
        db.close();
        return count;
    }

    // Delete an existing ahorro
    public int deleteAhorro(long ahorroId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = { String.valueOf(ahorroId) };
        int count = db.delete(TABLE_NAME, selection, selectionArgs);
        db.close();
        return count;
    }

}
