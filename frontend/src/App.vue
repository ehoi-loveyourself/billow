<template>
  <div id="app">
    <PageLoader />
    <router-view />
    <footer-bar />
  </div>
</template>

<script>
import FooterBar from "@/components/layout/FooterNavBar.vue";
import PageLoader from "@/components/load/PageLoader.vue";

export default {
  name: "App",
  components: {
    FooterBar,
    PageLoader,
  },
};
</script>

<style>
html,
body {
  height: 100%;
  margin: 0;
}

#app {
  font-size: 1em;
  line-height: 1.6;
  margin: 0;
  min-height: 100%;
  position: relative;
  padding-bottom: 180px;
  background-color: #141414;
  font-family: 'NanumBarunGothic', sans-serif !important;
}

.v-application {
  font-family: 'NanumBarunGothic', sans-serif !important;
}

@font-face {
  font-family: 'NanumBarunGothic';
  src: url('assets/fonts/NanumBarunGothic.ttf') format('truetype');
}
</style>
