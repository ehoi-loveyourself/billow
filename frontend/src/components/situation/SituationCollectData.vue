<template>
  <!-- <b-row> -->
  <!-- <b-col cols="8"> -->
  <header-bar />
  <!-- </b-col> -->
  <!-- <b-col cols="4">  -->
  <SecondBar />
  <!-- </b-col> -->
  <!-- </b-row> -->
  <br />
  <h2
    style="color: white; margin-left: 67px; font-size: 1.1vw; font-weight: 300"
  >
    #{{ who }} #{{ genre }}
  </h2>
  <h2
    style="color: white; margin-left: 67px; font-size: 1.1vw; font-weight: 300"
  >
    위와 같은 상황일 때 ㅇㅇ님은 어떤 프로그램을 보셨나요? 다른 사용자들을 위해
    회원님의 취향을 알려주세요!
  </h2>
  <div style="padding-left: 70px; margin-top: 10px">
    <br />
    <span v-for="random in randomProgram">
      <SituationRandom v-bind:random="random" />
    </span>
    <br /><br />
  </div>
  <p style="text-align: center">
    <a class="startButton" href="#" role="button" style="border-radius: 15px">
      <router-link
        :to="{ name: 'situationresult' }"
        class="nav-link"
        style="font-size: 20px; padding: 10px; color: black"
        @click="coditionRecommend()"
      >
        NEXT
      </router-link>
    </a>
  </p>
</template>

<script>
import HeaderBar from "@/components/layout/HeaderNavBar.vue";
import SecondBar from "@/components/layout/SecondNavBar.vue";
import { mapState, mapActions, mapMutations } from "vuex";
import SituationRandom from "@/components/situation/SituationRandom.vue";

export default {
  name: "SituationSelect",
  components: {
    HeaderBar,
    SecondBar,
    SituationRandom,
  },
  computed: {
    ...mapState(["who", "genre", "randomProgram"]),
  },
  created() {
    this.getRandomProgram();
  },
  methods: {
    ...mapActions(["getRandomProgram", "getConditionRecommendProgram"]),
    ...mapMutations(["CLEAR_CONDITION_ID"]),

    coditionRecommend() {
      this.getConditionRecommendProgram();
      this.CLEAR_CONDITION_ID();
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
