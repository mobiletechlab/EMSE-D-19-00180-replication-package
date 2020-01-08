package Investigation.backend.datatypes;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DateWrapper {
	
	@XmlElement
	@Temporal(TemporalType.DATE)
	protected Date date;
	
	protected DateWrapper() {
		// no-arg default constructor necessary
	}
	
	public DateWrapper(Date date) {
		this.date = date;
	}
}
