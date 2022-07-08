package de.fhws.fiw.fds.vuejsserver.database.studentprojecttostudent;

import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryRelationStorage;
import de.fhws.fiw.fds.vuejsserver.database.DaoFactory;
import de.fhws.fiw.fds.vuejsserver.models.Student;

public class StudentProjectToStudentsRelationStorage extends AbstractInMemoryRelationStorage<Student>
	implements StudentProjectToStudentDao
{
	public StudentProjectToStudentsRelationStorage( )
	{
		super( );
		populateData( );
	}

	@Override protected IDatabaseAccessObject<Student> getSecondaryStorage( )
	{
		return DaoFactory.getInstance( ).getStudentDao( );
	}

	private void populateData( )
	{
		this.storage.put( 1l, 1l );
		this.storage.put( 1l, 2l );
	}
}
