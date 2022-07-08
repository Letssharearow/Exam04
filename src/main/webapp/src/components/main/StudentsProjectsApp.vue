<template>
  <div class="studentprojectsapp">
    <StudentProjectsPanel/>
    <StudentProjectPanel/>
  </div>
</template>

<script>
import StudentProjectsPanel from "@/components/studentprojects/StudentProjectsPanel";
import StudentProjectPanel from "@/components/studentproject/StudentProjectPanel";

export default {
  name: "StudentsProjectsApp",
  components: {StudentProjectPanel, StudentProjectsPanel}
}
</script>

<style scoped>
.studentprojectsapp {
  flex: 1;
  display: flex;
  flex-direction: row;
  align-items: stretch;
  min-width: 100%;
}
</style>
