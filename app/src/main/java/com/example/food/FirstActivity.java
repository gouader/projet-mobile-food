package com.example.food;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {
    private Button getSartedButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        getSartedButton = findViewById(R.id.getStarted);
        getSartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Créer une intention pour démarrer la nouvelle activité (par exemple, MainActivity)
                Intent intent = new Intent(FirstActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        });
    }
}
