<template>
  <div id="app">
    <b-container>
        <router-view />
    </b-container>
    <footer-bar />
  </div>
</template>

<script>

import FooterBar from "@/components/layout/FooterNavBar.vue";

export default{
  name:"App",
  components:{
    FooterBar,
  },
}
</script>


<style>
html,
body {
	height: 100%;
  margin:0;
}

a {
	text-decoration: none;
}

a:hover {
	text-decoration: none;
	font-weight: bold;
	color: chartreuse;
}

a.router-link-exact-active {
	color: #064635;
  font-weight: bold;
}

#app {
	font-family: basic-scans, sans-serif;
	font-size: 1em;
	line-height: 1.6;
	margin: 0;
	min-height: 100%;
	position: relative;
	padding-bottom: 133px;
  background-color: #141414;
}
</style>
