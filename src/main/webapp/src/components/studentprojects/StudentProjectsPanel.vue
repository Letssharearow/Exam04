<template>
  <div class="studentprojectspanel">
    <div>
      <StudentProjectsSearchDialog/>
      <StudentProjects/>
    </div>
    <StudentProjectsButton/>
  </div>
</template>

<script>
import StudentProjects from "@/components/studentprojects/StudentProjects";
import StudentProjectsSearchDialog from "@/components/studentprojects/StudentProjectsSearchDialog";
import StudentProjectsButton from "@/components/studentprojects/StudentProjectsButton";

export default {
  name: "StudentProjectsPanel",
  components: {StudentProjectsButton, StudentProjectsSearchDialog, StudentProjects},
}
</script>

<style scoped>
.studentprojectspanel {
  flex-basis: 30%;
  background: #2c3e50;
  color: white;
  padding: 10px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
</style>
