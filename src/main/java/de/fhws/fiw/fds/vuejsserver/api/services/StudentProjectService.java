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

package de.fhws.fiw.fds.vuejsserver.api.services;

import de.fhws.fiw.fds.sutton.server.api.services.AbstractService;
import de.fhws.fiw.fds.vuejsserver.api.states.studentproject_student.*;
import de.fhws.fiw.fds.vuejsserver.api.states.studentprojects.*;
import de.fhws.fiw.fds.vuejsserver.models.Student;
import de.fhws.fiw.fds.vuejsserver.models.StudentProject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize.*;

@Path( "studentprojects" )
public class StudentProjectService extends AbstractService
{
	@GET
	@Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_HTML } )
	public Response getAllStudentProjects(
		@DefaultValue( "" ) @QueryParam( "q" ) final String query,
		@DefaultValue( "0" ) @QueryParam( QUERY_PARAM_OFFSET ) final int offset,
		@DefaultValue( DEFAULT_PAGE_SIZE_STR ) @QueryParam( QUERY_PARAM_SIZE ) final int size
	)

	{
		return new GetAllStudentProjects.Builder( )
			.setQuery( query )
			.setOffset( offset )
			.setSize( size )
			.setUriInfo( this.uriInfo )
			.setRequest( this.request )
			.setHttpServletRequest( this.httpServletRequest )
			.setContext( this.context )
			.build( )
			.execute( );
	}

	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_HTML } )
	public Response getSingleStudentProject( @PathParam( "id" ) final long id )
	{
		return new GetSingleStudentProject.Builder( )
			.setRequestedId( id )
			.setUriInfo( this.uriInfo )
			.setRequest( this.request )
			.setHttpServletRequest( this.httpServletRequest )
			.setContext( this.context )
			.build( )
			.execute( );
	}

	@POST
	@Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Response createSingleStudentProject( final StudentProject personModel )
	{
		return new PostNewStudentProject.Builder( )
			.setModelToCreate( personModel )
			.setUriInfo( this.uriInfo )
			.setRequest( this.request )
			.setHttpServletRequest( this.httpServletRequest )
			.setContext( this.context )
			.build( )
			.execute( );
	}

	@PUT
	@Path( "{id: \\d+}" )
	@Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Response updateSingleStudentProject( @PathParam( "id" ) final long id, final StudentProject personModel )
	{
		return new PutSingleStudentProject.Builder( )
			.setRequestedId( id )
			.setModelToUpdate( personModel )
			.setUriInfo( this.uriInfo )
			.setRequest( this.request )
			.setHttpServletRequest( this.httpServletRequest )
			.setContext( this.context )
			.build( )
			.execute( );
	}

	@DELETE
	@Path( "{id: \\d+}" )
	@Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Response deleteSingleStudentProject( @PathParam( "id" ) final long id )
	{
		return new DeleteSingleStudentProject.Builder( )
			.setRequestedId( id )
			.setUriInfo( this.uriInfo )
			.setRequest( this.request )
			.setHttpServletRequest( this.httpServletRequest )
			.setContext( this.context )
			.build( )
			.execute( );
	}

	@GET
	@Path( "{studentProjectId: \\d+}/students" )
	@Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Response getStudentsOfProject( @PathParam( "studentProjectId" ) final long studentProjectId,
		@DefaultValue( "false" ) @QueryParam( "showAll" ) final boolean showAll )
	{
		return new GetAllStudentsOfStudentProject.Builder( )
			.setParentId( studentProjectId )
			.setQuery( new GetAllStudentsOfStudentProject.FilterStudents( studentProjectId, showAll ) )
			.setUriInfo( this.uriInfo )
			.setRequest( this.request )
			.setHttpServletRequest( this.httpServletRequest )
			.setContext( this.context )
			.build( )
			.execute( );
	}

	@GET
	@Path( "{studentProjectId: \\d+}/students/{studentId: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Response getStudentOfProject( @PathParam( "studentProjectId" ) final long studentProjectId,
		@PathParam( "studentId" ) final long studentId )
	{
		return new GetSingleStudentOfStudentProject.Builder( )
			.setParentId( studentProjectId )
			.setRequestedId( studentId )
			.setUriInfo( this.uriInfo )
			.setRequest( this.request )
			.setHttpServletRequest( this.httpServletRequest )
			.setContext( this.context )
			.build( )
			.execute( );
	}

	@POST
	@Path( "{studentProjectId: \\d+}/students" )
	@Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Response createStudentOfProject( @PathParam( "studentProjectId" ) final long studentProjectId,
		final Student location )
	{
		return new PostNewStudentOfStudentProject.Builder( )
			.setParentId( studentProjectId )
			.setModelToCreate( location )
			.setUriInfo( this.uriInfo )
			.setRequest( this.request )
			.setHttpServletRequest( this.httpServletRequest )
			.setContext( this.context )
			.build( )
			.execute( );
	}

	@PUT
	@Path( "{studentProjectId: \\d+}/students/{studentId: \\d+}" )
	@Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Response updateStudentOfProject( @PathParam( "studentProjectId" ) final long studentProjectId,
		@PathParam( "studentId" ) final long studentId, final Student location )
	{
		return new PutSingleStudentOfStudentProject.Builder( )
			.setParentId( studentProjectId )
			.setRequestedId( studentId )
			.setModelToUpdate( location )
			.setUriInfo( this.uriInfo )
			.setRequest( this.request )
			.setHttpServletRequest( this.httpServletRequest )
			.setContext( this.context )
			.build( )
			.execute( );
	}

	@DELETE
	@Path( "{studentProjectId: \\d+}/students/{studentId: \\d+}" )
	public Response deleteLStudentOfProject( @PathParam( "studentProjectId" ) final long studentProjectId,
		@PathParam( "studentId" ) final long studentId )
	{
		return new DeleteSingleStudentOfStudentProject.Builder( )
			.setParentId( studentProjectId )
			.setRequestedId( studentId )
			.setUriInfo( this.uriInfo )
			.setRequest( this.request )
			.setHttpServletRequest( this.httpServletRequest )
			.setContext( this.context )
			.build( )
			.execute( );
	}
}
