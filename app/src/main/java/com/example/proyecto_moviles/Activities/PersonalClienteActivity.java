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

import com.example.proyecto_moviles.Controllers.ClienteController;
import com.example.proyecto_moviles.Modelos.Cliente;
import com.example.proyecto_moviles.R;

public class PersonalClienteActivity extends AppCompatActivity {

    EditText nombreEditText, salarioEditText, telefonoEditText, fechaEditText, direccionEditText;
    Spinner estadoCivilSpinner;
    Button guardarCambiosButton;

    Cliente cliente;
    ClienteController clienteController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_personal);

        cliente = (Cliente) getIntent().getSerializableExtra("ClienteSession");
        clienteController = new ClienteController(this);

        nombreEditText = findViewById(R.id.usernameIP);
        salarioEditText = findViewById(R.id.salarioIP);
        telefonoEditText = findViewById(R.id.telefonoIP);
        fechaEditText = findViewById(R.id.nacimientoIP);
        direccionEditText = findViewById(R.id.direccionIP);
        estadoCivilSpinner = findViewById(R.id.spinnerIP);
        guardarCambiosButton = findViewById(R.id.btnIP);

        nombreEditText.setText(cliente.getNombre());
        salarioEditText.setText(String.valueOf(cliente.getSalario()));
        telefonoEditText.setText(cliente.getTelefono());
        fechaEditText.setText(cliente.getFechaNacimiento());
        direccionEditText.setText(cliente.getDireccion());

        String estadoCivilCliente = cliente.getEstadoCivil();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.estado_civil_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        estadoCivilSpinner.setAdapter(adapter);
        int posicion = adapter.getPosition(estadoCivilCliente);
        estadoCivilSpinner.setSelection(posicion);

        guardarCambiosButton.setOnClickListener(view -> {

            if (actualizarCliente()) {
                Toast.makeText(getApplicationContext(), "El cliente se actualizó con éxito.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Ha ocurrido un error al actualizar el cliente.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean validarCampos() {
        boolean camposValidos = true;

        String nombre = nombreEditText.getText().toString().trim();
        String salario = salarioEditText.getText().toString().trim();
        String telefono = telefonoEditText.getText().toString().trim();
        String fechaNacimiento = fechaEditText.getText().toString().trim();
        String direccion = direccionEditText.getText().toString().trim();

        if (TextUtils.isEmpty(nombre)) {
            nombreEditText.setError("Este campo es obligatorio");
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
            fechaEditText.setError("Este campo es obligatorio");
            camposValidos = false;
        }

        if (TextUtils.isEmpty(direccion)) {
            direccionEditText.setError("Este campo es obligatorio");
            camposValidos = false;
        }

        return camposValidos;
    }
    public boolean actualizarCliente() {
        if (validarCampos()) {
            cliente.setNombre(nombreEditText.getText().toString().trim());
            cliente.setSalario(Double.parseDouble(salarioEditText.getText().toString().trim()));
            cliente.setTelefono(telefonoEditText.getText().toString().trim());
            cliente.setFechaNacimiento(fechaEditText.getText().toString().trim());
            cliente.setEstadoCivil(estadoCivilSpinner.getSelectedItem().toString());
            cliente.setDireccion(direccionEditText.getText().toString().trim());
            cliente.setCedula(cliente.getCedula());

            return clienteController.updateCliente(cliente);
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Cliente cliente  = (Cliente) getIntent().getSerializableExtra("ClienteSession");
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = new Intent(PersonalClienteActivity.this, MainScreenClienteActivity.class);
            intent.putExtra("ClienteSession", cliente);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}


