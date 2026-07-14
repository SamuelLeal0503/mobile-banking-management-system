package com.example.proyecto_moviles.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.example.proyecto_moviles.Controllers.ClienteController;
import com.example.proyecto_moviles.Modelos.Cliente;
import com.example.proyecto_moviles.R;

import java.util.Objects;

public class AgregarClienteActivity extends AppCompatActivity {

    private EditText cedulaEditText;
    private EditText nombreEditText;
    private EditText contrasenaEditText;
    private EditText salarioEditText;
    private EditText telefonoEditText;
    private EditText fechaNacimientoEditText;
    private Spinner estadoCivilSpinner;
    private EditText direccionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cliente);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        cedulaEditText = findViewById(R.id.et_cedula);
        nombreEditText = findViewById(R.id.et_nombre);
        contrasenaEditText = findViewById(R.id.et_contrasena);
        salarioEditText = findViewById(R.id.et_salario);
        telefonoEditText = findViewById(R.id.et_telefono);
        fechaNacimientoEditText = findViewById(R.id.et_fecha_nacimiento);
        estadoCivilSpinner = findViewById(R.id.sp_estado_civil);
        direccionEditText = findViewById(R.id.et_direccion);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.estado_civil_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estadoCivilSpinner.setAdapter(adapter);

        Button guardarButton = findViewById(R.id.btn_add_client);

        guardarButton.setOnClickListener(view -> {
            if (guardarCliente()) {
                Toast.makeText(AgregarClienteActivity.this, "Cliente agregado exitosamente", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(AgregarClienteActivity.this, "Error al agregar cliente", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validarCampos() {
        boolean camposValidos = true;

        String cedula = cedulaEditText.getText().toString().trim();
        String nombre = nombreEditText.getText().toString().trim();
        String contrasena = contrasenaEditText.getText().toString().trim();
        String salario = salarioEditText.getText().toString().trim();
        String telefono = telefonoEditText.getText().toString().trim();
        String fechaNacimiento = fechaNacimientoEditText.getText().toString().trim();
        String direccion = direccionEditText.getText().toString().trim();

        if (TextUtils.isEmpty(cedula)) {
            cedulaEditText.setError("Este campo es obligatorio");
            camposValidos = false;
        }

        if (TextUtils.isEmpty(nombre)) {
            nombreEditText.setError("Este campo es obligatorio");
            camposValidos = false;
        }

        if (TextUtils.isEmpty(contrasena)) {
            contrasenaEditText.setError("Este campo es obligatorio");
            camposValidos = false;
        }

        if (TextUtils.isEmpty(salario)) {
            salarioEditText.setError("Este campo es obligatorio");
            camposValidos = false;
        }

        if (TextUtils.isEmpty(telefono)) {
            telefonoEditText.setError("Este campo es obligatorio");
            camposValidos = false;
        }

        if (TextUtils.isEmpty(fechaNacimiento)) {
            fechaNacimientoEditText.setError("Este campo es obligatorio");
            camposValidos = false;
        }

        if (TextUtils.isEmpty(direccion)) {
            direccionEditText.setError("Este campo es obligatorio");
            camposValidos = false;
        }

        return camposValidos;
    }

    public boolean guardarCliente() {
        if (validarCampos()) {
            ClienteController clienteController = new ClienteController(this);

            Cliente cliente = new Cliente();
            cliente.setCedula(cedulaEditText.getText().toString().trim());
            cliente.setNombre(nombreEditText.getText().toString().trim());
            cliente.setContrasena(contrasenaEditText.getText().toString().trim());
            cliente.setSalario(Double.parseDouble(salarioEditText.getText().toString().trim()));
            cliente.setTelefono(telefonoEditText.getText().toString().trim());
            cliente.setFechaNacimiento(fechaNacimientoEditText.getText().toString().trim());
            cliente.setEstadoCivil(estadoCivilSpinner.getSelectedItem().toString());
            cliente.setDireccion(direccionEditText.getText().toString().trim());

            return clienteController.addCliente(cliente);
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = NavUtils.getParentActivityIntent(this);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
