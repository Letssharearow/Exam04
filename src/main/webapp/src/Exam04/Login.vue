<template>
  <div class="center">

    Login

  <div class="Login" @keydown.enter="login">
  <input data-v-54092aab="" v-model="username"  type="text" placeholder="Username ..." class="form-control">
    <div class="space"></div>
  <input data-v-54092aab="" v-model="password"  type="text" placeholder="Password ..." class="form-control">
  </div>

    <button @click="login">
      login
    </button>
  </div>
</template>

<script>
export default {
  name: "Login"

  ,  data: function () {
    return {
      username: "",
      password: ""
    }
  }

  ,methods: {
    login(){
      console.log(this.username, this.password);
      this.username = "";
      this.password = "";
      this.$store.dispatch('login', this.username, this.password);
    }
  }
}
</script>

<style scoped>
.Login{
  padding: 10px;
  background-color: #42b983;
  border-radius: 10px;
}

.center {
  margin: auto;
  width: 50%;
  border-radius: 10px;
  padding: 10px;
  width: max-content;
}

.space{
  height: 10px;
}
</style>