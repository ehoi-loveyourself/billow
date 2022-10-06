<template>
  <header-bar />
  <SecondBar />
  <br />
  <h2
    style="
      color: white;
      padding-left: 70px;
      padding-right: 70px;
      font-size: 1.1vw;
      font-weight: 300;
    "
  >
    "{{ searchWord }}" 검색 결과입니다.
  </h2>
  <br />
  <div style="padding-left: 70px; padding-right: 70px">
    <div class="flex" v-if="searchProgram != null">
      <span id="Img" v-for="search in searchProgram">
        <!-- <router-link
          :to="{ name: 'detail' }"
          @click="moveProgramDetail(search.id)"
          class="nav-link"
        > -->
        <div class="box-wrap">
          <div class="box">
        <div @click="moveProgramDetail(search.id)">       
          <img class="searchimg" :src="search.posterImg" alt="Image" />
        </div>
        <div class="info">
            <span class="title">{{ search.title }}</span>
          </div>
      </div>
          </div>
        <!-- </router-link> -->
      </span>
    </div>
    <div v-else>
      <h2
        style="
          color: white;
          margin-left: 45px;
          margin-top: 10px;
          font-size: 1.2vw;
          font-weight: 300;
        "
      >
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
import { mapState, mapActions, mapMutations } from "vuex";

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
    ...mapMutations(["SET_PROGRAM_ID"]),
    moveProgramDetail(programId) {
      this.SET_PROGRAM_ID(programId);
      window.location.replace(`/detail`);
    },
  },
};
</script>

<style scoped>
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
  bottom: 10px;
  color: #fff;
  box-sizing: border-box;
  opacity: 0;
  transition: all 0.3s ease-in-out;
}

.box .info .title {
  font-size: 1vw;
  font-weight: 400;
  text-align: left;
  white-space: normal;

}

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

/* @font-face {
    font-family: "GoyangIlsan";
    src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/GoyangIlsan.woff") format("woff");
    font-weight: normal;
    font-style: normal;
}

body {
    background-color: #1a237e;
    font-family: "GoyangIlsan";
} */

.searchimg {
  width: 14vw;
  height: 21vw;
  padding-right: 3%;
  padding-bottom: 3%;
  cursor: pointer;
}

img:hover {
  filter: brightness(0.5);
}

#Img {
  position: relative;
  text-align: left;
}

.flex {
  display: flex;
  flex-wrap: wrap;
  align-content: stretch;
}
</style>
