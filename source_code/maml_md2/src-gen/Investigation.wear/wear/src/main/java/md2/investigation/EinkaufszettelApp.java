// generated in de.wwu.md2.framework.generator.android.lollipop.controller.App.generateAppClass()
package md2.investigation;

import android.app.Application;
import android.content.Context;

import md2.investigation.md2.controller.Controller;
import md2.investigation.md2.model.sqlite.Md2SQLiteHelperImpl;
import de.wwu.md2.android.md2library.model.dataStore.interfaces.Md2SQLiteHelper;
import de.wwu.md2.android.md2library.model.contentProvider.implementation.Md2ContentProviderRegistry;
import de.wwu.md2.android.md2library.controller.action.implementation.customCode.Md2TaskQueue;
import de.wwu.md2.android.md2library.view.management.implementation.Md2ViewManager;
import de.wwu.md2.android.md2library.view.management.implementation.Md2WidgetRegistry;
import de.wwu.md2.android.md2library.model.dataStore.implementation.VolleyQueue;
import de.wwu.md2.android.md2library.model.contentProvider.implementation.Polling;


public class EinkaufszettelApp extends Application {

	private static Context context;
	private static Md2SQLiteHelper sqLiteHelper;
	
	private static Controller co;
	private static Md2ViewManager vm;
	private static Md2ContentProviderRegistry cpr;
	private static Md2TaskQueue tq;
	private static Md2WidgetRegistry wr;

    @Override
    public void onCreate() {
        super.onCreate();
        co = Controller.getInstance();
		vm = Md2ViewManager.getInstance();
		cpr = Md2ContentProviderRegistry.getInstance();
		tq = Md2TaskQueue.getInstance();
		wr = Md2WidgetRegistry.getInstance();
		context = getApplicationContext();
		VolleyQueue.getInstance(context);
        Controller.getInstance().run();
        Thread t = new Thread(new Polling(cpr));
        t.start();
        
    }
    
	public static Context getAppContext() {
        return EinkaufszettelApp.context;
    }
    
	public static Md2SQLiteHelper getMd2SQLiteHelper() {
        if (sqLiteHelper == null)
            synchronized (EinkaufszettelApp.class) {
                if (sqLiteHelper == null)
                    sqLiteHelper = new Md2SQLiteHelperImpl(EinkaufszettelApp.getAppContext());
            }
        return sqLiteHelper;
    }
}
