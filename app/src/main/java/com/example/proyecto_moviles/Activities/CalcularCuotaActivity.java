package com.example.proyecto_moviles.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_moviles.Modelos.Cliente;
import com.example.proyecto_moviles.R;

import java.util.Objects;

public class CalcularCuotaActivity extends AppCompatActivity {

    private EditText montoPrestamoEditText;
    private Spinner tipoCredito;
    private Spinner plazo;
    private TextView cuotaMensualTextView;
    String [] opcionesTime = {"3 años", "5 años", "10 años"};
    String [] opcionesType = {"Hipotecario", "Educacion", "Personal","Viajes"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_cuota);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        montoPrestamoEditText = findViewById(R.id.monto_prestamo_edit_text);
        tipoCredito = findViewById(R.id.tipo_credito_spinner);
        plazo = findViewById(R.id.plazo_spinner);
        cuotaMensualTextView = findViewById(R.id.cuota_mensual_text_view);
        Button calcularCuotaButton = findViewById(R.id.calcular_cuota_button);

        ArrayAdapter<String> adaptador1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcionesType);
        tipoCredito.setAdapter(adaptador1);
        ArrayAdapter<String> adaptador2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcionesTime);
        plazo.setAdapter(adaptador2);

        calcularCuotaButton.setOnClickListener(v -> calcularCuota());


    }

    private void calcularCuota() {
        if(montoPrestamoEditText.getText().toString().isEmpty()){
            Toast.makeText(CalcularCuotaActivity.this, "Debe ingresar un monto de crédito", Toast.LENGTH_SHORT).show();
            return;
        }
        if(montoPrestamoEditText.getText().toString().equals("0")){
            Toast.makeText(CalcularCuotaActivity.this, "Debe ingresar un monto de crédito mayor a 0", Toast.LENGTH_SHORT).show();
            return;
        }
        double montoCredito = Double.parseDouble(montoPrestamoEditText.getText().toString());


        String tipo = tipoCredito.getSelectedItem().toString();

        String periodoCredito = plazo.getSelectedItem().toString();

        double interes;
        switch (tipo) {
            case "Hipotecario":
                interes = 0.075;
                break;
            case "Educacion":
                interes = 0.08;
                break;
            case "Personal":
                interes = 0.1;
                break;
            case "Viajes":
                interes = 0.12;
                break;
            default:
                interes = 0;
        }
        if(interes == 0){
            Toast.makeText(CalcularCuotaActivity.this, "Debe seleccionar un tipo de crédito", Toast.LENGTH_SHORT).show();
            return;
        }
        double montoTotalPagar = montoCredito * (1 + interes);

        double montoPagarPorMes;
        int plazo;
        switch (periodoCredito) {
            case "3 años":
                plazo = 3;
                montoPagarPorMes = montoTotalPagar / 36;
                break;
            case "5 años":
                plazo = 5;
                montoPagarPorMes = montoTotalPagar / 60;
                break;
            case "10 años":
                plazo = 10;
                montoPagarPorMes = montoTotalPagar / 120;
                break;
            default:
                plazo = 0;
                montoPagarPorMes = 0;
        }
        if(plazo == 0){
            Toast.makeText(CalcularCuotaActivity.this, "Debe seleccionar un periodo de crédito", Toast.LENGTH_SHORT).show();
            return;
        }

        cuotaMensualTextView.setText(String.format("%s", montoPagarPorMes));
        Toast.makeText(CalcularCuotaActivity.this, "Cuota calculada correctamente!", Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Cliente cliente  = (Cliente) getIntent().getSerializableExtra("ClienteSession");
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = new Intent(CalcularCuotaActivity.this, MainScreenClienteActivity.class);
            intent.putExtra("ClienteSession", cliente);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
