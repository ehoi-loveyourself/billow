<template>
  <div>
    <h2 style="color: white">지금 방송 중인 프로그램(ON AIR)</h2>
    <splide id="carousel_recommend" :options="options">
      <splide-slide v-for="(d, idx) in state.onairProgram" :key="idx">
        <div class="box-wrap">
          <div class="box">
            <div class="img">
              <a class="enterDetail" href="#">
                <router-link
                  :to="{ name: 'detail' }"
                  @click="moveProgramDetail(state.onairProgram[idx].id)"
                  class="nav-link"
                >
                  <img
                    :src="state.onairProgram[idx].posterImg"
                    alt="Hover Effect"
                  />
                </router-link>
              </a>
            </div>
            <div class="info">
              <h3>{{ state.onairProgram[idx].title }}</h3>
              <div class="detailbox">
                <span
                  class="detailbox_design"
                  v-if="state.onairProgram[idx].age"
                  >{{ state.onairProgram[idx].age }}</span
                ><span
                  class="detailbox_design"
                  v-if="state.onairProgram[idx].broadcastingDay"
                  >{{ state.onairProgram[idx].broadcastingDay }}</span
                ><span
                  class="detailbox_design"
                  v-if="state.onairProgram[idx].genres[0]"
                  >{{ state.onairProgram[idx].genres[0] }}</span
                ><span
                  class="detailbox_design"
                  v-if="state.onairProgram[idx].broadcastingStation"
                  >{{ state.onairProgram[idx].broadcastingStation }}</span
                ><span
                  class="detailbox_design"
                  v-if="state.onairProgram[idx].broadcastingTime"
                  >{{ state.onairProgram[idx].broadcastingTime }} ~</span
                >
              </div>
            </div>
          </div>
        </div>
      </splide-slide>
    </splide>

    <h2 style="color: white">요즘 핫한 프로그램</h2>
    <splide id="carousel_recommend" :options="options">
      <splide-slide v-for="(d, idx) in state.hotProgram" :key="idx">
        <div class="box-wrap">
          <div class="box">
            <div class="img">
              <a class="enterDetail" href="#">
                <router-link
                  :to="{ name: 'detail' }"
                  @click="moveProgramDetail(state.hotProgram[idx].id)"
                  class="nav-link"
                >
                  <img
                    :src="state.hotProgram[idx].posterImg"
                    alt="Hover Effect"
                  />
                </router-link>
              </a>
            </div>
            <div class="info">
              <h3>{{ state.hotProgram[idx].title }}</h3>
              <div class="detailbox">
                <span
                  class="detailbox_design"
                  v-if="state.hotProgram[idx].age"
                  >{{ state.hotProgram[idx].age }}</span
                ><span
                  class="detailbox_design"
                  v-if="state.hotProgram[idx].broadcastingDay"
                  >{{ state.hotProgram[idx].broadcastingDay }}</span
                ><span
                  class="detailbox_design"
                  v-if="state.hotProgram[idx].genres[0]"
                  >{{ state.hotProgram[idx].genres[0] }}</span
                ><span
                  class="detailbox_design"
                  v-if="state.hotProgram[idx].broadcastingStation"
                  >{{ state.hotProgram[idx].broadcastingStation }}</span
                >
              </div>
            </div>
          </div>
        </div>
      </splide-slide>
    </splide>

    <h2 style="color: white">신규 프로그램을 추천드려요!</h2>
    <splide id="carousel_recommend" :options="options">
      <splide-slide v-for="(d, idx) in state.newProgram" :key="idx">
        <div class="box-wrap">
          <div class="box">
            <div class="img">
              <a class="enterDetail" href="#">
                <router-link
                  :to="{ name: 'detail' }"
                  @click="moveProgramDetail(state.newProgram[idx].id)"
                  class="nav-link"
                >
                  <img
                    :src="state.newProgram[idx].posterImg"
                    alt="Hover Effect"
                  />
                </router-link>
              </a>
            </div>
            <div class="info">
              <h3>{{ state.newProgram[idx].title }}</h3>
              <div class="detailbox">
                <span
                  class="detailbox_design"
                  v-if="state.newProgram[idx].age"
                  >{{ state.newProgram[idx].age }}</span
                ><span
                  class="detailbox_design"
                  v-if="state.newProgram[idx].broadcastingDay"
                  >{{ state.newProgram[idx].broadcastingDay }}</span
                ><span
                  class="detailbox_design"
                  v-if="state.newProgram[idx].genres[0]"
                  >{{ state.newProgram[idx].genres[0] }}</span
                ><span
                  class="detailbox_design"
                  v-if="state.newProgram[idx].broadcastingStation"
                  >{{ state.newProgram[idx].broadcastingStation }}</span
                >
              </div>
            </div>
          </div>
        </div>
      </splide-slide>
    </splide>

    <h2 style="color: white">
      {{ state.userAge }}대 {{ state.userGender }}자에게 인기 많은 프로그램
    </h2>
    <splide id="carousel_recommend" :options="options">
      <splide-slide v-for="(d, idx) in state.genderAgeProgram" :key="idx">
        <div class="box-wrap">
          <div class="box">
            <div class="img">
              <a class="enterDetail" href="#">
                <router-link
                  :to="{ name: 'detail' }"
                  @click="moveProgramDetail(state.genderAgeProgram[idx].id)"
                  class="nav-link"
                >
                  <img
                    :src="state.genderAgeProgram[idx].posterImg"
                    alt="Hover Effect"
                  />
                </router-link>
              </a>
            </div>
            <div class="info">
              <h3>{{ state.genderAgeProgram[idx].title }}</h3>
              <div class="detailbox">
                <span
                  class="detailbox_design"
                  v-if="state.genderAgeProgram[idx].age"
                  >{{ state.genderAgeProgram[idx].age }}</span
                ><span
                  class="detailbox_design"
                  v-if="state.genderAgeProgram[idx].broadcastingDay"
                  >{{ state.genderAgeProgram[idx].broadcastingDay }}</span
                ><span
                  class="detailbox_design"
                  v-if="state.genderAgeProgram[idx].genres[0]"
                  >{{ state.genderAgeProgram[idx].genres[0] }}</span
                ><span
                  class="detailbox_design"
                  v-if="state.genderAgeProgram[idx].broadcastingStation"
                  >{{ state.genderAgeProgram[idx].broadcastingStation }}</span
                >
              </div>
            </div>
          </div>
        </div>
      </splide-slide>
    </splide>

    <h2 style="color: white">
      혹시 {{ state.actorName }}님에게 관심이 있으신가요?
    </h2>
    <splide id="carousel_recommend" :options="options">
      <splide-slide v-for="(d, idx) in state.actorProgram" :key="idx">
        <div class="box-wrap">
          <div class="box">
            <div class="img">
              <a class="enterDetail" href="#">
                <router-link
                  :to="{ name: 'detail' }"
                  @click="moveProgramDetail(state.actorProgram[idx].id)"
                  class="nav-link"
                >
                  <img
                    :src="state.actorProgram[idx].posterImg"
                    alt="Hover Effect"
                  />
                </router-link>
              </a>
            </div>
            <div class="info">
              <h3>{{ state.actorProgram[idx].title }}</h3>
              <div class="detailbox">
                <span
                  class="detailbox_design"
                  v-if="state.actorProgram[idx].age"
                  >{{ state.actorProgram[idx].age }}</span
                ><span
                  class="detailbox_design"
                  v-if="state.actorProgram[idx].broadcastingDay"
                  >{{ state.actorProgram[idx].broadcastingDay }}</span
                ><span
                  class="detailbox_design"
                  v-if="state.actorProgram[idx].genres[0]"
                  >{{ state.actorProgram[idx].genres[0] }}</span
                ><span
                  class="detailbox_design"
                  v-if="state.actorProgram[idx].broadcastingStation"
                  >{{ state.actorProgram[idx].broadcastingStation }}</span
                >
              </div>
            </div>
          </div>
        </div>
      </splide-slide>
    </splide>
  </div>
</template>

<script>
import { reactive } from "@vue/reactivity";
import axios from "axios";
import { Splide, SplideSlide } from "@splidejs/vue-splide";
import "@splidejs/splide/dist/css/themes/splide-default.min.css";
import { mapActions } from "vuex";

export default {
  name: "MainSlider",
  components: {
    Splide,
    SplideSlide,
  },
  setup() {
    const state = reactive({
      data: [],
      hotProgram: [],

      newProgram: [],

      genderAgeProgram: [],
      userAge: {},
      userGender: {},

      actorProgram: [],
      actorName: {},

      onairProgram: [],
    });

    axios.get("/api/recommend/popular").then((res) => {
      // 인기 프로그램 추천 데이터 GET
      console.log(res.data);
      state.hotProgram = res.data;
    });

    axios.get("/api/recommend/new").then((res) => {
      // 신규 프로그램 추천 데이터 GET
      console.log(res.data);
      state.newProgram = res.data;
    });

    axios.get("/api/recommend/gender-age").then((res) => {
      // 성연령별 프로그램 추천 데이터 GET
      console.log(res.data);
      state.genderAgeProgram = res.data;
      state.userAge = res.data[0].userAge;
      state.userGender = res.data[0].userGender;
    });

    axios.get("/api/recommend/actor").then((res) => {
      // 출연진 프로그램 추천 데이터 GET
      console.log(res.data);
      state.actorProgram = res.data;
      state.actorName = res.data[0].actorName;
    });

    axios.get("/api/recommend/onair").then((res) => {
      // 온에어 프로그램 추천 데이터 GET
      console.log(res.data);
      state.onairProgram = res.data;
    });
    return { state };
  },

  data() {
    return {
      options: {
        // type: "loop",
        perPage: 7,
        // perMove:6,
        // pagination:false,
        // autoplay    : false,
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
  methods: {
    ...mapActions(["getProgramDetail"]),
    moveProgramDetail(programId) {
      this.getProgramDetail(programId);
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
  font-family: "Watcha Sans", Roboto, "Noto Sans KR", "Apple SD Gothic Neo",
    "Nanum Gothic", "Malgun Gothic", sans-serif;
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
  /* width:16.1%; */
  width: 100%;
  height: 20vw;
  padding-right: 0;
  margin-right: 0;
}

.box-wrap {
  /* width: 100vw; height: 100vh; */
  display: flex;
  align-items: center;
  justify-content: center;
  flex-wrap: wrap;
  align-content: stretch;
}
.box {
  position: relative;
  /* background: #000; */
  width: 100%;
  border: none;
  cursor: pointer;
  overflow: hidden;
  /* box-shadow: 1px 1px 3px rgba(0,0,0,0.4); */
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
  /* padding: 15px; */
  box-sizing: border-box;
  opacity: 0;
  transition: all 0.3s ease-in-out;
}
.box .info h3 {
  font-size: 12px;
  font-weight: 600;
  line-height: 20px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  padding-bottom: 3px;
  text-align: left;
}
/* .box .info p {
  font-size: 16px;
  overflow: hidden; 
  white-space: nowrap;
  text-overflow: ellipsis;
} */
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
