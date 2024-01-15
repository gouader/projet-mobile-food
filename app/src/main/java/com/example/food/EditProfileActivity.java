package com.example.food;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {
    private EditText editTextNewUsername, editTextNewEmail, editTextNewPhoneNumber;
    private Button buttonSaveChanges;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        editTextNewUsername = findViewById(R.id.editTextNewUsername);
        editTextNewEmail = findViewById(R.id.editTextNewEmail);
        editTextNewPhoneNumber = findViewById(R.id.editTextNewPhoneNumber);

        buttonSaveChanges = findViewById(R.id.buttonSaveChanges);
        databaseHelper = new DatabaseHelper(this);
        String currentUsername = getIntent().getStringExtra("USERNAME");
        String currentEmail = getIntent().getStringExtra("EMAIL");
        int currentPhoneNumber = getIntent().getIntExtra("PHONE", 0);


        editTextNewUsername.setText(currentUsername);
        editTextNewEmail.setText(currentEmail);
        editTextNewPhoneNumber.setText(String.valueOf(currentPhoneNumber));


        buttonSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newUsername = editTextNewUsername.getText().toString().trim();
                String newEmail = editTextNewEmail.getText().toString().trim();
                int newPhoneNumber = Integer.parseInt(editTextNewPhoneNumber.getText().toString().trim());
                String email = getIntent().getStringExtra("EMAIL");


                Log.d("EditProfileActivity", "Email récupéré : " + email);


                boolean isUpdated = databaseHelper.updateUserData(email, newUsername, newEmail, newPhoneNumber);

                if (isUpdated) {
                    // Les données ont été mises à jour avec succès
                    Toast.makeText(EditProfileActivity.this, "Modifications enregistrées avec succès", Toast.LENGTH_SHORT).show();
                } else {
                    // Échec de la mise à jour des données
                    Toast.makeText(EditProfileActivity.this, "Échec de l'enregistrement des modifications", Toast.LENGTH_SHORT).show();
                }


                finish();
            }
        });


    }
}