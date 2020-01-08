// generated in de.wwu.md2.framework.generator.android.lollipop.model.Md2ContentProvider.generateContentProvider()
package md2.investigation.md2.model.contentProvider;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.List;

import de.wwu.md2.android.md2library.controller.eventhandler.implementation.Md2OnAttributeChangedHandler;
import de.wwu.md2.android.md2library.model.contentProvider.implementation.AbstractMd2ContentProvider;
import de.wwu.md2.android.md2library.model.dataStore.interfaces.Md2DataStore;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Float;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Sensor;
import de.wwu.md2.android.md2library.model.type.implementation.Md2String;
import de.wwu.md2.android.md2library.model.type.interfaces.Md2Entity;
import de.wwu.md2.android.md2library.model.type.interfaces.Md2Type;
import md2.investigation.EinkaufszettelApp;
import md2.investigation.md2.model.InvestigationAccelerometer;
import md2.investigation.md2.model.InvestigationGeolocation;

public class SensorProvider extends AbstractMd2ContentProvider implements SensorEventListener {

    // We need a dedicated handler for the onSensorChanged
    HandlerThread handler = null;

    public SensorProvider(String key, Md2Entity content, Md2DataStore md2DataStore) {
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

    @Override
    public Md2Type getValue(String attribute) {
        // Get location manager
        SensorManager sensorManager = (SensorManager) EinkaufszettelApp.getCurrentActivity().getSystemService(Context.SENSOR_SERVICE);

        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Check sensor exists
        if(accelerometer == null){
            return null;
        }

        accelerometerX = null;
        accelerometerY = null;
        accelerometerZ = null;

        if(handler.getState() == Thread.State.NEW) {
            handler.start();
        }

        Handler sensorHandler = new Handler(handler.getLooper());
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_FASTEST, sensorHandler);

        while(!hasResult()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // e.printStackTrace();
            }
        }

        sensorManager.unregisterListener(this);

        switch (attribute) {
            case "accelerometerX": {
                if(accelerometerX != null) return new Md2Float(accelerometerX);
            }
            case "accelerometerY": {
                if(accelerometerY != null) return new Md2Float(accelerometerY);
            }
            case "accelerometerZ": {
                if(accelerometerZ != null) return new Md2Float(accelerometerZ);
            }
            default:
                return null;
        }
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
        content = new InvestigationAccelerometer();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    private Float accelerometerX, accelerometerY, accelerometerZ = null;

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // Accelerometer values are only available through sensor events
        Sensor mySensor = sensorEvent.sensor;
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            accelerometerX = sensorEvent.values[0];
            accelerometerY = sensorEvent.values[1];
            accelerometerZ = sensorEvent.values[2];
        }
    }

    public boolean hasResult(){
        return accelerometerX != null && accelerometerY != null && accelerometerZ != null;
    }
}