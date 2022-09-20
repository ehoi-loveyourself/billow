<template>
    <div id="mainslider" style="text-align:center; margin-top: 10px; padding-left: 70px; padding-right: 70px;">
      <br/>
        <splide id="carousel_new" :options="options">
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
        type : 'fade',
        rewind      : true,
        perPage     : 1,
        autoplay    : true,
        pauseOnHover: false,
        arrows      : true,
        dots: true,
        animatedDots: true,      
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
#carousel_new .splide__arrow--next{
    right:5rem;
}
#carousel_new .splide__arrow--prev{
    left:5rem;
}
#carousel_new .splide__arrow{
  background: none;
}
.img_1{
  width:100%;
  height:620px;
}
.img_2{
  height: 620px;
  width: 100%;
  border-radius: 20px;
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