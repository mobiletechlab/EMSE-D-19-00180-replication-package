package Investigation.backend.datatypes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DecimalWrapper {
	
	@XmlElement
	protected double decimal;
	
	protected DecimalWrapper() {
		// no-arg default constructor necessary
	}
	
	public DecimalWrapper(double decimal) {
		this.decimal = decimal;
	}
}
