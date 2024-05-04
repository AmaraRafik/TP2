package com.example.tp2;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(v -> finish());
    }
}
