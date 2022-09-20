<template>
    <header-bar />
    <br>
    <h2 style="color:white; margin-left:67px; font-size: 1.1vw; font-weight: 300;">중복 선택이 가능합니다!</h2>
    <br><br>
        <div>
            <div class="left" style="border-right:1px solid #ffffff;">
              <h2 style="color:white; text-align:center;">누구와 있으신가요?</h2>
                <img style="
                border-radius: 7px;
                -moz-border-radius: 7px;
                -khtml-border-radius: 7px;
                -webkit-border-radius: 7px;
                margin-top: 50px;
                margin-left: 200px;
                "
                src="@/assets/situation/solo.jpg" />
                <img style="
                border-radius: 7px;
                -moz-border-radius: 7px;
                -khtml-border-radius: 7px;
                -webkit-border-radius: 7px;
                margin-top: 50px;
                margin-left: 150px;
                "
                src="@/assets/situation/friend.jpg" />
                <br><br>
                <img style="
                border-radius: 7px;
                -moz-border-radius: 7px;
                -khtml-border-radius: 7px;
                -webkit-border-radius: 7px;
                margin-left: 200px;
                "
                src="@/assets/situation/couple.jpg" />
                <img style="
                border-radius: 7px;
                -moz-border-radius: 7px;
                -khtml-border-radius: 7px;
                -webkit-border-radius: 7px;
                margin-left: 150px;
                "
                src="@/assets/situation/family.jpg" />
                </div>
                <div class="right">
                  <h2 style="color:white; text-align:center;">기분이 어떠신가요?</h2>
                  <img style="
                border-radius: 7px;
                -moz-border-radius: 7px;
                -khtml-border-radius: 7px;
                -webkit-border-radius: 7px;
                margin-top: 50px;
                margin-left: 200px;
                "
                src="@/assets/situation/happy.jpg" />
                <img style="
                border-radius: 7px;
                -moz-border-radius: 7px;
                -khtml-border-radius: 7px;
                -webkit-border-radius: 7px;
                margin-top: 50px;
                margin-left: 150px;
                "
                src="@/assets/situation/boring.jpg" />
                <br><br>
                <img style="
                border-radius: 7px;
                -moz-border-radius: 7px;
                -khtml-border-radius: 7px;
                -webkit-border-radius: 7px;
                margin-left: 200px;
                "
                src="@/assets/situation/angry.jpg" />
                <img style="
                border-radius: 7px;
                -moz-border-radius: 7px;
                -khtml-border-radius: 7px;
                -webkit-border-radius: 7px;
                margin-left: 150px;
                "
                src="@/assets/situation/sad.jpg" />
            </div>
        </div>
        <br>
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
  div {
          width: 100%;
          height: 500px;
        }
        div.left {
          width: 50%;
          float: left;
          box-sizing: border-box;
        }
        div.right {
          width: 50%;
          float: right;
          box-sizing: border-box;
        }
  
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