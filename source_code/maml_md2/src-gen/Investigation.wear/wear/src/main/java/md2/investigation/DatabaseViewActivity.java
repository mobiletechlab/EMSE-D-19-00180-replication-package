// generated in de.wwu.md2.framework.generator.android.wearable.controller.Activity.generateActivity()
package md2.investigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Gravity;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.drawer.WearableDrawerLayout;
import android.support.wearable.view.drawer.WearableDrawerView;
import android.support.wearable.view.drawer.WearableNavigationDrawer;

import android.support.wearable.view.drawer.WearableActionDrawer;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;

import android.support.wearable.view.CurvedChildLayoutManager;
import android.support.wearable.view.WearableRecyclerView;


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

import de.wwu.md2.android.md2library.controller.action.interfaces.Md2Action;

import md2.investigation.md2.controller.action.Database_Database_getDatabaseValue_Action;
import md2.investigation.md2.controller.action.Database_Database_done_Action;

import de.wwu.md2.android.md2library.SensorHelper;


public class DatabaseViewActivity extends WearableActivity implements WearableActionDrawer.OnMenuItemClickListener {

	private WearableDrawerLayout drawerLayout;
	private WearableNavigationDrawer navigationDrawer;
	private NavigationAdapter adapter;
	private WearableActionDrawer actionDrawer;
	private WearableRecyclerView wrv;
	public Md2OnClickHandler clickHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_databaseview);

		clickHandler = new Md2OnClickHandler();

		Md2GridLayoutPane databaseView_databaserWrapper = (Md2GridLayoutPane) findViewById(R.id.DatabaseView_databaserWrapper);
		databaseView_databaserWrapper.setWidgetId(R.id.DatabaseView_databaserWrapper);
		Md2WidgetRegistry.getInstance().addWidget(databaseView_databaserWrapper);
		
		drawerLayout = (WearableDrawerLayout) findViewById(R.id.drawer_layout_DatabaseView);
		drawerLayout.setDrawerStateCallback(new WearableDrawerLayout.DrawerStateCallback() {
	   		@Override
			public void onDrawerOpened(View view) {
				navigationDrawer.setCurrentItem(adapter.getActive(), true);
		}
			@Override
			public void onDrawerClosed(View view) {
				if(adapter.close()){
					DatabaseViewActivity.this.finish();
				}
			}
			@Override
			public void onDrawerStateChanged(@WearableDrawerView.DrawerState int i) {
				if(i == 0){
				   if(navigationDrawer.isPeeking()) {
				   		final Handler handler = new Handler();
				   		handler.postDelayed(new Runnable() {
				   			@Override
				   			public void run() {
				   				if(navigationDrawer.isPeeking()){
				   					navigationDrawer.closeDrawer();
				   				}
			 				}
				   		 }, 2000);
					}
if(actionDrawer.isPeeking()){
	final Handler handler = new Handler();
	handler.postDelayed(new Runnable() {
		@Override
		public void run() {
			if(actionDrawer.isPeeking()){
					actionDrawer.closeDrawer();
			}
		}
	}, 2000);
}
if(actionDrawer.isPeeking()){
	final Handler handler = new Handler();
	handler.postDelayed(new Runnable() {
		@Override
		public void run() {
			if(actionDrawer.isPeeking()){
					actionDrawer.closeDrawer();
			}
		}
	}, 2000);
}
if(actionDrawer.isPeeking()){
	final Handler handler = new Handler();
	handler.postDelayed(new Runnable() {
		@Override
		public void run() {
			if(actionDrawer.isPeeking()){
					actionDrawer.closeDrawer();
			}
		}
	}, 2000);
}
if(actionDrawer.isPeeking()){
	final Handler handler = new Handler();
	handler.postDelayed(new Runnable() {
		@Override
		public void run() {
			if(actionDrawer.isPeeking()){
					actionDrawer.closeDrawer();
			}
		}
	}, 2000);
}
if(actionDrawer.isPeeking()){
	final Handler handler = new Handler();
	handler.postDelayed(new Runnable() {
		@Override
		public void run() {
			if(actionDrawer.isPeeking()){
					actionDrawer.closeDrawer();
			}
		}
	}, 2000);
}
if(actionDrawer.isPeeking()){
	final Handler handler = new Handler();
	handler.postDelayed(new Runnable() {
		@Override
		public void run() {
			if(actionDrawer.isPeeking()){
					actionDrawer.closeDrawer();
			}
		}
	}, 2000);
}
				  }
			   }
		});


		navigationDrawer = (WearableNavigationDrawer) findViewById(R.id.navigation_drawer_DatabaseView);
		adapter = NavigationAdapter.getInstance();
		navigationDrawer.setAdapter(adapter);

		actionDrawer = (WearableActionDrawer) findViewById(R.id.bottom_action_drawer_DatabaseView);
		actionDrawer.setOnMenuItemClickListener(this);

		navigationDrawer.setCurrentItem(adapter.getActive(), true);



	//HardwareSensoren
	SensorHelper meinSensorHelper_accelerometerValueX = new SensorHelper(this, "accelerometerValueX", "accelerometer", "X");
	SensorHelper meinSensorHelper_accelerometerValueY = new SensorHelper(this, "accelerometerValueY", "accelerometer", "Y");
	SensorHelper meinSensorHelper_accelerometerValueZ = new SensorHelper(this, "accelerometerValueZ", "accelerometer", "Z");

	}

	@Override
	protected void onStart(){
		super.onStart();
		Md2ViewManager.getInstance().setActiveView(this);

		Md2GridLayoutPane databaseView_databaserWrapper = (Md2GridLayoutPane) findViewById(R.id.DatabaseView_databaserWrapper);
		Md2WidgetRegistry.getInstance().loadWidget(databaseView_databaserWrapper);

		drawerLayout.peekDrawer(Gravity.TOP);
		drawerLayout.peekDrawer(Gravity.BOTTOM);

		adapter.maybeFirstStart();


		Md2TaskQueue.getInstance().tryExecutePendingTasks();


	}

	@Override
	protected void onPause(){
		super.onPause();
		Md2GridLayoutPane databaseView_databaserWrapper = (Md2GridLayoutPane) findViewById(R.id.DatabaseView_databaserWrapper);
		Md2WidgetRegistry.getInstance().saveWidget(databaseView_databaserWrapper);
	}
	
	@Override
	protected void onResume(){
		super.onResume();
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		Md2GridLayoutPane databaseView_databaserWrapper = (Md2GridLayoutPane) findViewById(R.id.DatabaseView_databaserWrapper);
		Md2WidgetRegistry.getInstance().saveWidget(databaseView_databaserWrapper);
	}

	@Override
	public void onBackPressed() {
		// go back to start screen
		Md2ViewManager.getInstance().goTo(getString(R.string.StartActivity));
	}

	@Override
	public boolean onMenuItemClick(MenuItem menuItem) {
	
		Md2Action ca = null;

		final int itemId = menuItem.getItemId();

		switch(itemId) {
			case R.id.DatabaseView_item0:
				ca = new Database_Database_getDatabaseValue_Action();
				break;

			case R.id.DatabaseView_item1:
				ca = new Database_Database_done_Action();
				break;

		}

		clickHandler.registerAction(ca);

		try {
			ca.execute();
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}
