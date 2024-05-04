package com.example.tp2;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityFour extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(v -> finish());
    }
}
