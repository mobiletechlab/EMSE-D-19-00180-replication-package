package Investigation.backend.ws;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Investigation.backend.Config;
import Investigation.backend.datatypes.IsValidWrapper;

@Path("/md2_model_version")
@Stateless
public class VersionNegotiationWS {
	
	/**
	 * WS that provides the current backend model version.
	 */
	@GET
	@Path(value = "current")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public Response jsonGetModelVersion() {
		final GenericEntity<String> entity = new GenericEntity<String>(Config.MODEL_VERSION) {};
		Response response = Response
				.ok()
				.entity(entity)
				.header("MD2-Model-Version", Config.MODEL_VERSION)
				.build();
		return response;
	}
	
	/**
	 * WS checks whether the requested model version is supported by the backend (returns true or false).
	 * @param version String representation of the requested version.
	 */
	@GET
	@Path(value = "is_valid")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public Response jsonGetSupportsModelVersion(@QueryParam("version") final String version) {
		final IsValidWrapper entity = new IsValidWrapper(Config.SUPPORTED_MODEL_VERSIONS.contains(version));
		Response response = Response
				.ok()
				.entity(entity)
				.header("MD2-Model-Version", Config.MODEL_VERSION)
				.build();
		return response;
	}
	
}
