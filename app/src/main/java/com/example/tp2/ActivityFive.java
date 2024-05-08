package com.example.tp2;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Switch;
import android.widget.TextView;

public class ActivityFive extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private boolean isFlashlightOn = false;
    private CameraManager cameraManager;
    private String cameraId;

    private Switch flashlightSwitch;

    private TextView switchStatusText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);

        RelativeLayout mainLayout = findViewById(R.id.main_layout);


        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(v -> finish());

        flashlightSwitch = findViewById(R.id.flashlightSwitch);
        switchStatusText = findViewById(R.id.switchStatusText);

        flashlightSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                switchStatusText.setText("Light ON");
                if (!isFlashlightOn) {  // Check if the flashlight is already on
                    toggleFlashlight();
                }
            } else {
                switchStatusText.setText("Light OFF");
                if (isFlashlightOn) {  // Check if the flashlight is already off
                    toggleFlashlight();
                }
            }
        });
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
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
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        float magnitude = (float) Math.sqrt(x * x + y * y + z * z);

        //Log.d(TAG, "mag = " + magnitude);


        if (magnitude > 13) {
            toggleFlashlight();

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // No action needed
    }

    private void toggleFlashlight() {
        try {
            isFlashlightOn = !isFlashlightOn;
            cameraManager.setTorchMode(cameraId, isFlashlightOn);
            flashlightSwitch.setChecked(isFlashlightOn);  // Update the switch state
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}