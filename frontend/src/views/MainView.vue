<template>
    <router-view>
        <UserRecommend></UserRecommend>
      <RecommendedProgram></RecommendedProgram>
    </router-view>
  </template>
  
  <script>
import RecommendedProgram from "../components/main/RecommendedProgram.vue";
import UserRecommend from "../components/main/UserRecommend.vue";

    export default {
    name: "MainView",
    components: { RecommendedProgram, UserRecommend }
};
  </script>
  
  <style></style>