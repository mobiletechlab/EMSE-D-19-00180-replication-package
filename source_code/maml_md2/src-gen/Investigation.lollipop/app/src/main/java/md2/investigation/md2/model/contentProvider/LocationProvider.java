// generated in de.wwu.md2.framework.generator.android.lollipop.model.Md2ContentProvider.generateContentProvider()
package md2.investigation.md2.model.contentProvider;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import de.wwu.md2.android.md2library.controller.eventhandler.implementation.Md2OnAttributeChangedHandler;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Calendar;
import java.util.List;

import md2.investigation.EinkaufszettelApp;
import md2.investigation.R;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Boolean;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Date;
import de.wwu.md2.android.md2library.model.type.implementation.Md2DateTime;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Float;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Integer;
import de.wwu.md2.android.md2library.model.type.implementation.Md2String;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Time;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Sensor;
import de.wwu.md2.android.md2library.model.type.implementation.Md2List;
import de.wwu.md2.android.md2library.model.contentProvider.implementation.Md2ContentProviderRegistry;
import de.wwu.md2.android.md2library.view.management.implementation.Md2ViewManager;

import de.wwu.md2.android.md2library.model.type.interfaces.Md2Type;
import de.wwu.md2.android.md2library.model.contentProvider.implementation.AbstractMd2ContentProvider;
import de.wwu.md2.android.md2library.model.dataStore.interfaces.Md2LocalStore;
import de.wwu.md2.android.md2library.model.dataStore.interfaces.Md2DataStore;
import de.wwu.md2.android.md2library.model.type.interfaces.Md2Entity;
import de.wwu.md2.android.md2library.model.type.implementation.Md2List;
import de.wwu.md2.android.md2library.model.dataStore.AtomicExpression;
import de.wwu.md2.android.md2library.model.dataStore.Operator;
import de.wwu.md2.android.md2library.model.dataStore.Filter;

import md2.investigation.md2.controller.action.Geolocation_Geolocation_getGeolocationValue_Action;
import md2.investigation.md2.model.InvestigationGeolocation;

import de.wwu.md2.android.md2library.model.type.implementation.Md2Boolean;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Date;
import de.wwu.md2.android.md2library.model.type.implementation.Md2DateTime;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Float;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Integer;
import de.wwu.md2.android.md2library.model.type.implementation.Md2String;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Time;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Sensor;
import de.wwu.md2.android.md2library.model.type.implementation.Md2List;

public class LocationProvider extends AbstractMd2ContentProvider {

    // We need a dedicated handler for the onSensorChanged
    HandlerThread handler = null;
    LocationHandler gpsHandler = null;

    public LocationProvider(String key, Md2Entity content, Md2DataStore md2DataStore) {
        super(key, content, md2DataStore);

        handler = new HandlerThread(key + "Thread");

    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    protected long getInternalId() {
        return this.internalId;
    }

    @Override
    protected void setInternalId(long internalId) {
        this.internalId = internalId;
    }

    @Override

    public Md2Entity getContent() {
        return this.content;
    }

    @Override
    public void setContent(Md2Entity content) {
        if (content != null) {
            this.content = content;
            this.backup = (Md2Entity) content.clone();
            this.internalId = -1L;
            this.load();
        }
    }

    @Override
    public void registerAttributeOnChangeHandler(String attribute, Md2OnAttributeChangedHandler onAttributeChangedHandler) {
        this.attributeChangedEventHandlers.put(attribute, onAttributeChangedHandler);
    }

    @Override
    public void unregisterAttributeOnChangeHandler(String attribute) {
        this.attributeChangedEventHandlers.remove(attribute);
    }

    @Override
    public Md2OnAttributeChangedHandler getOnAttributeChangedHandler(String attribute) {
        return (Md2OnAttributeChangedHandler) this.attributeChangedEventHandlers.get(attribute);
    }

    private static final long MIN_TIME_BETWEEN_UPDATES = 0; // 0 sec

    private FusedLocationProviderClient mFusedLocationClient;
    private Location mCurrentLocation;
    private LocationCallback mLocationCallback;

    // For benchmark usage
    public static Geolocation_Geolocation_getGeolocationValue_Action callback = null;


    private class LocationHandler extends Handler implements LocationListener {
        LocationProvider provider = null;

        public LocationHandler(Looper looper){
            super(looper);
        }

        public void setProvider(LocationProvider provider){
            this.provider = provider;
        }

        @Override
        public void onLocationChanged(Location location) {
            provider.location = location;
            callback.callback(location); // Notify action about completion
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
        }

        @Override
        public void onProviderEnabled(String s) {
        }

        @Override
        public void onProviderDisabled(String s) {
        }
    }

    public void stopCapture(){
        if(mFusedLocationClient != null && mLocationCallback != null) {
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        }
    }

    @Override
    public Md2Type getValue(String attribute) {

//        while(!hasResult()) {
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                // e.printStackTrace();
//            }
//        }

        //locationManager.removeUpdates(gpsHandler);
        // Retrieve location
        //Location locationGPS = getLastKnownLocation(locationManager); // locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        switch (attribute) {
            case "locationLong": {
                if (location != null) {
                    return new Md2String(location.getLongitude() + "");
                } else {
                    return null;
                }
            }
            case "locationLat": {
                if (location != null) {
                    return new Md2String(location.getLatitude() + "");
                } else {
                    return null;
                }
            }
            default:
                return null;
        }
    }

    private Location getLastKnownLocation(LocationManager mLocationManager) {
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            @SuppressLint("MissingPermission") Location l = mLocationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        return bestLocation;
    }

    public void prepareLocationCapture(){

        if(handler.getState() == Thread.State.NEW) {
            handler.start();
        }

        // Get fresh values
        gpsHandler = new LocationHandler(handler.getLooper());
        gpsHandler.setProvider(this);

        //resumeCapture();
    }

    public void resumeCapture(){

        // Check permissions
        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(EinkaufszettelApp.getAppContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(EinkaufszettelApp.getAppContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            EinkaufszettelApp.getBenchmarkFragment().onBenchmarkFailed("Permissions not given for accessing location!");
            return;
        }

        // Get location manager
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(MIN_TIME_BETWEEN_UPDATES);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setNumUpdates(1);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(EinkaufszettelApp.getCurrentActivity());

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult result) {
                super.onLocationResult(result);
                mCurrentLocation = result.getLocations().get(0);

                // Send new location to handler
                gpsHandler.onLocationChanged(mCurrentLocation);
            }
        };

        // Start listening for updates
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }

    @Override
    public void setValue(String name, Md2Type value) {
        // Virtual content provider cannot be set
    }

    public void reset() {
        newEntity();
    }

    @Override
    public void load() {

        super.load();
    }

    @Override
    public void save() {
        // Always call on demand
    }

    @Override
    public void remove() {
        if (this.content != null && this.md2DataStore != null) {
            this.md2DataStore.remove(this.internalId, this.content.getClass());
            this.internalId = -1L;
        }
    }

    @Override
    public void newEntity() {
        content = new InvestigationGeolocation();
    }

    protected Location location = null;

    public boolean hasResult(){
        return location != null;
    }
}

