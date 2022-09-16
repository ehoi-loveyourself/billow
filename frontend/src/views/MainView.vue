<template>
    <router-view>
        <NewProgram></NewProgram>
      <RecommendedProgram></RecommendedProgram>
    </router-view>
  </template>
  
  <script>
import RecommendedProgram from "../components/main/RecommendedProgram.vue";
import NewProgram from "../components/main/NewProgram.vue";

    export default {
    name: "MainView",
    components: { RecommendedProgram, NewProgram }
};
  </script>
  
  <style></style>