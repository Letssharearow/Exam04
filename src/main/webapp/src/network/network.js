import axios from 'axios';

export const notAuthorized = "not Authorized";
export const notConnected = "not Connected";

class NetworkService {

    httpClient = axios.create({
        headers: {
            "Accept": "application/json",
        }
    });

    getDispatcherState() {
        return this.httpClient.get("http://localhost:8080/login/api/");
    }

    getDispatcherStateToken(token) {
        const config = {
            headers: { Authorization: "Bearer "+ token }
        };
        return this.httpClient.get("http://localhost:8080/login/api/", config);
    }

    getToken(username, password){
        this.httpClient.interceptors.response.use(response => {
            return response;
        }, (error) => {
            console.log(error.statusCode)
            if(error.statusCode === 401){
                return notAuthorized;
            }
            return error.statusCode;
        });

        return this.httpClient.get("http://localhost:8080/login/api/me", {
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
