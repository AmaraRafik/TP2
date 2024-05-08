package com.example.tp2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import androidx.appcompat.app.AppCompatActivity;

public class ActivitySix extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private ImageView imageView;
    private TextView proximityText;
    private static final float PROXIMITY_THRESHOLD_1 = 1.f;
    private static final float PROXIMITY_THRESHOLD_2 = 5.0f;
    private static final float PROXIMITY_THRESHOLD_3 = 9.f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six);

        imageView = findViewById(R.id.imageProximity);
        proximityText = findViewById(R.id.proximityText);
        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(v -> finish());

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (event.values[0] < PROXIMITY_THRESHOLD_1) {
                imageView.setImageResource(R.drawable.gonna_hit);
                proximityText.setText("Très proche!");
            } else if (event.values[0] < PROXIMITY_THRESHOLD_2) {
                imageView.setImageResource(R.drawable.close);
                proximityText.setText("Proche");
            } else if (event.values[0] < PROXIMITY_THRESHOLD_3) {
                imageView.setImageResource(R.drawable.in_range);
                proximityText.setText("À distance modérée");
            } else {
                imageView.setImageResource(R.drawable.far);
                proximityText.setText("Loin");
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
