<template>
  <img :src="this.random.posterImg" alt="Image" @click="select()" />
</template>

<script>
import { mapMutations } from "vuex";

export default {
  name: "SituationRandom",
  props: ["random"],
  data() {
    return {
      checked: false,
    };
  },
  methods: {
    ...mapMutations(["ADD_CONDITION_ID", "DELETE_CONDITION_ID"]),
    select() {
      if (!this.checked) {
        this.checked = true;
        this.ADD_CONDITION_ID(this.random.id);
      } else {
        this.checked = false;
        this.DELETE_CONDITION_ID(this.random.id);
      }
    },
  },
};
</script>

<style scoped>
h2 {
  font-weight: 500;
  font-size: 1.4vw;
}
img {
  padding-right: 0.5%;
  padding-bottom: 0.5%;
  margin-right: 0;
  width: 16.1%;
}
img:hover {
  filter: brightness(0.2);
  transition: all 0s linear;
  transform: scale(1);
}
.startButton {
  background-color: #ffffff;
  padding: 3px 30px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
}
</style>
