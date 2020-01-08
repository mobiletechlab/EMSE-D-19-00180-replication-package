// generated in de.wwu.md2.framework.generator.android.lollipop.controller.Activity.generateActivity()
package md2.investigation;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.DefaultItemAnimator;

import de.wwu.md2.android.md2library.SensorHelper; // TODO Generalize
import de.wwu.md2.android.md2library.model.contentProvider.implementation.Md2ContentProviderRegistry;
import de.wwu.performancebenchmark.model.BenchmarkType;
import md2.investigation.md2.controller.Controller;
import de.wwu.md2.android.md2library.view.management.implementation.Md2ViewManager;
import de.wwu.md2.android.md2library.view.management.implementation.Md2WidgetRegistry;
import de.wwu.md2.android.md2library.controller.action.implementation.customCode.Md2TaskQueue;
import de.wwu.md2.android.md2library.view.widgets.implementation.Md2GridLayoutPane;
import de.wwu.md2.android.md2library.view.widgets.implementation.Md2FlowLayoutPane;
import de.wwu.md2.android.md2library.view.widgets.implementation.Md2Label;
import de.wwu.md2.android.md2library.view.widgets.implementation.Md2Button;
import de.wwu.md2.android.md2library.view.widgets.implementation.Md2TextInput;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Boolean;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Date;
import de.wwu.md2.android.md2library.model.type.implementation.Md2DateTime;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Float;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Integer;
import de.wwu.md2.android.md2library.model.type.implementation.Md2String;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Time;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Sensor;
import de.wwu.md2.android.md2library.model.type.implementation.Md2List;
import de.wwu.md2.android.md2library.controller.eventhandler.implementation.Md2ContentProviderEventTypes;
import de.wwu.md2.android.md2library.controller.eventhandler.implementation.Md2OnAttributeChangedHandler;
import de.wwu.md2.android.md2library.controller.eventhandler.implementation.Md2OnChangedHandler;
import de.wwu.md2.android.md2library.controller.eventhandler.implementation.Md2OnClickHandler;
import de.wwu.md2.android.md2library.controller.eventhandler.implementation.Md2OnLongClickHandler;
import de.wwu.md2.android.md2library.controller.eventhandler.implementation.Md2OnLeftSwipeHandler;
import de.wwu.md2.android.md2library.controller.eventhandler.implementation.Md2OnRightSwipeHandler;
import de.wwu.md2.android.md2library.controller.eventhandler.implementation.Md2WidgetEventType;
import md2.investigation.md2.controller.action.Geolocation_Geolocation_getGeolocationValue_Action;
import md2.investigation.md2.model.contentProvider.LocationProvider;

public class GeolocationViewActivity extends AppCompatActivity implements BenchmarkFragment.OnBenchmarkInteractionListener {
    public static final int BENCHMARK_PERMISSION_ACCESS_FINE_LOCATION = 1;

    private RecyclerView wrv;
    BenchmarkFragment benchmarkFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geolocationview);
        Md2GridLayoutPane geolocationView_geolocationWrapper = (Md2GridLayoutPane) findViewById(R.id.GeolocationView_geolocationWrapper);
        geolocationView_geolocationWrapper.setWidgetId(R.id.GeolocationView_geolocationWrapper);
        Md2WidgetRegistry.getInstance().addWidget(geolocationView_geolocationWrapper);
        Md2Button geolocationView_893087 = (Md2Button) findViewById(R.id.GeolocationView_893087);
        geolocationView_893087.setWidgetId(R.id.GeolocationView_893087);
        Md2WidgetRegistry.getInstance().addWidget(geolocationView_893087);

        // Initialize benchmark fragment
        Bundle bundle = new Bundle();
        bundle.putSerializable(BenchmarkFragment.TYPE, BenchmarkType.GEOLOCATION);

        benchmarkFragment = (BenchmarkFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment);
        benchmarkFragment.setArguments(bundle);

        // Ask for permissions upfront
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    BENCHMARK_PERMISSION_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Md2ViewManager.getInstance().setActiveView(this);

        Md2GridLayoutPane geolocationView_geolocationWrapper = (Md2GridLayoutPane) findViewById(R.id.GeolocationView_geolocationWrapper);
        Md2WidgetRegistry.getInstance().loadWidget(geolocationView_geolocationWrapper);
        Md2Button geolocationView_893087 = (Md2Button) findViewById(R.id.GeolocationView_893087);
        Md2WidgetRegistry.getInstance().loadWidget(geolocationView_893087);

        Md2TaskQueue.getInstance().tryExecutePendingTasks();

        EinkaufszettelApp.setBenchmarkFragment(benchmarkFragment);
        EinkaufszettelApp.setCurrentActivity(this);

        ((LocationProvider) Md2ContentProviderRegistry.getInstance().getContentProvider("LocationProvider")).prepareLocationCapture();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Md2GridLayoutPane geolocationView_geolocationWrapper = (Md2GridLayoutPane) findViewById(R.id.GeolocationView_geolocationWrapper);
        Md2WidgetRegistry.getInstance().saveWidget(geolocationView_geolocationWrapper);
        Md2Button geolocationView_893087 = (Md2Button) findViewById(R.id.GeolocationView_893087);
        Md2WidgetRegistry.getInstance().saveWidget(geolocationView_893087);
    }

    @Override
    public void onBackPressed() {
        // go back to start screen
        Md2ViewManager.getInstance().goTo(getString(R.string.StartActivity));
    }

    @Override
    public void onBenchmarkStart() {
        ((LocationProvider) Md2ContentProviderRegistry.getInstance().getContentProvider("LocationProvider")).resumeCapture();
        new Geolocation_Geolocation_getGeolocationValue_Action().execute();
    }

    @Override
    public void onBenchmarkReset() {
        ((LocationProvider) Md2ContentProviderRegistry.getInstance().getContentProvider("LocationProvider")).stopCapture();
        // Nothing to do
    }
}
