<template>
  <div class="studentprojectcancelbutton">
    <button
        class="btn btn-secondary"
        @click="cancelEdit()">Cancel
    </button>
  </div>
</template>

<script>
export default {
  name: "StudentProjectCancelButton",
  methods: {
    cancelEdit: function () {
      this.$store.dispatch('cancelEditAndReloadProject');
    }
  }
}
</script>

<style scoped>
.studentprojectcancelbutton {
  margin-right: 20px;
}
</style>
