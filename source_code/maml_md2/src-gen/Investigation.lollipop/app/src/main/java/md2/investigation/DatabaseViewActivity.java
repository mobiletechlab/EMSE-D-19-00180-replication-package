// generated in de.wwu.md2.framework.generator.android.lollipop.controller.Activity.generateActivity()
package md2.investigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.DefaultItemAnimator;

import de.wwu.md2.android.md2library.SensorHelper; // TODO Generalize
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
import md2.investigation.md2.controller.action.Database_Database_getDatabaseValue_Action;
import md2.investigation.md2.controller.action.Geolocation_Geolocation_getGeolocationValue_Action;

public class DatabaseViewActivity extends AppCompatActivity implements BenchmarkFragment.OnBenchmarkInteractionListener {

    private RecyclerView wrv;

    BenchmarkFragment benchmarkFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_databaseview);
        Md2GridLayoutPane databaseView_databaserWrapper = (Md2GridLayoutPane) findViewById(R.id.DatabaseView_databaserWrapper);
        databaseView_databaserWrapper.setWidgetId(R.id.DatabaseView_databaserWrapper);
        Md2WidgetRegistry.getInstance().addWidget(databaseView_databaserWrapper);
        Md2Button databaseView_727522 = (Md2Button) findViewById(R.id.DatabaseView_727522);
        databaseView_727522.setWidgetId(R.id.DatabaseView_727522);
        Md2WidgetRegistry.getInstance().addWidget(databaseView_727522);

        // Initialize benchmark fragment
        Bundle bundle = new Bundle();
        bundle.putSerializable(BenchmarkFragment.TYPE, BenchmarkType.DATABASE);

        benchmarkFragment = (BenchmarkFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment);
        benchmarkFragment.setArguments(bundle);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Md2ViewManager.getInstance().setActiveView(this);

        Md2GridLayoutPane databaseView_databaserWrapper = (Md2GridLayoutPane) findViewById(R.id.DatabaseView_databaserWrapper);
        Md2WidgetRegistry.getInstance().loadWidget(databaseView_databaserWrapper);
        Md2Button databaseView_727522 = (Md2Button) findViewById(R.id.DatabaseView_727522);
        Md2WidgetRegistry.getInstance().loadWidget(databaseView_727522);

        Md2TaskQueue.getInstance().tryExecutePendingTasks();

        EinkaufszettelApp.setBenchmarkFragment(benchmarkFragment);
        EinkaufszettelApp.setCurrentActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Md2GridLayoutPane databaseView_databaserWrapper = (Md2GridLayoutPane) findViewById(R.id.DatabaseView_databaserWrapper);
        Md2WidgetRegistry.getInstance().saveWidget(databaseView_databaserWrapper);
        Md2Button databaseView_727522 = (Md2Button) findViewById(R.id.DatabaseView_727522);
        Md2WidgetRegistry.getInstance().saveWidget(databaseView_727522);
    }

    @Override
    public void onBackPressed() {
        // go back to start screen
        Md2ViewManager.getInstance().goTo(getString(R.string.StartActivity));
    }

    @Override
    public void onBenchmarkStart() {
        new Database_Database_getDatabaseValue_Action().execute();
    }

    @Override
    public void onBenchmarkReset() {
        // Nothing to do
    }
}
