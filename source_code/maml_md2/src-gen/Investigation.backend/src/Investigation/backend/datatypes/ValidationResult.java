package Investigation.backend.datatypes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Object that contains the validation result of a remote validator.
 */
@XmlRootElement
public class ValidationResult {
	
	@XmlElement
	protected boolean ok;
	
	@XmlElement
	protected List<ValidationError> errors;
	
	protected ValidationResult() {
		// no-arg default constructor necessary
	}
	
	public ValidationResult(boolean ok) {
		this.ok = ok;
	}
	
	public ValidationResult appendError(ValidationError error) {
		
		if(this.ok) {
			throw new UnsupportedOperationException("Tried to add a ValidationError to a result object with validation result 'ok'.");
		}
		
		this.errors.add(error);
		return this;
	}
}
