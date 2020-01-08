package Investigation.backend.entities;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * 
 * Each workflowState corresponds to a workflowInstance and keeps track of its state,
 * which is represented by the current workflowElement and the last event fired.
 *
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class WorkflowState implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@NotNull
	@Column(name="INTERNAL_ID__")
	@XmlElement
	protected int __internalId;
	
	@Column(unique=true)
	@NotNull
	@XmlElement(nillable=true)
	protected String instanceId;
	
	@NotNull
	@XmlElement(nillable=true)
	protected String currentWorkflowElement;
	
	@NotNull
	@XmlElement(nillable=true)
	protected String lastEventFired; 
	
	@XmlElement(nillable=true)
	protected String contentProviderIds; 
	
	@XmlElement(nillable=true)
	protected boolean finished;
	
	@Temporal(TemporalType.TIMESTAMP)
	@XmlElement(nillable=true)
	protected Date lastUpdated;
	
	///////////////////////////////////////
	/// constructor
	///////////////////////////////////////
	
	public WorkflowState(){
		
	}
	
	public WorkflowState (String lastEventFired, String instanceId, String wfe, String contentProviderIds) {
		this.instanceId = instanceId;
		this.lastEventFired = lastEventFired;
		this.currentWorkflowElement = wfe;
		this.contentProviderIds = contentProviderIds;
		this.finished = false;
		this.lastUpdated = new Date();
	}
	
	///////////////////////////////////////
	/// Getters and setters
	///////////////////////////////////////
	
	
	public int get__internalId() {
	    return __internalId;
	}

	public String getInstanceId() {
	    return instanceId;
	}

	public String getCurrentWorkflowElement() {
		return currentWorkflowElement;
	}
	
	public void setCurrentWorkflowElement(String currentWorkflowElement) {
		this.currentWorkflowElement = currentWorkflowElement;
	}
	
	public String getLastEventFired() {
		return lastEventFired;
	}
	
	public void setLastEventFired(String lastEventFired) {
		this.lastEventFired = lastEventFired;
	}
	
	public String getContentProviderIds() {
		return contentProviderIds;
	}
	
	public void setContentProviderIds(String contentProviderIds) {
		this.contentProviderIds = contentProviderIds;
	}
	
	public void setFinished(){
		this.finished = true;
	}

	public boolean getFinished()
	{
		return this.finished;
	}
	
	public void setLastUpdated(Date lastUpdated){
		this.lastUpdated = lastUpdated;
	}

	public Date getLastUpdated()
	{
		return this.lastUpdated;
	}
	
}
