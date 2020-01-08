// generated in de.wwu.md2.framework.generator.android.lollipop.controller.Actions.generateAction()
package md2.investigation.md2.controller.action;

import android.util.Log;

import de.wwu.md2.android.md2library.model.type.interfaces.Md2Type;
import md2.investigation.EinkaufszettelApp;
import md2.investigation.R;

import md2.investigation.md2.controller.Controller;
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
import de.wwu.md2.android.md2library.model.contentProvider.implementation.Md2ContentProviderRegistry;
import de.wwu.md2.android.md2library.view.management.implementation.Md2ViewManager;
import de.wwu.md2.android.md2library.controller.action.implementation.customCode.Md2TaskQueue;
import de.wwu.md2.android.md2library.controller.action.implementation.Md2ContentProviderAddAction;
import de.wwu.md2.android.md2library.controller.action.implementation.Md2ContentProviderRemoveActiveAction;
import de.wwu.md2.android.md2library.controller.action.implementation.Md2ContentProviderGetActiveAction;
import de.wwu.md2.android.md2library.controller.action.implementation.Md2ContentProviderResetLocalAction;
import md2.investigation.md2.model.InvestigationAccelerometer;
import md2.investigation.md2.model.contentProvider.AccelerometerProvider;

public class Accelerometer_Accelerometer_getAccelerometerValue_Action extends AbstractMd2Action {

    public Accelerometer_Accelerometer_getAccelerometerValue_Action() {
		super("Accelerometer_Accelerometer_getAccelerometerValue_Action"); 
	}

    @Override
    public void execute() {

        Md2ContentProviderRegistry cpr = Md2ContentProviderRegistry.getInstance();
        Md2AttributeSetTask var1 = null;
        try {

            var1 = new Md2AttributeSetTask("AccelerometerProvider", "accelerometerValueX", cpr.getContentProvider("SensorProvider").getValue("accelerometerX"));
            var1.execute();
        }catch (Md2WidgetNotCreatedException e){
            Md2TaskQueue.getInstance().addPendingTask(var1);
        }
        try {

            var1 = new Md2AttributeSetTask("AccelerometerProvider", "accelerometerValueY", cpr.getContentProvider("SensorProvider").getValue("accelerometerY"));
            var1.execute();
        }catch (Md2WidgetNotCreatedException e){
            Md2TaskQueue.getInstance().addPendingTask(var1);
        }
        try {
            var1 = new Md2AttributeSetTask("AccelerometerProvider", "accelerometerValueZ", cpr.getContentProvider("SensorProvider").getValue("accelerometerZ"));
            var1.execute();
        }catch (Md2WidgetNotCreatedException e){
            Md2TaskQueue.getInstance().addPendingTask(var1);
        }

        // Capture benchmark result
        InvestigationAccelerometer entity = ((InvestigationAccelerometer) ((AccelerometerProvider) cpr.getContentProvider("AccelerometerProvider")).getContent());
        if (entity.getAccelerometerValueX() != null && entity.getAccelerometerValueY() != null && entity.getAccelerometerValueZ() != null) {
            EinkaufszettelApp.getBenchmarkFragment().onBenchmarkCompleted(entity.getAccelerometerValueX() + " " + entity.getAccelerometerValueY() + " " + entity.getAccelerometerValueZ());
        } else {
            EinkaufszettelApp.getBenchmarkFragment().onBenchmarkFailed("Accelerometer values could not be obtained!");
        }
	}
}
