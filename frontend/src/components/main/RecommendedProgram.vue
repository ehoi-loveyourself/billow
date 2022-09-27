<template>
  <div>
    <h2 style="color: white">지금 방송 중인 프로그램(ON AIR)</h2>
    <splide id="carousel_recommend" :options="options">
      <splide-slide>
        <div class="box-wrap">
          <div class="box">
            <div class="img">
              <a class="enterDetail" href="#">
                <router-link :to="{ name: 'detail' }" class="nav-link">
                  <img
                    src="@/assets/hyori.png"
                    alt="Hover Effect"
                  /> </router-link
              ></a>
            </div>
            <div class="info">
              <h3>효리네 민박</h3>
              <div class="detailbox">
                <span class="detailbox_design">15+</span
                ><span class="detailbox_design">금 오후7:00</span
                ><span class="detailbox_design">예능</span
                ><span class="detailbox_design">tvN</span>
              </div>
            </div>
          </div>
        </div>
      </splide-slide>
      <splide-slide>
        <div class="box-wrap">
          <div class="box">
            <div class="img">
              <img src="@/assets/gs.png" alt="Hover Effect" />
            </div>
            <div class="info">
              <h3>어차피 톡할거 행복하게</h3>
              <div class="detailbox">
                <span class="detailbox_design">12+</span
                ><span class="detailbox_design">월,화 오후9:00</span
                ><span class="detailbox_design">드라마</span
                ><span class="detailbox_design">EnA</span>
              </div>
            </div>
          </div>
        </div>
      </splide-slide>
      <splide-slide>
        <img src="@/assets/song.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/sign.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/heart_to_heart.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/alham.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/runningman.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/thatman.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/nangman.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/jugun.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/image_2.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/laggi.png" alt="Image" />
      </splide-slide>
    </splide>

    <h2 style="color: white">신규 프로그램을 추천드려요!</h2>
    <splide id="carousel_recommend" :options="options">
      <splide-slide v-for="(d, idx) in state.newProgramPosterImg" :key="idx">
        <img :src="state.newProgramPosterImg[idx]" alt="Image" />
      </splide-slide>
    </splide>

    <h2 style="color: white">요즘 핫한 프로그램</h2>
    <splide id="carousel_recommend" :options="options">
      <splide-slide>
        <img src="@/assets/laggi.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/runningman.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/thatman.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/nangman.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/jugun.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/image_2.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/gs.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/sign.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/hyori.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/song.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/heart_to_heart.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/alham.png" alt="Image" />
      </splide-slide>
    </splide>

    <h2 style="color: white">신규 프로그램을 추천드려요!</h2>
    <splide id="carousel_recommend" :options="options">
      <splide-slide v-for="(d, idx) in state.newProgramPosterImg" :key="idx">
        <img :src="state.newProgramPosterImg[idx]" alt="Image" />
      </splide-slide>
    </splide>

    <h2 style="color: white">20대 남자에게 인기 많은 프로그램</h2>
    <splide id="carousel_recommend" :options="options">
      <splide-slide>
        <img src="@/assets/sign.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/thatman.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/nangman.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/jugun.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/image_2.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/gs.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/hyori.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/song.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/laggi.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/runningman.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/heart_to_heart.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/alham.png" alt="Image" />
      </splide-slide>
    </splide>

    <h2 style="color: white">혹시 안소희님에게 관심이 있으신가요?</h2>
    <splide id="carousel_recommend" :options="options">
      <splide-slide>
        <img src="@/assets/heart_to_heart.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/nangman.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/jugun.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/image_2.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/gs.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/sign.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/hyori.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/song.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/alham.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/laggi.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/runningman.png" alt="Image" />
      </splide-slide>
      <splide-slide>
        <img src="@/assets/thatman.png" alt="Image" />
      </splide-slide>
    </splide>

    <!-- <h2 style="color: white">테스트 1</h2>
    <splide id="carousel_recommend" :options="options">
      <splide-slide v-for="(d, idx) in state.data" :key="idx">
        <img :src="state.data[idx]" alt="Image" />
      </splide-slide>
    </splide> -->

    <!-- <h2 style="color: white">테스트 2</h2>
    <splide id="carousel_recommend" :options="options">
      <splide-slide v-for="(d, idx) in state.newProgramPosterImg" :key="idx">
        <img :src="state.newProgramPosterImg[idx]" alt="Image" />
      </splide-slide>
    </splide> -->
  </div>
</template>

<script>
import { reactive } from "@vue/reactivity";
import axios from "axios";
import { Splide, SplideSlide } from "@splidejs/vue-splide";
import "@splidejs/splide/dist/css/themes/splide-default.min.css";

export default {
  name: "MainSlider",
  components: {
    Splide,
    SplideSlide,
  },
  setup() {
    const state = reactive({
      data: [],
      newProgramPosterImg: [],
    });

    const add = () => {
      // 지금 안됨.
      // state.data.push("추가한 내용");
      axios.post("/api/recommend/new").then((res) => {});
      console.log(res.data);
    };

    // axios.get("/api/recommend/new").then((res) => {
    //   // 데이터 가져오는 거.
    //   console.log(res.data);

    //   var index;

    //   for (index = 0; index < res.data.length; index++) {
    //     state.data[index] = res.data[index].posterImg;
    //   }
    // });

    axios.get("/api/recommend/new").then((res) => {
      // 신규 프로그램 추천 데이터 GET
      console.log(res.data);

      var index;

      for (index = 0; index < res.data.length; index++) {
        state.newProgramPosterImg[index] = res.data[index].posterImg;
      }
    });

    return { state, add };
  },

  data() {
    return {
      options: {
        type: "loop",
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
  method: {
    // gotoPage(link) {
    //   this.$router.push(link);
    // },
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
  padding-right: 0;
  margin-right: 0;
}

.box-wrap {
  /* width: 100vw; height: 100vh; */
  display: flex;
  align-items: center;
  justify-content: center;
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