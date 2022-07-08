<template>
  <div class="studentprojectssearchdialog">
    <div class="input-group">
      <input type="text" class="form-control" placeholder="Search projects ..."
             v-model="search" @keyup.enter="startSearch">
      <div class="input-group-append">
        <button class="btn btn-secondary" type="button" @click="startSearch">
          <i class="fa fa-search"></i>
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import {mapState} from "vuex";

export default {
  name: "StudentProjectsSearchDialog",
  data: function () {
    return {
      search: ""
    }
  },
  methods: {
    startSearch: function () {
      this.$store.dispatch("getAllStudentProjects", this.search);
    }
  },
  computed: {
    ...mapState(['clearSearchField'])
  },
  watch: {
    clearSearchField(newValue, oldValue) {
      if (newValue == true && oldValue == false) {
        this.search = "";
      }
    }
  }
}
</script>

<style scoped>
.studentprojectssearchdialog {
  margin-bottom: 24px;
}
</style>
