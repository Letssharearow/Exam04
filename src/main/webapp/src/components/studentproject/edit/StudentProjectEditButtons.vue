<template>
  <div class="studentprojectbuttons">
    <StudentProjectSaveButton/>
    <StudentProjectCancelButton/>
  </div>
</template>

<script>
import StudentProjectSaveButton from "@/components/studentproject/edit/StudentProjectSaveButton";
import StudentProjectCancelButton from "@/components/studentproject/edit/StudentProjectCancelButton";

export default {
  name: "StudentProjectEditButtons",
  components: {StudentProjectCancelButton, StudentProjectSaveButton}
}
</script>

<style scoped>
.studentprojectbuttons {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
}

</style>
