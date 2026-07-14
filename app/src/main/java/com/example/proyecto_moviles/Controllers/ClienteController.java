package com.example.proyecto_moviles.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.proyecto_moviles.BaseDeDatos.DBHelper;
import com.example.proyecto_moviles.Modelos.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    private final DBHelper dbHelper;

    public ClienteController(Context context) {
        dbHelper = new DBHelper(context);
    }

    // Create operation
    public boolean addCliente(Cliente cliente) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("cedula", cliente.getCedula());
        values.put("nombre", cliente.getNombre());
        values.put("contrasena", cliente.getContrasena());
        values.put("salario", cliente.getSalario());
        values.put("telefono", cliente.getTelefono());
        values.put("fechaNacimiento", cliente.getFechaNacimiento());
        values.put("estadoCivil", cliente.getEstadoCivil());
        values.put("direccion", cliente.getDireccion());
        long result = db.insert("Clientes", null, values);
        return result != -1;
    }

    // Read operation
    public List<Cliente> getAllClientes() {
        List<Cliente> clientes = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                "id",
                "cedula",
                "nombre",
                "contrasena",
                "salario",
                "telefono",
                "fechaNacimiento",
                "estadoCivil",
                "direccion"
        };
        Cursor cursor = db.query(
                "Clientes",
                projection,
                null,
                null,
                null,
                null,
                null
        );
        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
            String cedula = cursor.getString(cursor.getColumnIndexOrThrow("cedula"));
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
            String contrasena = cursor.getString(cursor.getColumnIndexOrThrow("contrasena"));
            double salario = cursor.getDouble(cursor.getColumnIndexOrThrow("salario"));
            String telefono = cursor.getString(cursor.getColumnIndexOrThrow("telefono"));
            String fechaNacimiento = cursor.getString(cursor.getColumnIndexOrThrow("fechaNacimiento"));
            String estadoCivil = cursor.getString(cursor.getColumnIndexOrThrow("estadoCivil"));
            String direccion = cursor.getString(cursor.getColumnIndexOrThrow("direccion"));
            clientes.add(new Cliente(id, cedula, nombre, contrasena, salario, telefono, fechaNacimiento, estadoCivil, direccion));
        }
        cursor.close();
        return clientes;
    }

    // Update operation
    public boolean updateCliente(Cliente cliente) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("cedula", cliente.getCedula());
        values.put("nombre", cliente.getNombre());
        values.put("contrasena", cliente.getContrasena());
        values.put("salario", cliente.getSalario());
        values.put("telefono", cliente.getTelefono());
        values.put("fechaNacimiento", cliente.getFechaNacimiento());
        values.put("estadoCivil", cliente.getEstadoCivil());
        values.put("direccion", cliente.getDireccion());
        String selection = "id" + " = ?";
        String[] selectionArgs = { String.valueOf(cliente.getId()) };
        int count = db.update(
                "Clientes",
                values,
                selection,
                selectionArgs
        );
        return count > 0;
    }

    // Delete operation
    public boolean deleteCliente(String cedula) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = "cedula" + " = ?";
        String[] selectionArgs = { cedula };
        int count = db.delete(
                "Clientes",
                selection,
                selectionArgs
        );
        return count > 0;
    }

    // Get single cliente operation
    public Cliente getCliente(String cedulaP) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                "id",
                "cedula",
                "nombre",
                "contrasena",
                "salario",
                "telefono",
                "fechaNacimiento",
                "estadoCivil",
                "direccion"
        };
        String selection = "cedula" + " = ?";
        String[] selectionArgs = { cedulaP };
        Cursor cursor = db.query(
                "Clientes",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        Cliente cliente = null;
        if (cursor.moveToFirst()) {
            long clientId = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
            String cedula = cursor.getString(cursor.getColumnIndexOrThrow("cedula"));
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
            String contrasena = cursor.getString(cursor.getColumnIndexOrThrow("contrasena"));
            double salario = cursor.getDouble(cursor.getColumnIndexOrThrow("salario"));
            String telefono = cursor.getString(cursor.getColumnIndexOrThrow("telefono"));
            String fechaNacimiento = cursor.getString(cursor.getColumnIndexOrThrow("fechaNacimiento"));
            String estadoCivil = cursor.getString(cursor.getColumnIndexOrThrow("estadoCivil"));
            String direccion = cursor.getString(cursor.getColumnIndexOrThrow("direccion"));
            cliente = new Cliente(clientId, cedula, nombre, contrasena, salario, telefono, fechaNacimiento, estadoCivil, direccion);
        }
        cursor.close();
        return cliente;
    }

    public Cliente getClienteId(long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                "id",
                "cedula",
                "nombre",
                "contrasena",
                "salario",
                "telefono",
                "fechaNacimiento",
                "estadoCivil",
                "direccion"
        };
        String selection = "id" + " = ?";
        String[] selectionArgs = { String.valueOf(id) };
        Cursor cursor = db.query(
                "Clientes",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        Cliente cliente = null;
        if (cursor.moveToFirst()) {
            long clientId = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
            String cedula = cursor.getString(cursor.getColumnIndexOrThrow("cedula"));
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
            String contrasena = cursor.getString(cursor.getColumnIndexOrThrow("contrasena"));
            double salario = cursor.getDouble(cursor.getColumnIndexOrThrow("salario"));
            String telefono = cursor.getString(cursor.getColumnIndexOrThrow("telefono"));
            String fechaNacimiento = cursor.getString(cursor.getColumnIndexOrThrow("fechaNacimiento"));
            String estadoCivil = cursor.getString(cursor.getColumnIndexOrThrow("estadoCivil"));
            String direccion = cursor.getString(cursor.getColumnIndexOrThrow("direccion"));
            cliente = new Cliente(clientId, cedula, nombre, contrasena, salario, telefono, fechaNacimiento, estadoCivil, direccion);
        }
        cursor.close();
        return cliente;
    }

    // Login operation
    public Cliente loginCliente(Cliente clienteP) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                "id",
                "cedula",
                "nombre",
                "contrasena",
                "salario",
                "telefono",
                "fechaNacimiento",
                "estadoCivil",
                "direccion"
        };
        String selection = "cedula" + " = ? AND " + "contrasena" + " = ?";
        String[] selectionArgs = { clienteP.getCedula(), clienteP.getContrasena() };
        Cursor cursor = db.query(
                "Clientes",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        Cliente cliente = null;
        if (cursor.moveToFirst()) {
            long clientId = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
            String cedula = cursor.getString(cursor.getColumnIndexOrThrow("cedula"));
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
            String contrasena = cursor.getString(cursor.getColumnIndexOrThrow("contrasena"));
            double salario = cursor.getDouble(cursor.getColumnIndexOrThrow("salario"));
            String telefono = cursor.getString(cursor.getColumnIndexOrThrow("telefono"));
            String fechaNacimiento = cursor.getString(cursor.getColumnIndexOrThrow("fechaNacimiento"));
            String estadoCivil = cursor.getString(cursor.getColumnIndexOrThrow("estadoCivil"));
            String direccion = cursor.getString(cursor.getColumnIndexOrThrow("direccion"));
            cliente = new Cliente(clientId, cedula, nombre, contrasena, salario, telefono, fechaNacimiento, estadoCivil, direccion);
        }
        cursor.close();
        return cliente;
    }

}
