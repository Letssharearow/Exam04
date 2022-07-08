<template>
  <div class="studentprojectpanel" v-if="isProjectSelected()">
    <div v-if="editMode == false">
      <StudentProjectDetailPanel/>
    </div>
    <div v-else>
      <StudentProjectEditPanel/>
    </div>
  </div>
</template>

<script>
import {mapState} from "vuex";
import StudentProjectDetailPanel from "@/components/studentproject/detail/StudentProjectDetailPanel";
import StudentProjectEditPanel from "@/components/studentproject/edit/StudentProjectEditPanel";

export default {
  name: "StudentProjectPanel",
  components: {
    StudentProjectEditPanel,
    StudentProjectDetailPanel,
  },
  methods: {
    isProjectSelected() {
      return Object.keys(this.selectedProject).length != 0
    }
  },
  computed: {
    ...mapState(['editMode', 'selectedProject'])
  }
}
</script>

<style scoped>
.studentprojectpanel {
  flex-basis: 70%;
  background: aquamarine;
  padding: 10px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

</style>
