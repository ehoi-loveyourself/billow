<template>
  <header-bar />
  <SecondBar />
  <br />
  <div style="padding-left:70px; padding-right:70px">
  <h2 style="color: white; font-size: 1.2vw; font-weight: 400; margin-bottom: 1%;">
    #{{ who }} #{{ genre }}
  </h2>
  <h2 style="color: white; font-size: 1.1vw; font-weight: 300">
    위와 같은 상황일 때 {{userNickName}}님은 어떤 프로그램을 보셨나요? 다른 사용자들을 위해
    회원님의 취향을 알려주세요!
  </h2>
  <br />

    <div class="wrap" v-for="random in randomProgram">
      <SituationRandom v-bind:random="random" />

  </div><br />
</div>
<br/><br/><br/>
  <p style="text-align: center">
    <a class="startButton" href="#" role="button" style="border-radius: 15px">
      <router-link :to="{ name: 'situationresult' }" class="nav-link"
        style="font-size: 20px; padding: 10px; color: black" @click="coditionRecommend()">
        추천 프로그램 확인하기
      </router-link>
    </a>
  </p>
  <br /><br /><br />
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
    ...mapState(["who", "genre", "randomProgram", "userNickName"]),
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
  margin-right: 0;
  margin-bottom: 0;
}

img:hover {
  filter: brightness(0.3);
  /* transition: all 0.5s linear;
  transform: scale(1.2); */
  cursor: pointer;
}

.startButton {
  background-color: #ffffff;
  padding: 3px 30px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
}

.wrap {
  display:inline-flex;
  flex-wrap:wrap;
  align-content:stretch;
}
</style>
