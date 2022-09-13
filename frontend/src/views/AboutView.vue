<template>
    <div class="about">
      <div id="app">
        <header-bar /> 
         <router-view />   
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