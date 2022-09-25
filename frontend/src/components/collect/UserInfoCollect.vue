<template>
    <header-bar />
    <br>
    <h2 style="color:white; margin-left:70px; font-size: 1.1vw; font-weight: 300;">회원님의 정보를 입력해주세요.<br/>
BILLOW가 회원님이 좋아할만한 프로그램을 추천해줄거예요.</h2>
    <br><br>
    <section>
       <b-row style="margin-bottom:2%">
        <b-col cols="2" style="text-align:right">
            프로필
        </b-col>
        <b-col cols="10" style="padding-left:1%">
            <b-avatar class="avatar" variant="info" src="https://placekitten.com/300/300" size="4rem"></b-avatar>
            <b-avatar class="avatar" variant="info" src="https://placekitten.com/300/400" size="4rem"></b-avatar>
            <b-avatar class="avatar" variant="info" src="https://placekitten.com/300/500" size="4rem"></b-avatar>
            <b-avatar class="avatar" variant="info" src="https://placekitten.com/300/600" size="4rem"></b-avatar>
            <b-avatar class="avatar" variant="info" src="https://placekitten.com/300/700" size="4rem"></b-avatar>
            <b-avatar class="avatar" variant="info" src="https://placekitten.com/300/800" size="4rem"></b-avatar>
            <b-avatar class="avatar" variant="info" src="https://placekitten.com/300/900" size="4rem"></b-avatar>
            <b-avatar class="avatar" variant="info" src="https://placekitten.com/300/200" size="4rem"></b-avatar>
            <b-avatar class="avatar" variant="info" src="https://placekitten.com/300/100" size="4rem"></b-avatar>
            <b-avatar class="avatar" variant="info" src="https://placekitten.com/300/350" size="4rem"></b-avatar>
            <b-avatar class="avatar" variant="info" src="https://placekitten.com/300/450" size="4rem"></b-avatar>
        </b-col>
        </b-row>
        <b-row style="margin-bottom:2%">        
            <b-col cols="2" style="text-align:right">
성별
</b-col>
<b-col cols="10" style="padding-left:1%">
    <b-row>
        <b-col cols="1">
    <b-form-radio v-model="selected_gender" :aria-describedby="ariaDescribedby" name="some-radios" value="A">남</b-form-radio>
</b-col>
<b-col>
      <b-form-radio v-model="selected_gender" :aria-describedby="ariaDescribedby" name="some-radios" value="B">여</b-form-radio>
    </b-col>
      </b-row>
</b-col>
       </b-row>
       <b-row style="margin-bottom:2%">        
            <b-col cols="2" style="text-align:right">
연령대
</b-col>
<b-col cols="10" style="padding-left:1%">
    <b-form-select v-model="selected" :options="options_age" style="width:20%"></b-form-select>
</b-col>
       </b-row>
       <b-row style="margin-bottom:2%">        
            <b-col cols="2" style="text-align:right">
닉네임
</b-col>
<b-col cols="10" style="padding-left:1%">
    <b-form-input v-model="text" placeholder="닉네임을 입력하세요." style="width:20%"></b-form-input>
</b-col>
       </b-row>
       <b-row style="margin-bottom:2%">        
            <b-col cols="2" style="text-align:right">
지역
</b-col>
<b-col cols="10" style="padding-left:1%">
    <b-form-select v-model="selected" :options="options_region" style="width:20%"></b-form-select>
</b-col>
       </b-row>
       <b-row style="margin-bottom:2%">        
            <b-col cols="2" style="text-align:right">
통신사
</b-col>
<b-col cols="10" style="padding-left:1%">
    <b-form-select v-model="selected" :options="options_tv_carrier" style="width:20%"></b-form-select>
</b-col>
       </b-row>
    </section>
        <br><br/>
        <p style="text-align:center">
          <a class="startButton" href="#" role="button" style="border-radius: 15px;">
            <router-link :to="{ name: 'userratingprogram' }" class="nav-link" style="font-size:20px; padding:10px; color:black;">
                NEXT
            </router-link>
          </a>
        </p>
        <br/><br/><br/><br/><br/>
</template>
  
<script>
import HeaderBar from "@/components/layout/HeaderNavBar.vue";

export default{
    data() {
      return {
        text: '',
        selected_gender:'',
        selected: null,
        options_age: [
          { value: null, text: '연령대를 선택하세요' },
          { value: 'a', text: '20대' },
          { value: 'b', text: '30대' },
          { value: 'c', text: '40대' },
          { value: 'd', text: '50대'},
          { value: 'd', text: '60대 이상'},
        ],
        options_region: [
          { value: null, text: '지역을 선택하세요' },
          { value: 'a', text: '서울/인천/경기' },
          { value: 'b', text: '부산/경남' },
          { value: 'c', text: '대구/경북' },
          { value: 'd', text: '광주/전남'},
          { value: 'e', text: '전북'},
          { value: 'f', text: '대전/세종/충남'},
          { value: 'g', text: '충북'},
          { value: 'h', text: '강원'},
          { value: 'i', text: '울산'},
          { value: 'j', text: '제주'},
        ],
        options_tv_carrier: [
          { value: null, text: '통신사를 선택하세요' },
          { value: 'a', text: 'SK 브로드밴드' },
          { value: 'b', text: 'KT olleh' },
          { value: 'c', text: 'LG U+' },
          { value: 'd', text: 'SkyLife'},
          { value: 'e', text: 'LG 헬로비전'},
          { value: 'f', text: '티브로드'},
          { value: 'g', text: '아름방송(성남)'},
          { value: 'h', text: '딜라이브'},
          { value: 'i', text: '현대 HCN(새로넷 방송)'},
          { value: 'j', text: '서경방송'},
          { value: 'k', text: 'NIB 남인천방송'},
          { value: 'l', text: 'CCS 충북방송'},
          { value: 'm', text: 'CMB방송'},
          { value: 'n', text: 'JCN울산중앙방송'},
          { value: 'o', text: '하나방송'},
        ],
      }
    },
    name:"UserInfoCollect",
components:{
    HeaderBar
},
}
</script>
  
<style scoped>
  section{
    color:#ffffff;
    padding-left: 70px;
    padding-right: 70px;
    font-size: 1.2vw;
  }
  h2{
    font-weight: 500;
    font-size: 1.4vw;
  }
  .avatar{
    margin-right: 2%;
  }
  .avatar:hover{
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
      <style>
         .form-check-input:checked {
    background-color:#a1a1a1;
    border-color:#141414;
}
      </style>