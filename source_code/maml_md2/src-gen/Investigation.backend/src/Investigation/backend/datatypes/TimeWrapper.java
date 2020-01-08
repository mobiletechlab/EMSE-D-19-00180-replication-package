package Investigation.backend.datatypes;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TimeWrapper {
	
	@XmlElement
	@Temporal(TemporalType.TIME)
	protected Date time;
	
	protected TimeWrapper() {
		// no-arg default constructor necessary
	}
	
	public TimeWrapper(Date time) {
		this.time = time;
	}
}
