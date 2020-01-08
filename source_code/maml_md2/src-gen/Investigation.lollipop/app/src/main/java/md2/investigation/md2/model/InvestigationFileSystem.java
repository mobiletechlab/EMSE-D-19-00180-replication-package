// generated in de.wwu.md2.framework.generator.android.lollipop.model.Md2Entity.generateEntity()
package md2.investigation.md2.model;

import de.wwu.md2.android.md2library.model.type.implementation.Md2File;
import md2.investigation.md2.model.InvestigationAccelerometer;
import md2.investigation.md2.model.InvestigationCamera;	
import md2.investigation.md2.model.InvestigationDatabase128;	
import md2.investigation.md2.model.InvestigationDatabase2048;	
import md2.investigation.md2.model.InvestigationFileSystem;	
import md2.investigation.md2.model.InvestigationGeolocation;	
import md2.investigation.md2.model.__ReturnStepStack;	
import md2.investigation.md2.model.__ProcessChainControllerState;

import java.io.File;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;
import de.wwu.md2.android.md2library.model.type.interfaces.Md2Entity;
import de.wwu.md2.android.md2library.model.type.implementation.AbstractMd2Entity;
import de.wwu.md2.android.md2library.model.type.interfaces.Md2Type;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Boolean;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Date;
import de.wwu.md2.android.md2library.model.type.implementation.Md2DateTime;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Float;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Integer;
import de.wwu.md2.android.md2library.model.type.implementation.Md2String;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Time;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Sensor;
import de.wwu.md2.android.md2library.model.type.implementation.Md2List;

@DatabaseTable(tableName = "investigationFileSystem")
public class InvestigationFileSystem implements Serializable, Md2Entity{

	@SerializedName("__internalId")
	@Expose
	@DatabaseField(generatedId = true, columnName = "id")
	private long id;
	
	@Expose(serialize = false)
	private Timestamp modifiedDate;

	public Timestamp getModifiedDate(){
  		return this.modifiedDate;
  	}

  	public void setModifiedDate(Timestamp modified){
  		this.modifiedDate=modified;	
  	}

	protected final String typeName = "InvestigationFileSystem";

@Expose
@DatabaseField(columnName = "fileValue", dataType=DataType.SERIALIZABLE)
private File fileValue;



	public InvestigationFileSystem() {
		super();
		this.setModifiedDate(new Timestamp(System.currentTimeMillis()));
	}
	

	@Override
	public Md2Type clone() {
		InvestigationFileSystem result = new InvestigationFileSystem();
	
		result.setFileValue(this.getFileValue());
		return result;
	}

	@Override
	public Md2String getString() {
		return new Md2String((toString()));
	}

	@Override
	public Md2Type get(String s) {
		switch(s) {
			// TODO Collections
		case "fileValue": 
		return new Md2File(getFileValue());
		}
		return null;
	}
	
	@Override
	public void set(String s, Md2Type md2Type) {
		switch(s) {
			// TODO Collections, TemporalTypes
		case "fileValue": 
		setFileValue((File) ((Md2File) md2Type).getPlatformValue());
		}
	}

	@Override
	public HashMap<String, Md2Type> getAttributes() {
		return null; //TODO
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTypeName() {
		return this.typeName;
	}

public File getFileValue(){
	return this.fileValue;
}

public void setFileValue(File fileValue ){
	if(fileValue != null){
		this.fileValue=fileValue;
		this.setModifiedDate(new Timestamp(System.currentTimeMillis()));
	}
}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append(this.getTypeName() + ": (");
	result.append(this.fileValue + " ");

		return result.append(")").toString();
	}

	@Override
	public boolean equals(Md2Type t){
		return this.equals((Object)t);
	}

	@Override
	public boolean equals(Object value) {
		if(value == null) {
			return false;
		} else if(!(value instanceof InvestigationFileSystem)) {
			return false;
		} else {
			InvestigationFileSystem md2EntityValue = (InvestigationFileSystem)value;
			boolean b = true;
			if(this.fileValue== null) {
				b &= ((InvestigationFileSystem) md2EntityValue).getFileValue() == null;	
			} else {
				b &= this.fileValue.equals(((InvestigationFileSystem) md2EntityValue).getFileValue()) ;
			}

			return b;
		}
	}
}
