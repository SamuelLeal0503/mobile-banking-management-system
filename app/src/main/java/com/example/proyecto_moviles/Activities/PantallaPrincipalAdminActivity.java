package com.example.proyecto_moviles.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_moviles.R;

public class PantallaPrincipalAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        Button addClientButton = findViewById(R.id.add_client_button);
        Button assignLoanButton = findViewById(R.id.assign_loan_button);
        Button logoutButton = findViewById(R.id.logout_button);


        addClientButton.setOnClickListener(v -> startActivity(new Intent(PantallaPrincipalAdminActivity.this, AgregarClienteActivity.class)));

        assignLoanButton.setOnClickListener(v -> {
            Intent intent = new Intent(PantallaPrincipalAdminActivity.this, prestamoClienteActivity.class);
            startActivity(intent);
            finish();
        });

        logoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(PantallaPrincipalAdminActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
