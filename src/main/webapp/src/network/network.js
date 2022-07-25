import axios from 'axios';

class NetworkService {

    httpClient = axios.create({
        headers: {
            "Accept": "application/json",
        }
    });

    getDispatcherState() {
        console.log(this.httpClient.defaults.headers);
        const username = "username";
        const password = "password";
        console.log(username, password);
        return this.httpClient.get("http://localhost:8080/login/api/");

    }

    getToken(){
        const username = "username";
        const password = "password";
        return this.httpClient.get("http://localhost:8080/login/api", {
            auth: {
                username,
                password
            }
        });
    }

    getAllStudentProjectsState(url) {
        return this.httpClient.get(url);
    }

    getSingleStudentProjectState(url) {
        return this.httpClient.get(url);
    }

    postSingleStudentProject(url, student) {
        return this.httpClient.post(url, student);
    }

    updateSingleStudentProject(url, student) {
        return this.httpClient.put(url, student);
    }

    /* eslint-disable no-unused-vars */
    deleteSingleStudentProject(url) {
        /* TODO */
    }

    getAllStudentOfProjectState(url) {
        return this.httpClient.get(url);
    }

    getSingleStudent(url) {
        return this.httpClient.get(url);
    }

    linkStudent(url, student) {
        return this.httpClient.put(url, student);
    }

    unlinkStudent(url) {
        return this.httpClient.delete(url);
    }
}

export default new NetworkService();
