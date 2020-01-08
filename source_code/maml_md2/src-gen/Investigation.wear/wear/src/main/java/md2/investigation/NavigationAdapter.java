// generated in de.wwu.md2.framework.generator.android.wearable.controller.ActivityGen.generateNavigationAdapter()
		package md2.investigation;
		
		import android.graphics.drawable.Drawable;
		import android.support.wearable.view.drawer.WearableNavigationDrawer;
		import de.wwu.md2.android.md2library.controller.action.interfaces.Md2Action;
		import de.wwu.md2.android.md2library.view.management.implementation.Md2ViewManager;
		import java.util.ArrayList;
		import md2.investigation.md2.controller.action.Accelerometer___Accelerometer_startupAction_Action;
		import md2.investigation.md2.controller.action.Camera___Camera_startupAction_Action;
		import md2.investigation.md2.controller.action.Database___Database_startupAction_Action;
		import md2.investigation.md2.controller.action.FileSystem___FileSystem_startupAction_Action;
		import md2.investigation.md2.controller.action.Geolocation___Geolocation_startupAction_Action;
		
		public class NavigationAdapter extends WearableNavigationDrawer.WearableNavigationDrawerAdapter{
			
			private static NavigationAdapter instance;
			private int active;
			private int selected;
			private ArrayList<String> names;
			private ArrayList<Md2Action> actions;
			private boolean isFirstStart;
			
			
			public static synchronized NavigationAdapter getInstance(){
					if (NavigationAdapter.instance == null) {
						NavigationAdapter.instance = new NavigationAdapter();
					}
					return instance;
			}
			
			private NavigationAdapter(){
				active = 0;
				selected = 0;
				isFirstStart = true;
				names = new ArrayList<String>();
				actions = new ArrayList<Md2Action>();
				names.add("Accelerometer");
				actions.add(new Accelerometer___Accelerometer_startupAction_Action());
				names.add("Camera");
				actions.add(new Camera___Camera_startupAction_Action());
				names.add("Database");
				actions.add(new Database___Database_startupAction_Action());
				names.add("FileSystem");
				actions.add(new FileSystem___FileSystem_startupAction_Action());
				names.add("Geolocation");
				actions.add(new Geolocation___Geolocation_startupAction_Action());
			}
			
			@Override
			public int getCount() {
				return actions.size();
			}
			
			@Override
			public void onItemSelected(int position) {
				selected = position;
			}
			
			@Override
			public String getItemText(int pos) {
				return names.get(pos);
			}
			
			@Override
			public Drawable getItemDrawable(int position) {
				return Md2ViewManager.getInstance().getActiveView().getDrawable(R.drawable.ic_add_shopping_cart_white_24dp);
			}
			
			public int getActive(){
				return active;
			}
			
			public int getSelected(){
				return selected;
			}
			
			public void maybeFirstStart(){
				if(isFirstStart){
					actions.get(0).execute();
					isFirstStart = false;
				}
			}
			
			public boolean close(){
				if (active != selected){
					active = selected;
					actions.get(active).execute();
					return true;
				} else {
					return false;
				}
			}
			
		}
		
