// generated in de.wwu.md2.framework.generator.android.lollipop.controller.Activity.generateActivity()
package md2.investigation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.RecyclerView;

import de.wwu.performancebenchmark.model.BenchmarkType;
import de.wwu.md2.android.md2library.view.management.implementation.Md2ViewManager;
import de.wwu.md2.android.md2library.view.management.implementation.Md2WidgetRegistry;
import de.wwu.md2.android.md2library.controller.action.implementation.customCode.Md2TaskQueue;
import de.wwu.md2.android.md2library.view.widgets.implementation.Md2GridLayoutPane;
import de.wwu.md2.android.md2library.view.widgets.implementation.Md2Button;
import md2.investigation.md2.controller.action.Camera_Camera_getCameraValue_Action;

public class ContactViewActivity extends AppCompatActivity implements BenchmarkFragment.OnBenchmarkInteractionListener {

    public static final int BENCHMARK_PERMISSION_READ_CONTACTS = 1;
    public static final int BENCHMARK_PERMISSION_WRITE_CONTACTS = 2;

    private RecyclerView wrv;

    BenchmarkFragment benchmarkFragment = null;

    Camera_Camera_getCameraValue_Action action = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactview);
        Md2GridLayoutPane cameraView_cameraWrapper = (Md2GridLayoutPane) findViewById(R.id.CameraView_cameraWrapper);
        cameraView_cameraWrapper.setWidgetId(R.id.CameraView_cameraWrapper);
        Md2WidgetRegistry.getInstance().addWidget(cameraView_cameraWrapper);
        Md2Button cameraView_634431 = (Md2Button) findViewById(R.id.CameraView_634431);
        cameraView_634431.setWidgetId(R.id.CameraView_634431);
        Md2WidgetRegistry.getInstance().addWidget(cameraView_634431);

        // Initialize benchmark fragment
        Bundle bundle = new Bundle();
        bundle.putSerializable(BenchmarkFragment.TYPE, BenchmarkType.CONTACT);

        benchmarkFragment = (BenchmarkFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment);
        benchmarkFragment.setArguments(bundle);

        // Ask for permissions upfront
        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.READ_CONTACTS ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.READ_CONTACTS  },
                    BENCHMARK_PERMISSION_READ_CONTACTS );
        }
        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.WRITE_CONTACTS ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.WRITE_CONTACTS  },
                    BENCHMARK_PERMISSION_WRITE_CONTACTS );
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Md2ViewManager.getInstance().setActiveView(this);

        Md2GridLayoutPane cameraView_cameraWrapper = (Md2GridLayoutPane) findViewById(R.id.CameraView_cameraWrapper);
        Md2WidgetRegistry.getInstance().loadWidget(cameraView_cameraWrapper);
        Md2Button cameraView_634431 = (Md2Button) findViewById(R.id.CameraView_634431);
        Md2WidgetRegistry.getInstance().loadWidget(cameraView_634431);

        Md2TaskQueue.getInstance().tryExecutePendingTasks();

        EinkaufszettelApp.setBenchmarkFragment(benchmarkFragment);
        EinkaufszettelApp.setCurrentActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Md2GridLayoutPane cameraView_cameraWrapper = (Md2GridLayoutPane) findViewById(R.id.CameraView_cameraWrapper);
        Md2WidgetRegistry.getInstance().saveWidget(cameraView_cameraWrapper);
        Md2Button cameraView_634431 = (Md2Button) findViewById(R.id.CameraView_634431);
        Md2WidgetRegistry.getInstance().saveWidget(cameraView_634431);
    }

    @Override
    public void onBackPressed() {
        // go back to start screen
        Md2ViewManager.getInstance().goTo(getString(R.string.StartActivity));
    }

    @Override
    public void onBenchmarkStart() {
        action = new Camera_Camera_getCameraValue_Action();
        action.execute();
    }

    @Override
    public void onBenchmarkReset() {
        // Nothing to do
    }
}
