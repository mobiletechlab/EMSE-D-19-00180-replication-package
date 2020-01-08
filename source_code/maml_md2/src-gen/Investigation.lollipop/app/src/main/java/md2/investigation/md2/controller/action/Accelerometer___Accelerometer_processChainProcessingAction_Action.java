// generated in de.wwu.md2.framework.generator.android.lollipop.controller.Actions.generateAction()
package md2.investigation.md2.controller.action;

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

public class Accelerometer___Accelerometer_processChainProcessingAction_Action extends AbstractMd2Action {

    public Accelerometer___Accelerometer_processChainProcessingAction_Action() {
		super("Accelerometer___Accelerometer_processChainProcessingAction_Action"); 
	}

    @Override
    public void execute() {
    	
		if((((Md2String) Md2ContentProviderRegistry.getInstance().getContentProvider("__processChainControllerStateProvider").getValue("currentProcessChainStep")).equals(new Md2String("firstProcessChain__firstStep")))){
		}
	}
}
