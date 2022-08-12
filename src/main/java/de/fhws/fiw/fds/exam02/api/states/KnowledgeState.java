package de.fhws.fiw.fds.exam02.api.states;

import de.fhws.fiw.fds.exam02.api.hypermedia.rel_types.IOtherRelTypes;
import de.fhws.fiw.fds.exam02.api.hypermedia.uris.IOtherServicesUri;
import de.fhws.fiw.fds.exam02.api.security.BearerAuthHelper;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetDispatcherState;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

public class KnowledgeState extends AbstractGetDispatcherState
{
	public KnowledgeState(final Builder builder)
	{
		super(builder);
	}

	@Override protected Response createResponse()
	{
		defineHttpResponseBody();

		defineSelfLink();

		defineTransitionLinks();

		String html = null;
		try
		{
			html = getHtml();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		this.responseBuilder.entity(html);

		return this.responseBuilder.build();
	}

	public String getHtml() throws IOException
	{
		OkHttpClient client = new OkHttpClient.Builder().build();
		String link = this.httpServletRequest.getHeader("link");
		System.out.println(link);
		final Request request = new Request.Builder().get().url(link).build();
		return client.newCall(request).execute().body().string();
	}

	@Override protected void defineTransitionLinks()
	{
	}

	public static class Builder extends AbstractDispatcherStateBuilder
	{
		@Override public AbstractState build()
		{
			return new KnowledgeState(this);
		}
	}
}