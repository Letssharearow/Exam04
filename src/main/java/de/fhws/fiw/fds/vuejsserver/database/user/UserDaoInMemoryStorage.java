package de.fhws.fiw.fds.vuejsserver.database.user;

import de.fhws.fiw.fds.sutton.server.api.security.User;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;

public class UserDaoInMemoryStorage extends AbstractInMemoryStorage<User> implements UserDao
{
	public UserDaoInMemoryStorage( )
	{
		super( );
		createUsers( );
	}

	private void createUsers( )
	{
		addUser( "braunp", "secret", "user" );
		addUser( "bond", "secret", "admin" );
	}

	private void addUser( final String name, final String password, final String role )
	{
		create( new User( name, password, role ) );
	}
}
