<template>
  <MainPageLoader v-if="isLoading" />
  <div v-else id="mainslider" style="text-align: center; margin-top: 10px">
    <splide id="carousel_user_recommend" :options="options">
      <splide-slide v-for="recommend in userRecommend">
        <figure>
          <a class="enterDetail" href="#">
            <div @click="moveProgramDetail(recommend.id)">
              <img class="img_2" :src="recommend.backdropPath" alt="main2" />
            </div>
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
import { mapState, mapActions, mapMutations } from "vuex";
import MainPageLoader from "@/components/load/MainPageLoader.vue";

export default {
  name: "MainSlider",
  components: {
    Splide,
    SplideSlide,
    MainPageLoader,
  },
  computed: {
    ...mapState(["userRecommend", "isLoading"]),
  },
  async created() {
    await this.getUserRecommendProgram();
  },
  created() {
    this.getUserRecommendProgram();
  },
  methods: {
    ...mapActions(["getProgramDetail", "getUserRecommendProgram"]),
    ...mapMutations(["SET_PROGRAM_ID"]),
    moveProgramDetail(programId) {
      this.SET_PROGRAM_ID(programId);
      window.location.replace(`/detail`);
    },
    gotoPage(link) {
      this.$router.push(link);
    },
  },
  mounted() {
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

<style lang="scss" scoped>
$colors: #8cc271, #69beeb, #f5aa39, #e9643b;

.page-loader {
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: #333;
  z-index: 999;
}

.cube {
  width: 40px;
  height: 40px;
  margin-right: 10px;

  @for $i from 1 through length($colors) {
    &:nth-child(#{$i}) {
      background-color: nth($colors, $i);
    }
  }

  &:first-child {
    animation: left 1s infinite;
  }

  &:last-child {
    animation: right 1s infinite 0.5s;
  }
}

@keyframes left {
  40% {
    transform: translateX(-60px);
  }

  50% {
    transform: translateX(0);
  }
}

@keyframes right {
  40% {
    transform: translateX(60px);
  }

  50% {
    transform: translateX(0);
  }
}
</style>
