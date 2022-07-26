package de.fhws.fiw.fds.exam02.api.states;

import de.fhws.fiw.fds.exam02.api.hypermedia.rel_types.IOtherRelTypes;
import de.fhws.fiw.fds.exam02.api.hypermedia.uris.IOtherServicesUri;
import de.fhws.fiw.fds.exam02.api.security.BearerAuthHelper;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetDispatcherState;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class DispatcherState extends AbstractGetDispatcherState
{
	public DispatcherState(final Builder builder)
	{
		super(builder);
	}

	@Override protected Response createResponse()
	{
		BearerAuthHelper.accessControl(this.httpServletRequest);

		defineHttpResponseBody();

		defineSelfLink();

		defineTransitionLinks();

		this.responseBuilder.header("Authentication", "Bearer Token");

		return this.responseBuilder.build();
	}

	@Override protected void defineTransitionLinks()
	{
		addLink(IOtherServicesUri.ME, IOtherRelTypes.GET_TOKEN, MediaType.APPLICATION_JSON);
	}

	public static class Builder extends AbstractDispatcherStateBuilder
	{
		@Override public AbstractState build()
		{
			return new DispatcherState(this);
		}
	}
}