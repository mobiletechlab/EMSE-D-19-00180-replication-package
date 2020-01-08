package Investigation.backend.datatypes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ValidationError {
	
	@XmlElement
	protected String message;
	
	@XmlElement
	protected List<String> attributes;
	
	protected ValidationError() {
		// no-arg default constructor necessary
	}
	
	public ValidationError(String message) {
		this.message = message;
	}
	
	public ValidationError appendAttribute(String attributeName) {
		this.attributes.add(attributeName);
		return this;
	}
}
