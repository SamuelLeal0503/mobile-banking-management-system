package com.example.proyecto_moviles.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.proyecto_moviles.Controllers.AhorroController;
import com.example.proyecto_moviles.Modelos.Ahorro;
import com.example.proyecto_moviles.Modelos.Cliente;
import com.example.proyecto_moviles.R;

import java.text.DecimalFormat;
import java.util.List;

public class GestionarAhorrosActivity extends AppCompatActivity {

    private TextView navideno;
    private TextView escolar;
    private TextView marchamo;
    private TextView extraordinario;
    private Button btnNavideno, btnEscolar, btnMarchamo, btnExtraordinario;
    private static final int VALOR_MINIMO = 5000;
    Cliente cliente;
    AhorroController ahorroController;
    Ahorro ahorro, ahorroNavideno, ahorroEscolar, ahorroMarchamo, ahorroExtraordinario;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_ahorros);

        cliente = (Cliente) getIntent().getSerializableExtra("ClienteSession");
        ahorroController = new AhorroController(this);

        navideno = findViewById(R.id.tipo_navideno);
        escolar = findViewById(R.id.tipo_escolar);
        marchamo = findViewById(R.id.tipo_marchamo);
        extraordinario = findViewById(R.id.tipo_extraordinario);

        TextView cuotaNavideno = findViewById(R.id.cuota_navideno);
        TextView cuotaEscolar = findViewById(R.id.cuota_escolar);
        TextView cuotaMarchamo = findViewById(R.id.cuota_marchamo);
        TextView cuotaExtraordinario = findViewById(R.id.cuota_extraordinario);

        btnNavideno = findViewById(R.id.btn_navideno);
        btnEscolar = findViewById(R.id.btn_escolar);
        btnMarchamo = findViewById(R.id.btn_marchamo);
        btnExtraordinario = findViewById(R.id.btn_extraordinario);

        ahorroNavideno = getAhorroByTipo(navideno.getText().toString());
        if (ahorroNavideno != null) {
            btnNavideno.setText("Editar");
            btnNavideno.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.backgroundDelLogo)));
            DecimalFormat formato = new DecimalFormat("#,##0.00");
            String salarioFormateado = formato.format(ahorroNavideno.getMonto());
            cuotaNavideno.setText(salarioFormateado);
        }
        ahorroEscolar = getAhorroByTipo(escolar.getText().toString());
        if (ahorroEscolar != null) {
            btnEscolar.setText("Editar");
            btnEscolar.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.backgroundDelLogo)));
            DecimalFormat formato = new DecimalFormat("#,##0.00");
            String salarioFormateado = formato.format(ahorroEscolar.getMonto());
            cuotaEscolar.setText(salarioFormateado);
        }
        ahorroMarchamo = getAhorroByTipo(marchamo.getText().toString());
        if (ahorroMarchamo != null) {
            btnMarchamo.setText("Editar");
            btnMarchamo.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.backgroundDelLogo)));
            DecimalFormat formato = new DecimalFormat("#,##0.00");
            String salarioFormateado = formato.format(ahorroMarchamo.getMonto());
            cuotaMarchamo.setText(salarioFormateado);
        }
        ahorroExtraordinario = getAhorroByTipo(extraordinario.getText().toString());
        if (ahorroExtraordinario != null) {
            btnExtraordinario.setText("Editar");
            btnExtraordinario.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.backgroundDelLogo)));
            DecimalFormat formato = new DecimalFormat("#,##0.00");
            String salarioFormateado = formato.format(ahorroExtraordinario.getMonto());
            cuotaExtraordinario.setText(salarioFormateado);
        }


        btnNavideno.setOnClickListener(v -> mostrarCuadroDialogo(valorIngresado -> {
            if (ahorroNavideno == null) {
                guardarAhorros(Double.valueOf(valorIngresado), navideno.getText().toString());
                btnNavideno.setText("Editar");
                btnNavideno.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.backgroundDelLogo)));
                recreate();
            }else{
                actualizarAhorro(Double.valueOf(valorIngresado), navideno.getText().toString());
                recreate();
            }
        }));

        btnEscolar.setOnClickListener(v -> mostrarCuadroDialogo(valorIngresado -> {
            if (ahorroEscolar == null) {
                guardarAhorros(Double.valueOf(valorIngresado), escolar.getText().toString());
                btnEscolar.setText("Editar");
                btnEscolar.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.backgroundDelLogo)));
                recreate();
            }else{
                actualizarAhorro(Double.valueOf(valorIngresado), escolar.getText().toString());
                recreate();
            }
        }));

        btnMarchamo.setOnClickListener(v -> mostrarCuadroDialogo(valorIngresado -> {
            if (ahorroMarchamo == null) {
                guardarAhorros(Double.valueOf(valorIngresado), marchamo.getText().toString());
                btnMarchamo.setText("Editar");
                btnMarchamo.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.backgroundDelLogo)));
                recreate();
            }else{
                actualizarAhorro(Double.valueOf(valorIngresado), marchamo.getText().toString());
                recreate();
            }

        }));

        btnExtraordinario.setOnClickListener(v -> mostrarCuadroDialogo(valorIngresado -> {
            if (ahorroExtraordinario == null) {
                guardarAhorros(Double.valueOf(valorIngresado), extraordinario.getText().toString());
                btnExtraordinario.setText("Editar");
                btnExtraordinario.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.backgroundDelLogo)));
                recreate();
            }else{
                actualizarAhorro(Double.valueOf(valorIngresado), extraordinario.getText().toString());
                recreate();
            }
        }));



    }


    public void guardarAhorros(Double cuota, String tipo) {
        try {
            ahorro = new Ahorro();
            ahorro.setIdCliente(cliente.getId());
            ahorro.setMonto(cuota);
            ahorro.setTipo(tipo);
            ahorro.setActivo(true);
            ahorroController.addAhorro(ahorro);
            Toast.makeText(this, "Se guardo con exito", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show();
        }
    }

    public void actualizarAhorro(Double nuevaCuota, String tipo) {
        try {
            List<Ahorro> list = ahorroController.getAhorrosByCliente(cliente.getId());
            for (Ahorro ahorro : list) {
                if (ahorro.getTipo().equals(tipo)) {
                    ahorro.setMonto(nuevaCuota);
                    ahorroController.updateMonto(ahorro);
                    Toast.makeText(this, "Se actualizo con exito", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error al actualizar", Toast.LENGTH_SHORT).show();
        }
    }
    public Ahorro getAhorroByTipo(String tipo) {
        try {
            List<Ahorro> list = ahorroController.getAhorrosByCliente(cliente.getId());
            for (Ahorro ahorro : list) {
                if (ahorro.getTipo().equals(tipo)) {
                    return ahorro;
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error al buscar el ahorro", Toast.LENGTH_SHORT).show();
        }
        return null;
    }



    private void mostrarCuadroDialogo(final OnInputListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ingrese el valor");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setRawInputType(Configuration.KEYBOARD_12KEY);
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> {
            String valorIngresado = input.getText().toString().trim();

            if (valorIngresado.isEmpty()) {
                Toast.makeText(GestionarAhorrosActivity.this, "Debe ingresar un valor", Toast.LENGTH_SHORT).show();
                return;
            }

            double valor = Double.parseDouble(valorIngresado);
            if (valor < VALOR_MINIMO) {
                Toast.makeText(GestionarAhorrosActivity.this, "El valor ingresado debe ser mayor o igual a " + VALOR_MINIMO, Toast.LENGTH_SHORT).show();
                return;
            }

            listener.onInput(valorIngresado);
        });
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        builder.show();
    }


    public interface OnInputListener {
        void onInput(String valorIngresado);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Cliente cliente  = (Cliente) getIntent().getSerializableExtra("ClienteSession");
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = new Intent(GestionarAhorrosActivity.this, MainScreenClienteActivity.class);
            intent.putExtra("ClienteSession", cliente);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
