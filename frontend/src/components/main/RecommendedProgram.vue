<template>
  <div style="padding-left:70px;">
<h2 style="color:white">지금 방송 중인 프로그램(ON AIR)</h2>
<!-- <div class="card" style="color:white"> -->
  <splide id="carousel_recommend" :options="options">
        <splide-slide>
  <img src="@/assets/runningman.png" alt="Image">
  <img src="@/assets/thatman.png" alt="Image"/>
  <img src="@/assets/nangman.png" alt="Image"/>
  <img src="@/assets/jugun.png" alt="Image"/>
  <img src="@/assets/image_2.png" alt="Image"/>
  <img src="@/assets/laggi.png" alt="Image"/>
</splide-slide>
        <splide-slide>
  <img src="@/assets/gs.png" alt="Image" />
  <img src="@/assets/hyori.png" alt="Image"/>
  <img src="@/assets/song.png" alt="Image"/>
  <img src="@/assets/sign.png" alt="Image"/>
  <img src="@/assets/heart_to_heart.png" alt="Image"/>
  <img src="@/assets/alham.png" alt="Image"/>
  </splide-slide>
  </splide>
  <!-- <div class="container">
    <h4><b>호버 했을 때 제목 등등</b></h4> 
    <p>카드 형식 사용</p>
  </div> -->
<!-- </div> -->

<h2 style="color:white">요즘 핫한 프로그램</h2>
<splide id="carousel_recommend" :options="options">
  <splide-slide>
  <img src="@/assets/thatman.png" alt="Image"/>
  <img src="@/assets/nangman.png" alt="Image"/>
  <img src="@/assets/jugun.png" alt="Image"/>
  <img src="@/assets/image_2.png" alt="Image"/>
  <img src="@/assets/runningman.png" alt="Image"/>
  <img src="@/assets/laggi.png" alt="Image"/>
</splide-slide>

  <splide-slide>
  <img src="@/assets/gs.png" alt="Image" />
  <img src="@/assets/hyori.png" alt="Image"/>
  <img src="@/assets/song.png" alt="Image"/>
  <img src="@/assets/sign.png" alt="Image"/>
  <img src="@/assets/heart_to_heart.png" alt="Image"/>
  <img src="@/assets/alham.png" alt="Image"/>
  </splide-slide>
</splide>

  <h2 style="color:white">재현님에게 추천드려요!</h2>
  <splide id="carousel_recommend" :options="options">
  <splide-slide>
  <img src="@/assets/nangman.png" alt="Image"/>
  <img src="@/assets/thatman.png" alt="Image"/>
  <img src="@/assets/jugun.png" alt="Image"/>
  <img src="@/assets/image_2.png" alt="Image"/>
  <img src="@/assets/runningman.png" alt="Image"/>
  <img src="@/assets/laggi.png" alt="Image"/>
</splide-slide>

  <splide-slide>
  <img src="@/assets/gs.png" alt="Image" />
  <img src="@/assets/hyori.png" alt="Image"/>
  <img src="@/assets/song.png" alt="Image"/>
  <img src="@/assets/sign.png" alt="Image"/>
  <img src="@/assets/heart_to_heart.png" alt="Image"/>
  <img src="@/assets/alham.png" alt="Image"/>
  </splide-slide>
</splide>

  <h2 style="color:white">20대 남자에게 인기 많은 프로그램</h2>
  <splide id="carousel_recommend" :options="options">
    <splide-slide>
  <img src="@/assets/jugun.png" alt="Image"/>
  <img src="@/assets/thatman.png" alt="Image"/>
  <img src="@/assets/nangman.png" alt="Image"/>
  <img src="@/assets/image_2.png" alt="Image"/>
  <img src="@/assets/runningman.png" alt="Image"/>
  <img src="@/assets/laggi.png" alt="Image"/>
  </splide-slide>

  <splide-slide>
  <img src="@/assets/gs.png" alt="Image" />
  <img src="@/assets/hyori.png" alt="Image"/>
  <img src="@/assets/song.png" alt="Image"/>
  <img src="@/assets/sign.png" alt="Image"/>
  <img src="@/assets/heart_to_heart.png" alt="Image"/>
  <img src="@/assets/alham.png" alt="Image"/>
  </splide-slide>
</splide>


<h2 style="color:white">혹시 이병헌님에게 관심이 있으신가요?</h2>
<splide id="carousel_recommend" :options="options">
<!-- <div class="card" style="color:white"> -->
  <splide-slide>
  <img src="@/assets/image_2.png" alt="Image"/>
  <img src="@/assets/thatman.png" alt="Image"/>
  <img src="@/assets/nangman.png" alt="Image"/>
  <img src="@/assets/jugun.png" alt="Image"/>
  <img src="@/assets/runningman.png" alt="Image"/>
  <img src="@/assets/laggi.png" alt="Image"/>
</splide-slide>

  <splide-slide>
  <img src="@/assets/gs.png" alt="Image" />
  <img src="@/assets/hyori.png" alt="Image"/>
  <img src="@/assets/song.png" alt="Image"/>
  <img src="@/assets/sign.png" alt="Image"/>
  <img src="@/assets/heart_to_heart.png" alt="Image"/>
  <img src="@/assets/alham.png" alt="Image"/>
  </splide-slide>
  <!-- <div class="container">
    <h4><b>카드 형식</b></h4> 
    <p>카드 형식 사용</p> 
  </div> -->
<!-- </div> -->
</splide>
</div>
  
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
        autoplay    : false,
        pauseOnHover: false,
        arrows      : true,
        dots: false,
        animatedDots: false,      
        },
      };
    },
  }
  </script>

<style scoped>
  .card {
    background-color: #a1a1a1;
    box-shadow: 0 4px 6px 0 rgba(0, 0, 0, 0.2);
    transition: 0.3s;
    width: 30%;
    padding-left: 70px;
  }
  
  .card:hover {
    box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
  }
  
  .container {
    padding: 2px 16px;
  }

  h2{
    font-weight: 400;
    margin-bottom:10px;
  }

  img{
    margin-bottom: 20px;
    width:15.5%;
    padding-right: 10px;
  }
  </style>

<style>
#carousel_recommend .splide__pagination li{
    display:none;
  }
  #carousel_recommend .splide__arrow svg {
    fill: white;
    height: 2em;
    width: 2em;
}
#carousel_recommend .splide__arrow{
  width:fit-content;
}
#carousel_recommend .splide__arrow--next{
    right:97px;
}
#carousel_recommend .splide__arrow--prev{
    left:20px;
}
#carousel_recommend .splide__arrow{
  background: none;
}
</style>