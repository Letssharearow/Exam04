<template>
  <div class="studentproject">
    <div class="container">
      <div class="row">
        <h1>{{ selectedProject.name }}</h1>
      </div>
      <dl class="row">
        <dt class="col-sm-3">Type</dt>
        <dd class="col-sm-9">
          <StudentProjectType v-bind:project-type="selectedProject.projectType"/>
        </dd>
      </dl>
      <dl class="row">
        <dt class="col-sm-3">Semester</dt>
        <dd class="col-sm-9">{{ selectedProject.semester }}</dd>
      </dl>
      <hr>
      <div class="row">
        <div class="col-sm-9">
          {{ selectedProject.description }}
        </div>
      </div>
      <hr>
    </div>
  </div>
</template>

<script>
import {mapState} from "vuex";
import StudentProjectType from "@/components/studentproject/detail/StudentProjectType";

export default {
  name: "StudentProjectDetail",
  components: {StudentProjectType},
  computed: {
    ...mapState(['selectedProject'])
  }
}
</script>

<style scoped>
.studentproject {
  display: flex;
}
</style>
