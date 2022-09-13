<template>
    <div class="about">
      <div id="app">
      <b-container>
        <header-bar />
        <b-row>
          <b-col>
            <router-view />
          </b-col>
        </b-row>
      </b-container>
    </div>
    </div>
  </template>
  
  <script>
  import HeaderBar from "@/components/layout/HeaderNavBar.vue";

  export default{
    name:"App",
    components:{
      HeaderBar,
    },
  }
  </script>