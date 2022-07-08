package de.fhws.fiw.fds.vuejsserver.api.states.studentproject_student;

import de.fhws.fiw.fds.vuejsserver.Start;

public interface StudentProjects2StudentUri
{
	String SHOW_ALL_PARAMETER = "showAll";
	String PATH_ELEMENT = "studentprojects/{id}/students";
	String REL_PATH = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT;
	String REL_PATH_SHOW_ALL = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT + "?" + SHOW_ALL_PARAMETER + "=true";
	String REL_PATH_SHOW_ONLY_LINKED =
		Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT + "?" + SHOW_ALL_PARAMETER + "=false";
	String REL_PATH_ID = REL_PATH + "/{id}";
}
