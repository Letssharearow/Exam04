import Vue from 'vue'
import Vuex from 'vuex'
import network from '@/network/network';

const parse = require('parse-link-header');

Vue.use(Vuex)

async function getStudentOfProject(url) {
    const response = await network.getSingleStudent(url);
    const relations = parse(response.headers.link);
    const student = response.data;
    const studentLinkHref = relations['linkStudentProjectToStudent'];
    const studentUnlinkHref = relations['unlinkStudentProjectToStudent'];
    if (studentLinkHref != undefined) {
        student.linkHref = studentLinkHref.url;
    }
    if (studentUnlinkHref != undefined) {
        student.unlinkHref = studentUnlinkHref.url;
    }
    return student;
}

async function getStudentOfProjectAfterRelationUpdate(response) {
    const relations = parse(response.headers.link);
    const studentUrl = relations['getStudentOfStudentProject'];
    return await getStudentOfProject(studentUrl.url);
}

export const store = new Vuex.Store({
    state: {
        projects: [],
        selectedProject: {},
        relatedStudents: [],
        editMode: false,
        createUrl: undefined,
        editUrl: undefined,
        deleteUrl: undefined,
        nextUrl: undefined,
        prevUrl: undefined,
        toggleStudentsOfProjectUrl: undefined,
        showingLinkedStudents: false,
        clearSearchField: false,
        editStudentProjectErrorMessage: ""
    },
    mutations: {
        SET_PROJECTS(state, {projects, createUrl, nextUrl, prevUrl}) {
            state.projects = projects;
            state.selectedProject = {};
            state.clearSearchField = false;
            state.createUrl = createUrl;
            state.prevUrl = prevUrl;
            state.nextUrl = nextUrl;
        },
        SET_PROJECT(state, {project, editUrl, deleteUrl}) {
            state.selectedProject = project;
            state.editUrl = editUrl;
            state.deleteUrl = deleteUrl;
            state.clearSearchField = true;
            state.editMode = false;
        },
        SET_STUDENTS(state, {students, showingAllLinkedStudents, showingAllLinkableStudents}) {
            state.relatedStudents = students;
            if (showingAllLinkedStudents != undefined) {
                state.toggleStudentsOfProjectUrl = showingAllLinkedStudents;
                state.showingLinkedStudents = false;
            } else if (showingAllLinkableStudents != undefined) {
                state.toggleStudentsOfProjectUrl = showingAllLinkableStudents;
                state.showingLinkedStudents = true;
            }
        },
        REPLACE_STUDENT(state, {oldStudent, newStudent}) {
            const index = state.relatedStudents.findIndex(student => student.id == oldStudent.id);
            state.relatedStudents.splice(index, 1, newStudent);
        },
        SET_EDIT_MODE(state, editMode) {
            state.editMode = editMode;
            if (editMode == false && state.selectedProject.id == 0) {
                state.selectedProject = {};
            }
        },
        /* eslint-disable no-unused-vars */
        CREATE_NEW_PROJECT(state) {
            /* TODO */
        },
        SET_ERROR_MESSAGE(state, errorMessage) {
            state.editStudentProjectErrorMessage = errorMessage;
        }
    },
    actions: {
        async login(context, username, password){
            const dispatcherResponse = await network.getDispatcherState();
            console.log(dispatcherResponse);
            const allHeaders = dispatcherResponse.headers;
            console.log(allHeaders);
            // const url = allLinks['getAllStudentProjectsWithFilter'].url.replace("{QUERY}", username);
            // await context.dispatch('loadPage', url);
        },
        async getAllStudentProjects(context, search) {
            const dispatcherResponse = await network.getDispatcherState();
            const allLinks = parse(dispatcherResponse.headers.link);
            const url = allLinks['getAllStudentProjectsWithFilter'].url.replace("{QUERY}", search);
            await context.dispatch('loadPage', url);
        },
        async loadPage(context, url) {
            const getCollectionResponse = await network.getAllStudentProjectsState(url);
            const nextRelations = parse(getCollectionResponse.headers.link);
            context.commit("SET_PROJECTS", {
                projects: getCollectionResponse.data,
                createUrl: nextRelations['createStudentProject'],
                nextUrl: nextRelations['next'],
                prevUrl: nextRelations['prev']
            });
        },
        async loadNextPage(context) {
            await context.dispatch('loadPage', this.state.nextUrl.url);
        },
        async loadPrevPage(context) {
            await context.dispatch('loadPage', this.state.prevUrl.url);
        },
        async getSingleStudentProject(context, url) {
            const response = await network.getSingleStudentProjectState(url);
            const nextRelations = parse(response.headers.link);
            context.commit("SET_PROJECT", {
                project: response.data,
                editUrl: nextRelations['updateStudentProject'],
                deleteUrl: nextRelations['deleteStudentProject'],
            });
            const studentUrl = response.data.students.href;
            await context.dispatch('getAllStudentsOfProject', studentUrl);
        },
        async getAllStudentsOfProject(context, url) {
            const studentsResponse = await network.getAllStudentOfProjectState(url);
            const studentsRelations = parse(studentsResponse.headers.link);
            const extendedStudents = [];
            for (let student of studentsResponse.data) {
                const extendedStudent = await getStudentOfProject(student.self.href);
                extendedStudents.push(extendedStudent);
            }
            context.commit('SET_STUDENTS', {
                students: extendedStudents,
                showingAllLinkedStudents: studentsRelations['getAllStudentsOfStudentProject'],
                showingAllLinkableStudents: studentsRelations['getAllLinkableStudents']
            });
        },
        async toggleListOfStudents(context) {
            await context.dispatch('getAllStudentsOfProject', this.state.toggleStudentsOfProjectUrl.url);
        },
        async linkStudent(context, student) {
            const response = await network.linkStudent(student.linkHref, student);
            const updatedStudent = await getStudentOfProjectAfterRelationUpdate(response);
            context.commit('REPLACE_STUDENT', {oldStudent: student, newStudent: updatedStudent});
        },
        async unlinkStudent(context, student) {
            const response = await network.unlinkStudent(student.unlinkHref);
            const updatedStudent = await getStudentOfProjectAfterRelationUpdate(response);
            context.commit('REPLACE_STUDENT', {oldStudent: student, newStudent: updatedStudent});
        },
        async switchToEditMode(context) {
            context.commit('SET_EDIT_MODE', true);
        },
        async switchToDetailMode(context) {
            context.commit('SET_EDIT_MODE', false);
        },
        async createNewProject(context) {
            context.commit('CREATE_NEW_PROJECT');
        },
        async saveOrUpdateStudentProject(context) {
            if (this.state.selectedProject.name.trim().length == 0) {
                context.commit('SET_ERROR_MESSAGE', 'Project name must not be empty');
            } else {
                context.commit('SET_ERROR_MESSAGE', '');
                if (this.state.selectedProject.id == 0) {
                    await context.dispatch('postSingleStudentProject');
                } else {
                    await context.dispatch('putSingleStudentProject');
                }
            }
        },
        async postSingleStudentProject(context) {
            const postResponse = await network.postSingleStudentProject(this.state.createUrl.url, this.state.selectedProject);
            const nextRelations = parse(postResponse.headers.link);
            const allProjectsUrl = nextRelations['getAllStudentProjects'];
            const projectUrl = postResponse.headers.location;
            await context.dispatch('loadPage', allProjectsUrl.url);
            await context.dispatch('getSingleStudentProject', projectUrl);
        },
        async putSingleStudentProject(context) {
            const response = await network.updateSingleStudentProject(this.state.editUrl.url, this.state.selectedProject);
            const nextRelations = parse(response.headers.link);
            const allProjectsUrl = nextRelations['getAllStudentProjects'];
            const projectUrl = nextRelations['getStudentProject'];
            await context.dispatch('loadPage', allProjectsUrl.url);
            await context.dispatch('getSingleStudentProject', projectUrl.url);
        },
        /* eslint-disable no-unused-vars */
        async deleteSingleStudentProject(context) {
            /* TODO */
        },
        async cancelEditAndReloadProject(context) {
            context.commit('SET_EDIT_MODE', false);
            context.commit('SET_ERROR_MESSAGE', '');
            await context.dispatch('getSingleStudentProject', this.state.selectedProject.self.href);
        }
    },
    getters: {
        isCreateAllowed(state) {
            return state.createUrl != undefined;
        },
        isProjectEditable(state) {
            return state.editUrl != undefined;
        },
        isProjectDeletable(state) {
            return state.deleteUrl != undefined;
        },
        isNextPageAvailable(state) {
            return state.nextUrl != undefined;
        },
        isPrevPageAvailable(state) {
            return state.prevUrl != undefined;
        }
    }
})

