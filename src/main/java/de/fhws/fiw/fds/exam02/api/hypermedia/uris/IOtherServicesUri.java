package de.fhws.fiw.fds.exam02.api.hypermedia.uris;

import de.fhws.fiw.fds.exam03.Start;

public interface IOtherServicesUri
{
	String PATH_ELEMENT_ME = "me";
	String REL_PATH = Start.CONTEXT_PATH + "/api/";
	String ME = REL_PATH + PATH_ELEMENT_ME;
}
