<template>
    <header-bar />
    <br>
    <h2 style="color:white; margin-left:67px; font-size: 1.1vw; font-weight: 300;">#혼자 #심심 에 ㅇㅇ님은 어떤 프로그램을 보셨나요?</h2>
    <LikedProgram/>
        <p style="text-align:center">
          <a class="startButton" href="#" role="button" style="border-radius: 15px;">
            <router-link :to="{ name: 'situationselect' }" class="nav-link" style="font-size:20px; padding:10px; color:black;">
                NEXT
            </router-link>
          </a>
        </p>
</template>
  
<script>
import HeaderBar from "@/components/layout/HeaderNavBar.vue";
import LikedProgram from "@/components/like/LikedProgram.vue"

export default{
    name:"SituationSelect",
components:{
    HeaderBar,
    LikedProgram
},
}
</script>
  
  <style scoped>
  h2{
    font-weight: 500;
    font-size: 1.4vw;
  }
  img{
    width:15%;
  }
  img:hover{
    filter: brightness(0.5);
  }
 .startButton{
    background-color: #ffffff;
    padding: 3px 30px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
 }
      </style>