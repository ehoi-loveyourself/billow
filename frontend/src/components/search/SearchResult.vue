<template>
    <header-bar />
    <SecondBar />
    <br />
    <h2 style="color: white; padding-left: 70px; padding-right: 70px; font-size: 1.1vw; font-weight: 300">
        "{{ searchWord }}" 검색 결과입니다.
    </h2>
    <br />
    <div style="padding-left:70px; padding-right:70px">
        <div class="flex" v-if="searchProgram != null">
            <span id="Img" v-for="search in searchProgram">
                <router-link :to="{ name: 'detail' }" @click="moveProgramDetail(search.id)" class="nav-link">
                    <img class="liked_2" :src="search.posterImg" alt="Image" />
                </router-link>
            </span>
        </div>
        <div v-else>
            <h2 style="
          color: white;
          margin-left: 45px;
          margin-top: 10px;
          font-size: 1.2vw;
          font-weight: 300;
        ">
                검색 결과가 없습니다.
            </h2>
        </div>
    </div>
    <br /><br /><br /><br /><br /><br /><br />
</template>

<script>
import HeaderBar from "@/components/layout/HeaderNavBar.vue";
import SecondBar from "@/components/layout/SecondNavBar.vue";
import { reactive } from "@vue/reactivity";
import axios from "axios";
import { mapState, mapActions } from "vuex";

export default {
    name: "SearchResult",
    components: {
        HeaderBar,
        SecondBar,
    },
    computed: {
        ...mapState(["searchWord", "searchProgram"]),
    },
    methods: {
        ...mapActions(["getProgramDetail"]),
        moveProgramDetail(programId) {
            this.getProgramDetail(programId);
        },
    },
};
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
    width: 100%;
    height: 21vw;
    padding-right: 3%;
    padding-bottom: 3%;
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

.liked {
    width: 14vw;
    height: 21vw;
    margin-right: 0.5%;
    margin-bottom: 0.5%;
}

.liked_2 {
    width: 14vw;
    height: 21vw;
    margin-right: 0.5%;
    margin-bottom: 0.5%;
}

img:hover {
    filter: brightness(0.5);
}

.button {
    display: none;
}

#Img {
    position: relative;
}

img:hover+.button,
.button:hover {
    display: inline-block;
    position: absolute;
    top: 1vw;
    left: 1vw;
}

.flex {
    display: flex;
    /* height: 600px; */
    flex-wrap: wrap;
    align-content: stretch;
}
</style>
