package Investigation.backend.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import Investigation.backend.Config;
import Investigation.backend.entities.WorkflowState;

@Stateless
public class WorkflowStateBean {
	
	@PersistenceContext(unitName = "Investigation.backend")
    EntityManager em;
	
	/*
	 * Default logic to get and set Complaint entities
	 */
	
	
	public List<WorkflowState> getAllWorkflowStates(String app){
		List<WorkflowState> states = new ArrayList<WorkflowState>();
		if(app == null || app.equals("")){
			TypedQuery<WorkflowState> query = em.createQuery("SELECT ws FROM WorkflowState ws", WorkflowState.class);
			return query.getResultList();
		}
		
		// app name was set:
		String[] wfes = Config.APP_WORKFLOWELEMENT_RELATIONSHIP.get(app);
		if (wfes == null) {
			throw new RuntimeException("The app " + app + " is not registered with this backend.");
		}
		for(String s:wfes)
		{
			TypedQuery<WorkflowState> query = em.createQuery("SELECT ws FROM WorkflowState ws WHERE ws.currentWorkflowElement = :wfe AND ws.finished = false", WorkflowState.class)
				.setParameter("wfe", s);
			states.addAll(query.getResultList());
		}
	
		return states;
	}
	
	public WorkflowState getWorkflowState(String instanceId){
		TypedQuery<WorkflowState> query = em.createQuery("SELECT ws FROM WorkflowState ws WHERE ws.instanceId = :id", WorkflowState.class)
				.setParameter("id", instanceId);
		List<WorkflowState> states = query.getResultList();
		
		return (states.size() > 0) ? states.get(0) : null;
	}
	
	/**
	 * Creates a new workflowState if it does not exist yet.
	 * Otherwise, the current workflowState is updated.
	 * @param lastEventFired
	 * @param instanceId
	 * @param wfe the current workflowElement
	 * @return current workflowState
	 */
	public WorkflowState createOrUpdateWorkflowState(String lastEventFired, String instanceId, String wfe, String contentProviderIds){
		
		HashMap<String, String> eventSuccessorMap = Config.WORKFLOWELEMENT_EVENT_SUCCESSION.get(wfe);
		if (eventSuccessorMap == null) {
			throw new RuntimeException("No events are registered for the workflow element " + wfe + ".");
		}
		
		String succeedingWfe = eventSuccessorMap.get(lastEventFired);
		if (succeedingWfe == null) {
			throw new RuntimeException("The event " + lastEventFired + " is not registered for the workflow element " + wfe + ".");
		}
		
		WorkflowState ws = getWorkflowState(instanceId);
		if(ws == null){
			ws = new WorkflowState(lastEventFired, instanceId, succeedingWfe, contentProviderIds);
			if (succeedingWfe.equals("_terminate")) {
				ws.setFinished();
			}
			em.persist(ws);
		}
		else {
			// set last updated to current date
			ws.setLastUpdated(new Date());
			// set to succeeding workflow element -- i.e. describe, what status the instance is in now.
			ws.setCurrentWorkflowElement(succeedingWfe);
			ws.setLastEventFired(lastEventFired); // in fact, this information is useless, but probably nice for display :)
			ws.setContentProviderIds(contentProviderIds);
			if (succeedingWfe.equals("_terminate")) {
				ws.setFinished();
			}
			em.merge(ws);
		}
		return ws;
	}
	
	public boolean deleteWorkflowStates(List<Integer> ids) {
		
		Long count = em.createQuery("SELECT COUNT(t) FROM WorkflowState t WHERE t.__internalId IN :ids", Long.class)
			.setParameter("ids", ids)
			.getSingleResult();
		
		if(count == ids.size()) {
			em.createQuery("DELETE FROM WorkflowState t WHERE t.__internalId IN :ids")
				.setParameter("ids", ids)
				.executeUpdate();
			return true;
		} else {
			return false;
		}
	}
}
