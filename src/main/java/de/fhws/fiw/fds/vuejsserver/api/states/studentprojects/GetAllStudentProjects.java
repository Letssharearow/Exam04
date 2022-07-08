/*
 * Copyright 2019 University of Applied Sciences Würzburg-Schweinfurt, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.fhws.fiw.fds.vuejsserver.api.states.studentprojects;

import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionState;
import de.fhws.fiw.fds.vuejsserver.api.queries.StudentProjectQuery;
import de.fhws.fiw.fds.vuejsserver.models.StudentProject;

import javax.ws.rs.core.GenericEntity;
import java.util.Collection;

public class GetAllStudentProjects extends AbstractGetCollectionState<StudentProject>
{
	public GetAllStudentProjects( final Builder builder )
	{
		super( builder );
	}

	@Override protected void authorizeRequest( )
	{
	}

	protected void defineHttpResponseBody( )
	{
		this.responseBuilder.entity( new GenericEntity<Collection<StudentProject>>( this.result.getResult( ) )
		{
		} );
	}

	@Override protected void defineTransitionLinks( )
	{
		addLink( StudentProjectUri.REL_PATH, StudentProjectRelTypes.CREATE_STUDENT_PROJECT, getAcceptRequestHeader( ) );
	}

	public static class Builder extends AbstractGetCollectionStateBuilder<StudentProject>
	{
		private final StudentProjectQuery theQuery;

		private int offset;

		private int size;

		public Builder( )
		{
			this.theQuery = new StudentProjectQuery( );
		}

		public Builder setQuery( final String query )
		{
			this.theQuery.setQuery( query );
			return this;
		}

		public Builder setOffset( final int offset )
		{
			this.offset = offset;
			return this;
		}

		public Builder setSize( final int size )
		{
			this.size = size;
			return this;
		}

		@Override public AbstractState build( )
		{
			this.query = theQuery;
			this.query.setPagingBehavior( new PagingBehaviorUsingOffsetSize( offset, size ) );
			return new GetAllStudentProjects( this );
		}
	}
}
