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

import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.vuejsserver.database.DaoFactory;
import de.fhws.fiw.fds.vuejsserver.models.StudentProject;

public class DeleteSingleStudentProject extends AbstractDeleteState<StudentProject>
{
	public DeleteSingleStudentProject( final Builder builder )
	{
		super( builder );
	}

	@Override protected void authorizeRequest( )
	{
	}

	@Override protected SingleModelResult<StudentProject> loadModel( )
	{
		return DaoFactory.getInstance( ).getStudentProjectDao( ).readById( this.modelIdToDelete );
	}

	@Override protected NoContentResult deleteModel( )
	{
		DaoFactory.getInstance( ).getStudentProjectToStudentDao( ).deleteRelationsFromPrimary( this.modelIdToDelete );
		return DaoFactory.getInstance( ).getStudentProjectDao( ).delete( this.modelIdToDelete );
	}

	@Override protected void defineTransitionLinks( )
	{
		addLink( StudentProjectUri.REL_PATH, StudentProjectRelTypes.GET_ALL_STUDENT_PROJECTS,
			getAcceptRequestHeader( ) );
	}

	public static class Builder extends AbstractDeleteStateBuilder
	{
		@Override public AbstractState build( )
		{
			return new DeleteSingleStudentProject( this );
		}
	}
}
