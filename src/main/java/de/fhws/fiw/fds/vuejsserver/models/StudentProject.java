package de.fhws.fiw.fds.vuejsserver.models;

import com.owlike.genson.annotation.JsonConverter;
import com.owlike.genson.annotation.JsonIgnore;
import de.fhws.fiw.fds.sutton.server.api.converter.JsonServerLinkConverter;
import de.fhws.fiw.fds.sutton.server.api.converter.XmlServerLinkConverter;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import org.glassfish.jersey.linking.InjectLink;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
public class StudentProject extends AbstractModel implements Serializable, Cloneable
{
	private String name;

	private String description;

	private String picture;

	private String semester;

	private ProjectType projectType;

	@InjectLink(
		style = InjectLink.Style.ABSOLUTE,
		value = "/studentprojects/${instance.id}",
		rel = "self",
		title = "self",
		type = "application/json" )
	@XmlJavaTypeAdapter( XmlServerLinkConverter.class )
	private Link selfLink;

	@InjectLink(
		style = InjectLink.Style.ABSOLUTE,
		value = "studentprojects/${instance.id}/students",
		rel = "getAllStudentsOfProject",
		title = "students",
		type = "application/json"
	)
	@XmlJavaTypeAdapter( XmlServerLinkConverter.class )
	private Link students;

	public StudentProject( )
	{
	}

	public StudentProject( final String name, final String description, final String semester,
		final ProjectType projectType )
	{
		this.name = name;
		this.description = description;
		this.semester = semester;
		this.projectType = projectType;
	}

	public String getName( )
	{
		return name;
	}

	public void setName( final String name )
	{
		this.name = name;
	}

	public String getDescription( )
	{
		return description;
	}

	public void setDescription( final String description )
	{
		this.description = description;
	}

	public String getPicture( )
	{
		return picture;
	}

	public void setPicture( final String picture )
	{
		this.picture = picture;
	}

	public String getSemester( )
	{
		return semester;
	}

	public void setSemester( final String semester )
	{
		this.semester = semester;
	}

	public ProjectType getProjectType( )
	{
		return projectType;
	}

	public void setProjectType( final ProjectType projectType )
	{
		this.projectType = projectType;
	}

	@JsonConverter( JsonServerLinkConverter.class )
	public Link getStudents( )
	{
		return students;
	}

	@JsonIgnore
	public void setStudents( final Link students )
	{
		this.students = students;
	}

	@JsonConverter( JsonServerLinkConverter.class )
	public Link getSelfLink( )
	{
		return selfLink;
	}

	public void setSelfLink( final Link selfLink )
	{
		this.selfLink = selfLink;
	}

	@Override public Object clone( ) throws CloneNotSupportedException
	{
		return super.clone( );
	}
}
