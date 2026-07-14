package com.example.proyecto_moviles.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.proyecto_moviles.BaseDeDatos.DBHelper;
import com.example.proyecto_moviles.Modelos.Prestamo;

import java.util.ArrayList;
import java.util.List;

public class PrestamoController {
    private static final String TABLE_NAME = "Prestamos";

    // Column names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ID_CLIENTE = "idCliente";
    private static final String COLUMN_MONTO = "monto";
    private static final String COLUMN_INTERES = "interes";
    private static final String COLUMN_PLAZO = "plazo";
    private static final String COLUMN_CUOTA_MENSUAL = "montoMensual";
    private static final String COLUMN_TIPO = "tipoCredito";
    private final DBHelper dbHelper;

    public PrestamoController(Context context) {
        dbHelper = new DBHelper(context);
    }

    // Create operation
    public long addPrestamo(Prestamo prestamo) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_CLIENTE, prestamo.getIdCliente());
        values.put(COLUMN_MONTO, prestamo.getMonto());
        values.put(COLUMN_INTERES, prestamo.getInteres());
        values.put(COLUMN_PLAZO, prestamo.getPlazo());
        values.put(COLUMN_CUOTA_MENSUAL, prestamo.getCuotaMensual());
        values.put(COLUMN_TIPO, prestamo.getTipo());
        return db.insert(TABLE_NAME, null, values);
    }

    // Read operations
    public Prestamo getPrestamo(long prestamoId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                COLUMN_ID,
                COLUMN_ID_CLIENTE,
                COLUMN_MONTO,
                COLUMN_INTERES,
                COLUMN_PLAZO,
                COLUMN_CUOTA_MENSUAL,
                COLUMN_TIPO
        };
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = { String.valueOf(prestamoId) };
        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        Prestamo prestamo = null;
        if (cursor.moveToFirst()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID));
            long idCliente = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID_CLIENTE));
            double monto = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_MONTO));
            double interes = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_INTERES));
            int plazo = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PLAZO));
            double cuotaMensual = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_CUOTA_MENSUAL));
            String tipo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIPO));
            prestamo = new Prestamo(id, idCliente, monto, interes, plazo, cuotaMensual, tipo);
        }
        cursor.close();
        return prestamo;
    }

    public List<Prestamo> getAllPrestamos() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                COLUMN_ID,
                COLUMN_ID_CLIENTE,
                COLUMN_MONTO,
                COLUMN_INTERES,
                COLUMN_PLAZO,
                COLUMN_CUOTA_MENSUAL,
                COLUMN_TIPO
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
        List<Prestamo> prestamos = new ArrayList<>();
        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID));
            long idCliente = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID_CLIENTE));
            double monto = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_MONTO));
            double interes = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_INTERES));
            int plazo = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PLAZO));
            double cuotaMensual = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_CUOTA_MENSUAL));
            String tipo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIPO));
            Prestamo prestamo = new Prestamo(id, idCliente, monto, interes, plazo, cuotaMensual, tipo);
            prestamos.add(prestamo);
        }
        cursor.close();
        return prestamos;
    }

    // Update operation
    public int updatePrestamo(Prestamo prestamo) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_CLIENTE, prestamo.getIdCliente());
        values.put(COLUMN_MONTO, prestamo.getMonto());
        values.put(COLUMN_INTERES, prestamo.getInteres());
        values.put(COLUMN_PLAZO, prestamo.getPlazo());
        values.put(COLUMN_CUOTA_MENSUAL, prestamo.getCuotaMensual());
        values.put(COLUMN_TIPO, prestamo.getTipo());
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = { String.valueOf(prestamo.getId()) };
        return db.update(TABLE_NAME, values, selection, selectionArgs);
    }

    // Delete operation
    public void deletePrestamo(long prestamoId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = { String.valueOf(prestamoId) };
        db.delete(TABLE_NAME, selection, selectionArgs);
    }

    // Get all prestamos of a cliente
    public List<Prestamo> getPrestamosByCliente(long clienteId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                COLUMN_ID,
                COLUMN_ID_CLIENTE,
                COLUMN_MONTO,
                COLUMN_INTERES,
                COLUMN_PLAZO,
                COLUMN_CUOTA_MENSUAL,
                COLUMN_TIPO
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
        List<Prestamo> prestamos = new ArrayList<>();
        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID));
            long idCliente = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID_CLIENTE));
            double monto = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_MONTO));
            double interes = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_INTERES));
            int plazo = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PLAZO));
            double cuotaMensual = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_CUOTA_MENSUAL));
            String tipo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIPO));
            Prestamo prestamo = new Prestamo(id, idCliente, monto, interes, plazo, cuotaMensual, tipo);
            prestamos.add(prestamo);
        }
        cursor.close();
        return prestamos;
    }

}
