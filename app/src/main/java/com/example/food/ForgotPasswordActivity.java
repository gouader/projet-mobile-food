package com.example.food;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText emailEditText;
    private Button resetPassButton;
    private Button retourButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpassword);

        emailEditText = findViewById(R.id.emailForgotPass);
        resetPassButton = findViewById(R.id.resetPass);
        databaseHelper = new DatabaseHelper(this);

        resetPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logique du clic sur le bouton
                String email = emailEditText.getText().toString().trim();


                    if (emailExistsInDatabase(email)) {

                        Toast.makeText(ForgotPasswordActivity.this, "Email trouvé, procédez à la réinitialisation du mot de passe.", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(ForgotPasswordActivity.this, "Email non trouvé.", Toast.LENGTH_SHORT).show();
                    }

            }
        });
        retourButton = findViewById(R.id.retour);
        retourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPasswordActivity.this, MainActivity.class);
                startActivity(intent);
                finish();


            }
        });

    }

    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private boolean emailExistsInDatabase(String email) {
        return databaseHelper.emailExists(email);
    }

}
