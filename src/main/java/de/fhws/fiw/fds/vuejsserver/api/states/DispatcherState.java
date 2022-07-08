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

package de.fhws.fiw.fds.vuejsserver.api.states;

import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetDispatcherState;
import de.fhws.fiw.fds.vuejsserver.api.states.studentprojects.StudentProjectRelTypes;
import de.fhws.fiw.fds.vuejsserver.api.states.studentprojects.StudentProjectUri;

import javax.ws.rs.core.MediaType;

public class DispatcherState extends AbstractGetDispatcherState
{
	public DispatcherState( final Builder builder )
	{
		super( builder );
	}

	@Override protected void defineTransitionLinks( )
	{
		addLink( StudentProjectUri.REL_PATH, StudentProjectRelTypes.CREATE_STUDENT_PROJECT,
			MediaType.APPLICATION_JSON );
		addLink( StudentProjectUri.REL_PATH, StudentProjectRelTypes.GET_ALL_STUDENT_PROJECTS,
			MediaType.APPLICATION_JSON );
		addLink( StudentProjectUri.REL_PATH_QUERY, StudentProjectRelTypes.GET_ALL_STUDENT_PROJECTS_FILTER,
			MediaType.APPLICATION_JSON );
	}

	public static class Builder extends AbstractDispatcherStateBuilder
	{
		@Override public AbstractState build( )
		{
			return new DispatcherState( this );
		}
	}
}