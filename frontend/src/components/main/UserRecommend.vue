<template>
  <div id="mainslider" style="text-align: center; margin-top: 10px">
    <splide id="carousel_user_recommend" :options="options">
      <splide-slide v-for="recommend in userRecommend">
        <figure>
          <a class="enterDetail" href="#">
                <router-link
                  :to="{ name: 'detail' }"
                  @click="moveProgramDetail(recommend.id)"
                  class="nav-link"
                >
          <img class="img_2" :src="recommend.backdropPath" alt="main2" />
          </router-link>
          </a>
          <figcaption>{{ recommend.title }}</figcaption>
        </figure>
      </splide-slide>
    </splide>
  </div>
  <br /><br /><br />
</template>

<script>
import { Splide, SplideSlide } from "@splidejs/vue-splide";
import "@splidejs/splide/dist/css/themes/splide-default.min.css";
import { mapState, mapActions } from "vuex";

export default {
  name: "MainSlider",
  components: {
    Splide,
    SplideSlide,
  },
  computed: {
    ...mapState(["userRecommend"]),
  },
  created() {
    this.getUserRecommendProgram();
  },
  methods: {
    ...mapActions(["getProgramDetail", "getUserRecommendProgram"]),
    moveProgramDetail(programId) {
      this.getProgramDetail(programId);
    },
  },
  data() {
    return {
      options: {
        type: "loop",
        rewind: true,
        perPage: 1,
        autoplay: true,
        pauseOnHover: false,
        arrows: true,
        dots: true,
        animatedDots: true,
        padding: "7rem",
        gap: 0,
      },
    };
  },
  method: {
    gotoPage(link) {
      this.$router.push(link);
    },
  },
};
</script>

<style>
#carousel_user_recommend .splide__arrow--next {
  right: 0.5em;
}
#carousel_user_recommend .splide__arrow--prev {
  left: 0.5em;
}
#carousel_user_recommend .splide__arrow {
  background: none;
}
.img_2 {
  height: 40vw;
  width: 100%;
  padding-left: 0.5%;
  padding-right: 0.5%;
  border-radius: 1.4pc;
}
.splide__arrow svg {
  fill: white;
  opacity: 50%;
  height: 6em;
  width: 6em;
}
.splide__arrow {
  width: fit-content;
}
</style>

<style scoped>
figcaption {
  color: #fff;
  background: rgba(55, 55, 55, 0.6);
  border-radius: 0.8vw;
  font-weight: 600;
  padding: 0.7em;
  padding-right: 0.2em;
  padding-left: 0.2em;
  /* line-height: 100; */
  position: absolute;
  left: 5%;
  bottom: 9%;
  width: fit-content;
  font-size: 2.4vw;
}

figure {
  margin: 0;
  line-height: 0;

  position: relative;
}

/* Alignment styles */
body {
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: system-ui;
}

body,
html {
  height: 100%;
}

* {
  box-sizing: border-box;
}
</style>
