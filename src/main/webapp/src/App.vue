<template>
  <div >
    <div id="app" v-if="!$store.getters.isAuthorized">

    <Login/>
    <ErrorMessage></ErrorMessage>
    </div>
    <div class="animation" v-if="$store.getters.isAuthorized">
      <div>
      <p class="center">

      Your login was successful
      </p>
      </div>
      <button @click="$store.dispatch('setUnauthorized')">
        Logout
      </button>
    </div>
  </div>
</template>

<script>
import Login from "@/Exam04/Login";
import ErrorMessage from "@/Exam04/ErrorMessage";

export default {
  name: 'App',
  components: {
    Login,
    ErrorMessage
  }
}
</script>


<style>

.center {
  margin: auto;
  border-radius: 10px;
  padding: 10px;
  width: max-content;
}
</style>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  min-height: 100vh;
  display: flex;
  flex-direction: column;

  background-color: #2c3e50;
}

.animation{
  width: 100vw;
  height: 100vh;
  animation: fade;
  animation-fill-mode: forwards;
  animation-duration: 2s;
}

@keyframes fade {
  0% {
    background-color: #2c3e50;
  }
  20% {
    background-color: #2c3e50;
  }
  100% {
    background-color: #007741;
  }
}


</style>
