<template>
<header-bar />
<br>
<h2 style="color:white; margin-left:70px; font-size: 1.1vw; font-weight: 300;">회원님이 봤던 프로그램들을 평가해주세요.<br/>
BILLOW가 회원님이 좋아할만한 프로그램을 추천해줄거예요.</h2>
<br>
<section style="padding-left: 70px; margin-top: 10px;">
    <splide id="carousel_recommend" :options="options">
    <splide-slide>
  <span class="box-wrap">
  <span class="box">
<span class="img">
  <a class="enterDetail" href="#">
      <router-link :to="{ name: 'detail' }" class="nav-link">
  <img src="@/assets/hyori.png" alt="Hover Effect">
  </router-link></a>
</span>
<span class="info">
  <h3>효리네 민박</h3>
  <div class="detailbox" style="text-align:center"><span class="detailbox_design"><svg width="61" height="24" fill="none" xmlns="http://www.w3.org/2000/svg"
            xmlns:xlink="http://www.w3.org/1999/xlink">
            <path fill="url(#a)" d="M0 0h61v24H0z" />
            <defs>
                <pattern id="a" patternContentUnits="objectBoundingBox" width="1" height="1">
                    <use xlink:href="#b" transform="scale(.0164 .04167)" />
                </pattern>
                <image id="b" width="61" height="24"
                    xlink:href="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAD0AAAAYCAYAAABJA/VsAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAFhSURBVFhHY2RgYPgPxCMKMEHpEQWI9vSeCUpgTC0wkOYR5Wl7A24UTCkYaPOI8nRtgjiUhcomFwy0eQQ9jR56xIYmLjAYzMPraXkJNqwhR27sDBbz4FUWyIB4D0EQk8GOiNACgQcvfjE8fPEbTB+68JXhIBA/BLJBYDCbB/d0HNDAuRUyICbZILnjCcOiHe/B7MFsHjOQ3QASuHjnBzAEGIkKQWxgIdCw5gWvoLzBbR7c0yAAin5yDAYZmAIMRXQwWM1D8TQIkGowKPSKpzyH8jDBYDQPZ9sb1LohZDDIAS4F96A8/GAwmYezypKXYIWycANi1MDAYDIPp6cVgFUENcFgMg+rp7ElG1Bdhw5AFhOTtwabeVg9DWoIwADIMFBhoBpxk0EFiGHFPilgMJoHKshQcF2C+P/fB3TBNDZ5oKUE1SDjQWgehgBYEzZxdDxU1Y0OF40UMOrpkQJGoKcZGABqIX9owqCb9AAAAABJRU5ErkJggg==" />
            </defs>
        </svg></span></div>
    </span>
</span>
</span>
</splide-slide>

<splide-slide>
<span class="box-wrap">
  <span class="box">
<span class="img">
  <a class="enterDetail" href="#">
      <router-link :to="{ name: 'detail' }" class="nav-link">
  <img src="@/assets/hyori.png" alt="Hover Effect">
  </router-link></a>
</span>
<span class="info">
  <h3>효리네 민박</h3>
  <div class="detailbox" style="text-align:center"><span class="detailbox_design"><svg width="61" height="24" fill="none" xmlns="http://www.w3.org/2000/svg"
            xmlns:xlink="http://www.w3.org/1999/xlink">
            <path fill="url(#a)" d="M0 0h61v24H0z" />
            <defs>
                <pattern id="a" patternContentUnits="objectBoundingBox" width="1" height="1">
                    <use xlink:href="#b" transform="scale(.0164 .04167)" />
                </pattern>
                <image id="b" width="61" height="24"
                    xlink:href="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAD0AAAAYCAYAAABJA/VsAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAFhSURBVFhHY2RgYPgPxCMKMEHpEQWI9vSeCUpgTC0wkOYR5Wl7A24UTCkYaPOI8nRtgjiUhcomFwy0eQQ9jR56xIYmLjAYzMPraXkJNqwhR27sDBbz4FUWyIB4D0EQk8GOiNACgQcvfjE8fPEbTB+68JXhIBA/BLJBYDCbB/d0HNDAuRUyICbZILnjCcOiHe/B7MFsHjOQ3QASuHjnBzAEGIkKQWxgIdCw5gWvoLzBbR7c0yAAin5yDAYZmAIMRXQwWM1D8TQIkGowKPSKpzyH8jDBYDQPZ9sb1LohZDDIAS4F96A8/GAwmYezypKXYIWycANi1MDAYDIPp6cVgFUENcFgMg+rp7ElG1Bdhw5AFhOTtwabeVg9DWoIwADIMFBhoBpxk0EFiGHFPilgMJoHKshQcF2C+P/fB3TBNDZ5oKUE1SDjQWgehgBYEzZxdDxU1Y0OF40UMOrpkQJGoKcZGABqIX9owqCb9AAAAABJRU5ErkJggg==" />
            </defs>
        </svg></span></div>
    </span>
</span>
</span>
</splide-slide>
</splide>
</section>
<br/><br/>
<p style="text-align:center">
      <a class="startButton" href="#" role="button" style="border-radius: 15px;">
        <router-link :to="{ name: 'main' }" class="nav-link" style="font-size:20px; padding:10px; color:black;">
            NEXT
        </router-link>
      </a>
    </p>
<br/><br/><br/><br/>      <br/><br/>
</template>
    
<script>
  import { Splide, SplideSlide } from '@splidejs/vue-splide';
  import '@splidejs/splide/dist/css/themes/splide-default.min.css';
  import HeaderBar from "@/components/layout/HeaderNavBar.vue";

  export default {
    name:"MainSlider",
    components: {
    Splide,
    SplideSlide, HeaderBar
  },
  data() {
      return {
        options: {
        type : 'loop',
        perPage     : 7,
        // perMove:6,
        // pagination:false,
        // autoplay    : false,
        gap:7,
        pauseOnHover: false,
        arrows      : true,
        dots: false,
        animatedDots: false,
        padding: '2rem'
        },
        list:[]
      };
    }, 
}
    </script>
  

<style scoped>    
  h2{
    font-weight: 500;
    font-size: 1.4vw;
  }
  
 .startButton{
    background-color: #ffffff;
    padding: 3px 30px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
 }
 .box .info .detailbox{
    font-size: 12px;
}
.box .info .detailbox_design{
    display: inline-block;
    background: rgb(46, 47, 49);
    color: rgb(255, 255, 255);
    font-family: "Watcha Sans", Roboto, "Noto Sans KR", "Apple SD Gothic Neo", "Nanum Gothic", "Malgun Gothic", sans-serif;
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
img{
    margin-bottom: 20%;
    /* width:16.1%; */
    width:100%;
    padding-right:0;
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
  /* width: 100%; */
  border: none;
  cursor: pointer;
  overflow: hidden;
  /* box-shadow: 1px 1px 3px rgba(0,0,0,0.4); */
}
.box img {
  /* width: 100%; */
  transition: all 0.3s ease-in-out;
}
/* .box:before {
  content: '';
  background: #fff;
  width: 0px; height: 1px;
  position: absolute; right: 20px; top: 20px;
  transition: all 0.35s ease;
}
.box:after {
  content: '';
  background: #fff;
  width: 1px; height: 0px;
  position: absolute; right: 20px; top: 20px;
  transition: all 0.35s ease;
} */

.box .info {
  position: absolute; left: 5px; bottom: 45px;
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
  text-align: center;
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
@font-face { font-family: 'GoyangIlsan'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/GoyangIlsan.woff') format('woff'); font-weight: normal; font-style: normal; }

body {
  background-color: #1a237e;
  font-family: 'GoyangIlsan'
}
 </style>

 
<style>
    #carousel_recommend .splide__pagination li{
        display:none;
      }
      #carousel_recommend .splide__arrow svg {
        fill: #ffffff;
        height: 2em;
        width: 2em;
        opacity:100%;
    }
    #carousel_recommend .splide__arrow{
      width:fit-content;
      top:45%;
    }
    #carousel_recommend .splide__arrow--next{
        right:0.8%;
    }
    #carousel_recommend .splide__arrow--prev{
        left:0.8%;
    }
    #carousel_recommend .splide__arrow{
      background: none;
    }
    #carousel_recommend .splide__slide {
        margin:0;
    }
    </style>