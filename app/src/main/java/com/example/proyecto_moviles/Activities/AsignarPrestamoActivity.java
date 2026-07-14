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

import com.example.proyecto_moviles.Controllers.PrestamoController;
import com.example.proyecto_moviles.Modelos.Cliente;
import com.example.proyecto_moviles.Modelos.Prestamo;
import com.example.proyecto_moviles.R;

import java.text.DecimalFormat;

public class AsignarPrestamoActivity extends AppCompatActivity {
    TextView txtCedula;
    TextView txtNombre;
    TextView txtSalario;
    TextView txtTelefono;
    TextView txtNacimiento;
    TextView txtCivil;
    TextView txtDireccion;
    EditText txtCredito;
    String [] opcionesTime = {"3 años", "5 años", "10 años"};
    String [] opcionesType = {"Hipotecario", "Educacion", "Personal","Viajes"};
    Spinner spinnerTime;
    Spinner spinnerType;
    Button btnAsignar;
    TextView txtMontoMensual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_asignar_prestamo);

        Cliente clienteSession = (Cliente) getIntent().getSerializableExtra("clienteSession");

        txtCedula = findViewById(R.id.txtCedula);
        txtCedula.setText(clienteSession.getCedula());
        txtNombre = findViewById(R.id.txtMonto);
        txtNombre.setText(clienteSession.getNombre());
        txtSalario = findViewById(R.id.txtInteres);
        double salario = clienteSession.getSalario();
        DecimalFormat formato = new DecimalFormat("#,##0.00");
        String salarioFormateado = formato.format(salario);
        txtSalario.setText(salarioFormateado);
        txtTelefono = findViewById(R.id.txtPlazo);
        txtTelefono.setText(clienteSession.getTelefono());
        txtNacimiento = findViewById(R.id.txtCuota);
        txtNacimiento.setText(clienteSession.getFechaNacimiento());
        txtCivil = findViewById(R.id.txtTipo);
        txtCivil.setText(clienteSession.getEstadoCivil());
        txtDireccion = findViewById(R.id.txtDireccion);
        txtDireccion.setText(clienteSession.getDireccion());
        txtCredito = findViewById(R.id.txtCedulaCliente);
        btnAsignar = findViewById(R.id.btnAsignar);
        txtMontoMensual = findViewById(R.id.txtMontoMensual);
        spinnerTime = findViewById(R.id.spinnerTime);
        spinnerType = findViewById(R.id.spinnerType);

        ArrayAdapter<String> adaptador1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcionesTime);
        spinnerTime.setAdapter(adaptador1);
        ArrayAdapter<String> adaptador2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcionesType);
        spinnerType.setAdapter(adaptador2);

        PrestamoController prestamoController = new PrestamoController(this);

        btnAsignar.setOnClickListener(v -> {
            if(txtCredito.getText().toString().isEmpty()){
                Toast.makeText(AsignarPrestamoActivity.this, "Debe ingresar un monto de crédito", Toast.LENGTH_SHORT).show();
                return;
            }
            if(txtCredito.getText().toString().equals("0")){
                Toast.makeText(AsignarPrestamoActivity.this, "Debe ingresar un monto de crédito mayor a 0", Toast.LENGTH_SHORT).show();
                return;
            }
            double montoCredito = Double.parseDouble(txtCredito.getText().toString());

            double montoMaximoCredito = clienteSession.getSalario() * 0.45;

            if (montoCredito > montoMaximoCredito) {
                Toast.makeText(AsignarPrestamoActivity.this, "El monto del crédito no puede ser mayor al 45% del salario del cliente", Toast.LENGTH_SHORT).show();
                return;
            }

            String tipoCredito = spinnerType.getSelectedItem().toString();

            String periodoCredito = spinnerTime.getSelectedItem().toString();

            double interes;
            switch (tipoCredito) {
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
                Toast.makeText(AsignarPrestamoActivity.this, "Debe seleccionar un tipo de crédito", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(AsignarPrestamoActivity.this, "Debe seleccionar un periodo de crédito", Toast.LENGTH_SHORT).show();
                return;
            }
            Prestamo prestamo = new Prestamo();
            prestamo.setIdCliente(clienteSession.getId());
            prestamo.setMonto(montoCredito);
            prestamo.setInteres(interes);
            prestamo.setPlazo(plazo);
            prestamo.setCuotaMensual(montoPagarPorMes);
            prestamo.setTipo(tipoCredito);
            prestamoController.addPrestamo(prestamo);

            txtMontoMensual.setText(String.format("%s", montoPagarPorMes));
            Toast.makeText(AsignarPrestamoActivity.this, "Prestamo agregado correctamente!", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = new Intent(AsignarPrestamoActivity.this, prestamoClienteActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}