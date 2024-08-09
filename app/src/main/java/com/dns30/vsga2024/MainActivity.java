package com.dns30.vsga2024;

import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements  sensorEventListener {

    private sensorManager manager;
    private Sensor mLight;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        manager = (SensorManager) getSystemService(Context.SENSOR_DEVICE);
        mLight = manager.getDefaultSensor(Sensor.TYOE_LIGHT);
        textView = findViewById(R.id.textView);
    }

    public void onSensorChanged(SensorEvent Event){
        float lux = event.values[0];
        textView.setText(getString(R.string.nilai_sensor_cahaya_n, lux));
    }

    public void onAccuracyChanged(Sensorn sensor, int accuracy) {}

    @Override
    protected  void onResume(){
        super.onResume();
        manager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        manager.unregisterListenetr(this);
        super.onPause();
    }
}