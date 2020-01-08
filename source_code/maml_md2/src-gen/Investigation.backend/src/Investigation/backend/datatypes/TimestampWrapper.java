package Investigation.backend.datatypes;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TimestampWrapper {
	
	@XmlElement
	@Temporal(TemporalType.TIMESTAMP)
	protected Date timestamp;
	
	protected TimestampWrapper() {
		// no-arg default constructor necessary
	}
	
	public TimestampWrapper(Date timestamp) {
		this.timestamp = timestamp;
	}
}
