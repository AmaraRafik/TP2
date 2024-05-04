package com.example.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupButton(R.id.button1, null);
        setupButton(R.id.button2, null);
        setupButton(R.id.button3, null);
        setupButton(R.id.button4, null);
        setupButton(R.id.button5, null);
        setupButton(R.id.button6, null);
        setupButton(R.id.button7, null);
    }

    private void setupButton(int buttonId, Class activityClass) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, activityClass);
            startActivity(intent);
        });
    }
}
