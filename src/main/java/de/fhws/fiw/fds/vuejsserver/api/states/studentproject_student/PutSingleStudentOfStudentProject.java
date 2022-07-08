package de.fhws.fiw.fds.vuejsserver.api.states.studentproject_student;

import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.put.AbstractPutRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.vuejsserver.database.DaoFactory;
import de.fhws.fiw.fds.vuejsserver.models.Student;

public class PutSingleStudentOfStudentProject extends AbstractPutRelationState<Student>
{
	public PutSingleStudentOfStudentProject( final Builder builder )
	{
		super( builder );
	}

	@Override protected void authorizeRequest( )
	{
	}

	@Override protected SingleModelResult<Student> loadModel( )
	{
		return DaoFactory.getInstance( ).getStudentDao( ).readById( this.requestedId );
	}

	@Override protected NoContentResult updateModel( )
	{
		return DaoFactory.getInstance( ).getStudentProjectToStudentDao( ).update( this.primaryId, this.modelToUpdate );
	}

	@Override protected void defineTransitionLinks( )
	{
		addLink( StudentProjects2StudentUri.REL_PATH_ID,
			StudentProjects2StudentRelTypes.GET_SINGLE_STUDENT_OF_STUDENT_PROJECT,
			getAcceptRequestHeader( ),
			this.primaryId, this.requestedId );
	}

	public static class Builder extends AbstractPutRelationStateBuilder<Student>
	{
		@Override public AbstractState build( )
		{
			return new PutSingleStudentOfStudentProject( this );
		}
	}
}
