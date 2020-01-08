package Investigation.backend.entities.models;

import java.io.Serializable;
import java.sql.Timestamp;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class InvestigationDatabase2048 implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@NotNull
	@Column(name="INTERNAL_ID__")
	@XmlElement
	protected int __internalId;
	
	
	 @JsonIgnore
		@Column(name="IS_DELETED")
		private Boolean deleted= false; 
		
		 
		 
		 public Boolean getDeleted() {
			return deleted;
		}
	
		public void setDeleted(Boolean deleted) {
			this.deleted = deleted;
		}
	
	
		@XmlElement(nillable=true)
		@Column(name="MODIFIED_TIMESTAMP")
		protected Timestamp modifiedDate;
		
		@PrePersist
		@PreUpdate
		private void setModifieddate(){
		this.modifiedDate= new Timestamp(new Date().getTime());	
		}
	
	
	@NotNull
	@ElementCollection(fetch=FetchType.EAGER)
	@XmlElement(nillable=true)
	protected List<String> databaseValue2048;
	
	
	///////////////////////////////////////
	/// Getters and setters
	///////////////////////////////////////
	
	public int get__internalId() {
		return __internalId;
	}
	
	public List<String> getDatabaseValue2048() {
		return databaseValue2048;
	}
	
	public void setDatabaseValue2048(List<String> databaseValue2048) {
		this.databaseValue2048 = databaseValue2048;
	}
	
}
