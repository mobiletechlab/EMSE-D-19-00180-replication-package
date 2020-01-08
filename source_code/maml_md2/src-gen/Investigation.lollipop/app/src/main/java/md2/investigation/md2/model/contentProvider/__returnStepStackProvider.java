// generated in de.wwu.md2.framework.generator.android.lollipop.model.Md2ContentProvider.generateContentProvider()
package md2.investigation.md2.model.contentProvider;

import md2.investigation.md2.model.__ReturnStepStack;		

import de.wwu.md2.android.md2library.controller.eventhandler.implementation.Md2OnAttributeChangedHandler;
import java.util.HashMap;
import java.util.Calendar;

import md2.investigation.R;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Boolean;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Date;
import de.wwu.md2.android.md2library.model.type.implementation.Md2DateTime;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Float;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Integer;
import de.wwu.md2.android.md2library.model.type.implementation.Md2String;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Time;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Sensor;
import de.wwu.md2.android.md2library.model.type.implementation.Md2List;
import de.wwu.md2.android.md2library.model.contentProvider.implementation.Md2ContentProviderRegistry;
import de.wwu.md2.android.md2library.view.management.implementation.Md2ViewManager;
		
import de.wwu.md2.android.md2library.model.type.interfaces.Md2Type;
import de.wwu.md2.android.md2library.model.contentProvider.implementation.AbstractMd2ContentProvider;
import de.wwu.md2.android.md2library.model.dataStore.interfaces.Md2LocalStore;
import de.wwu.md2.android.md2library.model.dataStore.interfaces.Md2DataStore;
import de.wwu.md2.android.md2library.model.type.interfaces.Md2Entity;
import de.wwu.md2.android.md2library.model.type.implementation.Md2List;
import de.wwu.md2.android.md2library.model.dataStore.AtomicExpression;
import de.wwu.md2.android.md2library.model.dataStore.Operator;
import de.wwu.md2.android.md2library.model.dataStore.Filter;
			
import md2.investigation.md2.model.__ReturnStepStack;

import de.wwu.md2.android.md2library.model.type.implementation.Md2Boolean;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Date;
import de.wwu.md2.android.md2library.model.type.implementation.Md2DateTime;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Float;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Integer;
import de.wwu.md2.android.md2library.model.type.implementation.Md2String;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Time;
import de.wwu.md2.android.md2library.model.type.implementation.Md2Sensor;
import de.wwu.md2.android.md2library.model.type.implementation.Md2List;

public class __returnStepStackProvider extends AbstractMd2ContentProvider {

	public __returnStepStackProvider(String key, Md2Entity content, Md2DataStore md2DataStore) {
		super(key, content, md2DataStore);
	}
	
	@Override
	public String getKey() {
		return this.key;
	}
	
	@Override
	protected long getInternalId() {
		return this.internalId;
	}
	
	@Override
	protected void setInternalId(long internalId) {
		this.internalId = internalId;
	}
	@Override
	
	public Md2Entity getContent() {
		return this.content;
	}
	
	@Override
	public void setContent(Md2Entity content) {
		if(content != null) {
			this.content = content;
			this.backup = (Md2Entity)content.clone();
			this.internalId = -1L;
			this.load();
		}
	}
	
	@Override
	public void registerAttributeOnChangeHandler(String attribute, Md2OnAttributeChangedHandler onAttributeChangedHandler) {
		this.attributeChangedEventHandlers.put(attribute, onAttributeChangedHandler);
	}
	
	@Override
	public void unregisterAttributeOnChangeHandler(String attribute) {
		this.attributeChangedEventHandlers.remove(attribute);
	}
	
	@Override
	public Md2OnAttributeChangedHandler getOnAttributeChangedHandler(String attribute) {
		return (Md2OnAttributeChangedHandler)this.attributeChangedEventHandlers.get(attribute);
	}
	
	@Override
	public Md2Type getValue(String attribute) {			
		switch (attribute){
			case "returnStep":
				if(((__ReturnStepStack)content).getReturnStep() != null){
					return  
						new Md2String
						(((__ReturnStepStack)content).getReturnStep());	
				} else { return null;}
			case "returnAndReverseStep":
				if(((__ReturnStepStack)content).getReturnAndReverseStep() != null){
					return  
						new Md2String
						(((__ReturnStepStack)content).getReturnAndReverseStep());	
				} else { return null;}
			case "returnAndProceedStep":
				if(((__ReturnStepStack)content).getReturnAndProceedStep() != null){
					return  
						new Md2String
						(((__ReturnStepStack)content).getReturnAndProceedStep());	
				} else { return null;}
			case "tail":
				if(((__ReturnStepStack)content).getTail() != null){
					return  
						((__ReturnStepStack)content).getTail();	
				} else { return null;}
			default: return null;
		}
	}
	
	@Override
	public void setValue(String name, Md2Type value){
		if (content == null) {
			return;
		}
		
		// set only if value is different to current value
		if ((this.getValue(name) == null && value != null) || value != null && !this.getValue(name).toString().equals(value.toString())) {
			switch (name){
				case "returnStep":
					((__ReturnStepStack)content).setReturnStep(((Md2String) value).getPlatformValue());
					notifyChangeHandler(name);
					break;
				case "returnAndReverseStep":
					((__ReturnStepStack)content).setReturnAndReverseStep(((Md2String) value).getPlatformValue());
					notifyChangeHandler(name);
					break;
				case "returnAndProceedStep":
					((__ReturnStepStack)content).setReturnAndProceedStep(((Md2String) value).getPlatformValue());
					notifyChangeHandler(name);
					break;
				case "tail":
					// Umgang mit anderen Datentypen hier einfügen - derzeit kein Support für Listen innerhalb v. Entities
					// angenommen wird entweder Md2String oder passender Md2Type als value
					
break; // Avoid errors for unsupported type
			}
		}
	}
	
	public void reset(){ 
	   newEntity();
	}
	
	@Override
	public void load() {
		
		super.load();
		
	//	if(!(this.content == null | this.md2DataStore == null)) {
	//		if(this.content.getId() > 0L) {
	//			this.existsInDataStore = true;
	//			this.internalId = this.content.getId();
	//		} else {
	//			long id = -1;
	//			this.md2DataStore.getInternalId(this.content);
	//			if(id == -1L) {
	//				this.existsInDataStore = false;
	//				this.internalId = -1L;
	//			} else {
	//				this.existsInDataStore = true;
	//				this.internalId = id;
	//				this.content.setId(id);
	//			}
	//		}
	//	}
	}
	
	@Override
	public void save() {
		if(this.content != null && this.md2DataStore != null) {
			if(this.existsInDataStore) {
				this.md2DataStore.put(this.internalId, this.content);
			} else {
				long newId = 0;
				this.md2DataStore.put(this.content);
				if(newId > 0L) {
					this.existsInDataStore = true;
					this.internalId = newId;
				}
			}

			this.backup = (Md2Entity)this.content.clone();
		}
	}
	
	@Override
	public void remove() {
		if(this.content != null && this.md2DataStore != null) {
			this.md2DataStore.remove(this.internalId, this.content.getClass());
			this.internalId = -1L;
		}
	}
	
	@Override
	public void newEntity(){
		content = new __ReturnStepStack();
	}
}

