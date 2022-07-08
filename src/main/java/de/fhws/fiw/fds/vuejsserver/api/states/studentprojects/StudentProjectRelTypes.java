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

public interface StudentProjectRelTypes
{
	String CREATE_STUDENT_PROJECT = "createStudentProject";
	String GET_ALL_STUDENT_PROJECTS = "getAllStudentProjects";
	String GET_ALL_STUDENT_PROJECTS_FILTER = "getAllStudentProjectsWithFilter";
	String UPDATE_SINGLE_STUDENT_PROJECT = "updateStudentProject";
	String DELETE_SINGLE_STUDENT_PROJECT = "deleteStudentProject";
	String GET_SINGLE_STUDENT_PROJECT = "getStudentProject";
}
