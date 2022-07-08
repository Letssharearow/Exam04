<template>
  <div class="studentsinproject">
    <div class="container">
      <table class="table table-borderless">
        <thead>
        <tr>
          <th scope="col">Degree programme</th>
          <th scope="col">Semester</th>
          <th scope="col">First name</th>
          <th scope="col">Last name</th>
          <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="s in relatedStudents" v-bind:key="s.id">
          <td>{{ s.studyProgram }}</td>
          <td>{{ s.studentSemester }}</td>
          <td>{{ s.firstName }}</td>
          <td>{{ s.lastName }}</td>
          <td class="align-text-top">
            <div v-if="s.linkHref">
              <button class="btn btn-outline-secondary" type="button" @click="linkAction(s)" v-bind:key="s.id">
                Link
              </button>
            </div>
            <div v-if="s.unlinkHref">
              <button class="btn btn-outline-secondary" type="button" @click="unlinkAction(s)" v-bind:key="s.id">
                Unlink
              </button>
            </div>
          </td>
        </tr>
        </tbody>
      </table>
      <div class="togglebutton">
        <button class="btn btn-dark" type="button" @click="toggleStudentList">
          <div v-if="showingLinkedStudents">Show all</div>
          <div v-else>Show only linked</div>
        </button>
      </div>
    </div>
  </div>
</template>

<script>

import {mapState} from "vuex";

export default {
  name: "StudentsInProject",
  computed: {
    ...mapState(['relatedStudents', 'showingLinkedStudents'])
  },
  methods: {
    linkAction: function (student) {
      this.$store.dispatch('linkStudent', student);
    },
    unlinkAction: function (student) {
      this.$store.dispatch('unlinkStudent', student);
    },
    toggleStudentList: function () {
      this.$store.dispatch('toggleListOfStudents');
    }
  }
}
</script>

<style scoped>
.fa-link {
  color: black;
}

.fa-unlink {
  color: black;
}

.togglebutton {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  margin-right: 20px;
}
</style>
