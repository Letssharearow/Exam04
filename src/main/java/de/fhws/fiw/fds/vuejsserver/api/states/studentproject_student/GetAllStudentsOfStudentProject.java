package de.fhws.fiw.fds.vuejsserver.api.states.studentproject_student;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractRelationQuery;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionRelationState;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.vuejsserver.database.DaoFactory;
import de.fhws.fiw.fds.vuejsserver.database.studentprojecttostudent.StudentProjectToStudentDao;
import de.fhws.fiw.fds.vuejsserver.models.Student;

import javax.ws.rs.core.GenericEntity;
import java.util.Collection;

public class GetAllStudentsOfStudentProject extends AbstractGetCollectionRelationState<Student>
{
	public GetAllStudentsOfStudentProject( final Builder builder )
	{
		super( builder );
	}

	@Override protected void authorizeRequest( )
	{
	}

	@Override protected void defineHttpResponseBody( )
	{
		this.responseBuilder.entity( new GenericEntity<Collection<Student>>( this.result.getResult( ) )
		{
		} );
	}

	@Override protected void defineTransitionLinks( )
	{
		addLink( StudentProjects2StudentUri.REL_PATH,
			StudentProjects2StudentRelTypes.CREATE_STUDENT_OF_STUDENT_PROJECT,
			getAcceptRequestHeader( ),
			this.primaryId );

		if ( this.query.isShowAll( ) )
		{
			addLink( StudentProjects2StudentUri.REL_PATH_SHOW_ONLY_LINKED,
				StudentProjects2StudentRelTypes.GET_ALL_LINKED_STUDENTS_OF_STUDENT_PROJECT,
				getAcceptRequestHeader( ),
				this.primaryId );
		}
		else
		{
			addLink( StudentProjects2StudentUri.REL_PATH_SHOW_ALL,
				StudentProjects2StudentRelTypes.GET_ALL_STUDENTS_OF_STUDENT_PROJECT,
				getAcceptRequestHeader( ),
				this.primaryId );
		}
	}

	public static class AllLocations extends AbstractRelationQuery<Student>
	{
		private final StudentProjectToStudentDao storage;

		public AllLocations( final long primaryId, final boolean showAll )
		{
			super( primaryId, showAll );
			this.storage = DaoFactory.getInstance( ).getStudentProjectToStudentDao( );
		}

		@Override protected CollectionModelResult<Student> doExecuteQuery( ) throws DatabaseException
		{
			if ( showAll )
			{
				return storage.readAllByPredicate( this.primaryId, all( ) );
			}
			else
			{
				return this.storage.readByPredicate( this.primaryId, all( ) );
			}
		}
	}

	public static class FilterStudents extends AbstractRelationQuery<Student>
	{
		private final StudentProjectToStudentDao storage;

		public FilterStudents( final long primaryId, final boolean showAll )
		{
			super( primaryId, showAll );
			this.storage = DaoFactory.getInstance( ).getStudentProjectToStudentDao( );
		}

		@Override protected CollectionModelResult<Student> doExecuteQuery( ) throws DatabaseException
		{
			if ( showAll )
			{
				return this.storage.readAllByPredicate( this.primaryId, all( ) );
			}
			else
			{
				return this.storage.readByPredicate( this.primaryId, all( ) );
			}
		}
	}

	public static class Builder extends AbstractGetCollectionRelationStateBuilder<Student>
	{
		@Override public AbstractState build( )
		{
			return new GetAllStudentsOfStudentProject( this );
		}
	}
}
