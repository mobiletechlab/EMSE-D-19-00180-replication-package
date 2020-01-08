// generated in de.wwu.md2.framework.generator.android.wearable.model.Md2Entity.generateEntity()
package md2.investigation.md2.model;

import md2.investigation.md2.model.InvestigationAccelerometer;	
import md2.investigation.md2.model.InvestigationCamera;	
import md2.investigation.md2.model.InvestigationDatabase128;	
import md2.investigation.md2.model.InvestigationDatabase2048;	
import md2.investigation.md2.model.InvestigationFileSystem;	
import md2.investigation.md2.model.InvestigationGeolocation;	
import md2.investigation.md2.model.__ReturnStepStack;	
import md2.investigation.md2.model.__ProcessChainControllerState;	

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Calendar;
import java.util.ArrayList;
import java.io.Serializable;
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

@DatabaseTable(tableName = "__ProcessChainControllerState")
public class __ProcessChainControllerState implements Serializable, Md2Entity{

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

	protected final String typeName = "__ProcessChainControllerState";

@Expose
@DatabaseField(columnName = "currentProcessChainStep")
private String currentProcessChainStep;

@Expose
@DatabaseField(columnName = "lastEventFired")
private String lastEventFired;



	public __ProcessChainControllerState() {
		super();
		this.setModifiedDate(new Timestamp(System.currentTimeMillis()));
	}
	

	@Override
	public Md2Type clone() {
		__ProcessChainControllerState result = new __ProcessChainControllerState();
	
	result.setCurrentProcessChainStep(this.getCurrentProcessChainStep());
	result.setLastEventFired(this.getLastEventFired());
		return result;
	}


	@Override
	public Md2String getString() {
		return new Md2String(this.toString());
	}

	@Override
	public Md2Type get(String s) {
		switch(s) {
			// TODO Collections
		case "currentProcessChainStep": 
		return new Md2String(getCurrentProcessChainStep());
		case "lastEventFired": 
		return new Md2String(getLastEventFired());
		}
		return null;
	}
	
	@Override
	public void set(String s, Md2Type md2Type) {
		switch(s) {
			// TODO Collections, TemporalTypes
		case "currentProcessChainStep": 
		setCurrentProcessChainStep(((Md2String) md2Type).getPlatformValue();
		case "lastEventFired": 
		setLastEventFired(((Md2String) md2Type).getPlatformValue();
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

public String getCurrentProcessChainStep(){
	return this.currentProcessChainStep;	
}

public void setCurrentProcessChainStep(String currentProcessChainStep ){
	this.currentProcessChainStep=currentProcessChainStep;
	this.setModifiedDate(new Timestamp(System.currentTimeMillis()));
}
public String getLastEventFired(){
	return this.lastEventFired;	
}

public void setLastEventFired(String lastEventFired ){
	this.lastEventFired=lastEventFired;
	this.setModifiedDate(new Timestamp(System.currentTimeMillis()));
}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append(this.getTypeName() + ": (");
	result.append(this.currentProcessChainStep + " ");
	result.append(this.lastEventFired + " ");

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
		} else if(!(value instanceof __ProcessChainControllerState)) {
			return false;
		} else {
			__ProcessChainControllerState md2EntityValue = (__ProcessChainControllerState)value;
			boolean b = true;
			if(this.currentProcessChainStep== null) {
				b &= ((__ProcessChainControllerState) md2EntityValue).getCurrentProcessChainStep() == null;	
			} else {
				b &= this.currentProcessChainStep.equals(((__ProcessChainControllerState) md2EntityValue).getCurrentProcessChainStep()) ;
			}
			if(this.lastEventFired== null) {
				b &= ((__ProcessChainControllerState) md2EntityValue).getLastEventFired() == null;	
			} else {
				b &= this.lastEventFired.equals(((__ProcessChainControllerState) md2EntityValue).getLastEventFired()) ;
			}

			return b;
		}
	}
}
