package de.wwu.performancebenchmark;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import de.wwu.performancebenchmark.model.BenchmarkType;

public class AccelerometerActivity extends AppCompatActivity implements BenchmarkFragment.OnBenchmarkInteractionListener, SensorEventListener {

    BenchmarkFragment benchmarkFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        // Initialize benchmark fragment
        Bundle bundle = new Bundle();
        bundle.putSerializable(BenchmarkFragment.TYPE, BenchmarkType.ACCELEROMETER);

        benchmarkFragment = (BenchmarkFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment);
        benchmarkFragment.setArguments(bundle);
    }

    @Override
    public void onBenchmarkStart() {
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Check sensor exists
        if(accelerometer == null){
            benchmarkFragment.onBenchmarkFailed("Accelerometer not available!");
        }

        // Attach listener
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onBenchmarkReset() {
        // Unregister listener
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // Accelerometer values are only available through sensor events
        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            ((SensorManager) getSystemService(Context.SENSOR_SERVICE)).unregisterListener(this);

            benchmarkFragment.onBenchmarkCompleted(x + ", " + y + ", " + z);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}
}
