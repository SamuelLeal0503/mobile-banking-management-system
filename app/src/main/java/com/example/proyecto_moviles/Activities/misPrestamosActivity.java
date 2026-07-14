package com.example.proyecto_moviles.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.proyecto_moviles.Controllers.PrestamoController;
import com.example.proyecto_moviles.Modelos.Cliente;
import com.example.proyecto_moviles.Modelos.Prestamo;
import com.example.proyecto_moviles.R;

import java.text.DecimalFormat;
import java.util.List;

public class misPrestamosActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_prestamos);

        Cliente cliente = (Cliente) getIntent().getSerializableExtra("ClienteSession");
        PrestamoController prestamoController = new PrestamoController(this);
        List<Prestamo> prestamos = prestamoController.getPrestamosByCliente(cliente.getId());

        LinearLayout screenContainer = findViewById(R.id.screenContainer);
        double cuotaMensual;

        for (Prestamo prestamo : prestamos) {
            TextView idTextView = new TextView(this);
            idTextView.setText(String.valueOf(prestamo.getId()));
            idTextView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            idTextView.setGravity(Gravity.CENTER);

            TextView tipoTextView = new TextView(this);
            tipoTextView.setText(prestamo.getTipo());
            tipoTextView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            tipoTextView.setGravity(Gravity.CENTER);

            TextView mensualTextView = new TextView(this);
            cuotaMensual = prestamo.getCuotaMensual();
            DecimalFormat formato = new DecimalFormat("#.##");
            String cuotaMensualFormateada = formato.format(cuotaMensual);
            mensualTextView.setText(cuotaMensualFormateada);
            mensualTextView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            mensualTextView.setGravity(Gravity.CENTER);

            Button pagarButton = new Button(this);
            pagarButton.setText("PAGAR");
            int color = ContextCompat.getColor(this, R.color.soldelLogo);
            pagarButton.setBackgroundTintList(ColorStateList.valueOf(color));
            pagarButton.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            pagarButton.setGravity(Gravity.CENTER);
            pagarButton.setOnClickListener(v -> {

                Intent intent = new Intent(misPrestamosActivity.this, prestamoPagadoActivity.class);
                intent.putExtra("ClienteSession", cliente);
                intent.putExtra("PrestamoSession", prestamo);
                startActivity(intent);
                finish();
            });

            LinearLayout prestamoLayout = new LinearLayout(this);
            prestamoLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            prestamoLayout.setGravity(Gravity.CENTER_VERTICAL);
            prestamoLayout.setOrientation(LinearLayout.HORIZONTAL);
            prestamoLayout.setPadding(16, 0, 0, 16);
            prestamoLayout.addView(idTextView);
            prestamoLayout.addView(tipoTextView);
            prestamoLayout.addView(mensualTextView);
            prestamoLayout.addView(pagarButton);

            screenContainer.addView(prestamoLayout);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Cliente cliente  = (Cliente) getIntent().getSerializableExtra("ClienteSession");
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = new Intent(misPrestamosActivity.this, MainScreenClienteActivity.class);
            intent.putExtra("ClienteSession", cliente);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
