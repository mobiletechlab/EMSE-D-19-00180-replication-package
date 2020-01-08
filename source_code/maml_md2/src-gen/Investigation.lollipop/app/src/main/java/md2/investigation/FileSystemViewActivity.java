// generated in de.wwu.md2.framework.generator.android.lollipop.controller.Activity.generateActivity()
package md2.investigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.DefaultItemAnimator;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

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
import md2.investigation.md2.controller.action.FileSystem_FileSystem_getFileSystemValue_Action;

public class FileSystemViewActivity extends AppCompatActivity implements BenchmarkFragment.OnBenchmarkInteractionListener {

	private RecyclerView wrv;

    BenchmarkFragment benchmarkFragment = null;

    private static final String FILENAME = "file_system_benchmark_2";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filesystemview);
Md2GridLayoutPane fileSystemView_fileSystemWrapper = (Md2GridLayoutPane) findViewById(R.id.FileSystemView_fileSystemWrapper);
fileSystemView_fileSystemWrapper.setWidgetId(R.id.FileSystemView_fileSystemWrapper);
Md2WidgetRegistry.getInstance().addWidget(fileSystemView_fileSystemWrapper);
Md2Button fileSystemView_662844 = (Md2Button) findViewById(R.id.FileSystemView_662844);
fileSystemView_662844.setWidgetId(R.id.FileSystemView_662844);
Md2WidgetRegistry.getInstance().addWidget(fileSystemView_662844);

        // Initialize benchmark fragment
        Bundle bundle = new Bundle();
        bundle.putSerializable(BenchmarkFragment.TYPE, BenchmarkType.FILE_SYSTEM);

        benchmarkFragment = (BenchmarkFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment);
        benchmarkFragment.setArguments(bundle);

        // Setup: Copy file to file system
        FileOutputStream outputStream;

        try {
            InputStream res = this.getResources().openRawResource(R.raw.file_system_benchmark_2);
            Bitmap bm = BitmapFactory.decodeStream(res);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 100 , baos);
            byte[] b = baos.toByteArray();
            String encoded = Base64.encodeToString(b, Base64.DEFAULT);

            outputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            outputStream.write(encoded.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

    @Override
    protected void onStart(){
		super.onStart();
        Md2ViewManager.getInstance().setActiveView(this);
        
        Md2GridLayoutPane fileSystemView_fileSystemWrapper = (Md2GridLayoutPane) findViewById(R.id.FileSystemView_fileSystemWrapper);
        Md2WidgetRegistry.getInstance().loadWidget(fileSystemView_fileSystemWrapper);
        Md2Button fileSystemView_662844 = (Md2Button) findViewById(R.id.FileSystemView_662844);
        Md2WidgetRegistry.getInstance().loadWidget(fileSystemView_662844);
        
        Md2TaskQueue.getInstance().tryExecutePendingTasks();

        EinkaufszettelApp.setBenchmarkFragment(benchmarkFragment);
        EinkaufszettelApp.setCurrentActivity(this);
    }
    
	@Override
    protected void onPause(){
        super.onPause();
        Md2GridLayoutPane fileSystemView_fileSystemWrapper = (Md2GridLayoutPane) findViewById(R.id.FileSystemView_fileSystemWrapper);
        Md2WidgetRegistry.getInstance().saveWidget(fileSystemView_fileSystemWrapper);
        Md2Button fileSystemView_662844 = (Md2Button) findViewById(R.id.FileSystemView_662844);
        Md2WidgetRegistry.getInstance().saveWidget(fileSystemView_662844);
    }
    
    @Override
	public void onBackPressed() {
		// go back to start screen
		Md2ViewManager.getInstance().goTo(getString(R.string.StartActivity));
	}

    @Override
    public void onBenchmarkStart() {
        new FileSystem_FileSystem_getFileSystemValue_Action().execute();
    }

    @Override
    public void onBenchmarkReset() {
        // Nothing to do
    }
}
