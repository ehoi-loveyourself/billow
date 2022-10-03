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
    딱 맞는 프로그램을 추천해드릴게요.
  </h2>
  <div style="padding-left: 70px; margin-top: 10px">
    <br />
    <span v-for="condition in conditionRecommend">
      <router-link
        :to="{ name: 'detail' }"
        @click="moveProgramDetail(condition.id)"
        class="nav-link"
      >
        <img :src="condition.posterImg" alt="Image" />
      </router-link>
    </span>
    <br /><br />
  </div>
  <p style="text-align: center">
    <a class="startButton" href="#" role="button" style="border-radius: 15px">
      <router-link
        :to="{ name: 'main' }"
        class="nav-link"
        style="font-size: 20px; padding: 10px; color: black"
      >
        홈으로
      </router-link>
    </a>
  </p>
</template>

<script>
import HeaderBar from "@/components/layout/HeaderNavBar.vue";
import SecondBar from "@/components/layout/SecondNavBar.vue";
import { mapState } from "vuex";

export default {
  name: "SituationSelect",
  components: {
    HeaderBar,
    SecondBar,
  },
  computed: {
    ...mapState(["who", "genre", "conditionRecommend"]),
  },
  methods: {
    moveProgramDetail(programId) {
      this.getProgramDetail(programId);
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
.startButton {
  background-color: #ffffff;
  padding: 3px 30px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
}
</style>
