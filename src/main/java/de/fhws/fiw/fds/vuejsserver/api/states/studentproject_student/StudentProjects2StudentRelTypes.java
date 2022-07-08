package de.fhws.fiw.fds.vuejsserver.api.states.studentproject_student;

public interface StudentProjects2StudentRelTypes
{
	String CREATE_STUDENT_OF_STUDENT_PROJECT = "createStudentOfStudentProject";
	String GET_ALL_LINKED_STUDENTS_OF_STUDENT_PROJECT = "getAllStudentsOfStudentProject";
	String GET_ALL_STUDENTS_OF_STUDENT_PROJECT = "getAllLinkableStudents";
	String UPDATE_SINGLE_STUDENT_OF_STUDENT_PROJECT = "updateStudentOfStudentProject";
	String CREATE_LINK_FROM_STUDENT_PROJECT_TO_STUDENT = "linkStudentProjectToStudent";
	String DELETE_LINK_FROM_STUDENT_PROJECT_TO_STUDENT = "unlinkStudentProjectToStudent";
	String GET_SINGLE_STUDENT_OF_STUDENT_PROJECT = "getStudentOfStudentProject";
}
