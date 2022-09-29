<template>
  <!-- <b-row> -->
          <!-- <b-col cols="8"> -->
      <header-bar/>
    <!-- </b-col> -->
    <!-- <b-col cols="4">  -->
      <SecondBar/>
      <!-- </b-col> -->
    <!-- </b-row> -->
    <br/>
  <h2
    style="color: white; margin-left: 70px; font-size: 1.1vw; font-weight: 300"
  >
    중복 선택이 가능합니다!
  </h2>
  <br /><br />
  <div>
    <div class="left" style="border-right: 1px solid #ffffff">
      <h2 style="color: white; text-align: center">누구와 있으신가요?</h2>
      <img
        style="margin-top: 50px; margin-left: 169px; width: 25%"
        src="@/assets/situation/alone.png"
      />
      <img
        style="margin-top: 50px; margin-left: 50px; width: 25%"
        src="@/assets/situation/friend.png"
      />
      <br /><br />
      <img
        style="margin-left: 169px; margin-top: 15px; width: 25%"
        src="@/assets/situation/couple.png"
      />
      <img
        style="margin-left: 50px; margin-top: 15px; width: 25%"
        src="@/assets/situation/family.png"
      />
    </div>
    <div class="right">
      <h2 style="color: white; text-align: center">기분이 어떠신가요?</h2>
      <img
        style="margin-top: 50px; margin-left: 169px; width: 25%"
        src="@/assets/situation/happy.png"
      />
      <img
        style="margin-top: 50px; margin-left: 50px; width: 25%"
        src="@/assets/situation/boring.png"
      />
      <br /><br />
      <img
        style="margin-left: 169px; margin-top: 15px; width: 25%"
        src="@/assets/situation/angry.png"
      />
      <img
        style="margin-left: 50px; margin-top: 15px; width: 25%"
        src="@/assets/situation/sad.png"
      />
    </div>
  </div>
  <br />
  <p style="text-align: center">
    <a class="startButton" href="#" role="button" style="border-radius: 15px">
      <router-link
        :to="{ name: 'situationcollectdata' }"
        class="nav-link"
        style="font-size: 20px; padding: 10px; color: black"
      >
        NEXT
      </router-link>
    </a>
  </p>
  <br /><br /><br /><br /><br />
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
};
</script>
  
  <style scoped>
div {
  width: 100%;
  height: 550px;
}
div.left {
  width: 50%;
  float: left;
  box-sizing: border-box;
}
div.right {
  width: 50%;
  float: right;
  box-sizing: border-box;
}

h2 {
  font-weight: 500;
  font-size: 1.4vw;
}
img {
  width: 15%;
}
img:hover {
  filter: brightness(0.5);
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