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

@DatabaseTable(tableName = "investigationAccelerometer")
public class InvestigationAccelerometer implements Serializable, Md2Entity{

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

	protected final String typeName = "InvestigationAccelerometer";

@Expose
@DatabaseField(columnName = "accelerometerValueX")
private Float accelerometerValueX;

@Expose
@DatabaseField(columnName = "accelerometerValueY")
private Float accelerometerValueY;

@Expose
@DatabaseField(columnName = "accelerometerValueZ")
private Float accelerometerValueZ;



	public InvestigationAccelerometer() {
		super();
		this.setModifiedDate(new Timestamp(System.currentTimeMillis()));
	}
	

	@Override
	public Md2Type clone() {
		InvestigationAccelerometer result = new InvestigationAccelerometer();
	
	result.setAccelerometerValueX(this.getAccelerometerValueX());
	result.setAccelerometerValueY(this.getAccelerometerValueY());
	result.setAccelerometerValueZ(this.getAccelerometerValueZ());
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
		case "accelerometerValueX": 
		return new Md2Float(getAccelerometerValueX());
		case "accelerometerValueY": 
		return new Md2Float(getAccelerometerValueY());
		case "accelerometerValueZ": 
		return new Md2Float(getAccelerometerValueZ());
		}
		return null;
	}
	
	@Override
	public void set(String s, Md2Type md2Type) {
		switch(s) {
			// TODO Collections, TemporalTypes
		case "accelerometerValueX": 
		setAccelerometerValueX(((Md2Float) md2Type).getPlatformValue();
		case "accelerometerValueY": 
		setAccelerometerValueY(((Md2Float) md2Type).getPlatformValue();
		case "accelerometerValueZ": 
		setAccelerometerValueZ(((Md2Float) md2Type).getPlatformValue();
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

public Float getAccelerometerValueX(){
	return this.accelerometerValueX;	
}

public void setAccelerometerValueX(Float accelerometerValueX ){
	this.accelerometerValueX=accelerometerValueX;
	this.setModifiedDate(new Timestamp(System.currentTimeMillis()));
}
public Float getAccelerometerValueY(){
	return this.accelerometerValueY;	
}

public void setAccelerometerValueY(Float accelerometerValueY ){
	this.accelerometerValueY=accelerometerValueY;
	this.setModifiedDate(new Timestamp(System.currentTimeMillis()));
}
public Float getAccelerometerValueZ(){
	return this.accelerometerValueZ;	
}

public void setAccelerometerValueZ(Float accelerometerValueZ ){
	this.accelerometerValueZ=accelerometerValueZ;
	this.setModifiedDate(new Timestamp(System.currentTimeMillis()));
}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append(this.getTypeName() + ": (");
	result.append(this.accelerometerValueX + " ");
	result.append(this.accelerometerValueY + " ");
	result.append(this.accelerometerValueZ + " ");

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
		} else if(!(value instanceof InvestigationAccelerometer)) {
			return false;
		} else {
			InvestigationAccelerometer md2EntityValue = (InvestigationAccelerometer)value;
			boolean b = true;
			if(this.accelerometerValueX== null) {
				b &= ((InvestigationAccelerometer) md2EntityValue).getAccelerometerValueX() == null;	
			} else {
				b &= this.accelerometerValueX.equals(((InvestigationAccelerometer) md2EntityValue).getAccelerometerValueX()) ;
			}
			if(this.accelerometerValueY== null) {
				b &= ((InvestigationAccelerometer) md2EntityValue).getAccelerometerValueY() == null;	
			} else {
				b &= this.accelerometerValueY.equals(((InvestigationAccelerometer) md2EntityValue).getAccelerometerValueY()) ;
			}
			if(this.accelerometerValueZ== null) {
				b &= ((InvestigationAccelerometer) md2EntityValue).getAccelerometerValueZ() == null;	
			} else {
				b &= this.accelerometerValueZ.equals(((InvestigationAccelerometer) md2EntityValue).getAccelerometerValueZ()) ;
			}

			return b;
		}
	}
}
