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

package de.fhws.fiw.fds.vuejsserver.database.studentproject;

import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.vuejsserver.models.ProjectType;
import de.fhws.fiw.fds.vuejsserver.models.StudentProject;

public class StudentProjectInMemoryStorage extends AbstractInMemoryStorage<StudentProject> implements StudentProjectDao
{
	public StudentProjectInMemoryStorage( )
	{
		super( );
		populateData( 100 );
	}

	private void populateData( final int numberOfElements )
	{
		create( new StudentProject( "QR Code Scanner", "No description provided",
			"2021ss", ProjectType.PROGRAMMING_PROJECT ) );
		create( new StudentProject( "QR Code Scanner 2", "No description provided",
			"2021ss", ProjectType.PROGRAMMING_PROJECT ) );
		create( new StudentProject( "QR Code Scanner 3", "No description provided",
			"2021ss", ProjectType.PROGRAMMING_PROJECT ) );
		create( new StudentProject( "QR Code Scanner 4", "No description provided",
			"2021ss", ProjectType.PROGRAMMING_PROJECT ) );
		create( new StudentProject( "QR Code Scanner 5", "No description provided",
			"2021ss", ProjectType.PROGRAMMING_PROJECT ) );
		create( new StudentProject( "QR Code Scanner 6", "No description provided",
			"2021ss", ProjectType.PROGRAMMING_PROJECT ) );
		create( new StudentProject( "QR Code Scanner 7", "No description provided",
			"2021ss", ProjectType.PROGRAMMING_PROJECT ) );
		create( new StudentProject( "QR Code Scanner 8", "No description provided",
			"2021ss", ProjectType.PROGRAMMING_PROJECT ) );
		create( new StudentProject( "QR Code Scanner 9", "No description provided",
			"2021ss", ProjectType.PROGRAMMING_PROJECT ) );
		create( new StudentProject( "QR Code Scanner 10", "No description provided",
			"2021ss", ProjectType.PROGRAMMING_PROJECT ) );
		create( new StudentProject( "QR Code Scanner 11", "No description provided",
			"2021ss", ProjectType.PROGRAMMING_PROJECT ) );
	}

}
