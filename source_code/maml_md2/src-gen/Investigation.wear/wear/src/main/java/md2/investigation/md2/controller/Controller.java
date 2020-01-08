
// generated in de.wwu.md2.framework.generator.android.lollipop.controller.Md2Controller.generateController()
package md2.investigation.md2.controller;

import android.app.Activity;

import md2.investigation.EinkaufszettelApp;

import md2.investigation.md2.model.InvestigationAccelerometer;
import md2.investigation.md2.model.InvestigationCamera;
import md2.investigation.md2.model.InvestigationDatabase128;
import md2.investigation.md2.model.InvestigationDatabase2048;
import md2.investigation.md2.model.InvestigationFileSystem;
import md2.investigation.md2.model.InvestigationGeolocation;
import md2.investigation.md2.model.__ReturnStepStack;
import md2.investigation.md2.model.__ProcessChainControllerState;

import md2.investigation.md2.model.contentProvider.AccelerometerProvider;
import md2.investigation.md2.model.contentProvider.CameraProvider;
import md2.investigation.md2.model.contentProvider.Database128Provider;
import md2.investigation.md2.model.contentProvider.Database2048Provider;
import md2.investigation.md2.model.contentProvider.FileSystemProvider;
import md2.investigation.md2.model.contentProvider.GeolocationProvider;
import md2.investigation.md2.model.contentProvider.__returnStepStackProvider;
import md2.investigation.md2.model.contentProvider.__processChainControllerStateProvider;

import java.util.ArrayList;
import java.util.HashSet;

import de.wwu.md2.android.md2library.model.contentProvider.interfaces.Md2MultiContentProvider;
import de.wwu.md2.android.md2library.model.dataStore.implementation.Md2RemoteStoreFactory;

import de.wwu.md2.android.md2library.controller.action.implementation.AbstractMd2Action;
import de.wwu.md2.android.md2library.controller.action.implementation.Md2ContentProviderOperationAction;
import de.wwu.md2.android.md2library.controller.action.implementation.Md2ContentProviderOperations;
import de.wwu.md2.android.md2library.controller.action.implementation.Md2ContentProviderResetAction;
import de.wwu.md2.android.md2library.controller.action.implementation.Md2DisplayMessageAction;
import de.wwu.md2.android.md2library.controller.action.implementation.Md2FireEventAction;
import de.wwu.md2.android.md2library.controller.action.implementation.Md2GoToViewAction;
import de.wwu.md2.android.md2library.controller.action.implementation.Md2SynchronizeContentProviderDataMappingAction;
import de.wwu.md2.android.md2library.controller.action.implementation.Md2SynchronizeWidgetDataMappingAction;
import de.wwu.md2.android.md2library.controller.action.interfaces.Md2Action;

import de.wwu.md2.android.md2library.model.type.implementation.Md2Boolean;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Date;
import de.wwu.md2.android.md2library.model.type.implementation.Md2DateTime;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Float;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Integer;
import de.wwu.md2.android.md2library.model.type.implementation.Md2String;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Time;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Sensor;
import de.wwu.md2.android.md2library.model.type.implementation.Md2List;
import de.wwu.md2.android.md2library.exception.Md2WidgetNotCreatedException;
import de.wwu.md2.android.md2library.controller.eventhandler.implementation.Md2ContentProviderEventTypes;
import de.wwu.md2.android.md2library.controller.eventhandler.implementation.Md2OnAttributeChangedHandler;
import de.wwu.md2.android.md2library.controller.eventhandler.implementation.Md2OnChangedHandler;
import de.wwu.md2.android.md2library.controller.eventhandler.implementation.Md2OnClickHandler;
import de.wwu.md2.android.md2library.controller.eventhandler.implementation.Md2OnLongClickHandler;
import de.wwu.md2.android.md2library.controller.eventhandler.implementation.Md2OnLeftSwipeHandler;
import de.wwu.md2.android.md2library.controller.eventhandler.implementation.Md2OnRightSwipeHandler;
import de.wwu.md2.android.md2library.controller.eventhandler.implementation.Md2WidgetEventType;
import de.wwu.md2.android.md2library.controller.action.implementation.customCode.interfaces.Md2CustomCodeTask;
import de.wwu.md2.android.md2library.controller.action.implementation.customCode.Md2AttributeSetTask;
import de.wwu.md2.android.md2library.controller.action.implementation.customCode.Md2BindTask;
import de.wwu.md2.android.md2library.controller.action.implementation.customCode.Md2CallTask;
import de.wwu.md2.android.md2library.controller.action.implementation.customCode.Md2MapTask;
import de.wwu.md2.android.md2library.controller.action.implementation.customCode.Md2UnbindTask;
import de.wwu.md2.android.md2library.controller.action.implementation.customCode.Md2UnmapTask;

import de.wwu.md2.android.md2library.controller.implementation.AbstractMd2Controller;
import de.wwu.md2.android.md2library.model.contentProvider.implementation.Md2ContentProviderRegistry;
import de.wwu.md2.android.md2library.model.contentProvider.interfaces.Md2ContentProvider;
import de.wwu.md2.android.md2library.model.dataStore.interfaces.Md2SQLiteHelper;
import de.wwu.md2.android.md2library.model.dataStore.implementation.Md2SQLiteDataStore;
import de.wwu.md2.android.md2library.view.management.implementation.Md2ViewManager;
import de.wwu.md2.android.md2library.workflow.Md2WorkflowEventRegistry;
import de.wwu.md2.android.md2library.workflow.Md2WorkflowElement;
import de.wwu.md2.android.md2library.workflow.Md2WorkflowAction;
import md2.investigation.md2.model.sqlite.Md2LocalStoreFactory;

public class Controller extends AbstractMd2Controller {

	protected ArrayList<Md2CustomCodeTask> pendingTasks;

    private static Controller instance;

    private Controller() {
        pendingTasks = new ArrayList<Md2CustomCodeTask>();
    }

    public static synchronized Controller getInstance() {
        if (Controller.instance == null) {
            Controller.instance = new Controller();
        }
        return Controller.instance;
    }

    @Override
    public void run() {
        this.registerContentProviders();
        this.registerWorkflowEvents();
    }

    public void registerContentProviders() {
        Md2ContentProviderRegistry cpr = Md2ContentProviderRegistry.getInstance();
        Md2LocalStoreFactory lsf = new Md2LocalStoreFactory(this.instance);
        Md2RemoteStoreFactory rsf= new Md2RemoteStoreFactory();
        Md2ContentProvider accelerometerProvider = new AccelerometerProvider("AccelerometerProvider",new InvestigationAccelerometer(),  lsf.getDataStore("InvestigationAccelerometer"));
        cpr.add("AccelerometerProvider", accelerometerProvider);
        
        
        Md2ContentProvider cameraProvider = new CameraProvider("CameraProvider",new InvestigationCamera(),  lsf.getDataStore("InvestigationCamera"));
        cpr.add("CameraProvider", cameraProvider);
        
        
        Md2ContentProvider database128Provider = new Database128Provider("Database128Provider",new InvestigationDatabase128(),  lsf.getDataStore("InvestigationDatabase128"));
        cpr.add("Database128Provider", database128Provider);
        
        
        Md2ContentProvider database2048Provider = new Database2048Provider("Database2048Provider",new InvestigationDatabase2048(),  lsf.getDataStore("InvestigationDatabase2048"));
        cpr.add("Database2048Provider", database2048Provider);
        
        
        Md2ContentProvider fileSystemProvider = new FileSystemProvider("FileSystemProvider",new InvestigationFileSystem(),  lsf.getDataStore("InvestigationFileSystem"));
        cpr.add("FileSystemProvider", fileSystemProvider);
        
        
        Md2ContentProvider geolocationProvider = new GeolocationProvider("GeolocationProvider",new InvestigationGeolocation(),  lsf.getDataStore("InvestigationGeolocation"));
        cpr.add("GeolocationProvider", geolocationProvider);
        
        
        Md2ContentProvider __returnStepStackProvider = new __returnStepStackProvider("__returnStepStackProvider",new __ReturnStepStack(),  lsf.getDataStore("__ReturnStepStack"));
        cpr.add("__returnStepStackProvider", __returnStepStackProvider);
        
        
        Md2ContentProvider __processChainControllerStateProvider = new __processChainControllerStateProvider("__processChainControllerStateProvider",new __ProcessChainControllerState(),  lsf.getDataStore("__ProcessChainControllerState"));
        cpr.add("__processChainControllerStateProvider", __processChainControllerStateProvider);
        
        
    }
    
    public void registerWorkflowEvents() {
    	    
    	Md2WorkflowEventRegistry.getInstance().addWorkflowEvent(
    	    "Md2FireEventActionAccelerometerDone",
    	    new Md2WorkflowElement("Accelerometer", 
    	    	new md2.investigation.md2.controller.action.Accelerometer___Accelerometer_startupAction_Action()),
    	    Md2WorkflowAction.END);
    	    
    	Md2WorkflowEventRegistry.getInstance().addWorkflowEvent(
    	    "Md2FireEventActionCameraDone",
    	    new Md2WorkflowElement("Camera", 
    	    	new md2.investigation.md2.controller.action.Camera___Camera_startupAction_Action()),
    	    Md2WorkflowAction.END);
    	    
    	Md2WorkflowEventRegistry.getInstance().addWorkflowEvent(
    	    "Md2FireEventActionDatabaseDone",
    	    new Md2WorkflowElement("Database", 
    	    	new md2.investigation.md2.controller.action.Database___Database_startupAction_Action()),
    	    Md2WorkflowAction.END);
    	    
    	Md2WorkflowEventRegistry.getInstance().addWorkflowEvent(
    	    "Md2FireEventActionFileSystemDone",
    	    new Md2WorkflowElement("FileSystem", 
    	    	new md2.investigation.md2.controller.action.FileSystem___FileSystem_startupAction_Action()),
    	    Md2WorkflowAction.END);
    	    
    	Md2WorkflowEventRegistry.getInstance().addWorkflowEvent(
    	    "Md2FireEventActionGeolocationDone",
    	    new Md2WorkflowElement("Geolocation", 
    	    	new md2.investigation.md2.controller.action.Geolocation___Geolocation_startupAction_Action()),
    	    Md2WorkflowAction.END);
    }

    @Override
    public Md2SQLiteHelper getMd2SQLiteHelper() {
        return EinkaufszettelApp.getMd2SQLiteHelper();
    }
}
