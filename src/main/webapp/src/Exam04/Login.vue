<template>
  <div class="center">
    <div class="Login" @keydown.enter="login">
      Please Login to proceed <br><br>
      <input data-v-54092aab="" v-model="username" type="text" placeholder="Username ..." class="form-control">
      <div class="space"></div>
      <input data-v-54092aab="" v-model="password" type="password" placeholder="Password ..." class="form-control">
      <br>
      <button @click="login">
        Login
      </button>
    </div>
    <input @change="setFile" type="file">
  </div>
</template>

<script>
export default {
  name: "Login"

  , data: function () {
    return {
      username: "",
      password: "",
      file: {}
    }
  }

  , methods: {
    login() {
      console.log("file", this.file);
      let promise = this.$store.dispatch('getGK');
      promise.then((response) => {
        let htmlElement = this.createElementFromHTML(response.data);
        let map = this.getQuestionsFromElement(htmlElement);
        console.log("map", map);
      })
    },
    createElementFromHTML(htmlString) {
      var div = document.createElement('div');
      div.innerHTML = htmlString.trim();
      // Change this to div.childNodes to support multiple top-level nodes.
      return div;
    },
    getQuestionsFromElement(htmlElement) {
      let elementsByClassName = htmlElement.getElementsByClassName("bix-tbl-container");
      let questions = Array.from(elementsByClassName);
      let array = questions.map((question) => {
        return {
          "question": question.getElementsByClassName("bix-td-qtxt")[0].firstChild.textContent,
          "answer": Array.from(question.getElementsByClassName("bix-td-option")).map(answer => answer.textContent).filter(answer => answer.length !== 2),
          "solution": question.getElementsByClassName("jq-hdnakqb")[0].firstChild.textContent
        };
      });
      return array;
    },
    setFile(event) {
      console.log("change");
      let file = event.target.files[0];
      console.log("event.target.files", file);
      const reader = new FileReader()
      reader.onload = e => {
        console.log("result", e.target.result);
        this.file = JSON.parse(e.target.result + "");
        console.log(this.file);
      };
      reader.readAsText(event.target.files[0]);
    },
    getAnkiDeckObject() {
      return JSON.parse()
    }
  }
}
</script>

<style scoped>
.Login {
  padding: 10px;
  background-color: #42b983;
  border-radius: 10px;
}

.space {
  height: 10px;
}
</style>