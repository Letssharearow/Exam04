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

package de.fhws.fiw.fds.vuejsserver.database;

import de.fhws.fiw.fds.vuejsserver.database.student.StudentDao;
import de.fhws.fiw.fds.vuejsserver.database.student.StudentInMemoryStorage;
import de.fhws.fiw.fds.vuejsserver.database.studentproject.StudentProjectDao;
import de.fhws.fiw.fds.vuejsserver.database.studentproject.StudentProjectInMemoryStorage;
import de.fhws.fiw.fds.vuejsserver.database.studentprojecttostudent.StudentProjectToStudentDao;
import de.fhws.fiw.fds.vuejsserver.database.studentprojecttostudent.StudentProjectToStudentsRelationStorage;
import de.fhws.fiw.fds.vuejsserver.database.user.UserDao;
import de.fhws.fiw.fds.vuejsserver.database.user.UserDaoInMemoryStorage;

public class DaoFactory
{
	private static DaoFactory INSTANCE;

	public static final DaoFactory getInstance( )
	{
		if ( INSTANCE == null )
		{
			INSTANCE = new DaoFactory( );
		}

		return INSTANCE;
	}

	private final StudentProjectDao studentProjectDao;

	private final StudentDao studentDao;

	private final StudentProjectToStudentDao studentProjectToStudentDao;

	private final UserDao userDao;

	private DaoFactory( )
	{
		this.studentProjectDao = new StudentProjectInMemoryStorage( );
		this.studentDao = new StudentInMemoryStorage( );
		this.studentProjectToStudentDao = new StudentProjectToStudentsRelationStorage( );
		this.userDao = new UserDaoInMemoryStorage( );
	}

	public StudentProjectDao getStudentProjectDao( )
	{
		return this.studentProjectDao;
	}

	public StudentDao getStudentDao( )
	{
		return this.studentDao;
	}

	public StudentProjectToStudentDao getStudentProjectToStudentDao( )
	{
		return studentProjectToStudentDao;
	}

	public UserDao getUserDao( )
	{
		return this.userDao;
	}
}
