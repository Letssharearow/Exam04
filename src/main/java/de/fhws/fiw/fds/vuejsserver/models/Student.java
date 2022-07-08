package de.fhws.fiw.fds.vuejsserver.models;

import com.owlike.genson.annotation.JsonConverter;
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
public class Student extends AbstractModel implements Serializable, Cloneable
{
	private String firstName;

	private String lastName;

	private DegreeProgram studyProgram;

	private int studentSemester;

	private long primaryId;

	@InjectLink(
		style = InjectLink.Style.ABSOLUTE,
		value = "/studentprojects/${instance.primaryId}/students/${instance.id}",
		rel = "self",
		title = "self",
		type = "application/json",
		condition = "${instance.primaryId != 0}"
	)
	@XmlJavaTypeAdapter( XmlServerLinkConverter.class )
	private Link selfLinkOnSecond;

	@InjectLink(
		style = InjectLink.Style.ABSOLUTE,
		value = "/students/${instance.id}",
		rel = "self",
		title = "self",
		type = "application/json",
		condition = "${instance.primaryId == 0}"
	)
	@XmlJavaTypeAdapter( XmlServerLinkConverter.class )
	private Link selfLinkPrimary;

	public Student( )
	{
	}

	public Student( final String firstName, final String lastName, final DegreeProgram studyProgram, final int studentSemester )
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.studyProgram = studyProgram;
		this.studentSemester = studentSemester;
	}

	@JsonConverter( JsonServerLinkConverter.class )
	public Link getSelfLinkOnSecond( )
	{
		return selfLinkOnSecond;
	}

	public void setSelfLinkOnSecond( final Link selfLinkOnSecond )
	{
		this.selfLinkOnSecond = selfLinkOnSecond;
	}

	@JsonConverter( JsonServerLinkConverter.class )
	public Link getSelfLinkPrimary( )
	{
		return selfLinkPrimary;
	}

	public void setSelfLinkPrimary( final Link selfLinkPrimary )
	{
		this.selfLinkPrimary = selfLinkPrimary;
	}

	public String getFirstName( )
	{
		return firstName;
	}

	public void setFirstName( final String firstName )
	{
		this.firstName = firstName;
	}

	public String getLastName( )
	{
		return lastName;
	}

	public void setLastName( final String lastName )
	{
		this.lastName = lastName;
	}

	public DegreeProgram getStudyProgram( )
	{
		return studyProgram;
	}

	public void setStudyProgram( final DegreeProgram studyProgram )
	{
		this.studyProgram = studyProgram;
	}

	public int getStudentSemester( )
	{
		return studentSemester;
	}

	public void setStudentSemester( final int studentSemester )
	{
		this.studentSemester = studentSemester;
	}

	@Override public Object clone( ) throws CloneNotSupportedException
	{
		return super.clone( );
	}
}
