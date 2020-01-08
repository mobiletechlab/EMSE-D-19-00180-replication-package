package de.wwu.performancebenchmark;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import de.wwu.performancebenchmark.model.BenchmarkType;

public class GeolocationActivity extends AppCompatActivity implements BenchmarkFragment.OnBenchmarkInteractionListener {
    public static final int BENCHMARK_PERMISSION_ACCESS_FINE_LOCATION = 1;

    private static final long MIN_TIME_BETWEEN_UPDATES = 0; // 0 sec

    private BenchmarkFragment benchmarkFragment = null;
    private FusedLocationProviderClient mFusedLocationClient;
    private Location mCurrentLocation;
    private LocationCallback mLocationCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geolocation);

        // Initialize benchmark fragment
        Bundle bundle = new Bundle();
        bundle.putSerializable(BenchmarkFragment.TYPE, BenchmarkType.GEOLOCATION);

        benchmarkFragment = (BenchmarkFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment);
        benchmarkFragment.setArguments(bundle);

        // Ask for permissions upfront
        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_FINE_LOCATION  },
                    BENCHMARK_PERMISSION_ACCESS_FINE_LOCATION );
        }
    }

    @Override
    public void onBenchmarkStart() {
        // Check permissions
        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission( getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            benchmarkFragment.onBenchmarkFailed("Permissions not given for accessing location!");
            return;
        }

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(MIN_TIME_BETWEEN_UPDATES);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setNumUpdates(1);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult result) {
                super.onLocationResult(result);
                mCurrentLocation = result.getLocations().get(0);

                // Stop listening for updates
                mFusedLocationClient.removeLocationUpdates(mLocationCallback);

                // Process location
                if(mCurrentLocation != null) {
                    benchmarkFragment.onBenchmarkCompleted(mCurrentLocation.getLongitude() + " " + mCurrentLocation.getLatitude());
                } else {
                    benchmarkFragment.onBenchmarkFailed("Location could not be obtained!");
                }
            }
        };

        // Start listening for updates
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }

    @Override
    public void onBenchmarkReset() {
        // Nothing to do
    }

}
