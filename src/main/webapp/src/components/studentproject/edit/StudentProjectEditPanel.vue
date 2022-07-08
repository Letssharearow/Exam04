<template>
  <div>
    <StudentProjectEdit/>
    <hr>
    <StudentProjectEditButtons/>
  </div>
</template>

<script>
import StudentProjectEdit from "@/components/studentproject/edit/StudentProjectEdit";
import StudentProjectEditButtons from "@/components/studentproject/edit/StudentProjectEditButtons";

export default {
  name: "StudentProjectEditPanel",
  components: {StudentProjectEditButtons, StudentProjectEdit}
}
</script>

<style scoped>

</style>
