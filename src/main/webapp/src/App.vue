<template>
  <div id="app">
    <Login/>
  </div>
</template>

<script>
import Login from "@/Exam04/Login";

export default {
  name: 'App',
  components: {
    Login,
  }
}
</script>

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
</style>
