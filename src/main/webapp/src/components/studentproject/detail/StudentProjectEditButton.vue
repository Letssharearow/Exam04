<template>
  <div class="editstudentprojectbutton">
    <button
        class="btn btn-secondary"
        @click="editThisProject()">Edit Project
    </button>
  </div>
</template>

<script>

export default {
  name: "StudentProjectEditButton",
  methods: {
    editThisProject: function () {
      this.$store.dispatch('switchToEditMode');
    }
  },
  computed: {
    isEditable() {
      return this.$store.getters.isProjectEditable
    }
  }
}
</script>

<style scoped>
.editstudentprojectbutton {
  margin-right: 20px;
}
</style>
