package com.example.tp2;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.widget.TextView;


public class ActivityOne extends AppCompatActivity {

    private SensorManager sensorManager;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        List<String> sensorNames = new ArrayList<>();

        for (Sensor sensor : sensorList) {
            sensorNames.add(sensor.getName());  // Ajouter uniquement le nom du capteur à la liste
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, sensorNames);
        listView.setAdapter(adapter);

        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(v -> finish());
    }

}
