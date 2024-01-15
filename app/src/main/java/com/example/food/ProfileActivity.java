package com.example.food;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
public class ProfileActivity extends AppCompatActivity {
    private Button signOutButton;
    private Button editProfileImageView;
    private String username;
    private String email;
    private String password;
    private int phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Initialisez le bouton à l'intérieur de la méthode onCreate
        signOutButton = findViewById(R.id.logout);
        // Initialisez l'icône de modification du profil
        editProfileImageView = findViewById(R.id.imageViewEdit);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("USERNAME") && intent.hasExtra("EMAIL") && intent.hasExtra("PHONE")) {
            username = intent.getStringExtra("USERNAME");
            email = intent.getStringExtra("EMAIL");
            phoneNumber = intent.getIntExtra("PHONE", 0);


            TextView usernameTextView = findViewById(R.id.textViewUsername);
            TextView emailTextView = findViewById(R.id.textViewEmail);
            TextView phoneNumberTextView = findViewById(R.id.textViewPhone);


            usernameTextView.setText(username);
            emailTextView.setText(email);
            phoneNumberTextView.setText(String.valueOf(phoneNumber));
        }

        // Mettez en place un gestionnaire de clics pour le bouton de déconnexion
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Créer une intention pour démarrer la nouvelle activité (par exemple, MainActivity)
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
                // Terminez l'activité actuelle (ProfileActivity)
                finish();
            }
        });


        editProfileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);

                intent.putExtra("USERNAME", username);
                intent.putExtra("EMAIL", email);
                intent.putExtra("PHONE", phoneNumber);

                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Gérer les actions du menu ici
        if (id == R.id.Profile) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.Food) {
            Intent intent = new Intent(this, FoodActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.Reclamation) {
            // Faire quelque chose lorsque "About" est sélectionné
            return true;
        }
        else if (id == R.id.LogOut) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}