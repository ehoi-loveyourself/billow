<template>
  <div class="about">
    <div id="app">
      <div>
        <b-row>
          <b-col cols="8">
      <header-bar/>
    </b-col>
    <b-col cols="4"> 
      <SecondBar/>
      </b-col>
    </b-row>
    </div>
       <router-view />   
  </div>
  </div>
</template>
  
<script>
  import HeaderBar from "@/components/layout/HeaderNavBar.vue";
  import SecondBar from "@/components/layout/SecondNavBar.vue";

  export default{
    name:"App",
    components:{
    HeaderBar,
    SecondBar
  },
  }
  </script>