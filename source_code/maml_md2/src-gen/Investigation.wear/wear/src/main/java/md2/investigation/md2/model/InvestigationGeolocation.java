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

@DatabaseTable(tableName = "investigationGeolocation")
public class InvestigationGeolocation implements Serializable, Md2Entity{

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

	protected final String typeName = "InvestigationGeolocation";

@Expose
@DatabaseField(columnName = "locationLong")
private String locationLong;

@Expose
@DatabaseField(columnName = "locationLat")
private String locationLat;



	public InvestigationGeolocation() {
		super();
		this.setModifiedDate(new Timestamp(System.currentTimeMillis()));
	}
	

	@Override
	public Md2Type clone() {
		InvestigationGeolocation result = new InvestigationGeolocation();
	
	result.setLocationLong(this.getLocationLong());
	result.setLocationLat(this.getLocationLat());
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
		case "locationLong": 
		return new Md2String(getLocationLong());
		case "locationLat": 
		return new Md2String(getLocationLat());
		}
		return null;
	}
	
	@Override
	public void set(String s, Md2Type md2Type) {
		switch(s) {
			// TODO Collections, TemporalTypes
		case "locationLong": 
		setLocationLong(((Md2String) md2Type).getPlatformValue();
		case "locationLat": 
		setLocationLat(((Md2String) md2Type).getPlatformValue();
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

public String getLocationLong(){
	return this.locationLong;	
}

public void setLocationLong(String locationLong ){
	this.locationLong=locationLong;
	this.setModifiedDate(new Timestamp(System.currentTimeMillis()));
}
public String getLocationLat(){
	return this.locationLat;	
}

public void setLocationLat(String locationLat ){
	this.locationLat=locationLat;
	this.setModifiedDate(new Timestamp(System.currentTimeMillis()));
}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append(this.getTypeName() + ": (");
	result.append(this.locationLong + " ");
	result.append(this.locationLat + " ");

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
		} else if(!(value instanceof InvestigationGeolocation)) {
			return false;
		} else {
			InvestigationGeolocation md2EntityValue = (InvestigationGeolocation)value;
			boolean b = true;
			if(this.locationLong== null) {
				b &= ((InvestigationGeolocation) md2EntityValue).getLocationLong() == null;	
			} else {
				b &= this.locationLong.equals(((InvestigationGeolocation) md2EntityValue).getLocationLong()) ;
			}
			if(this.locationLat== null) {
				b &= ((InvestigationGeolocation) md2EntityValue).getLocationLat() == null;	
			} else {
				b &= this.locationLat.equals(((InvestigationGeolocation) md2EntityValue).getLocationLat()) ;
			}

			return b;
		}
	}
}
