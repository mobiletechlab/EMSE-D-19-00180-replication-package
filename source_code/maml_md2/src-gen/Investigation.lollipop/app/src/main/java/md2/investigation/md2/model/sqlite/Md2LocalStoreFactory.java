package md2.investigation.md2.model.sqlite;
import de.wwu.md2.android.md2library.model.dataStore.interfaces.Md2DataStore;
import de.wwu.md2.android.md2library.model.type.interfaces.Md2Entity;
import de.wwu.md2.android.md2library.controller.interfaces.Md2Controller;
import de.wwu.md2.android.md2library.model.dataStore.implementation.AbstractMd2LocalStoreFactory;
import de.wwu.md2.android.md2library.model.dataStore.interfaces.Md2LocalStore;
import md2.investigation.md2.model.InvestigationAccelerometer;
import md2.investigation.md2.model.InvestigationCamera;
import md2.investigation.md2.model.InvestigationDatabase128;
import md2.investigation.md2.model.InvestigationDatabase2048;
import md2.investigation.md2.model.InvestigationFileSystem;
import md2.investigation.md2.model.InvestigationGeolocation;
import md2.investigation.md2.model.__ReturnStepStack;
import md2.investigation.md2.model.__ProcessChainControllerState;


public class Md2LocalStoreFactory extends AbstractMd2LocalStoreFactory{
	
	public Md2LocalStoreFactory(Md2Controller controller){
		super(controller);
	
	}	
	
	public <T extends Md2Entity>  Md2DataStore getDataStore(String entity){
		final String entityName= entity;
		
		switch(entity){
			case "InvestigationAccelerometer": return new Md2OrmLiteDatastore<InvestigationAccelerometer>(entity); 	
			case "InvestigationCamera": return new Md2OrmLiteDatastore<InvestigationCamera>(entity); 	
			case "InvestigationDatabase128": return new Md2OrmLiteDatastore<InvestigationDatabase128>(entity); 	
			case "InvestigationDatabase2048": return new Md2OrmLiteDatastore<InvestigationDatabase2048>(entity); 	
			case "InvestigationFileSystem": return new Md2OrmLiteDatastore<InvestigationFileSystem>(entity); 	
			case "InvestigationGeolocation": return new Md2OrmLiteDatastore<InvestigationGeolocation>(entity); 	
			case "__ReturnStepStack": return new Md2OrmLiteDatastore<__ReturnStepStack>(entity); 	
			case "__ProcessChainControllerState": return new Md2OrmLiteDatastore<__ProcessChainControllerState>(entity); 	
			default: throw new IllegalArgumentException("Unknown Entity Type: "+ entity); 
		}
	}
}
