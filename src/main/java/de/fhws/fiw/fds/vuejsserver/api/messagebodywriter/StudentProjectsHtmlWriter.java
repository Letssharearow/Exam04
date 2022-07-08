package de.fhws.fiw.fds.vuejsserver.api.messagebodywriter;

import de.fhws.fiw.fds.vuejsserver.models.StudentProject;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collection;

@Produces( MediaType.TEXT_HTML )
public class StudentProjectsHtmlWriter implements MessageBodyWriter<Collection<StudentProject>>
{
	@Override public boolean isWriteable( final Class<?> aClass, final Type type, final Annotation[] annotations, final MediaType mediaType )
	{
		return true;
	}

	@Override public long getSize( final Collection<StudentProject> studentProjects, final Class<?> aClass, final Type type,
		final Annotation[] annotations, final MediaType mediaType )
	{
		return -1;
	}

	@Override public void writeTo( final Collection<StudentProject> studentProjects, final Class<?> aClass, final Type type,
		final Annotation[] annotations, final MediaType mediaType, final MultivaluedMap<String, Object> multivaluedMap,
		final OutputStream outputStream ) throws IOException, WebApplicationException
	{
		final OutputStreamWriter osw = new OutputStreamWriter( outputStream );

		osw.append( "<html>" );
		osw.append( "<body>" );
		osw.append( "<h1>All Students' Projects</h1>" );
		osw.append( "<ul>" );

		for ( final StudentProject project : studentProjects )
		{
			osw.append( "<li>" + project.getName( ) + "</li>" );
		}

		osw.append( "</ul>" );
		osw.append( "</body>" );
		osw.append( "</html>" );
		osw.flush( );

	}
}
