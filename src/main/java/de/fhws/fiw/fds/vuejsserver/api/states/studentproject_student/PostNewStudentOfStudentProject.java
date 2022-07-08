package de.fhws.fiw.fds.vuejsserver.api.states.studentproject_student;

import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.vuejsserver.database.DaoFactory;
import de.fhws.fiw.fds.vuejsserver.models.Student;

public class PostNewStudentOfStudentProject extends AbstractPostRelationState<Student>
{
	public PostNewStudentOfStudentProject( final Builder builder )
	{
		super( builder );
	}

	@Override protected void authorizeRequest( )
	{
	}

	@Override protected NoContentResult saveModel( )
	{
		return DaoFactory.getInstance( ).getStudentProjectToStudentDao( ).create( this.primaryId, this.modelToStore );
	}

	@Override protected void defineTransitionLinks( )
	{

	}

	public static class Builder extends AbstractPostRelationStateBuilder<Student>
	{
		@Override public AbstractState build( )
		{
			return new PostNewStudentOfStudentProject( this );
		}
	}
}
