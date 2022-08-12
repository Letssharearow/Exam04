import axios from 'axios';

export const notAuthorized = "not Authorized";
export const notConnected = "not Connected";

class NetworkService {

    httpClient = axios.create({
        headers: {
            "Accept": "*/*",
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

    getDispatcherStateToken(token) {
        const config = {
            headers: { Authorization: "Bearer "+ token }
        };
        this.httpClient.interceptors.response.use(this.onFulfilled, this.onRejected);
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

    getKnowledge(){
        this.httpClient.interceptors.response.use(this.onFulfilled, this.onRejected);
        return this.httpClient.get("http://localhost:8080/login/api/knowledge");
    }
}

export default new NetworkService();
