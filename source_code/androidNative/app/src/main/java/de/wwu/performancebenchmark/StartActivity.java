package de.wwu.performancebenchmark;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void startAccelerometer(View view) {
        Intent intent = new Intent(this, AccelerometerActivity.class);
        startActivity(intent);
    }

    public void startContact(View view) {
        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }

    public void startDatabase(View view) {
        Intent intent = new Intent(this, DatabaseActivity.class);
        startActivity(intent);
    }

    public void startFileSystem(View view) {
        Intent intent = new Intent(this, FileSystemActivity.class);
        startActivity(intent);
    }

    public void startGeolocation(View view) {
        Intent intent = new Intent(this, GeolocationActivity.class);
        startActivity(intent);
    }
}
