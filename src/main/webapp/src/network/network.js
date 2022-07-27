import axios from 'axios';

export const notAuthorized = "not Authorized";
export const notConnected = "not Connected";

class NetworkService {

    httpClient = axios.create({
        headers: {
            "Accept": "application/json",
        }
    });

    onFulfilled = response => {
        return response;
    };

    onRejected = (error) => {
        //https://axios-http.com/docs/handling_errors
        if (error.response) {
            if(error.response.status === 401){
                return notAuthorized;
            }
        } else if (error.request) {
            console.log("error.request", error.request);
            return notConnected;
        } else {
            // Something happened in setting up the request that triggered an Error
            console.log('Error', error.message);
        }
        return error;
    };

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
        this.httpClient.interceptors.response.use(this.onFulfilled, this.onRejected);

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
