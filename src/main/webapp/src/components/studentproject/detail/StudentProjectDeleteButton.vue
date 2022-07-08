<template>
  <div class="deletestudentprojectbutton">
    <button
        v-if="isDeletable"
        class="btn btn-secondary"
        @click="openModalDialog()">Delete Project
    </button>
    <StudentProjectDeleteModalDialog
        v-bind:showDeleteDialog="showDeleteDialog"
        @yesDelete="deleteThisProject()" @cancelDelete="closeModelDialog()"/>
  </div>
</template>

<script>

import StudentProjectDeleteModalDialog from "@/components/studentproject/detail/StudentProjectDeleteModalDialog";

export default {
  name: "StudentProjectDeleteButton",
  components: {StudentProjectDeleteModalDialog},
  data: function () {
    return {
      showDeleteDialog: false
    }
  },
  methods: {
    openModalDialog: function () {
      this.showDeleteDialog = true;
    },
    deleteThisProject: function () {
      this.showDeleteDialog = false;
      this.$store.dispatch('deleteSingleStudentProject');
    },
    closeModelDialog: function () {
      this.showDeleteDialog = false;
    }
  },
  computed: {
    isDeletable() {
      return this.$store.getters.isProjectDeletable
    }
  }
}
</script>

<style scoped>
.deletestudentprojectbutton {
  margin-right: 20px;
}
</style>
