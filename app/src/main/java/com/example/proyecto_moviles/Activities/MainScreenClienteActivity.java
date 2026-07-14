package com.example.proyecto_moviles.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_moviles.Modelos.Cliente;
import com.example.proyecto_moviles.R;

public class MainScreenClienteActivity extends AppCompatActivity {

    Button btnInfo, btnAhorros, btnCuota, btnPrestamos, btnCerrarSesion;
    TextView txtMainNombre;
    Cliente cliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mainscreen_cliente);

        cliente  = (Cliente) getIntent().getSerializableExtra("ClienteSession");
        btnPrestamos = findViewById(R.id.btnPrestamos);
        btnCuota = findViewById(R.id.btnCuota);
        btnInfo = findViewById(R.id.btnInfo);
        btnAhorros = findViewById(R.id.btnAhorros);
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        txtMainNombre = findViewById(R.id.txtMainNombre);
        txtMainNombre.setText(cliente.getNombre());

        btnPrestamos.setOnClickListener(v -> {
            Intent intent = new Intent(MainScreenClienteActivity.this, misPrestamosActivity.class);
            intent.putExtra("ClienteSession", cliente);
            startActivity(intent);
            finish();
        });

        btnCuota.setOnClickListener(v -> {
            Intent intent = new Intent(MainScreenClienteActivity.this, CalcularCuotaActivity.class);
            intent.putExtra("ClienteSession", cliente);
            startActivity(intent);
            finish();
        });

        btnInfo.setOnClickListener(v -> {
            Intent intent = new Intent(MainScreenClienteActivity.this, PersonalClienteActivity.class);
            intent.putExtra("ClienteSession", cliente);
            startActivity(intent);
            finish();
        });

        btnAhorros.setOnClickListener(v -> {
            Intent intent = new Intent(MainScreenClienteActivity.this, GestionarAhorrosActivity.class);
            intent.putExtra("ClienteSession", cliente);
            startActivity(intent);
            finish();
        });

        btnCerrarSesion.setOnClickListener(v -> {
            Intent intent = new Intent(MainScreenClienteActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
