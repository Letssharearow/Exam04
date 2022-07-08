<template>
  <div class="studentprojectdetail">
    <StudentProjectDetail/>
    <StudentsInProject/>
    <hr>
    <StudentProjectDetailButtons/>
  </div>
</template>

<script>
import StudentProjectDetail from "@/components/studentproject/detail/StudentProjectDetail";
import StudentProjectDetailButtons from "@/components/studentproject/detail/StudentProjectDetailButtons";
import StudentsInProject from "@/components/students/StudentsInProject";

export default {
  name: "StudentProjectDetailPanel",
  components: {StudentsInProject, StudentProjectDetail, StudentProjectDetailButtons}
}
</script>

<style scoped>
.studentprojectdetail {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
</style>
