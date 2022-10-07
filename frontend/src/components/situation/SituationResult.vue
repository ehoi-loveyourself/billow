<template>
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
  <LoadingSpinner v-if="isLoading" />
  <div v-else style="padding-left: 70px; padding-right: 70px; margin-top: 10px">
    <br />
    <div class="flex">
      <span v-for="condition in conditionRecommend">
        <div class="box-wrap">
          <div class="box">
            <div class="img">
              <div @click="moveProgramDetail(condition.id)">
                <img :src="condition.posterImg" alt="Image" />
              </div>
            </div>
            <div class="info">
              <div class="title">{{ condition.title }}</div>
              <div class="detailbox">
                <span class="detailbox_design" v-if="condition.age">{{
                  condition.age
                }}</span
                ><span
                  class="detailbox_design"
                  v-if="condition.broadcastingDay"
                  >{{ condition.broadcastingDay }}</span
                ><span class="detailbox_design" v-if="condition.genres[0]">{{
                  condition.genres[0]
                }}</span
                ><span
                  class="detailbox_design"
                  v-if="condition.broadcastingStation"
                  >{{ condition.broadcastingStation }}</span
                ><span
                  class="detailbox_design"
                  v-if="condition.broadcastingTime"
                  >{{ condition.broadcastingTime }} ~</span
                >
              </div>
            </div>
          </div>
        </div>
      </span>
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
</template>

<script>
import HeaderBar from "@/components/layout/HeaderNavBar.vue";
import SecondBar from "@/components/layout/SecondNavBar.vue";
import { mapState, mapActions, mapMutations } from "vuex";
import LoadingSpinner from "@/components/load/LoadingSpinner.vue";

export default {
  name: "App",
  components: {
    HeaderBar,
    SecondBar,
    LoadingSpinner,
  },
  computed: {
    ...mapState(["who", "genre", "conditionRecommend", "isLoading"]),
  },
  async created() {
    await this.getConditionRecommendProgram();
  },
  methods: {
    ...mapActions(["getProgramDetail", "getConditionRecommendProgram"]),
    ...mapMutations(["SET_PROGRAM_ID"]),
    moveProgramDetail(programId) {
      this.SET_PROGRAM_ID(programId);
      window.location.replace(`/detail`);
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
  width: 14vw;
  height: 21vw;
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

.box .info .detailbox {
  font-size: 12px;
}

.box .info .detailbox_design {
  display: inline-block;
  background: rgb(46, 47, 49);
  color: rgb(255, 255, 255);
  font-size: 12px;
  font-weight: 400;
  vertical-align: top;
  line-height: 18px;
  padding: 1px 5px;
  border: none;
  margin: 0px 10px 0px 0px;
  margin-bottom: 5px;
}

.box-wrap {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-wrap: wrap;
  align-content: stretch;
}

.box {
  position: relative;
  width: 100%;
  border: none;
  cursor: pointer;
  overflow: hidden;
}

.box img {
  transition: all 0.3s ease-in-out;
}

.box .info {
  position: absolute;
  left: 5px;
  bottom: 10px;
  color: #fff;
  box-sizing: border-box;
  opacity: 0;
  transition: all 0.3s ease-in-out;
}

.box .info .title {
  font-size: 1vw;
  font-weight: 400;
  text-align: left;
  white-space: normal;
  margin-bottom: 3%;
}

.box:hover .info {
  opacity: 1;
}

.box:hover img {
  opacity: 0.2;
}

.box:hover:before {
  width: 60px;
}

.box:hover:after {
  height: 60px;
}

@font-face {
  font-family: "GoyangIlsan";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/GoyangIlsan.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

body {
  background-color: #1a237e;
  font-family: "GoyangIlsan";
}
</style>
