<template>
    <router-view>
        <br>
      <div style="text-align:center">
        <br/><br/>
        <img src="@/assets/image_1.png"/>
        <br/><br/><br/><br/><br/>
      </div>
      <MainOnAirProgram></MainOnAirProgram>
    </router-view>
  </template>
  
  <script>
import MainOnAirProgram from "../components/main/MainOnAirProgram.vue";

    export default {
    name: "MainView",
    components: { MainOnAirProgram }
};
  </script>
  
  <style></style>