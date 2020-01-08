// generated in de.wwu.md2.framework.generator.android.lollipop.model.SQLite.generateSQLiteHelper()
package md2.investigation.md2.model.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import md2.investigation.md2.model.sqlite.Md2DataContract.InvestigationAccelerometerEntry;
import md2.investigation.md2.model.sqlite.Md2DataContract.InvestigationCameraEntry;
import md2.investigation.md2.model.sqlite.Md2DataContract.InvestigationDatabase128Entry;
import md2.investigation.md2.model.sqlite.Md2DataContract.InvestigationDatabase2048Entry;
import md2.investigation.md2.model.sqlite.Md2DataContract.InvestigationFileSystemEntry;
import md2.investigation.md2.model.sqlite.Md2DataContract.InvestigationGeolocationEntry;
import md2.investigation.md2.model.sqlite.Md2DataContract.__ReturnStepStackEntry;
import md2.investigation.md2.model.sqlite.Md2DataContract.__ProcessChainControllerStateEntry;

import de.wwu.md2.android.md2library. model.dataStore.interfaces.Md2SQLiteHelper;

public class Md2SQLiteHelperImpl extends SQLiteOpenHelper implements Md2SQLiteHelper {
	private static final String DATABASE_NAME = "einkaufszettelapp.db";
	private static final int DATABASE_VERSION = (int) 1.0;
	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_INVESTIGATIONACCELEROMETER_ENTRIES =
		"CREATE TABLE " + InvestigationAccelerometerEntry.TABLE_NAME + " (" +
		InvestigationAccelerometerEntry._ID + " INTEGER PRIMARY KEY" +
		COMMA_SEP +
		InvestigationAccelerometerEntry.COLUMN_NAME_ACCELEROMETERVALUEX + TEXT_TYPE + COMMA_SEP +
		InvestigationAccelerometerEntry.COLUMN_NAME_ACCELEROMETERVALUEY + TEXT_TYPE + COMMA_SEP +
		InvestigationAccelerometerEntry.COLUMN_NAME_ACCELEROMETERVALUEZ
 + TEXT_TYPE +		" )";
								
		private static final String SQL_DELETE_INVESTIGATIONACCELEROMETER_ENTRIES =
			"DROP TABLE IF EXISTS " + InvestigationAccelerometerEntry.TABLE_NAME;
						
		private final String[] allInvestigationAccelerometerColumns = {
		InvestigationAccelerometerEntry.COLUMN_NAME_ACCELEROMETERVALUEX, 
		InvestigationAccelerometerEntry.COLUMN_NAME_ACCELEROMETERVALUEY, 
		InvestigationAccelerometerEntry.COLUMN_NAME_ACCELEROMETERVALUEZ
		};
	private static final String SQL_CREATE_INVESTIGATIONCAMERA_ENTRIES =
		"CREATE TABLE " + InvestigationCameraEntry.TABLE_NAME + " (" +
		InvestigationCameraEntry._ID + " INTEGER PRIMARY KEY" +
		COMMA_SEP +
		InvestigationCameraEntry.COLUMN_NAME_ACCELEROMETERVALUEX
 + TEXT_TYPE +		" )";
								
		private static final String SQL_DELETE_INVESTIGATIONCAMERA_ENTRIES =
			"DROP TABLE IF EXISTS " + InvestigationCameraEntry.TABLE_NAME;
						
		private final String[] allInvestigationCameraColumns = {
		InvestigationCameraEntry.COLUMN_NAME_ACCELEROMETERVALUEX
		};
	private static final String SQL_CREATE_INVESTIGATIONDATABASE128_ENTRIES =
		"CREATE TABLE " + InvestigationDatabase128Entry.TABLE_NAME + " (" +
		InvestigationDatabase128Entry._ID + " INTEGER PRIMARY KEY" +
		COMMA_SEP +
		InvestigationDatabase128Entry.COLUMN_NAME_DATABASEVALUE128
 + TEXT_TYPE +		" )";
								
		private static final String SQL_DELETE_INVESTIGATIONDATABASE128_ENTRIES =
			"DROP TABLE IF EXISTS " + InvestigationDatabase128Entry.TABLE_NAME;
						
		private final String[] allInvestigationDatabase128Columns = {
		InvestigationDatabase128Entry.COLUMN_NAME_DATABASEVALUE128
		};
	private static final String SQL_CREATE_INVESTIGATIONDATABASE2048_ENTRIES =
		"CREATE TABLE " + InvestigationDatabase2048Entry.TABLE_NAME + " (" +
		InvestigationDatabase2048Entry._ID + " INTEGER PRIMARY KEY" +
		COMMA_SEP +
		InvestigationDatabase2048Entry.COLUMN_NAME_DATABASEVALUE2048
 + TEXT_TYPE +		" )";
								
		private static final String SQL_DELETE_INVESTIGATIONDATABASE2048_ENTRIES =
			"DROP TABLE IF EXISTS " + InvestigationDatabase2048Entry.TABLE_NAME;
						
		private final String[] allInvestigationDatabase2048Columns = {
		InvestigationDatabase2048Entry.COLUMN_NAME_DATABASEVALUE2048
		};
	private static final String SQL_CREATE_INVESTIGATIONFILESYSTEM_ENTRIES =
		"CREATE TABLE " + InvestigationFileSystemEntry.TABLE_NAME + " (" +
		InvestigationFileSystemEntry._ID + " INTEGER PRIMARY KEY" +
		COMMA_SEP +
		InvestigationFileSystemEntry.COLUMN_NAME_FILEVALUE
 + TEXT_TYPE +		" )";
								
		private static final String SQL_DELETE_INVESTIGATIONFILESYSTEM_ENTRIES =
			"DROP TABLE IF EXISTS " + InvestigationFileSystemEntry.TABLE_NAME;
						
		private final String[] allInvestigationFileSystemColumns = {
		InvestigationFileSystemEntry.COLUMN_NAME_FILEVALUE
		};
	private static final String SQL_CREATE_INVESTIGATIONGEOLOCATION_ENTRIES =
		"CREATE TABLE " + InvestigationGeolocationEntry.TABLE_NAME + " (" +
		InvestigationGeolocationEntry._ID + " INTEGER PRIMARY KEY" +
		COMMA_SEP +
		InvestigationGeolocationEntry.COLUMN_NAME_LOCATIONLONG + TEXT_TYPE + COMMA_SEP +
		InvestigationGeolocationEntry.COLUMN_NAME_LOCATIONLAT
 + TEXT_TYPE +		" )";
								
		private static final String SQL_DELETE_INVESTIGATIONGEOLOCATION_ENTRIES =
			"DROP TABLE IF EXISTS " + InvestigationGeolocationEntry.TABLE_NAME;
						
		private final String[] allInvestigationGeolocationColumns = {
		InvestigationGeolocationEntry.COLUMN_NAME_LOCATIONLONG, 
		InvestigationGeolocationEntry.COLUMN_NAME_LOCATIONLAT
		};
	private static final String SQL_CREATE___RETURNSTEPSTACK_ENTRIES =
		"CREATE TABLE " + __ReturnStepStackEntry.TABLE_NAME + " (" +
		__ReturnStepStackEntry._ID + " INTEGER PRIMARY KEY" +
		COMMA_SEP +
		__ReturnStepStackEntry.COLUMN_NAME_RETURNSTEP + TEXT_TYPE + COMMA_SEP +
		__ReturnStepStackEntry.COLUMN_NAME_RETURNANDREVERSESTEP + TEXT_TYPE + COMMA_SEP +
		__ReturnStepStackEntry.COLUMN_NAME_RETURNANDPROCEEDSTEP + TEXT_TYPE + COMMA_SEP +
		__ReturnStepStackEntry.COLUMN_NAME_TAIL
 + TEXT_TYPE +		" )";
								
		private static final String SQL_DELETE___RETURNSTEPSTACK_ENTRIES =
			"DROP TABLE IF EXISTS " + __ReturnStepStackEntry.TABLE_NAME;
						
		private final String[] all__ReturnStepStackColumns = {
		__ReturnStepStackEntry.COLUMN_NAME_RETURNSTEP, 
		__ReturnStepStackEntry.COLUMN_NAME_RETURNANDREVERSESTEP, 
		__ReturnStepStackEntry.COLUMN_NAME_RETURNANDPROCEEDSTEP, 
		__ReturnStepStackEntry.COLUMN_NAME_TAIL
		};
	private static final String SQL_CREATE___PROCESSCHAINCONTROLLERSTATE_ENTRIES =
		"CREATE TABLE " + __ProcessChainControllerStateEntry.TABLE_NAME + " (" +
		__ProcessChainControllerStateEntry._ID + " INTEGER PRIMARY KEY" +
		COMMA_SEP +
		__ProcessChainControllerStateEntry.COLUMN_NAME_CURRENTPROCESSCHAINSTEP + TEXT_TYPE + COMMA_SEP +
		__ProcessChainControllerStateEntry.COLUMN_NAME_LASTEVENTFIRED
 + TEXT_TYPE +		" )";
								
		private static final String SQL_DELETE___PROCESSCHAINCONTROLLERSTATE_ENTRIES =
			"DROP TABLE IF EXISTS " + __ProcessChainControllerStateEntry.TABLE_NAME;
						
		private final String[] all__ProcessChainControllerStateColumns = {
		__ProcessChainControllerStateEntry.COLUMN_NAME_CURRENTPROCESSCHAINSTEP, 
		__ProcessChainControllerStateEntry.COLUMN_NAME_LASTEVENTFIRED
		};

   public Md2SQLiteHelperImpl(Context context) {
       super(context, DATABASE_NAME, null, DATABASE_VERSION);
   }


    @Override
    public void onCreate(SQLiteDatabase database) {
    	database.execSQL(SQL_CREATE_INVESTIGATIONACCELEROMETER_ENTRIES);
    	database.execSQL(SQL_CREATE_INVESTIGATIONCAMERA_ENTRIES);
    	database.execSQL(SQL_CREATE_INVESTIGATIONDATABASE128_ENTRIES);
    	database.execSQL(SQL_CREATE_INVESTIGATIONDATABASE2048_ENTRIES);
    	database.execSQL(SQL_CREATE_INVESTIGATIONFILESYSTEM_ENTRIES);
    	database.execSQL(SQL_CREATE_INVESTIGATIONGEOLOCATION_ENTRIES);
    	database.execSQL(SQL_CREATE___RETURNSTEPSTACK_ENTRIES);
    	database.execSQL(SQL_CREATE___PROCESSCHAINCONTROLLERSTATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	db.execSQL(SQL_DELETE_INVESTIGATIONACCELEROMETER_ENTRIES);
    	db.execSQL(SQL_DELETE_INVESTIGATIONCAMERA_ENTRIES);
    	db.execSQL(SQL_DELETE_INVESTIGATIONDATABASE128_ENTRIES);
    	db.execSQL(SQL_DELETE_INVESTIGATIONDATABASE2048_ENTRIES);
    	db.execSQL(SQL_DELETE_INVESTIGATIONFILESYSTEM_ENTRIES);
    	db.execSQL(SQL_DELETE_INVESTIGATIONGEOLOCATION_ENTRIES);
    	db.execSQL(SQL_DELETE___RETURNSTEPSTACK_ENTRIES);
    	db.execSQL(SQL_DELETE___PROCESSCHAINCONTROLLERSTATE_ENTRIES);
        onCreate(db);		
    }

    @Override
    public SQLiteDatabase open(boolean writeAccess) {
        return (writeAccess) ? this.getWritableDatabase() : this.getReadableDatabase();
    }

    @Override
    public String[] getAllColumns(String typeName) {
        switch (typeName) {
        	case "InvestigationAccelerometer": {
        		return this.allInvestigationAccelerometerColumns;
        	}
        	case "InvestigationCamera": {
        		return this.allInvestigationCameraColumns;
        	}
        	case "InvestigationDatabase128": {
        		return this.allInvestigationDatabase128Columns;
        	}
        	case "InvestigationDatabase2048": {
        		return this.allInvestigationDatabase2048Columns;
        	}
        	case "InvestigationFileSystem": {
        		return this.allInvestigationFileSystemColumns;
        	}
        	case "InvestigationGeolocation": {
        		return this.allInvestigationGeolocationColumns;
        	}
        	case "__ReturnStepStack": {
        		return this.all__ReturnStepStackColumns;
        	}
        	case "__ProcessChainControllerState": {
        		return this.all__ProcessChainControllerStateColumns;
        	}
        }
        return null;
    }
}
