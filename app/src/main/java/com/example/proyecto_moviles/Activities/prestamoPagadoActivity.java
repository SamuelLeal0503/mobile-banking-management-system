package com.example.proyecto_moviles.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto_moviles.Controllers.PrestamoController;
import com.example.proyecto_moviles.R;
import com.example.proyecto_moviles.Modelos.Cliente;
import com.example.proyecto_moviles.Modelos.Prestamo;

import java.text.DecimalFormat;

public class prestamoPagadoActivity extends AppCompatActivity {

    TextView txtID, txtCedula, txtMonto, txtCuota, txtPlazo, txtTipo, txtInteres;
    EditText txtPagar;
    Button btnVolver, btnPagar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestamo_pagado);

        Cliente cliente = (Cliente) getIntent().getSerializableExtra("ClienteSession");
        Prestamo prestamo = (Prestamo) getIntent().getSerializableExtra("PrestamoSession");

        txtID = findViewById(R.id.txtID);
        txtCedula = findViewById(R.id.txtCedula);
        txtMonto = findViewById(R.id.txtMonto);
        txtCuota = findViewById(R.id.txtCuota);
        txtPlazo = findViewById(R.id.txtPlazo);
        txtTipo = findViewById(R.id.txtTipo);
        txtInteres = findViewById(R.id.txtInteres);
        txtPagar = findViewById(R.id.txtPagar);
        btnPagar = findViewById(R.id.btnPagar);
        btnVolver = findViewById(R.id.btnVolver);
        txtID.setText(String.format("%s", prestamo.getId()));
        txtCedula.setText(String.format("%s", cliente.getCedula()));
        DecimalFormat formato = new DecimalFormat("#,##0.00");
        String salarioFormateado = formato.format(prestamo.getMonto());
        txtMonto.setText(salarioFormateado);
        txtCuota.setText(String.format("%s", prestamo.getCuotaMensual()));
        txtPlazo.setText(String.format("%s", prestamo.getPlazo()));
        txtTipo.setText(prestamo.getTipo());
        txtInteres.setText(String.format("%s", prestamo.getInteres()));

        PrestamoController prestamoController = new PrestamoController(this);

        btnPagar.setOnClickListener(v -> {
            if (txtPagar.getText().toString().isEmpty()) {
                Toast.makeText(prestamoPagadoActivity.this, "El monto de pago no puede estar vacio!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (txtPagar.getText().toString().equals("0")) {
                Toast.makeText(prestamoPagadoActivity.this, "Debe ingresar un monto de pago mayor a 0!", Toast.LENGTH_SHORT).show();
                return;
            }
            double montoPagar = Double.parseDouble(txtPagar.getText().toString());

            if (montoPagar > prestamo.getMonto()) {
                Toast.makeText(prestamoPagadoActivity.this, "Debe ingresar un monto de pago menor al monto del prestamo!", Toast.LENGTH_LONG).show();
                return;
            }

            if(montoPagar == prestamo.getMonto()){
                prestamoController.deletePrestamo(prestamo.getId());
                Intent intent = new Intent(prestamoPagadoActivity.this, misPrestamosActivity.class);
                intent.putExtra("ClienteSession", cliente);
                startActivity(intent);
                finish();
            }

            double nuevoMonto = prestamo.getMonto() - montoPagar;

            double montoPagarPorMes;
            int plazo = prestamo.getPlazo();
            switch (plazo) {
                case 3:
                    montoPagarPorMes = nuevoMonto / 36;
                    break;
                case 5:
                    montoPagarPorMes = nuevoMonto / 60;
                    break;
                case 10:
                    montoPagarPorMes = nuevoMonto / 120;
                    break;
                default:
                    montoPagarPorMes = 0;
            }

            Prestamo newPrestamo = new Prestamo();
            newPrestamo.setId(prestamo.getId());
            newPrestamo.setIdCliente(prestamo.getIdCliente());
            newPrestamo.setMonto(nuevoMonto);
            newPrestamo.setInteres(prestamo.getInteres());
            newPrestamo.setPlazo(prestamo.getPlazo());
            newPrestamo.setCuotaMensual(montoPagarPorMes);
            newPrestamo.setTipo(prestamo.getTipo());
            prestamoController.updatePrestamo(newPrestamo);

            Intent intent = getIntent();
            intent.putExtra("ClienteSession", cliente);
            intent.putExtra("PrestamoSession", newPrestamo);
            recreate();
        });

        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(prestamoPagadoActivity.this, misPrestamosActivity.class);
            intent.putExtra("ClienteSession", cliente);
            startActivity(intent);
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Cliente cliente  = (Cliente) getIntent().getSerializableExtra("ClienteSession");
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = new Intent(prestamoPagadoActivity.this, misPrestamosActivity.class);
            intent.putExtra("ClienteSession", cliente);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
