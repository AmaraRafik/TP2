package com.example.tp2;

import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        SensorAdapter adapter = new SensorAdapter(sensors);
        listView.setAdapter(adapter);
    }

    private class SensorAdapter extends ArrayAdapter<Sensor> {
        SensorAdapter(List<Sensor> sensors) {
            super(MainActivity.this, R.layout.sensor_item, sensors);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.sensor_item, parent, false);
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