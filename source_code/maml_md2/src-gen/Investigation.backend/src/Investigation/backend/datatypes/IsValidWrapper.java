package Investigation.backend.datatypes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class IsValidWrapper {
	
	@XmlElement
	protected boolean isValid;
	
	protected IsValidWrapper() {
		// no-arg default constructor necessary
	}
	
	public IsValidWrapper(boolean isValid) {
		this.isValid = isValid;
	}
}
