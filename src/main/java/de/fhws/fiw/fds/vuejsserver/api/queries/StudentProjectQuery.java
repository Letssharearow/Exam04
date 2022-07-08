/*
 * Copyright 2021 University of Applied Sciences Würzburg-Schweinfurt, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package de.fhws.fiw.fds.vuejsserver.api.queries;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.vuejsserver.database.DaoFactory;
import de.fhws.fiw.fds.vuejsserver.models.StudentProject;
import org.apache.commons.lang.StringUtils;

import java.util.Locale;
import java.util.function.Predicate;

public class StudentProjectQuery extends AbstractQuery<StudentProject>
{
	private String query;

	public void setQuery( final String query )
	{
		this.query = query.toLowerCase( Locale.ROOT );
	}

	@Override protected CollectionModelResult<StudentProject> doExecuteQuery( ) throws DatabaseException
	{
		return DaoFactory.getInstance( ).getStudentProjectDao( ).readByPredicate( filter( ) );
	}

	private Predicate<StudentProject> filter( )
	{
		return filterByProjectName( ).or( filterBySemester( ) );
	}

	private Predicate<StudentProject> filterByProjectName( )
	{
		return p -> StringUtils.isEmpty( this.query )
			|| p.getName( ).toLowerCase( Locale.ROOT ).startsWith( this.query );
	}

	private Predicate<StudentProject> filterBySemester( )
	{
		return p -> StringUtils.isEmpty( this.query )
			|| p.getSemester( ).startsWith( this.query );
	}

}
