package de.fhws.fiw.fds.vuejsserver.api.states.students;

import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.vuejsserver.api.states.studentprojects.StudentProjectUri;
import de.fhws.fiw.fds.vuejsserver.database.DaoFactory;
import de.fhws.fiw.fds.vuejsserver.models.Student;

public class GetSingleStudent extends AbstractGetState<Student>
{
	public GetSingleStudent( final Builder builder )
	{
		super( builder );
	}

	@Override protected SingleModelResult<Student> loadModel( )
	{
		return DaoFactory.getInstance( ).getStudentDao( ).readById( this.requestedId );
	}

	@Override protected void authorizeRequest( )
	{
	}

	@Override protected void defineTransitionLinks( )
	{
		addLink( StudentUri.REL_PATH_ID, StudentRelTypes.UPDATE_SINGLE_STUDENT, getAcceptRequestHeader( ),
			this.requestedId );
		addLink( StudentProjectUri.REL_PATH_ID, StudentRelTypes.DELETE_SINGLE_STUDENT, getAcceptRequestHeader( ),
			this.requestedId );
	}

	public static class Builder extends AbstractGetStateBuilder
	{
		@Override public AbstractState build( )
		{
			return new GetSingleStudent( this );
		}
	}
}
