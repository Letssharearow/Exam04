<template>
<div @click="setErrorMessageNull" class="error" v-if="this.getErrorMessage">
  {{this.getErrorMessage}}
</div>
</template>

<script>
export default {
  name: "ErrorMessage",

  methods: {
    setErrorMessageNull() {
      this.$store.dispatch("resetErrorMessage");
    }
  },

  computed: {
    getErrorMessage(){
      return this.$store.getters.errorMessage;
    }
  }
}
</script>

<style scoped>
.error{
  background-color: #fd3333;
  color: #650101;
}
</style>