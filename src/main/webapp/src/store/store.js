import Vue from 'vue'
import Vuex from 'vuex'
import network, {notAuthorized, notConnected} from '@/network/network';

Vue.use(Vuex)

export const store = new Vuex.Store({
    state: {
        currentToken: "",
        errorMessage: "",
        isAuthorized: false,
    },
    mutations: {
        SET_CURRENT_TOKEN(state, token) {
            state.currentToken = token;
        },
        SET_ERROR_MESSAGE(state, errorMessage) {
            state.errorMessage = errorMessage;
        },
        SET_AUTHORIZED(state, value) {
            state.isAuthorized = value;
        }
    },
    actions: {

        resetErrorMessage(context){
            context.commit("SET_ERROR_MESSAGE", "");
        },

        async setToken(context, usernameAndPassword){
            const meResponse = await network.getToken(usernameAndPassword.username, usernameAndPassword.password);
            if(await context.dispatch("handleResponse", meResponse)){
                context.commit("SET_CURRENT_TOKEN", meResponse.data);
                return true;
            }
            return false;
        },

        async getDispatcher(context){
            let firstDispatcherStateToken = await network.getDispatcherStateToken(context.state.currentToken);
            if(await context.dispatch("handleResponse", firstDispatcherStateToken)){
                context.commit("SET_AUTHORIZED", true);
                return true;
            }
            return false;
        },

        handleResponse(context, tokenResponse){
            if(tokenResponse === notConnected) {
                context.commit("SET_ERROR_MESSAGE", "Cannot Connect to Server");
            }
            else if(tokenResponse === notAuthorized){
                context.commit("SET_ERROR_MESSAGE", "wrong Username or Password");
            }
            else {
                return true;
            }
            return false;
        },

        reset(context){
            context.commit("SET_AUTHORIZED", false);
            context.commit("SET_CURRENT_TOKEN", "");
        },

        async login(context, usernameAndPassword){
            if(this.state.currentToken === "" || ! await context.dispatch("getDispatcher",context)){
                if(await context.dispatch("setToken", usernameAndPassword)){
                    await context.dispatch("getDispatcher",context);
                }
            }
        },

        async getGK(){
            return await network.getKnowledge();
        }
    },
    getters: {
        errorMessage(state){
            return state.errorMessage;
        },
        isAuthorized(state){
            return state.isAuthorized;
        }
    }
})

