package Investigation.backend.datatypes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StringWrapper {
	
	@XmlElement
	protected String string;
	
	protected StringWrapper() {
		// no-arg default constructor necessary
	}
	
	public StringWrapper(String string) {
		this.string = string;
	}
}
