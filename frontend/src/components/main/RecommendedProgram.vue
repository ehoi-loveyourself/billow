<template>
  <h2 style="color: white">
    {{ userAge }}대 {{ userGender }}자에게 인기 많은 프로그램
  </h2>
  <div v-if="genderAgeProgram != null && genderAgeProgram.length > 0">
    <splide id="carousel_recommend" :options="options">
      <splide-slide v-for="genderAge in genderAgeProgram">
        <div class="box-wrap">
          <div class="box">
            <div class="img">
              <a class="enterDetail" href="#">
                <div @click="moveProgramDetail(genderAge.id)">
                  <img :src="genderAge.posterImg" alt="Hover Effect" />
                </div>
              </a>
            </div>
            <div class="info">
              <div class="title">{{ genderAge.title }}</div>
              <div class="detailbox">
                <span class="detailbox_design" v-if="genderAge.age">{{
                  genderAge.age
                }}</span
                ><span
                  class="detailbox_design"
                  v-if="genderAge.broadcastingDay"
                  >{{ genderAge.broadcastingDay }}</span
                ><span class="detailbox_design" v-if="genderAge.genres[0]">{{
                  genderAge.genres[0]
                }}</span
                ><span
                  class="detailbox_design"
                  v-if="genderAge.broadcastingStation"
                  >{{ genderAge.broadcastingStation }}</span
                >
              </div>
            </div>
          </div>
        </div>
      </splide-slide>
    </splide>
  </div>

  <div v-else>
    <h2
      style="
        color: #a1a1a1;
        font-size: 1.1vw;
        font-weight: 300;
        margin-top: 1.5%;
        margin-bottom: 4%;
      "
    >
      성,연령별 추천 프로그램이 없습니다.
    </h2>
  </div>

  <div v-if="actorProgram != null && actorProgram.length > 0">
    <h2 style="color: white">
      혹시 {{ actorProgram[0].actorName }}
      님에게 관심이 있으신가요?
    </h2>
    <splide id="carousel_recommend" :options="options">
      <splide-slide v-for="actor in actorProgram">
        <div class="box-wrap">
          <div class="box">
            <div class="img">
              <a class="enterDetail" href="#">
                <div @click="moveProgramDetail(actor.id)">
                  <img :src="actor.posterImg" alt="Hover Effect" />
                </div>
              </a>
            </div>
            <div class="info">
              <div class="title">{{ actor.title }}</div>
              <div class="detailbox">
                <span class="detailbox_design" v-if="actor.age">{{
                  actor.age
                }}</span
                ><span class="detailbox_design" v-if="actor.broadcastingDay">{{
                  actor.broadcastingDay
                }}</span
                ><span class="detailbox_design" v-if="actor.genres[0]">{{
                  actor.genres[0]
                }}</span
                ><span
                  class="detailbox_design"
                  v-if="actor.broadcastingStation"
                  >{{ actor.broadcastingStation }}</span
                >
              </div>
            </div>
          </div>
        </div>
      </splide-slide>
    </splide>
  </div>

  <h2 style="color: white">지금 방송 중인 프로그램(ON AIR)</h2>
  <template v-if="onairProgram != null && onairProgram.length > 0">
    <splide id="carousel_recommend" :options="options">
      <splide-slide v-for="onair in onairProgram">
        <div class="box-wrap">
          <div class="box">
            <div class="img">
              <a class="enterDetail" href="#">
                <div @click="moveProgramDetail(onair.id)">
                  <img :src="onair.posterImg" alt="Hover Effect" />
                </div>
              </a>
            </div>
            <div class="info">
              <div class="title">{{ onair.title }}</div>
              <div class="detailbox">
                <span class="detailbox_design" v-if="onair.age">{{
                  onair.age
                }}</span
                ><span class="detailbox_design" v-if="onair.broadcastingDay">{{
                  onair.broadcastingDay
                }}</span
                ><span class="detailbox_design" v-if="onair.genres[0]">{{
                  onair.genres[0]
                }}</span
                ><span
                  class="detailbox_design"
                  v-if="onair.broadcastingStation"
                  >{{ onair.broadcastingStation }}</span
                ><span class="detailbox_design" v-if="onair.broadcastingTime"
                  >{{ onair.broadcastingTime }} ~</span
                >
              </div>
            </div>
          </div>
        </div>
      </splide-slide>
    </splide>
  </template>

  <template v-else>
    <h2
      style="
        color: #a1a1a1;
        font-size: 1.1vw;
        font-weight: 300;
        margin-top: 1.5%;
        margin-bottom: 10%;
      "
    >
      현재 방송 중인 프로그램이 없습니다.
    </h2>
  </template>

  <h2 style="color: white">인기 프로그램</h2>
  <splide
    v-if="hotProgram != null && hotProgram.length > 0"
    id="carousel_recommend"
    :options="options"
  >
    <splide-slide v-for="hot in hotProgram">
      <div class="box-wrap">
        <div class="box">
          <div class="img">
            <a class="enterDetail" href="#">
              <div @click="moveProgramDetail(hot.id)">
                <img :src="hot.posterImg" alt="Hover Effect" />
              </div>
            </a>
          </div>
          <div class="info">
            <div class="title">{{ hot.title }}</div>
            <div class="detailbox">
              <span class="detailbox_design" v-if="hot.age">{{ hot.age }}</span
              ><span class="detailbox_design" v-if="hot.broadcastingDay">{{
                hot.broadcastingDay
              }}</span
              ><span class="detailbox_design" v-if="hot.genres[0]">{{
                hot.genres[0]
              }}</span
              ><span class="detailbox_design" v-if="hot.broadcastingStation">{{
                hot.broadcastingStation
              }}</span>
            </div>
          </div>
        </div>
      </div>
    </splide-slide>
  </splide>

  <span v-else>
    <h2
      style="
        color: #a1a1a1;
        font-size: 1.1vw;
        font-weight: 300;
        margin-top: 1.5%;
        margin-bottom: 4%;
      "
    >
      인기 프로그램이 없습니다.
    </h2>
  </span>

  <h2 style="color: white">신규 프로그램을 추천드려요!</h2>
  <splide id="carousel_recommend" :options="options">
    <template v-if="newProgram != null && newProgram.length > 0">
      <splide-slide v-for="newProgram in newProgram">
        <div class="box-wrap">
          <div class="box">
            <div class="img">
              <a class="enterDetail" href="#">
                <div @click="moveProgramDetail(newProgram.id)">
                  <img :src="newProgram.posterImg" alt="Hover Effect" />
                </div>
              </a>
            </div>
            <div class="info">
              <div class="title">{{ newProgram.title }}</div>
              <div class="detailbox">
                <span class="detailbox_design" v-if="newProgram.age">{{
                  newProgram.age
                }}</span
                ><span
                  class="detailbox_design"
                  v-if="newProgram.broadcastingDay"
                  >{{ newProgram.broadcastingDay }}</span
                ><span class="detailbox_design" v-if="newProgram.genres[0]">{{
                  newProgram.genres[0]
                }}</span
                ><span
                  class="detailbox_design"
                  v-if="newProgram.broadcastingStation"
                  >{{ newProgram.broadcastingStation }}</span
                >
              </div>
            </div>
          </div>
        </div>
      </splide-slide>
    </template>
    <template v-else>
      <h2
        style="
          color: #a1a1a1;
          font-size: 1.1vw;
          font-weight: 300;
          margin-top: 1.5%;
          margin-bottom: 4%;
        "
      >
        신규 프로그램이 없습니다.
      </h2>
    </template>
  </splide>
</template>

<script>
import { reactive } from "@vue/reactivity";
import axios from "axios";
import { Splide, SplideSlide } from "@splidejs/vue-splide";
import "@splidejs/splide/dist/css/themes/splide-default.min.css";
import { mapState, mapActions, mapMutations } from "vuex";

export default {
  name: "MainSlider",
  components: {
    Splide,
    SplideSlide,
  },
  computed: {
    ...mapState([
      "hotProgram",
      "newProgram",
      "genderAgeProgram",
      "userAge",
      "userGender",
      "actorProgram",
      "actorName",
      "onairProgram",
    ]),
  },
  data() {
    return {
      options: {
        perPage: 7,
        gap: 7,
        pauseOnHover: false,
        arrows: true,
        dots: false,
        animatedDots: false,
        padding: "2rem",
      },
      list: [],
    };
  },
  created() {
    this.getRecommendProgram();
  },
  methods: {
    ...mapActions(["getProgramDetail", "getRecommendProgram"]),
    ...mapMutations(["SET_PROGRAM_ID"]),
    moveProgramDetail(programId) {
      this.SET_PROGRAM_ID(programId);
      window.location.replace(`/detail`);
    },
  },
};
</script>

<style scoped>
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
  height: 20px;
  padding: 1px 5px;
  border: none;
  margin: 0px 10px 0px 0px;
  margin-bottom: 5px;
}

h2 {
  font-weight: 500;
  font-size: 1.4vw;
  margin-bottom: 0.7%;
  padding-left: 70px;
}

img {
  margin-bottom: 20%;
  width: 100%;
  height: 20vw;
  padding-right: 0;
  margin-right: 0;
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
  width: 100%;
  transition: all 0.3s ease-in-out;
}

.box .info {
  position: absolute;
  left: 5px;
  bottom: 45px;
  color: #fff;
  width: 100%;
  box-sizing: border-box;
  opacity: 0;
  transition: all 0.3s ease-in-out;
}

.box .info .title {
  font-size: 1.15vw;
  font-weight: 400;
  text-align: left;
  white-space: normal;
  padding-bottom: 3%;
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

<style>
#carousel_recommend .splide__pagination li {
  display: none;
}

#carousel_recommend .splide__arrow svg {
  fill: #ffffff;
  height: 2em;
  width: 2em;
  opacity: 100%;
}

#carousel_recommend .splide__arrow {
  width: fit-content;
  top: 45%;
}

#carousel_recommend .splide__arrow--next {
  right: 0.8%;
}

#carousel_recommend .splide__arrow--prev {
  left: 0.8%;
}

#carousel_recommend .splide__arrow {
  background: none;
}

#carousel_recommend .splide__slide {
  margin: 0;
}
</style>
