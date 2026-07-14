package com.example.proyecto_moviles.BaseDeDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "moviles.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableUsuarios = "CREATE TABLE Usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cedula INTEGER UNIQUE," +
                "nombreUsuario TEXT," +
                "contrasena TEXT)";
        db.execSQL(createTableUsuarios);


        String createTableClientes = "CREATE TABLE Clientes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cedula INTEGER UNIQUE," +
                "contrasena TEXT," +
                "nombre TEXT," +
                "salario REAL," +
                "telefono TEXT," +
                "fechaNacimiento TEXT," +
                "estadoCivil TEXT," +
                "direccion TEXT)";
        db.execSQL(createTableClientes);

        String createTablePrestamos = "CREATE TABLE Prestamos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idCliente INTEGER," +
                "monto REAL," +
                "plazo INTEGER," +
                "tipoCredito TEXT," +
                "interes REAL," +
                "montoMensual REAL," +
                "FOREIGN KEY (idCliente) REFERENCES Clientes(id))";
        db.execSQL(createTablePrestamos);

        String createTableAhorros = "CREATE TABLE Ahorros (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idCliente INTEGER," +
                "monto REAL," +
                "cuotaMensual REAL," +
                "tipoAhorro TEXT CHECK (tipoAhorro IN ('Navideño', 'Escolar', 'Marchamo', 'Extraordinario'))," +
                "activo BOOLEAN," +
                "FOREIGN KEY (idCliente) REFERENCES Clientes(id))";
        db.execSQL(createTableAhorros);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        db.execSQL("DROP TABLE IF EXISTS Clientes");
        db.execSQL("DROP TABLE IF EXISTS Prestamos");
        db.execSQL("DROP TABLE IF EXISTS Ahorros");

        onCreate(db);
    }
}
