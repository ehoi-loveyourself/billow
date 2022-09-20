<template>
    <header-bar />
    <h2 style="color:white; margin-left:67px; font-size: 1.1vw; font-weight: 300;">중복 선택이 가능합니다!</h2>
        <div>
            <div class="left">
              <h2 style="color:white; text-align:center;">누구와 있으신가요?</h2>
                <div class="box">행복</div>
                <div class="box">심심</div>
                <img style="
                border: 3px solid gold;
                border-radius: 7px;
                -moz-border-radius: 7px;
                -khtml-border-radius: 7px;
                -webkit-border-radius: 7px;
                width: 170px;
                height: 170px;
                "
                src="@/assets/gs.png" />
                </div>
                <div class="right">
                  <h2 style="color:white; text-align:center;">기분이 어떠신가요?</h2>
            </div>
        </div>
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
          border: 1px solid #003458;
        }
        div.left {
          width: 50%;
          float: left;
          box-sizing: border-box;
          background: #8977ad;
        }
        div.right {
          width: 50%;
          float: right;
          box-sizing: border-box;
          background: #ece6cc;
        }
        div.box {
      float: left;
      width: 200px;
      height: 100px;
      margin: 10px;
      font-size: 30px;
      color: #fff;
      line-height: 100px;
      text-align: center;
      background: #555;
      border-radius: 20px;
  }
  
  h2{
    font-weight: 500;
    font-size: 1.4vw;
  }
      </style>