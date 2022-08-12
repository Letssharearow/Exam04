<template>
  <div class="center">
    <div class="Login" @keydown.enter="login">
      Enter Link to download <br><br>
      <input data-v-54092aab="" v-model="url" type="text" placeholder="Link ..." class="form-control">
      <div class="space"></div>
      <br>
      <button @click="login">
        Login
      </button>
    </div>
    <input @change="setFile" type="file">
    <a id="downloadAnchorElem">Download</a>
  </div>
</template>

<script>
export default {
  name: "Login"

  , data: function () {
    return {
      url: "",
      password: "",
      file: {}
    }
  }

  , methods: {
    updateFile: function (allQuestions) {
      let tempFile = JSON.parse(JSON.stringify(this.file));
      console.log("tempFile before", tempFile);
      for (let i = 0; i < allQuestions.length; i++) {
        let random = this.random(11);
        if (!tempFile.notes[i]) {
          let tempNote = JSON.parse(JSON.stringify(tempFile.notes[0]));
          tempFile.notes.push(tempNote);
        }
        tempFile.notes[i].fields = allQuestions[i];
        tempFile.notes[i].guid = random;
      }
      console.log("tempFile after", tempFile);
      this.file = tempFile;
    }, login() {
      console.log("file", this.file);
      let promise = this.$store.dispatch('getGK', this.url);
      promise.then((response) => {
        let htmlElement = this.createElementFromHTML(response.data);
        let allQuestions = this.getQuestionsFromElement(htmlElement);
        console.log("allQuestions", allQuestions);
        this.updateFile(allQuestions);
        this.downloadJSON();
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


      // function isTextAnswer(answer) {
      //   return !answer.match("[ABCD]\\.");
      // }

      return questions.map((question) => {
        return [question.getElementsByClassName("bix-td-qtxt")[0].firstChild.textContent].concat(
            Array.from(question.getElementsByClassName("bix-td-option")).filter(answer => {
              return !(answer.firstChild instanceof HTMLAnchorElement);
            }).map(answer => answer.textContent)).concat(
            question.getElementsByClassName("jq-hdnakqb")[0].firstChild.textContent);
      });
    },
    setFile(event) {
      const reader = new FileReader()
      reader.onload = e => {
        this.file = JSON.parse(e.target.result + "");
      };
      reader.readAsText(event.target.files[0]);
    },
    downloadJSON() {
      var dataStr = "data:text/json;charset=utf-8," + encodeURIComponent(JSON.stringify(this.file));
      var dlAnchorElem = document.getElementById('downloadAnchorElem');
      dlAnchorElem.setAttribute("href", dataStr);
      dlAnchorElem.setAttribute("download", "deck.json");
      dlAnchorElem.click();
    },
    random(length) {
      var array = new Uint8Array(length);
      window.crypto.getRandomValues(array);
      var str = '';
      for (var i = 0; i < array.length; i++) {
        str += String.fromCharCode(array[i]);
      }
      console.log("str", str);
      return str;
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