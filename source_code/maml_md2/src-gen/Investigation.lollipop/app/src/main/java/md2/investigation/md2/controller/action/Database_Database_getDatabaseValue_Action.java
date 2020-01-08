// generated in de.wwu.md2.framework.generator.android.lollipop.controller.Actions.generateAction()
package md2.investigation.md2.controller.action;

import android.util.Log;

import java.security.SecureRandom;

import de.wwu.md2.android.md2library.controller.action.implementation.AbstractMd2Action;
import de.wwu.md2.android.md2library.controller.action.implementation.Md2ContentProviderOperationAction;
import de.wwu.md2.android.md2library.controller.action.implementation.Md2ContentProviderOperations;

import de.wwu.md2.android.md2library.model.type.implementation.Md2List;
import de.wwu.md2.android.md2library.model.type.implementation.Md2String;
import de.wwu.md2.android.md2library.exception.Md2WidgetNotCreatedException;
import de.wwu.md2.android.md2library.controller.action.implementation.customCode.Md2AttributeSetTask;
import de.wwu.md2.android.md2library.controller.action.implementation.customCode.Md2CallTask;
import de.wwu.md2.android.md2library.model.contentProvider.implementation.Md2ContentProviderRegistry;
import de.wwu.md2.android.md2library.controller.action.implementation.customCode.Md2TaskQueue;
import md2.investigation.EinkaufszettelApp;
import md2.investigation.md2.model.InvestigationAccelerometer;
import md2.investigation.md2.model.InvestigationDatabase128;
import md2.investigation.md2.model.InvestigationDatabase2048;
import md2.investigation.md2.model.contentProvider.AccelerometerProvider;
import md2.investigation.md2.model.contentProvider.Database128Provider;

public class Database_Database_getDatabaseValue_Action extends AbstractMd2Action {

	protected byte[][] dataset128;
	protected byte[][] dataset2048;
	
	final int DATABASE128_AMT = 5000;
	final int DATABASE2048_AMT = 1000;

    public Database_Database_getDatabaseValue_Action() {
		super("Database_Database_getDatabaseValue_Action");

		// Setup data sets
		dataset128 = new byte[DATABASE128_AMT][16];
		dataset2048 = new byte[DATABASE2048_AMT][16];

		SecureRandom random = new SecureRandom();
		for(int i = 0; i < DATABASE128_AMT; i++ ){
			random.nextBytes(dataset128[i]);
		}
		for(int i = 0; i < DATABASE2048_AMT; i++ ){
			random.nextBytes(dataset2048[i]);
		}
	}

    @Override
    public void execute() {

		for(int i = 0; i < DATABASE128_AMT; i++ ){
			InvestigationDatabase128 entity = (InvestigationDatabase128) Md2ContentProviderRegistry.getInstance().getContentProvider("Database128Provider").getContent();
			entity.getDatabaseValue128().add(new Md2String(dataset128[i].toString()));
			Md2AttributeSetTask task = new Md2AttributeSetTask("Database128Provider", "databaseValue128", entity.get("databaseValue128"));
			try {
				task.execute();
			} catch (Md2WidgetNotCreatedException e) {
				//e.printStackTrace();
			}

			Md2CallTask var1 = null;
			try {
				var1 = new Md2CallTask(new Md2ContentProviderOperationAction("Database128Provider", Md2ContentProviderOperations.CREATE_OR_UPDATE));
				var1.execute();
			}catch (Md2WidgetNotCreatedException e){
				Md2TaskQueue.getInstance().addPendingTask(var1);
			}

			//read
			try {
				var1 = new Md2CallTask(new Md2ContentProviderOperationAction("Database128Provider", Md2ContentProviderOperations.READ));
				var1.execute();
			}catch (Md2WidgetNotCreatedException e){
				Md2TaskQueue.getInstance().addPendingTask(var1);
			}
		}

		for(int i = 0; i < DATABASE2048_AMT; i++ ){
			InvestigationDatabase2048 entity = (InvestigationDatabase2048) Md2ContentProviderRegistry.getInstance().getContentProvider("Database2048Provider").getContent();
			entity.getDatabaseValue2048().add(new Md2String(dataset2048[i].toString()));
			Md2AttributeSetTask task = new Md2AttributeSetTask("Database2048Provider", "databaseValue2048", entity.get("databaseValue2048"));
			try {
				task.execute();
			} catch (Md2WidgetNotCreatedException e) {
				//e.printStackTrace();
			}

			Md2CallTask var2 = null;
			try {
				var2 = new Md2CallTask(new Md2ContentProviderOperationAction("Database2048Provider", Md2ContentProviderOperations.CREATE_OR_UPDATE));
				var2.execute();
			}catch (Md2WidgetNotCreatedException e){
				Md2TaskQueue.getInstance().addPendingTask(var2);
			}

			//read
			try {
				var2 = new Md2CallTask(new Md2ContentProviderOperationAction("Database2048Provider", Md2ContentProviderOperations.READ));
				var2.execute();
			}catch (Md2WidgetNotCreatedException e){
				Md2TaskQueue.getInstance().addPendingTask(var2);
			}

		}

		// Capture benchmark result
		InvestigationDatabase128 entity = ((InvestigationDatabase128) ((Database128Provider) Md2ContentProviderRegistry.getInstance().getContentProvider("Database128Provider")).getContent());
		Log.d("OUTPUT", entity.getDatabaseValue128().size() + " elements in 128");
		if (entity.getDatabaseValue128() != null) {
			EinkaufszettelApp.getBenchmarkFragment().onBenchmarkCompleted(DATABASE128_AMT + " small + " + DATABASE2048_AMT + " large values written and read");
		} else {
			EinkaufszettelApp.getBenchmarkFragment().onBenchmarkFailed("Database values could not be written/read!");
		}
	}
}