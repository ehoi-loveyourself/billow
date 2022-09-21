<template>
    <header-bar />
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

export default{
    name:"SituationSelect",
components:{
    HeaderBar
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
    padding: 5px 30px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
 }
      </style>