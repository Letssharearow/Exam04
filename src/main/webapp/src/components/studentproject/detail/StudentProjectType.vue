<template>
  <div class="studentprojecttype">
    {{ type }}
  </div>
</template>

<script>
import {TYPE_MAP} from '../projectTypes';

export default {
  name: "StudentProjectType",
  props: {
    projectType: String
  },
  computed: {
    type: function () {
      return TYPE_MAP.get(this.projectType);
    }
  }
}
</script>

<style scoped>

</style>
