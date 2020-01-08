package Investigation.backend.datatypes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BooleanWrapper {
	
	@XmlElement(name="boolean")
	protected boolean bool;
	
	protected BooleanWrapper() {
		// no-arg default constructor necessary
	}
	
	public BooleanWrapper(boolean bool) {
		this.bool = bool;
	}
}
