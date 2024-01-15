package com.example.food;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class Inscrire extends AppCompatActivity {
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscrire);

        dbHelper = new DatabaseHelper(this);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText emailEditText = findViewById(R.id.email);
        final EditText passwordEditText = findViewById(R.id.password);
        final EditText phoneNumberEditText = findViewById(R.id.phone); // Ajout du champ de numéro de téléphone
        Button signUpButton = findViewById(R.id.inscription);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
               // int phoneNumber = Integer.parseInt(phoneNumberEditText.getText().toString()); // Récupération du numéro de téléphone
                EditText phoneNumberEditText = findViewById(R.id.phone);
                int phoneNumber = 0; // La valeur par défaut si la conversion échoue
                String phoneNumberString = phoneNumberEditText.getText().toString();
                if (!TextUtils.isEmpty(phoneNumberString)) {
                    phoneNumber = Integer.parseInt(phoneNumberString);
                }
                boolean isInserted = dbHelper.insertData(username, email, password, phoneNumber);

                if (isInserted) {
                    Toast.makeText(Inscrire.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                    Log.d("InscrireActivity", "User registered successfully");
                } else {
                    Toast.makeText(Inscrire.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    Log.d("InscrireActivity", "Registration failed");
                }
            }
        });

        Button signInButton = findViewById(R.id.connexion);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Créer une intention pour démarrer la nouvelle activité
                Intent intent = new Intent(Inscrire.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}