<template>
    <!-- <b-row> -->
    <!-- <b-col cols="8"> -->
    <header-bar />
    <!-- </b-col> -->
    <!-- <b-col cols="4">  -->
    <SecondBar />
    <!-- </b-col> -->
    <!-- </b-row> -->
    <br />
    <h2 style="color: white; margin-left: 70px; font-size: 1.1vw; font-weight: 300">
        "글리치" 검색 결과입니다.
    </h2>
    <br /><br />
    <div class="box-wrap">
        <div class="box">
            <div class="img">
                <a class="enterDetail" href="#">
                    <router-link :to="{ name: 'detail' }" class="nav-link">
                        <img :src="state.newProgramPosterImg[idx]" alt="Hover Effect" />
                    </router-link>
                </a>
            </div>
            <div class="info">
                <h3>{{ state.newProgramTitle[idx] }}</h3>
                <div class="detailbox">
                    <span class="detailbox_design" v-if="state.newProgramAge[idx]">{{ state.newProgramAge[idx]
                    }}</span><span class="detailbox_design" v-if="state.newProgramBroadcastingDay[idx]">{{
                        state.newProgramBroadcastingDay[idx] }}</span><span class="detailbox_design"
                        v-if="state.newProgramGenres[idx][0]">{{ state.newProgramGenres[idx][0] }}</span><span
                        class="detailbox_design" v-if="state.newProgramBroadcastingStation[idx]">{{
                        state.newProgramBroadcastingStation[idx] }}</span>
                </div>
            </div>
        </div>
    </div>

    <br /><br /><br /><br /><br />
</template>
    
<script>
import HeaderBar from "@/components/layout/HeaderNavBar.vue";
import SecondBar from "@/components/layout/SecondNavBar.vue";
import { reactive } from "@vue/reactivity";
import axios from "axios";

export default {
    name: "SearchResult",
    components: {
        HeaderBar,
        SecondBar
    },
    setup() {
        const state = reactive({
            data: [],
            newProgramPosterImg: [],
            newProgramTitle: [],
            newProgramAge: [],
            newProgramBroadcastingDay: [],
            newProgramGenres: [],
            newProgramBroadcastingStation: [],
        });
        axios.get("/api/recommend/new").then((res) => {
            // 신규 프로그램 추천 데이터 GET
            console.log(res.data);
            console.log(res.data[0].title);

            var index;

            for (index = 0; index < res.data.length; index++) {
                state.newProgramPosterImg[index] = res.data[index].posterImg;
            }

            for (index = 0; index < res.data.length; index++) {
                state.newProgramTitle[index] = res.data[index].title;
            }

            for (index = 0; index < res.data.length; index++) {
                state.newProgramAge[index] = res.data[index].age;
            }

            for (index = 0; index < res.data.length; index++) {
                state.newProgramBroadcastingDay[index] =
                    res.data[index].broadcastingDay;
            }

            for (index = 0; index < res.data.length; index++) {
                state.newProgramBroadcastingStation[index] =
                    res.data[index].broadcastingStation;
            }

            for (index = 0; index < res.data.length; index++) {
                state.newProgramGenres[index] = res.data[index].genres;
            }
        });
        return { state };
    },
    data() {
        return {
            list: [],
        };
    },
}
</script>
    
<style scoped>
.box .info .detailbox {
    font-size: 12px;
}

.box .info .detailbox_design {
    display: inline-block;
    background: rgb(46, 47, 49);
    color: rgb(255, 255, 255);
    font-family: "Watcha Sans", Roboto, "Noto Sans KR", "Apple SD Gothic Neo",
        "Nanum Gothic", "Malgun Gothic", sans-serif;
    font-size: 12px;
    font-weight: 400;
    vertical-align: top;
    line-height: 18px;
    height: 20px;
    padding: 1px 5px;
    border: none;
    margin: 0px 10px 0px 0px;
    margin-bottom: 5px;
}

h2 {
    font-weight: 500;
    font-size: 1.4vw;
    margin-bottom: 0.7%;
    padding-left: 70px;
}

img {
    margin-bottom: 20%;
    /* width:16.1%; */
    width: 100%;
    height: 20vw;
    padding-right: 0;
    margin-right: 0;
}

.box-wrap {
    /* width: 100vw; height: 100vh; */
    display: flex;
    align-items: center;
    justify-content: center;
}

.box {
    position: relative;
    /* background: #000; */
    width: 100%;
    border: none;
    cursor: pointer;
    overflow: hidden;
    /* box-shadow: 1px 1px 3px rgba(0,0,0,0.4); */
}

.box img {
    width: 100%;
    transition: all 0.3s ease-in-out;
}

.box .info {
    position: absolute;
    left: 5px;
    bottom: 45px;
    color: #fff;
    width: 100%;
    /* padding: 15px; */
    box-sizing: border-box;
    opacity: 0;
    transition: all 0.3s ease-in-out;
}

.box .info h3 {
    font-size: 12px;
    font-weight: 600;
    line-height: 20px;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    padding-bottom: 3px;
    text-align: left;
}

/* .box .info p {
  font-size: 16px;
  overflow: hidden; 
  white-space: nowrap;
  text-overflow: ellipsis;
} */
.box:hover .info {
    opacity: 1;
}

.box:hover img {
    opacity: 0.2;
}

.box:hover:before {
    width: 60px;
}

.box:hover:after {
    height: 60px;
}

@font-face {
    font-family: "GoyangIlsan";
    src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/GoyangIlsan.woff") format("woff");
    font-weight: normal;
    font-style: normal;
}

body {
    background-color: #1a237e;
    font-family: "GoyangIlsan";
}
</style>