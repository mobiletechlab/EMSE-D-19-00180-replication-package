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

@DatabaseTable(tableName = "__ReturnStepStack")
public class __ReturnStepStack implements Serializable, Md2Entity{

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

	protected final String typeName = "__ReturnStepStack";

@Expose
@DatabaseField(columnName = "returnStep")
private String returnStep;

@Expose
@DatabaseField(columnName = "returnAndReverseStep")
private String returnAndReverseStep;

@Expose
@DatabaseField(columnName = "returnAndProceedStep")
private String returnAndProceedStep;

@DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
private __ReturnStepStack tail;



	public __ReturnStepStack() {
		super();
		this.setModifiedDate(new Timestamp(System.currentTimeMillis()));
	}
	

	@Override
	public Md2Type clone() {
		__ReturnStepStack result = new __ReturnStepStack();
	
	result.setReturnStep(this.getReturnStep());
	result.setReturnAndReverseStep(this.getReturnAndReverseStep());
	result.setReturnAndProceedStep(this.getReturnAndProceedStep());
	result.setTail(this.getTail());
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
		case "returnStep": 
		return new Md2String(getReturnStep());
		case "returnAndReverseStep": 
		return new Md2String(getReturnAndReverseStep());
		case "returnAndProceedStep": 
		return new Md2String(getReturnAndProceedStep());
		case "tail": 
		return getTail();
		}
		return null;
	}
	
	@Override
	public void set(String s, Md2Type md2Type) {
		switch(s) {
			// TODO Collections, TemporalTypes
		case "returnStep": 
		setReturnStep(((Md2String) md2Type).getPlatformValue();
		case "returnAndReverseStep": 
		setReturnAndReverseStep(((Md2String) md2Type).getPlatformValue();
		case "returnAndProceedStep": 
		setReturnAndProceedStep(((Md2String) md2Type).getPlatformValue();
		case "tail": 
		setTail(md2Type);
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

public String getReturnStep(){
	return this.returnStep;	
}

public void setReturnStep(String returnStep ){
	this.returnStep=returnStep;
	this.setModifiedDate(new Timestamp(System.currentTimeMillis()));
}
public String getReturnAndReverseStep(){
	return this.returnAndReverseStep;	
}

public void setReturnAndReverseStep(String returnAndReverseStep ){
	this.returnAndReverseStep=returnAndReverseStep;
	this.setModifiedDate(new Timestamp(System.currentTimeMillis()));
}
public String getReturnAndProceedStep(){
	return this.returnAndProceedStep;	
}

public void setReturnAndProceedStep(String returnAndProceedStep ){
	this.returnAndProceedStep=returnAndProceedStep;
	this.setModifiedDate(new Timestamp(System.currentTimeMillis()));
}
public __ReturnStepStack getTail(){
	return this.tail;	
}

public void setTail(__ReturnStepStack tail ){
	this.tail=tail;
	this.setModifiedDate(new Timestamp(System.currentTimeMillis()));
}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append(this.getTypeName() + ": (");
	result.append(this.returnStep + " ");
	result.append(this.returnAndReverseStep + " ");
	result.append(this.returnAndProceedStep + " ");
	result.append(this.tail + " ");

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
		} else if(!(value instanceof __ReturnStepStack)) {
			return false;
		} else {
			__ReturnStepStack md2EntityValue = (__ReturnStepStack)value;
			boolean b = true;
			if(this.returnStep== null) {
				b &= ((__ReturnStepStack) md2EntityValue).getReturnStep() == null;	
			} else {
				b &= this.returnStep.equals(((__ReturnStepStack) md2EntityValue).getReturnStep()) ;
			}
			if(this.returnAndReverseStep== null) {
				b &= ((__ReturnStepStack) md2EntityValue).getReturnAndReverseStep() == null;	
			} else {
				b &= this.returnAndReverseStep.equals(((__ReturnStepStack) md2EntityValue).getReturnAndReverseStep()) ;
			}
			if(this.returnAndProceedStep== null) {
				b &= ((__ReturnStepStack) md2EntityValue).getReturnAndProceedStep() == null;	
			} else {
				b &= this.returnAndProceedStep.equals(((__ReturnStepStack) md2EntityValue).getReturnAndProceedStep()) ;
			}
			if(this.tail== null) {
				b &= ((__ReturnStepStack) md2EntityValue).getTail() == null;	
			} else {
				b &= this.tail.equals(((__ReturnStepStack) md2EntityValue).getTail()) ;
			}

			return b;
		}
	}
}
