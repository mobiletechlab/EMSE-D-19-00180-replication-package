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

public class Database___Database_startupAction_Action extends AbstractMd2Action {

    public Database___Database_startupAction_Action() {
		super("Database___Database_startupAction_Action"); 
	}

    @Override
    public void execute() {
    	
		Md2CallTask var1 = null;
		try {
			var1 = new Md2CallTask(new Database___Database_registerProcessChainActionEventTrigger_Action());
			var1.execute();
		}catch (Md2WidgetNotCreatedException e){
			Md2TaskQueue.getInstance().addPendingTask(var1);
		}
		Md2CallTask var2 = null;
		try {
			var2 = new Md2CallTask(new Database___processChainSetProcessChainFirstProcessChainAction_Action());
			var2.execute();
		}catch (Md2WidgetNotCreatedException e){
			Md2TaskQueue.getInstance().addPendingTask(var2);
		}
		Md2CallTask var3 = null;
		try {
			var3 = new Md2CallTask(new Database_Database_init_Action());
			var3.execute();
		}catch (Md2WidgetNotCreatedException e){
			Md2TaskQueue.getInstance().addPendingTask(var3);
		}
		Md2BindTask var4 = null;
		try {
			var4 = new Md2BindTask(new Database_Database_getDatabaseValue_Action(), R.id.DatabaseView_687142, Md2WidgetEventType.ON_CLICK
			);
			var4.execute();
		}catch (Md2WidgetNotCreatedException e){
			Md2TaskQueue.getInstance().addPendingTask(var4);
		}
		Md2BindTask var5 = null;
		try {
			var5 = new Md2BindTask(new Database_Database_done_Action(), R.id.DatabaseView_727522, Md2WidgetEventType.ON_CLICK
			);
			var5.execute();
		}catch (Md2WidgetNotCreatedException e){
			Md2TaskQueue.getInstance().addPendingTask(var5);
		}
	}
}