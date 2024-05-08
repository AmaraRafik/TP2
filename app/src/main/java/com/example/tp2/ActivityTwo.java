package com.example.tp2;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import java.util.ArrayList;
import java.util.List;

public class ActivityTwo extends AppCompatActivity {

    private ListView lvUnavailableSensors;
    private SensorManager sensorManager;
    private ArrayAdapter<String> adapter;
    private List<String> unavailableSensors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        lvUnavailableSensors = findViewById(R.id.lvUnavailableSensors);
        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(v -> finish());

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        unavailableSensors = new ArrayList<>();
        checkUnavailableSensors(deviceSensors);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, unavailableSensors);
        lvUnavailableSensors.setAdapter(adapter);
    }

    private void checkUnavailableSensors(List<Sensor> sensors) {
        int[] sensorTypes = {
                Sensor.TYPE_ACCELEROMETER,
                Sensor.TYPE_AMBIENT_TEMPERATURE,
                Sensor.TYPE_GRAVITY,
                Sensor.TYPE_GYROSCOPE,
                Sensor.TYPE_LIGHT,
                Sensor.TYPE_LINEAR_ACCELERATION,
                Sensor.TYPE_MAGNETIC_FIELD,
                Sensor.TYPE_ORIENTATION,
                Sensor.TYPE_PRESSURE,
                Sensor.TYPE_PROXIMITY,
                Sensor.TYPE_RELATIVE_HUMIDITY,
                Sensor.TYPE_ROTATION_VECTOR,
                Sensor.TYPE_SIGNIFICANT_MOTION,
                Sensor.TYPE_STEP_COUNTER,
                Sensor.TYPE_STEP_DETECTOR,
                Sensor.TYPE_TEMPERATURE,
        };

        for (int type : sensorTypes) {
            boolean isAvailable = false;
            for (Sensor sensor : sensors) {
                if (sensor.getType() == type) {
                    isAvailable = true;
                    break;
                }
            }
            if (!isAvailable) {
                String sensorName = getSensorName(type);
                unavailableSensors.add(sensorName);
                Log.d("SensorDebug", "Unavailable Sensor: " + sensorName);
            }
        }
    }

    private String getSensorName(int sensorType) {
        switch (sensorType) {
            case Sensor.TYPE_ACCELEROMETER:
                return "Accelerometer";
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                return "Ambient Temperature Sensor";
            case Sensor.TYPE_GRAVITY:
                return "Gravity Sensor";
            case Sensor.TYPE_GYROSCOPE:
                return "Gyroscope";
            case Sensor.TYPE_LIGHT:
                return "Light Sensor";
            case Sensor.TYPE_LINEAR_ACCELERATION:
                return "Linear Acceleration Sensor";
            case Sensor.TYPE_MAGNETIC_FIELD:
                return "Magnetic Field Sensor";
            case Sensor.TYPE_ORIENTATION:
                return "Orientation Sensor";
            case Sensor.TYPE_PRESSURE:
                return "Pressure Sensor";
            case Sensor.TYPE_PROXIMITY:
                return "Proximity Sensor";
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                return "Relative Humidity Sensor";
            case Sensor.TYPE_ROTATION_VECTOR:
                return "Rotation Vector Sensor";
            case Sensor.TYPE_SIGNIFICANT_MOTION:
                return "Significant Motion Sensor";
            case Sensor.TYPE_STEP_COUNTER:
                return "Step Counter Sensor";
            case Sensor.TYPE_STEP_DETECTOR:
                return "Step Detector Sensor";
            case Sensor.TYPE_TEMPERATURE:
                return "Temperature Sensor";
            default:
                return "Unknown Sensor";
        }
    }

}
