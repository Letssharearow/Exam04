<template>
  <div class="center">
  <div class="Login" @keydown.enter="login">
    Login to proceed <br><br>
  <input data-v-54092aab="" v-model="username"  type="text" placeholder="Username ..." class="form-control">
    <div class="space"></div>
  <input data-v-54092aab="" v-model="password"  type="password" placeholder="Password ..." class="form-control">
    <br><br>
    <button @click="login">
      Login
    </button>
  </div>
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
      this.$store.dispatch('login', {"username": this.username, "password": this.password});
      // this.username = "";
      // this.password = "";
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

.space{
  height: 10px;
}
</style>