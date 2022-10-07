<template>
  <router-view>
    <div class="help-tip">
      <p>본 화면에서 회원님만을 위한 맞춤 추천 프로그램 리스트를 확인하실 수 있습니다!</p>
    </div>
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
  
<style>
  
.help-tip {
  position: absolute;
  top: 0.8%;
  right:1.2%;
  text-align: center;
  background-color: #6e7c7c;
  border-radius: 50%;
  width: 24px;
  height: 24px;
  font-size: 14px;
  line-height: 26px;
  cursor: default;
  padding-left: 2px;
}

.help-tip:before {
  content: '?';
  font-weight: bold;
  color: #fff;
}

.help-tip:hover p {
  display: block;
  transform-origin: 100% 0%;
  -webkit-animation: fadeIn 0.3s ease-in-out;
  animation: fadeIn 0.3s ease-in-out;
}

.help-tip p {
  /* The tooltip */
  display: none;
  text-align: left;
  background-color: #1E2021;
  width: 30vw;
  position: absolute;
  border-radius: 3px;
  box-shadow: 1px 1px 1px rgba(0, 0, 0, 0.2);
  right: -4px;
  color: #FFF;
  font-size: 0.9vw;
  line-height: 1.4;
  padding:3px;
}

.help-tip p:before {
  /* The pointer of the tooltip */
  position: absolute;
  content: '';
  width: 0;
  height: 0;
  border: 6px solid transparent;
  border-bottom-color: #1E2021;
  left: 20%;
  top: 12px;
}

.help-tip p:after {
  /* Prevents the tooltip from being hidden */
  width: 100%;
  height: 40px;
  content: '';
  position: absolute;
  top: -40px;
  left: 0;
}

/* CSS animation */

@-webkit-keyframes fadeIn {
  0% {
    opacity: 0;
    transform: scale(0.6);
  }

  100% {
    opacity: 100%;
    transform: scale(1);
  }
}

@keyframes fadeIn {
  0% {
    opacity: 0;
  }

  100% {
    opacity: 100%;
  }
}
</style>