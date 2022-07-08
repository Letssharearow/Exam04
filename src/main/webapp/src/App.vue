<template>
  <div id="app">
    <StudentsProjectsApp/>
  </div>
</template>

<script>
import StudentsProjectsApp from "@/components/main/StudentsProjectsApp";

export default {
  name: 'App',
  components: {
    StudentsProjectsApp,
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
}
</style>
