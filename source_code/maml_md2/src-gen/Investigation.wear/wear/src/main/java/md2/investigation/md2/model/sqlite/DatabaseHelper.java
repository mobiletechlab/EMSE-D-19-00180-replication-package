	package md2.investigation.md2.model.sqlite;

import java.sql.SQLException;
import de.wwu.md2.android.md2library.model.type.interfaces.Md2Entity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import md2.investigation.R;

import md2.investigation.md2.model.InvestigationAccelerometer;
import md2.investigation.md2.model.InvestigationCamera;
import md2.investigation.md2.model.InvestigationDatabase128;
import md2.investigation.md2.model.InvestigationDatabase2048;
import md2.investigation.md2.model.InvestigationFileSystem;
import md2.investigation.md2.model.InvestigationGeolocation;
import md2.investigation.md2.model.__ReturnStepStack;
import md2.investigation.md2.model.__ProcessChainControllerState;

 
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
 
/**
 * Database helper which creates and upgrades the database and provides the DAOs for the app.
 * 
 * 
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
 
 
	private static final String DATABASE_NAME = "EinkaufszettelApp.db";
	private static final int DATABASE_VERSION = 1; 
 
	
	private Dao<InvestigationAccelerometer, Integer> InvestigationAccelerometerDao;	
	private Dao<InvestigationCamera, Integer> InvestigationCameraDao;	
	private Dao<InvestigationDatabase128, Integer> InvestigationDatabase128Dao;	
	private Dao<InvestigationDatabase2048, Integer> InvestigationDatabase2048Dao;	
	private Dao<InvestigationFileSystem, Integer> InvestigationFileSystemDao;	
	private Dao<InvestigationGeolocation, Integer> InvestigationGeolocationDao;	
	private Dao<__ReturnStepStack, Integer> __ReturnStepStackDao;	
	private Dao<__ProcessChainControllerState, Integer> __ProcessChainControllerStateDao;	
	
 
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
	}
 
 
	@Override
	public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
		try {
			
			// Create tables. This onCreate() method will be invoked only once of the application life time i.e. the first time when the application starts.
			TableUtils.createTable(connectionSource, InvestigationAccelerometer.class);	
			TableUtils.createTable(connectionSource, InvestigationCamera.class);	
			TableUtils.createTable(connectionSource, InvestigationDatabase128.class);	
			TableUtils.createTable(connectionSource, InvestigationDatabase2048.class);	
			TableUtils.createTable(connectionSource, InvestigationFileSystem.class);	
			TableUtils.createTable(connectionSource, InvestigationGeolocation.class);	
			TableUtils.createTable(connectionSource, __ReturnStepStack.class);	
			TableUtils.createTable(connectionSource, __ProcessChainControllerState.class);	
			
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Unable to create datbases", e);
		}
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource, int oldVer, int newVer) {
		try {
			
			// In case of change in database of next version of application, please increase the value of DATABASE_VERSION variable, then this method will be invoked 
			//automatically. Developer needs to handle the upgrade logic here, i.e. create a new table or a new column to an existing table, take the backups of the
			// existing database etc.
			
			//TableUtils.dropTable(connectionSource, TeacherDetails.class, true);
			//TableUtils.dropTable(connectionSource, StudentDetails.class, true);
			//onCreate(sqliteDatabase, connectionSource);
			
		} catch (Exception e) {
			Log.e(DatabaseHelper.class.getName(), "Unable to upgrade database from version " + oldVer + " to new "
					+ newVer, e);
		}
	}
	
	
	// Create the getDao methods of all database tables to access those from android code.
	// Insert, delete, read, update everything will be happened through DAOs
 
public Dao<InvestigationAccelerometer, Integer> getInvestigationAccelerometerDao() throws SQLException {
		if (InvestigationAccelerometerDao == null) {
			InvestigationAccelerometerDao = getDao(InvestigationAccelerometer.class);
		}
		return InvestigationAccelerometerDao;
	}	
public Dao<InvestigationCamera, Integer> getInvestigationCameraDao() throws SQLException {
		if (InvestigationCameraDao == null) {
			InvestigationCameraDao = getDao(InvestigationCamera.class);
		}
		return InvestigationCameraDao;
	}	
public Dao<InvestigationDatabase128, Integer> getInvestigationDatabase128Dao() throws SQLException {
		if (InvestigationDatabase128Dao == null) {
			InvestigationDatabase128Dao = getDao(InvestigationDatabase128.class);
		}
		return InvestigationDatabase128Dao;
	}	
public Dao<InvestigationDatabase2048, Integer> getInvestigationDatabase2048Dao() throws SQLException {
		if (InvestigationDatabase2048Dao == null) {
			InvestigationDatabase2048Dao = getDao(InvestigationDatabase2048.class);
		}
		return InvestigationDatabase2048Dao;
	}	
public Dao<InvestigationFileSystem, Integer> getInvestigationFileSystemDao() throws SQLException {
		if (InvestigationFileSystemDao == null) {
			InvestigationFileSystemDao = getDao(InvestigationFileSystem.class);
		}
		return InvestigationFileSystemDao;
	}	
public Dao<InvestigationGeolocation, Integer> getInvestigationGeolocationDao() throws SQLException {
		if (InvestigationGeolocationDao == null) {
			InvestigationGeolocationDao = getDao(InvestigationGeolocation.class);
		}
		return InvestigationGeolocationDao;
	}	
public Dao<__ReturnStepStack, Integer> get__ReturnStepStackDao() throws SQLException {
		if (__ReturnStepStackDao == null) {
			__ReturnStepStackDao = getDao(__ReturnStepStack.class);
		}
		return __ReturnStepStackDao;
	}	
public Dao<__ProcessChainControllerState, Integer> get__ProcessChainControllerStateDao() throws SQLException {
		if (__ProcessChainControllerStateDao == null) {
			__ProcessChainControllerStateDao = getDao(__ProcessChainControllerState.class);
		}
		return __ProcessChainControllerStateDao;
	}	
	
 
 public <T extends Md2Entity> Dao<T, Integer> getDaoByName(String entity){
 final String entityType= entity;
 try{
 switch(entityType){
 case "InvestigationAccelerometer": 	if (InvestigationAccelerometerDao == null) {
 			InvestigationAccelerometerDao =  getDao(InvestigationAccelerometer.class);
 		}
 		return (Dao<T, Integer>) InvestigationAccelerometerDao;
 case "InvestigationCamera": 	if (InvestigationCameraDao == null) {
 			InvestigationCameraDao =  getDao(InvestigationCamera.class);
 		}
 		return (Dao<T, Integer>) InvestigationCameraDao;
 case "InvestigationDatabase128": 	if (InvestigationDatabase128Dao == null) {
 			InvestigationDatabase128Dao =  getDao(InvestigationDatabase128.class);
 		}
 		return (Dao<T, Integer>) InvestigationDatabase128Dao;
 case "InvestigationDatabase2048": 	if (InvestigationDatabase2048Dao == null) {
 			InvestigationDatabase2048Dao =  getDao(InvestigationDatabase2048.class);
 		}
 		return (Dao<T, Integer>) InvestigationDatabase2048Dao;
 case "InvestigationFileSystem": 	if (InvestigationFileSystemDao == null) {
 			InvestigationFileSystemDao =  getDao(InvestigationFileSystem.class);
 		}
 		return (Dao<T, Integer>) InvestigationFileSystemDao;
 case "InvestigationGeolocation": 	if (InvestigationGeolocationDao == null) {
 			InvestigationGeolocationDao =  getDao(InvestigationGeolocation.class);
 		}
 		return (Dao<T, Integer>) InvestigationGeolocationDao;
 case "__ReturnStepStack": 	if (__ReturnStepStackDao == null) {
 			__ReturnStepStackDao =  getDao(__ReturnStepStack.class);
 		}
 		return (Dao<T, Integer>) __ReturnStepStackDao;
 case "__ProcessChainControllerState": 	if (__ProcessChainControllerStateDao == null) {
 			__ProcessChainControllerStateDao =  getDao(__ProcessChainControllerState.class);
 		}
 		return (Dao<T, Integer>) __ProcessChainControllerStateDao;
 default: return null;	
 } 	}
 catch(SQLException e){
 e.printStackTrace();	
 return null;
 }
 
 }
 
 

}
