package de.fhws.fiw.fds.vuejsserver.api.states.studentproject_student;

import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.vuejsserver.database.DaoFactory;
import de.fhws.fiw.fds.vuejsserver.models.Student;

public class DeleteSingleStudentOfStudentProject extends AbstractDeleteRelationState<Student>
{
	public DeleteSingleStudentOfStudentProject( final Builder builder )
	{
		super( builder );
	}

	@Override protected void authorizeRequest( )
	{
	}

	@Override protected SingleModelResult<Student> loadModel( )
	{
		return DaoFactory.getInstance( )
						 .getStudentProjectToStudentDao( )
						 .readById( this.primaryId, this.modelIdToDelete );
	}

	@Override protected NoContentResult deleteModel( )
	{
		return DaoFactory.getInstance( )
						 .getStudentProjectToStudentDao( )
						 .deleteRelation( this.primaryId, this.modelIdToDelete );
	}

	@Override protected void defineTransitionLinks( )
	{
		addLink( StudentProjects2StudentUri.REL_PATH,
			StudentProjects2StudentRelTypes.GET_ALL_LINKED_STUDENTS_OF_STUDENT_PROJECT,
			getAcceptRequestHeader( ),
			this.primaryId );

		addLink( StudentProjects2StudentUri.REL_PATH_ID,
			StudentProjects2StudentRelTypes.GET_SINGLE_STUDENT_OF_STUDENT_PROJECT,
			getAcceptRequestHeader( ),
			this.primaryId, this.modelIdToDelete );

	}

	public static class Builder extends AbstractDeleteRelationStateBuilder
	{
		@Override public AbstractState build( )
		{
			return new DeleteSingleStudentOfStudentProject( this );
		}
	}
}
