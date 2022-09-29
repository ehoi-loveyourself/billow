<template>
  <!-- <b-row> -->
          <!-- <b-col cols="8"> -->
            <header-bar/>
    <!-- </b-col> -->
    <!-- <b-col cols="4">  -->
      <SecondBar/>
      <!-- </b-col> -->
    <!-- </b-row> -->
  <br />
  <h2
    style="color: white; margin-left: 67px; font-size: 1.1vw; font-weight: 300"
  >
    #혼자 #심심
  </h2>
  <h2
    style="color: white; margin-left: 67px; font-size: 1.1vw; font-weight: 300"
  >
    위와 같은 상황일 때 ㅇㅇ님은 어떤 프로그램을 보셨나요?
  </h2>
  <div style="padding-left: 70px; margin-top: 10px">
    <br />
    <img src="@/assets/runningman.png" alt="Image" @click="select()" />
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
        :to="{ name: 'situationresult' }"
        class="nav-link"
        style="font-size: 20px; padding: 10px; color: black"
      >
        NEXT
      </router-link>
    </a>
  </p>
</template>
  
<script>
import HeaderBar from "@/components/layout/HeaderNavBar.vue";
import SecondBar from "@/components/layout/SecondNavBar.vue";

export default {
  name: "SituationSelect",
  components: {
    HeaderBar,
    SecondBar
  },
  methods: {
    select() {
      alert("g");
    },
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
img:hover {
  filter: brightness(0.6);
  transition: all 0.5s linear;
  transform: scale(1.2);
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