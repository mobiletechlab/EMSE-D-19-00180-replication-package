// generated in de.wwu.md2.framework.generator.android.lollipop.model.Md2Entity.generateEntity()
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
import java.util.Calendar;
import java.util.Collection;
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

@DatabaseTable(tableName = "investigationDatabase128")
public class InvestigationDatabase128 implements Serializable, Md2Entity{

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

	protected final String typeName = "InvestigationDatabase128";

@Expose
@DatabaseField(columnName = "databaseValue128", dataType = DataType.SERIALIZABLE)
//@ForeignCollectionField
private String[] databaseValue128;



	public InvestigationDatabase128() {
		super();
		this.setModifiedDate(new Timestamp(System.currentTimeMillis()));
	}
	

	@Override
	public Md2Type clone() {
		InvestigationDatabase128 result = new InvestigationDatabase128();
	
		result.setDatabaseValue128(this.getDatabaseValue128());
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
		case "databaseValue128": {
			ArrayList<Md2String> list = new ArrayList<Md2String>();
			if(databaseValue128 == null || databaseValue128.length == 0) return new Md2List<Md2String>(new ArrayList<Md2String>());
			for (String elem : databaseValue128) {
				list.add(new Md2String(elem));
			}
			return new Md2List<Md2String>(list);
			}
			//TODO return new  ArrayList<Md2String>(getDatabaseValue128());
		}
		return null;
	}
	
	@Override
	public void set(String s, Md2Type md2Type) {
		switch(s) {
			// TODO Collections, TemporalTypes
		case "databaseValue128":
		{
			if(md2Type instanceof Md2List && ((Md2List) md2Type).getContents().get(0) != null && ((Md2List) md2Type).getContents().get(0) instanceof Md2String) {
				ArrayList<String> databaseValue128dyn = new ArrayList<String>();
				for (Object elem : ((Md2List) md2Type).getContents()) {
					databaseValue128dyn.add(elem.toString());
				}
				databaseValue128 = databaseValue128dyn.toArray(new String[databaseValue128dyn.size()]);
			}
		}
		//setDatabaseValue128(((Md2String) md2Type).getPlatformValue());
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

public List<Md2String> getDatabaseValue128(){
	ArrayList<Md2String> list = new ArrayList<Md2String>();
	if(this.databaseValue128 == null) return list;
	for(String s : this.databaseValue128){
		list.add(new Md2String(s));
	}
	return list;
}	

public void setDatabaseValue128(List<Md2String> databaseValue128 ){
	ArrayList<String> databaseValue128dyn= new ArrayList<String>();
	for(Md2String s : databaseValue128){
		databaseValue128dyn.add(s.getPlatformValue());
	}
	this.databaseValue128 = databaseValue128dyn.toArray(new String[databaseValue128dyn.size()]);
}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append(this.getTypeName() + ": (");
	result.append(this.databaseValue128 + " ");

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
		} else if(!(value instanceof InvestigationDatabase128)) {
			return false;
		} else {
			InvestigationDatabase128 md2EntityValue = (InvestigationDatabase128)value;
			boolean b = true;
			if(this.databaseValue128== null) {
				b &= ((InvestigationDatabase128) md2EntityValue).getDatabaseValue128() == null;	
			} else {
				b &= this.databaseValue128.equals(((InvestigationDatabase128) md2EntityValue).getDatabaseValue128()) ;
			}

			return b;
		}
	}
}
