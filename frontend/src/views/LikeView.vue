<template>
  <router-view>
    <LikedProgram></LikedProgram>
    <br/><br/><br/><br/><br/><br/><br/>
  </router-view>
</template>

<script>
import LikedProgram from '@/components/like/LikedProgram.vue';
export default {
    name: "LikeView",
    components: { LikedProgram }
};
</script>

<style></style>
