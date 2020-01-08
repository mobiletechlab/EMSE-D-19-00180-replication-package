// generated in de.wwu.md2.framework.generator.android.lollipop.model.Md2Entity.generateEntity()
package md2.investigation.md2.model;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;
import de.wwu.md2.android.md2library.model.type.interfaces.Md2Entity;
import de.wwu.md2.android.md2library.model.type.interfaces.Md2Type;
import de.wwu.md2.android.md2library.model.type.implementation.Md2String;
import de.wwu.md2.android.md2library.model.type.implementation.Md2List;

@DatabaseTable(tableName = "investigationDatabase2048")
public class InvestigationDatabase2048 implements Serializable, Md2Entity{

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

	protected final String typeName = "InvestigationDatabase2048";

@Expose
@DatabaseField(columnName = "databaseValue2048", dataType = DataType.SERIALIZABLE)
//@ForeignCollectionField
private String[] databaseValue2048;



	public InvestigationDatabase2048() {
		super();
		this.setModifiedDate(new Timestamp(System.currentTimeMillis()));
	}
	

	@Override
	public Md2Type clone() {
		InvestigationDatabase2048 result = new InvestigationDatabase2048();
	
		result.setDatabaseValue2048(this.getDatabaseValue2048());
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
			case "databaseValue2048":
				ArrayList<Md2String> list = new ArrayList<Md2String>();
				if(databaseValue2048 == null || databaseValue2048.length == 0) return new Md2List<Md2String>(new ArrayList<Md2String>());
				for (String elem : databaseValue2048) {
					list.add(new Md2String(elem));
				}
				return new Md2List<Md2String>(list);
		}
		return null;
	}
	
	@Override
	public void set(String s, Md2Type md2Type) {
		switch(s) {
			// TODO Collections, TemporalTypes
		case "databaseValue2048": {
			if(md2Type instanceof Md2List && ((Md2List) md2Type).getContents().get(0) != null && ((Md2List) md2Type).getContents().get(0) instanceof Md2String) {
				ArrayList<String> databaseValue2048dyn = new ArrayList<String>();
				for (Object elem : ((Md2List) md2Type).getContents()) {
					databaseValue2048dyn.add(elem.toString());
				}
				databaseValue2048 = databaseValue2048dyn.toArray(new String[databaseValue2048dyn.size()]);
			}
		}
		//TODO setDatabaseValue2048(((Md2String) md2Type).getPlatformValue());
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

	public List<Md2String> getDatabaseValue2048(){
		ArrayList<Md2String> list = new ArrayList<Md2String>();
		if(this.databaseValue2048 == null) return list;
		for(String s : this.databaseValue2048){
			list.add(new Md2String(s));
		}
		return list;
	}

	public void setDatabaseValue2048(List<Md2String> databaseValue2048 ){
		ArrayList<String> databaseValue2048dyn= new ArrayList<String>();
		for(Md2String s : databaseValue2048){
			databaseValue2048dyn.add(s.getPlatformValue());
		}
		this.databaseValue2048 = databaseValue2048dyn.toArray(new String[databaseValue2048dyn.size()]);
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append(this.getTypeName() + ": (");
	result.append(this.databaseValue2048 + " ");

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
		} else if(!(value instanceof InvestigationDatabase2048)) {
			return false;
		} else {
			InvestigationDatabase2048 md2EntityValue = (InvestigationDatabase2048)value;
			boolean b = true;
			if(this.databaseValue2048== null) {
				b &= ((InvestigationDatabase2048) md2EntityValue).getDatabaseValue2048() == null;	
			} else {
				b &= this.databaseValue2048.equals(((InvestigationDatabase2048) md2EntityValue).getDatabaseValue2048()) ;
			}

			return b;
		}
	}
}
