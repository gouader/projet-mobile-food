package com.example.food;

import static com.example.food.R.id.resetpassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dbHelper = new DatabaseHelper(this);

        final EditText emailEditText = findViewById(R.id.emailConnexion);
        final EditText passwordEditText = findViewById(R.id.passwordConnexion);
        Button signInButton = findViewById(R.id.connexion);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                Cursor userData = dbHelper.getUserData(email, password);

                String username = null;
                String userEmail = null;
                int phoneNumber = 0;

                if (userData.moveToFirst()) {
                    int usernameIndex = userData.getColumnIndexOrThrow("USERNAME");
                    int emailIndex = userData.getColumnIndexOrThrow("EMAIL");
                    int phoneNumberIndex = userData.getColumnIndexOrThrow("PHONE");

                    username = userData.getString(usernameIndex);
                    userEmail = userData.getString(emailIndex);
                    phoneNumber = userData.getInt(phoneNumberIndex);
                }

                if (username != null) {
                    // Les informations de l'utilisateur ont été récupérées avec succès
                    // Passer les informations à l'activité ProfileActivity
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    intent.putExtra("USERNAME", username);
                    intent.putExtra("EMAIL", userEmail);
                    intent.putExtra("PHONE", phoneNumber);
                    startActivity(intent);
                } else {
                    // Gérer le cas où la colonne n'existe pas ou si le nom d'utilisateur n'est pas trouvé
                    Toast.makeText(MainActivity.this, "Unable to retrieve user information", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Button signUpButton = findViewById(R.id.inscription);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Créer une intention pour démarrer la nouvelle activité
                Intent intent = new Intent(MainActivity.this, Inscrire.class);
                startActivity(intent);
            }
        });

        Button forgotpasswordButton = findViewById(resetpassword);
        forgotpasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

    }



}