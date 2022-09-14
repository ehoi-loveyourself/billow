<template>
  <router-view>
    <SituationIntroduction></SituationIntroduction>
    <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
  </router-view>
</template>

<script>
  import SituationIntroduction from "@/components/situation/SituationIntroduction.vue";

  export default {
    name:"SituationView",
    components:{
    SituationIntroduction,
},
};
</script>

<style></style>
