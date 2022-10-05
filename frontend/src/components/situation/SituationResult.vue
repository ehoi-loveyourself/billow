<template>
  <!-- <div id="app">
    <PageLoader/> -->
  <header-bar />
  <SecondBar />
  <br />
  <h2
    style="
      color: white;
      margin-left: 70px;
      font-size: 1.2vw;
      font-weight: 400;
      margin-bottom: 1%;
    "
  >
    #{{ who }} #{{ genre }}
  </h2>
  <h2
    style="color: white; margin-left: 70px; font-size: 1.1vw; font-weight: 300"
  >
    딱 맞는 프로그램을 추천해드릴게요.
  </h2>
  <div style="padding-left: 70px; margin-top: 10px">
    <br />
    <div class="flex">
      <template v-if="conditionRecommend != null">
        <span v-for="condition in conditionRecommend">
          <router-link
            :to="{ name: 'detail' }"
            @click="moveProgramDetail(condition.id)"
            class="nav-link"
          >
            <img :src="condition.posterImg" alt="Image" />
          </router-link>
        </span>
      </template>
      <template v-else>추천 내역이 없습니다.</template>
    </div>
    <br /><br /><br /><br /><br />
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
  <br /><br /><br /><br /><br />
  <!-- </div> -->
</template>

<script>
import HeaderBar from "@/components/layout/HeaderNavBar.vue";
import SecondBar from "@/components/layout/SecondNavBar.vue";
import { mapState, mapActions } from "vuex";
// import PageLoader from "@/components/PageLoader.vue";

export default {
  name: "App",
  components: {
    HeaderBar,
    SecondBar,
    // PageLoader,
  },
  computed: {
    ...mapState(["who", "genre", "conditionRecommend"]),
  },
  created() {
    this.getConditionRecommendProgram();
  },
  methods: {
    ...mapActions(["getProgramDetail", "getConditionRecommendProgram"]),
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
  padding-right: 3%;
  width: 15vw;
  height: 22.5vw;
  padding-bottom: 3%;
}

.startButton {
  background-color: #ffffff;
  padding: 3px 30px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
}

.flex {
  display: flex;
  flex-wrap: wrap;
  align-content: stretch;
}

img:hover {
  filter: brightness(0.5);
}
</style>
