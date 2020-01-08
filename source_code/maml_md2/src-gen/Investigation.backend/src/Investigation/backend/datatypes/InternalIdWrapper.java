package Investigation.backend.datatypes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "internalId")
public class InternalIdWrapper {
	
	@XmlElement
	protected int __internalId;
	
	protected InternalIdWrapper() {
		// no-arg default constructor necessary
	}
	
	public InternalIdWrapper(int integer) {
		this.__internalId = integer;
	}
}
