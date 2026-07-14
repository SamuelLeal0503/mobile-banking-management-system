package com.example.proyecto_moviles.Controllers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.proyecto_moviles.BaseDeDatos.DBHelper;
import com.example.proyecto_moviles.Modelos.Admin;

public class AdminController {
    private final DBHelper dbHelper;

    public AdminController(Context context) {
        dbHelper = new DBHelper(context);
    }

    public Admin adminLogin(Admin admin){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("select * from Usuarios where cedula = ? and contrasena = ?", new String[] {admin.getCedula(), admin.getContrasena()});

        Admin adminL = null;
        if (c.moveToFirst()){
            String id = c.getString(c.getColumnIndexOrThrow("id"));
            String cedula = c.getString(c.getColumnIndexOrThrow("cedula"));
            String nombreUsuario = c.getString(c.getColumnIndexOrThrow("nombreUsuario"));
            adminL = new Admin(id, cedula, nombreUsuario, "");
        }
        c.close();
        return adminL;
    }

}
