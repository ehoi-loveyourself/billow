<template>
    <div id="mainslider" style="text-align:center; margin-top: 10px; ">
        <splide id="carousel_user_recommend" :options="options">
        <splide-slide><a class="enterDetail" href="#">
          <router-link :to="{ name: 'detail' }" class="nav-link">
        <img class="img_1" src="@/assets/image_180.png" alt="main1"></router-link>
      </a>
        </splide-slide>
        <splide-slide>
        <img class="img_2" src="https://image.tmdb.org/t/p/original/FbUIi5WeDU5j1EtipQ274tts1E.jpg" alt="main2">
        </splide-slide>
        <splide-slide>
        <img class="img_1" src="@/assets/daedang.png" alt="main3">
        </splide-slide>
        <splide-slide>
        <img class="img_1" src="@/assets/mondai.png" alt="main3">
        </splide-slide>
    </splide>
    </div>
    <br/><br/><br/>
</template>
  
<script>
  import { Splide, SplideSlide } from '@splidejs/vue-splide';
  import '@splidejs/splide/dist/css/themes/splide-default.min.css';
  
  export default {
    name:"MainSlider",
    components: {
    Splide,
    SplideSlide,
  },
    data() {
      return {
        options: {
        type : 'loop',
        rewind      : true,
        perPage     : 1,
        autoplay    : true,
        pauseOnHover: false,
        arrows      : true,
        dots: true,
        animatedDots: true,
        padding: '7rem',
        gap : 0,      
        },
      };
    },
    method:{
    gotoPage(link) {
      this.$router.push(link);
    },
  }
  }
</script>
  
<style>
#carousel_user_recommend .splide__arrow--next{
    right:0.5em;
}
#carousel_user_recommend .splide__arrow--prev{
    left:0.5em;
}
#carousel_user_recommend .splide__arrow{
  background: none;
}
.img_1{
  width:100%;
  padding-left:0.5%;
  padding-right:0.5%;
  height:640px;
}
.img_2{
  height: 640px;
  width: 100%;
  padding-left:0.5%;
  padding-right:0.5%;
  border-radius:1.4pc;
}
.splide__arrow svg {
    fill: white;
    opacity: 50%;
    height: 6em;
    width: 6em;
}
.splide__arrow{
  width:fit-content;
}
</style>