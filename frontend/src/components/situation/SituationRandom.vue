<template>
  <input @click="select()" type="checkbox" name="choice" class="checkbox" :id="this.random.programId" />
  <label for="this.random.programId">
    <img :src="this.random.posterImg" alt="Image" />
  </label>
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
  width: 14.3vw;
  height: 21vw;
  margin-bottom: 5%;
  padding-right: 5%;
}

.startButton {
  background-color: #ffffff;
  padding: 3px 30px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
}

/* img:active{
  border:3px solid yellow;
} */

.checkbox {
  position: absolute;
}

input[type="checkbox"] {
  width: 1.8vw;
  height: 1.8vw;
  cursor: pointer;
  background-color: transparent;
}
</style>
