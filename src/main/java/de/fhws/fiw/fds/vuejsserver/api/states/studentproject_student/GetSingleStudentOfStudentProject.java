package de.fhws.fiw.fds.vuejsserver.api.states.studentproject_student;

import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.vuejsserver.database.DaoFactory;
import de.fhws.fiw.fds.vuejsserver.models.Student;

public class GetSingleStudentOfStudentProject extends AbstractGetRelationState<Student>
{
	public GetSingleStudentOfStudentProject( final Builder builder )
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

	@Override protected void defineTransitionLinks( )
	{
		addLink( StudentProjects2StudentUri.REL_PATH_SHOW_ONLY_LINKED,
			StudentProjects2StudentRelTypes.GET_ALL_LINKED_STUDENTS_OF_STUDENT_PROJECT,
			getAcceptRequestHeader( ),
			this.primaryId );

		if ( isPersonLinkedToThisLocation( ) )
		{
			addLink( StudentProjects2StudentUri.REL_PATH_ID,
				StudentProjects2StudentRelTypes.UPDATE_SINGLE_STUDENT_OF_STUDENT_PROJECT,
				getAcceptRequestHeader( ),
				this.primaryId, this.requestedId );

			addLink( StudentProjects2StudentUri.REL_PATH_ID,
				StudentProjects2StudentRelTypes.DELETE_LINK_FROM_STUDENT_PROJECT_TO_STUDENT,
				getAcceptRequestHeader( ),
				this.primaryId, this.requestedId );
		}
		else
		{
			addLink( StudentProjects2StudentUri.REL_PATH_ID,
				StudentProjects2StudentRelTypes.CREATE_LINK_FROM_STUDENT_PROJECT_TO_STUDENT,
				getAcceptRequestHeader( ),
				this.primaryId, this.requestedId );
		}
	}

	private boolean isPersonLinkedToThisLocation( )
	{
		return !DaoFactory.getInstance( )
						  .getStudentProjectToStudentDao( )
						  .readById( this.primaryId, this.requestedId )
						  .isEmpty( );
	}

	public static class Builder extends AbstractGetRelationStateBuilder
	{
		@Override public AbstractState build( )
		{
			return new GetSingleStudentOfStudentProject( this );
		}
	}
}
