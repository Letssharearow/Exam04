package de.fhws.fiw.fds.exam02.api.services;

import de.fhws.fiw.fds.exam02.api.states.DispatcherState;
import de.fhws.fiw.fds.exam02.api.states.KnowledgeState;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("knowledge") public class KnowledgeService extends AbstractService
{
	@GET @Produces(MediaType.TEXT_HTML) public Response get()
	{
		return new KnowledgeState.Builder().setUriInfo(this.uriInfo).setRequest(this.request)
			.setHttpServletRequest(this.httpServletRequest).setContext(this.context).build().execute();
	}
}