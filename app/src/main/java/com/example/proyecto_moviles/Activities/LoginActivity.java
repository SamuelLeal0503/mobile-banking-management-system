package com.example.proyecto_moviles.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_moviles.Controllers.AdminController;
import com.example.proyecto_moviles.Controllers.ClienteController;
import com.example.proyecto_moviles.Modelos.Admin;
import com.example.proyecto_moviles.Modelos.Cliente;
import com.example.proyecto_moviles.R;

public class LoginActivity extends AppCompatActivity {

    EditText usernameEditText, passwordEditText;
    Button loginButton, exitButton;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.et_username);
        passwordEditText = findViewById(R.id.et_password);
        loginButton = findViewById(R.id.btn_login);
        exitButton = findViewById(R.id.btn_exit);

        radioGroup = findViewById(R.id.radio_group);

        AdminController adminController = new AdminController(this);
        ClienteController clienteController = new ClienteController(this);

        loginButton.setOnClickListener(view -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            if (selectedId == R.id.radio_admin) {
                Admin admin = new Admin();
                admin.setCedula(usernameEditText.getText().toString());
                admin.setContrasena(passwordEditText.getText().toString());
                Admin ad = adminController.adminLogin(admin);
                if (ad != null) {
                    // Authentication successful, start main activity
                    Intent intent = new Intent(LoginActivity.this, PantallaPrincipalAdminActivity.class);
                    intent.putExtra("AdminSession", ad);
                    startActivity(intent);
                    finish();
                } else {
                    // Authentication failed, show error message
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            } else if (selectedId == R.id.radio_cliente) {
                Cliente cliente = new Cliente();
                cliente.setCedula(usernameEditText.getText().toString());
                cliente.setContrasena(passwordEditText.getText().toString());
                Cliente cd = clienteController.loginCliente(cliente);
                if (cd != null) {
                    // Authentication successful, start main activity
                    Intent intent = new Intent(LoginActivity.this, MainScreenClienteActivity.class);
                    intent.putExtra("ClienteSession", cd);
                    startActivity(intent);
                    finish();
                } else {
                    // Authentication failed, show error message
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(LoginActivity.this, "Seleccione un tipo de usuario", Toast.LENGTH_SHORT).show();
            }
        });

        exitButton.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setMessage("Seguro que quieres salir de la aplicacion?");
            builder.setPositiveButton("Si", (dialogInterface, i) -> finishAffinity());
            builder.setNegativeButton("No", null);
            builder.show();
        });

    }
}

