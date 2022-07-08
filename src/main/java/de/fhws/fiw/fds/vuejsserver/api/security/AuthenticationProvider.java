package de.fhws.fiw.fds.vuejsserver.api.security;

import de.fhws.fiw.fds.sutton.server.api.security.AbstractAuthenticationProvider;
import de.fhws.fiw.fds.sutton.server.api.security.User;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.vuejsserver.database.DaoFactory;

import javax.ws.rs.NotAuthorizedException;
import java.util.Optional;
import java.util.function.Predicate;

public class AuthenticationProvider extends AbstractAuthenticationProvider
{
	public AuthenticationProvider( )
	{
	}

	@Override protected Optional<User> loadUserFromDatabase( final String name )
	{
		final CollectionModelResult<User> result =
			DaoFactory.getInstance( ).getUserDao( ).readByPredicate( byUserName( name ) );

		if ( result.getResult( ).size( ) == 1 )
		{
			return result.getResult( ).stream( ).findFirst( );
		}
		else
		{
			throw new NotAuthorizedException( "" );
		}
	}

	private Predicate<User> byUserName( final String name )
	{
		return p -> p.getName( ).equalsIgnoreCase( name );
	}
}
