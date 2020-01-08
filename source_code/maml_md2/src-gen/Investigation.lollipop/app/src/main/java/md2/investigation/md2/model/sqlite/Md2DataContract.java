// generated in de.wwu.md2.framework.generator.android.lollipop.model.SQLite.generateDataContract()
package md2.investigation.md2.model.sqlite;

import android.provider.BaseColumns;

public class Md2DataContract {

	public Md2DataContract() {
	}

public static abstract class InvestigationAccelerometerEntry implements BaseColumns {
	public static final String TABLE_NAME = "investigationaccelerometer";
	public static final String COLUMN_NAME_ACCELEROMETERVALUEX = "accelerometerValueX";
	public static final String COLUMN_NAME_ACCELEROMETERVALUEY = "accelerometerValueY";
	public static final String COLUMN_NAME_ACCELEROMETERVALUEZ = "accelerometerValueZ";
}
public static abstract class InvestigationCameraEntry implements BaseColumns {
	public static final String TABLE_NAME = "investigationcamera";
	public static final String COLUMN_NAME_ACCELEROMETERVALUEX = "accelerometerValueX";
}
public static abstract class InvestigationDatabase128Entry implements BaseColumns {
	public static final String TABLE_NAME = "investigationdatabase128";
	public static final String COLUMN_NAME_DATABASEVALUE128 = "databaseValue128";
}
public static abstract class InvestigationDatabase2048Entry implements BaseColumns {
	public static final String TABLE_NAME = "investigationdatabase2048";
	public static final String COLUMN_NAME_DATABASEVALUE2048 = "databaseValue2048";
}
public static abstract class InvestigationFileSystemEntry implements BaseColumns {
	public static final String TABLE_NAME = "investigationfilesystem";
	public static final String COLUMN_NAME_FILEVALUE = "fileValue";
}
public static abstract class InvestigationGeolocationEntry implements BaseColumns {
	public static final String TABLE_NAME = "investigationgeolocation";
	public static final String COLUMN_NAME_LOCATIONLONG = "locationLong";
	public static final String COLUMN_NAME_LOCATIONLAT = "locationLat";
}
public static abstract class __ReturnStepStackEntry implements BaseColumns {
	public static final String TABLE_NAME = "__returnstepstack";
	public static final String COLUMN_NAME_RETURNSTEP = "returnStep";
	public static final String COLUMN_NAME_RETURNANDREVERSESTEP = "returnAndReverseStep";
	public static final String COLUMN_NAME_RETURNANDPROCEEDSTEP = "returnAndProceedStep";
	public static final String COLUMN_NAME_TAIL = "tail";
}
public static abstract class __ProcessChainControllerStateEntry implements BaseColumns {
	public static final String TABLE_NAME = "__processchaincontrollerstate";
	public static final String COLUMN_NAME_CURRENTPROCESSCHAINSTEP = "currentProcessChainStep";
	public static final String COLUMN_NAME_LASTEVENTFIRED = "lastEventFired";
}
}
