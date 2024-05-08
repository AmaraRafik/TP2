package com.example.tp2;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

public class ActivityThree extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = ActivityThree.class.getSimpleName();
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private RelativeLayout mainLayout;
    private float previousX, previousY, previousZ;
    private boolean isFirstReading = true;

    private static final float THRESHOLD_LOW = 2.0f;
    private static final float THRESHOLD_MEDIUM = 7.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        mainLayout = findViewById(R.id.main_layout);

        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(v -> finish());

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (accelerometer != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];



        float distance = 0;
        if (!isFirstReading) {
            distance = (float) Math.sqrt(Math.pow(x - previousX, 2) + Math.pow(y - previousY, 2) + Math.pow(z - previousZ, 2));
        } else {
            isFirstReading = false;
        }

        previousX = x;
        previousY = y;
        previousZ = z;


        if (distance < THRESHOLD_LOW) {
            mainLayout.setBackgroundColor(Color.GREEN);
        } else if ((distance < THRESHOLD_MEDIUM) && (distance > THRESHOLD_LOW)) {
            mainLayout.setBackgroundColor(Color.BLACK);
        } else {
            mainLayout.setBackgroundColor(Color.RED);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}