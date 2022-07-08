<template>
  <div class="studentprojects">
    <div v-for="p in projects" v-bind:key="p.id" @click="selectRow(p)">
      <div class="studentproject">
        <span>{{ p.name }}</span>&nbsp;<span>({{ p.semester }})</span>
      </div>
    </div>
  </div>
</template>

<script>
import {mapState} from "vuex";

export default {
  name: "StudentProjects",
  mounted() {
    this.$store.dispatch("getAllStudentProjects", "");
  },
  computed: {
    ...mapState(['projects'])
  },
  methods: {
    selectRow: function (project) {
      this.$store.dispatch("getSingleStudentProject", project.self.href);
    }
  }
}
</script>

<style scoped>
.studentprojects {
  float: left;
  clear: both;
}

.studentproject {
  font-size: 24pt;
  margin-bottom: 10px;
}
</style>
