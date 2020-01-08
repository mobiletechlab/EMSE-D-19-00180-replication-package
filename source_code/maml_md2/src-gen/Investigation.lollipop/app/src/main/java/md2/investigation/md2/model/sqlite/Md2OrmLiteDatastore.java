package md2.investigation.md2.model.sqlite;
//package md2.investigation.EinkaufszettelApp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.text.SimpleDateFormat;

import de.wwu.md2.android.md2library.model.dataStore.AtomicExpression;
import de.wwu.md2.android.md2library.model.dataStore.CombinedExpression;
import de.wwu.md2.android.md2library.model.dataStore.Expression;
import de.wwu.md2.android.md2library.model.dataStore.implementation.AbstractMd2OrmLiteDatastore;
import de.wwu.md2.android.md2library.model.type.interfaces.Md2Entity;

import md2.investigation.EinkaufszettelApp;

public class Md2OrmLiteDatastore<T extends Md2Entity> extends AbstractMd2OrmLiteDatastore<T> {
	
	private String entityType;
	private DatabaseHelper databaseHelper;
	
	Dao<T , Integer> myDao;
	   private  SimpleDateFormat simpleDateFormat;

	public Md2OrmLiteDatastore(String entity){
	this.entityType=entity;
		initDatabaseHelper(EinkaufszettelApp.getAppContext());
		 this.simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	}
	
	public void initDatabaseHelper(Context context){
		databaseHelper = OpenHelperManager.getHelper(context,DatabaseHelper.class);
		//SQLiteDatabase db = databaseHelper.getWritableDatabase();
		/*Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

		if (c.moveToFirst()) {
			while ( !c.isAfterLast() ) {
				Log.d("DB", "Table Name=> "+c.getString(0));
				c.moveToNext();
			}
		}*/
	}
	
	public DatabaseHelper getHelper() {
		return databaseHelper;
	}
	
	public Dao<T, Integer> getMyDao(){
		if(myDao==null) {
			myDao= this.getHelper().getDaoByName(entityType);
		}
		return myDao;	   
	}
	
	public List<T> loadAll(){
		List<T> all= new ArrayList<T>();
		try {
		   all=  getMyDao().queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}
	
	@Override
	public Where<T, Integer> whereBuilder(Expression exp, Where<T, Integer> where ){
		if (exp != null) {
	
			if (exp instanceof AtomicExpression) {
				AtomicExpression aexp = (AtomicExpression) exp;
			  switch (aexp.getOperator()) {
				  case EQUAL:
					  try {
						  where.eq((aexp).getLeftOperand(), aexp.getRightOperand());
					  } catch (SQLException e) {
						  e.printStackTrace();
					  }
					  break;
				  case GREATEREQUAL:
					  try {
						  where.ge((aexp).getLeftOperand(), aexp.getRightOperand());
					  } catch (SQLException e) {
						  e.printStackTrace();
					  }
					  break;
				  case LESSEQUAL:
					  try {
						  where.le((aexp).getLeftOperand(), aexp.getRightOperand());
					  } catch (SQLException e) {
						  e.printStackTrace();
					  }
					  break;
				  case LESS:
					  try {
						  where.lt((aexp).getLeftOperand(), aexp.getRightOperand());
					  } catch (SQLException e) {
						  e.printStackTrace();
					  }
					  break;
				  case GREATER:
					  try {
						  where.gt((aexp).getLeftOperand(), aexp.getRightOperand());
					  } catch (SQLException e) {
						  e.printStackTrace();
					  }
					  break;
				}
			} else {
				CombinedExpression cexp = (CombinedExpression) exp;
				whereBuilder(cexp.getLeftExpression(),where);
				whereBuilder(cexp.getRightExpression(),where);
				switch (cexp.getJunction()) {
					case AND:
						where.and(2);
						break;
					case OR:
						where.or(2);
						break;
				}
			}
		}
		return where;
	}
}
