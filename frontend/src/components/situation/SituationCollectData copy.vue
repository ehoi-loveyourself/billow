<template>
  <img :src="this.random.posterImg" alt="Image" @click="select()" />
</template>

<script>
import { mapState, mapActions } from "vuex";

export default {
  name: "SituationRandom",
  props: ["random"],

  computed: {
    ...mapState(["who", "genre", "randomProgram"]),
  },
  created() {},
  methods: {
    ...mapActions(["getRandomProgram"]),
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
  filter: brightness(0.6);
  transition: all 0.5s linear;
  transform: scale(1.2);
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
