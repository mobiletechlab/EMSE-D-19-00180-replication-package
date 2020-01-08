package Investigation.backend.ws;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Investigation.backend.Config;
import Investigation.backend.beans.WorkflowStateBean;

@Path("/eventHandler")
@Stateless
public class EventHandlerWS {
	
	@EJB
	WorkflowStateBean workflowStateBean;
	

	
	/**
	 * Receives workflowInstanceId, lastEventFired and the current workflowElement and starts their persistence.
	 */
	
	@POST
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public Response createOrUpdate(@FormParam("instanceId") String id, @FormParam("lastEventFired") String event,
			@FormParam("currentWfe") String wfe, @FormParam("contentProviderIds") String contentProviderIds) {

		workflowStateBean.createOrUpdateWorkflowState(event, id, wfe, contentProviderIds);
				
		return Response
				.ok()
				.header("MD2-Model-Version", Config.MODEL_VERSION)
				.build();
	}
}
