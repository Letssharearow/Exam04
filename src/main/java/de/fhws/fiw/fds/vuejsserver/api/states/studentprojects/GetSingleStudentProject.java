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
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.vuejsserver.api.states.studentproject_student.StudentProjects2StudentRelTypes;
import de.fhws.fiw.fds.vuejsserver.api.states.studentproject_student.StudentProjects2StudentUri;
import de.fhws.fiw.fds.vuejsserver.database.DaoFactory;
import de.fhws.fiw.fds.vuejsserver.models.StudentProject;

public class GetSingleStudentProject extends AbstractGetState<StudentProject>
{
	public GetSingleStudentProject( final AbstractGetStateBuilder builder )
	{
		super( builder );
	}

	@Override protected void authorizeRequest( )
	{
	}

	@Override protected SingleModelResult<StudentProject> loadModel( )
	{
		return DaoFactory.getInstance( ).getStudentProjectDao( ).readById( this.requestedId );
	}

	@Override protected void defineTransitionLinks( )
	{
		addLink( StudentProjectUri.REL_PATH_ID, StudentProjectRelTypes.UPDATE_SINGLE_STUDENT_PROJECT,
			getAcceptRequestHeader( ),
			this.requestedId );
		addLink( StudentProjectUri.REL_PATH_ID, StudentProjectRelTypes.DELETE_SINGLE_STUDENT_PROJECT,
			getAcceptRequestHeader( ),
			this.requestedId );
		addLink( StudentProjects2StudentUri.REL_PATH, StudentProjects2StudentRelTypes.CREATE_STUDENT_OF_STUDENT_PROJECT,
			getAcceptRequestHeader( ),
			this.requestedId );
	}

	public static class Builder extends AbstractGetStateBuilder
	{
		@Override public AbstractState build( )
		{
			return new GetSingleStudentProject( this );
		}
	}
}
