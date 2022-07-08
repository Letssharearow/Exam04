package de.fhws.fiw.fds.vuejsserver.api.states.students;

import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.vuejsserver.api.states.studentprojects.StudentProjectRelTypes;
import de.fhws.fiw.fds.vuejsserver.api.states.studentprojects.StudentProjectUri;
import de.fhws.fiw.fds.vuejsserver.database.DaoFactory;
import de.fhws.fiw.fds.vuejsserver.models.Student;

public class DeleteSingleStudent extends AbstractDeleteState<Student>
{
	public DeleteSingleStudent( final Builder builder )
	{
		super( builder );
	}

	@Override protected void authorizeRequest( )
	{
	}

	@Override protected SingleModelResult<Student> loadModel( )
	{
		return DaoFactory.getInstance( ).getStudentDao( ).readById( this.modelIdToDelete );
	}

	@Override protected NoContentResult deleteModel( )
	{
		DaoFactory.getInstance( ).getStudentProjectToStudentDao( ).deleteRelationsToSecondary( this.modelIdToDelete );
		return DaoFactory.getInstance( ).getStudentDao( ).delete( this.modelIdToDelete );
	}

	@Override protected void defineTransitionLinks( )
	{
		addLink( StudentProjectUri.REL_PATH, StudentProjectRelTypes.GET_ALL_STUDENT_PROJECTS,
			getAcceptRequestHeader( ) );
	}

	public static class Builder extends AbstractDeleteStateBuilder
	{
		@Override public AbstractState build( )
		{
			return new DeleteSingleStudent( this );
		}
	}
}
