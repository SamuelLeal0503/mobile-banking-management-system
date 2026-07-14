package com.example.proyecto_moviles.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_moviles.Controllers.ClienteController;
import com.example.proyecto_moviles.Modelos.Cliente;
import com.example.proyecto_moviles.R;

public class prestamoClienteActivity extends AppCompatActivity {

        Button btnAsignar;

        EditText txtCedula;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_cliente_prestamo);

            btnAsignar = findViewById(R.id.btnAsignar);
            txtCedula = findViewById(R.id.txtCedulaCliente);

            ClienteController clienteController = new ClienteController(prestamoClienteActivity.this);

            btnAsignar.setOnClickListener(v -> {
                if (txtCedula.getText().toString().isEmpty()) {
                    Toast.makeText(prestamoClienteActivity.this, "La cedula no puede estar vacia!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (clienteController.getCliente(txtCedula.getText().toString()) == null) {
                    Toast.makeText(prestamoClienteActivity.this, "El cliente no existe!", Toast.LENGTH_SHORT).show();
                } else {
                    Cliente cliente = clienteController.getCliente(txtCedula.getText().toString());
                    Intent intent = new Intent(prestamoClienteActivity.this, AsignarPrestamoActivity.class);
                    intent.putExtra("clienteSession", cliente);
                    startActivity(intent);
                    finish();
                }
            });
        }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Cliente cliente  = (Cliente) getIntent().getSerializableExtra("ClienteSession");
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = new Intent(prestamoClienteActivity.this, PantallaPrincipalAdminActivity.class);
            intent.putExtra("ClienteSession", cliente);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
