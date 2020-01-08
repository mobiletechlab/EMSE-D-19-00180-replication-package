// generated in de.wwu.md2.framework.generator.android.lollipop.controller.Activity.generateStartActivity()
package md2.investigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

import md2.investigation.md2.controller.action.Accelerometer___Accelerometer_startupAction_Action;
import md2.investigation.md2.controller.action.Camera___Camera_startupAction_Action;
import md2.investigation.md2.controller.action.Database___Database_startupAction_Action;
import md2.investigation.md2.controller.action.FileSystem___FileSystem_startupAction_Action;
import md2.investigation.md2.controller.action.Geolocation___Geolocation_startupAction_Action;

import de.wwu.md2.android.md2library.controller.action.implementation.Md2GoToViewAction;

public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Md2Button AccelerometerButton = (Md2Button) findViewById(R.id.startActivity_AccelerometerButton);
        AccelerometerButton.setWidgetId(R.id.startActivity_AccelerometerButton);
        Md2WidgetRegistry.getInstance().addWidget(AccelerometerButton);
        Md2Button CameraButton = (Md2Button) findViewById(R.id.startActivity_ContactButton);
        CameraButton.setWidgetId(R.id.startActivity_ContactButton);
        Md2WidgetRegistry.getInstance().addWidget(CameraButton);
        Md2Button DatabaseButton = (Md2Button) findViewById(R.id.startActivity_DatabaseButton);
        DatabaseButton.setWidgetId(R.id.startActivity_DatabaseButton);
        Md2WidgetRegistry.getInstance().addWidget(DatabaseButton);
        Md2Button FileSystemButton = (Md2Button) findViewById(R.id.startActivity_FileSystemButton);
        FileSystemButton.setWidgetId(R.id.startActivity_FileSystemButton);
        Md2WidgetRegistry.getInstance().addWidget(FileSystemButton);
        Md2Button GeolocationButton = (Md2Button) findViewById(R.id.startActivity_GeolocationButton);
        GeolocationButton.setWidgetId(R.id.startActivity_GeolocationButton);
        Md2WidgetRegistry.getInstance().addWidget(GeolocationButton);
    }

    @Override
    protected void onStart(){
		super.onStart();
		Md2ViewManager.getInstance().setActiveView(this);
        
        // TODO move startableWorkflowElements to Md2WorkflowManager
		Md2Button AccelerometerButton = (Md2Button) findViewById(R.id.startActivity_AccelerometerButton);
		AccelerometerButton.getOnClickHandler().registerAction(new Accelerometer___Accelerometer_startupAction_Action());
		Md2Button CameraButton = (Md2Button) findViewById(R.id.startActivity_ContactButton);
		CameraButton.getOnClickHandler().registerAction(new Camera___Camera_startupAction_Action());
		Md2Button DatabaseButton = (Md2Button) findViewById(R.id.startActivity_DatabaseButton);
		DatabaseButton.getOnClickHandler().registerAction(new Database___Database_startupAction_Action());
		Md2Button FileSystemButton = (Md2Button) findViewById(R.id.startActivity_FileSystemButton);
		FileSystemButton.getOnClickHandler().registerAction(new FileSystem___FileSystem_startupAction_Action());
		Md2Button GeolocationButton = (Md2Button) findViewById(R.id.startActivity_GeolocationButton);
		GeolocationButton.getOnClickHandler().registerAction(new Geolocation___Geolocation_startupAction_Action());
		Md2TaskQueue.getInstance().tryExecutePendingTasks();
    }
    
	@Override
    protected void onPause(){
        super.onPause();
	Md2Button AccelerometerButton = (Md2Button) findViewById(R.id.startActivity_AccelerometerButton);
	Md2WidgetRegistry.getInstance().saveWidget(AccelerometerButton);
	Md2Button CameraButton = (Md2Button) findViewById(R.id.startActivity_ContactButton);
	Md2WidgetRegistry.getInstance().saveWidget(CameraButton);
	Md2Button DatabaseButton = (Md2Button) findViewById(R.id.startActivity_DatabaseButton);
	Md2WidgetRegistry.getInstance().saveWidget(DatabaseButton);
	Md2Button FileSystemButton = (Md2Button) findViewById(R.id.startActivity_FileSystemButton);
	Md2WidgetRegistry.getInstance().saveWidget(FileSystemButton);
	Md2Button GeolocationButton = (Md2Button) findViewById(R.id.startActivity_GeolocationButton);
	Md2WidgetRegistry.getInstance().saveWidget(GeolocationButton);
    }
    
    @Override
	public void onBackPressed() {
		// remain on start screen
	}
}
