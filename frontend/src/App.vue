<template>
  <div id="app">
    <!-- <PageLoader /> -->
    <router-view />
    <footer-bar />
  </div>
</template>

<script>
import FooterBar from "@/components/layout/FooterNavBar.vue";
import PageLoader from "@/components/PageLoader.vue";

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

/* a {
  text-decoration: none;
  color: #e5e5e5;
} */

/* a:hover {
  text-decoration: none;
  color: #a1a1a1;
} */

/* a.router-link-exact-active {
  color: #ffffff;
  font-weight: 700;
} */

#app {
  font-family: basic-scans, sans-serif;
  font-size: 1em;
  line-height: 1.6;
  margin: 0;
  min-height: 100%;
  position: relative;
  padding-bottom: 180px;
  background-color: #141414;
}
</style>
