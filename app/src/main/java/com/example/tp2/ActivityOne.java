package com.example.tp2;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import java.util.List;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ActivityOne extends AppCompatActivity {

    private SensorManager sensorManager;
    private ListView listView;
    private Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        listView = findViewById(R.id.listView);
        returnButton = findViewById(R.id.returnButton);

        returnButton.setOnClickListener(v -> finish());

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        SensorAdapter adapter = new SensorAdapter(sensors);
        listView.setAdapter(adapter);
    }

    private class SensorAdapter extends ArrayAdapter<Sensor> {
        SensorAdapter(List<Sensor> sensors) {
            super(ActivityOne.this, R.layout.sensor_item, sensors);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.sensor_item, parent, false);
            }
            TextView textViewName = convertView.findViewById(R.id.textViewSensorName);
            TextView textViewVersion = convertView.findViewById(R.id.textViewSensorVersion);

            Sensor sensor = getItem(position);
            textViewName.setText(sensor.getName());
            textViewVersion.setText("Version: " + sensor.getVersion());

            return convertView;
        }
    }
}
