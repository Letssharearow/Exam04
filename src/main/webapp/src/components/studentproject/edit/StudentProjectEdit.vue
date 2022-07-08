<template>
  <div>
    <form>
      <div v-if="editStudentProjectErrorMessage != ''" class="errorMessage">{{ editStudentProjectErrorMessage }}</div>
      <div class="form-group">
        <label for="name">Project name</label>
        <input type="text" class="form-control"
               v-model="selectedProject.name"
               id="name"
               placeholder="Enter project name">
      </div>
      <div class="form-group">
        <label for="description">Project description</label>
        <textarea class="form-control"
                  v-model="selectedProject.description"
                  id="description" rows="4"
                  placeholder="Enter project description"></textarea>
      </div>
      <div class="form-group">
        <label for="type">Project type</label>
        <select class="form-control"
                v-model="ptype"
                id="type">
          <option v-for="t in allowedTypes" v-bind:key="t">{{ t }}</option>
        </select>
      </div>
      <div class="form-group">
        <label for="semester">Semester</label>
        <select class="form-control"
                v-model="selectedProject.semester"
                id="semester">
          <option>2021ss</option>
          <option>2021ws</option>
          <option>2022ss</option>
          <option>2022ws</option>
          <option>2023ss</option>
        </select>
      </div>

    </form>
  </div>
</template>

<script>
import {mapState} from "vuex";
import {TYPE_MAP} from '../projectTypes';

export default {
  name: "StudentProjectEdit",
  data: function () {
    return {
      allowedTypes: Array.from(TYPE_MAP.values())
    }
  },
  computed: {
    ...mapState(['selectedProject', 'editStudentProjectErrorMessage']),
    ptype: {
      get() {
        return TYPE_MAP.get(this.selectedProject.projectType);
      },
      set(value) {
        const type = [...TYPE_MAP].find(([, val]) => val == value)[0];
        this.selectedProject.projectType = type;
      }
    }
  }
}
</script>

<style scoped>
.errorMessage {
  color: red;
  font-size: larger;
  font-weight: bold;
}
</style>
