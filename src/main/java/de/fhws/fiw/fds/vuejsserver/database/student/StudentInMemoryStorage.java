package de.fhws.fiw.fds.vuejsserver.database.student;

import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.vuejsserver.models.DegreeProgram;
import de.fhws.fiw.fds.vuejsserver.models.Student;

public class StudentInMemoryStorage extends AbstractInMemoryStorage<Student> implements StudentDao
{
	public StudentInMemoryStorage( )
	{
		super( );
		populateData( );
	}

	private void populateData( )
	{
		create( new Student( "James", "Bond", DegreeProgram.BIN, 4 ) );
		create( new Student( "Ernst Stavro", "Blofeld", DegreeProgram.BEC, 1 ) );
		create( new Student( "Miss", "Moneypenny", DegreeProgram.MIS, 3 ) );
	}
}
