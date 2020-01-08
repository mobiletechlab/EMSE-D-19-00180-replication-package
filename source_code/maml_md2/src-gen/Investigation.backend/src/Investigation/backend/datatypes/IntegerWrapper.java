package Investigation.backend.datatypes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class IntegerWrapper {
	
	@XmlElement
	protected int integer;
	
	protected IntegerWrapper() {
		// no-arg default constructor necessary
	}
	
	public IntegerWrapper(int integer) {
		this.integer = integer;
	}
}
