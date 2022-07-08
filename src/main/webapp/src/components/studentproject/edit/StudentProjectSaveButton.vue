<template>
  <div class="studentprojectsavebutton">
    <button
        class="btn btn-secondary"
        @click="saveProject()">Save
    </button>
  </div>
</template>

<script>
export default {
  name: "StudentProjectSaveButton",
  methods: {
    saveProject: function () {
      this.$store.dispatch('saveOrUpdateStudentProject');
    }
  }
}
</script>

<style scoped>
.studentprojectsavebutton {
  margin-right: 20px;
}
</style>
