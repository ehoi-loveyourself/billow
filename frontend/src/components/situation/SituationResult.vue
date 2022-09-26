<template>
  <header-bar />
  <br />
  <h2
    style="color: white; margin-left: 67px; font-size: 1.1vw; font-weight: 300"
  >
    #혼자 #심심
  </h2>
  <h2
    style="color: white; margin-left: 67px; font-size: 1.1vw; font-weight: 300"
  >
    딱 맞는 프로그램을 추천해드릴게요.
  </h2>
  <div style="padding-left: 70px; margin-top: 10px">
    <br />
    <img src="@/assets/runningman.png" alt="Image" />
    <img src="@/assets/thatman.png" alt="Image" />
    <img src="@/assets/nangman.png" alt="Image" />
    <img src="@/assets/jugun.png" alt="Image" />
    <img src="@/assets/image_2.png" alt="Image" />
    <img src="@/assets/gs.png" alt="Image" />
    <img src="@/assets/laggi.png" alt="Image" />
    <br /><br />
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
</template>
  
<script>
import HeaderBar from "@/components/layout/HeaderNavBar.vue";

export default {
  name: "SituationSelect",
  components: {
    HeaderBar,
  },
};
</script>
  
  <style scoped>
h2 {
  font-weight: 500;
  font-size: 1.4vw;
}
img {
  padding-right: 0.5%;
  padding-bottom: 0.5%;
  margin-right: 0;
  width: 16.1%;
}
.startButton {
  background-color: #ffffff;
  padding: 3px 30px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
}
</style>