<template>
  <div class="studentprojectbuttons">
    <EditStudentProjectButton/>
    <DeleteStudentProjectButton/>
  </div>

</template>

<script>
import EditStudentProjectButton from "@/components/studentproject/detail/StudentProjectEditButton";
import DeleteStudentProjectButton from "@/components/studentproject/detail/StudentProjectDeleteButton";

export default {
  name: "StudentProjectDetailButtons",
  components: {DeleteStudentProjectButton, EditStudentProjectButton}
}
</script>

<style scoped>
.studentprojectbuttons {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
}
</style>
