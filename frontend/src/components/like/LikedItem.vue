<template>
  <div class="box-wrap">
    <div class="box">
      <img class="liked_2" :src="like.posterImg" alt="Image" @click="moveProgramDetail(like.programId)" />
      <div class="info">
        <h3>{{ like.title }}</h3>
      </div>
      <div class="button">
        <button @click="addToFavorites(like.programId)" :id="like.programId" v-show="!isFavorite"
          style="border: none; background: none">
          <img class="hearted" src="@/assets/white_heart.png" />
        </button>
        <button @click="deleteFromFavorites(like.programId)" :id="like.programId" v-show="isFavorite"
          style="background: none; border: none">
          <img class="hearted" src="@/assets/red_heart.png" />
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import router from "@/router";
import { reactive } from "@vue/reactivity";
import axios from "axios";
import { mapActions } from "vuex";
import { mapState } from "vuex";

export default {
  name: "LikedProgram",
  data() {
    return {
      isFavorite: true,
    };
  },
  props: ["like"],
  computed: {
    ...mapState(["programId"]),
  },
  setup() {
    const state = reactive({
      likeList: [],
    });
    axios.get(`/api/bookmark`).then((res) => {
      //즐겨찾기 데이터 GET
      console.log(res.data);
      state.likeList = res.data;
    });
    return { state };
  },

  methods: {
    ...mapActions([
      "getProgramDetail",
      "userRegistBookmark",
      "userDeleteBookmark",
    ]),
    addToFavorites(programId) {
      this.isFavorite = true;
      this.userRegistBookmark(programId);
    },
    deleteFromFavorites(programId) {
      this.isFavorite = false;
      this.userDeleteBookmark(programId);
    },
    moveProgramDetail(programId) {
      this.getProgramDetail(programId);
      this.$router.push({ name: 'detail' })
    },
  },
};
</script>

<style scoped>
.liked {
  width: 14vw;
  height: 21vw;
}

.liked_2 {
  width: 14vw;
  height: 21vw;
  margin-right: 0.5%;
  margin-bottom: 0.5%;
  cursor: pointer;
}

img:hover {
  filter: brightness(0.5);
}

.button {
  display: none;
}

#Img {
  position: relative;
  margin-right: 0.5%;
  margin-bottom: 0.5%;
}

img:hover+.button,
.button:hover {
  display: inline-block;
  position: absolute;
  top: 1vw;
  left: 1vw;
}

.favoriting {
  display: inline-block;
}

.favorite__heart {
  display: inline-block;
  padding: 3px;
  vertical-align: middle;
  line-height: 1;
  font-size: 16px;
  color: #ababab;
  cursor: pointer;
  -webkit-transition: color 0.2s ease-out;
  transition: color 0.2s ease-out;
}

.favorite__heart.is-disabled:hover {
  cursor: default;
}

.favorite__checkbox {
  position: absolute;
  overflow: hidden;
  clip: rect(0 0 0 0);
  height: 1px;
  width: 1px;
  margin: -1px;
  padding: 0;
  border: 0;
}

.favorite__heart__selected {
  color: #df470b;
}

.flex {
  display: flex;
  flex-wrap: wrap;
  align-content: stretch;
}


img:hover {
  filter: brightness(0.5)
}

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
  padding: 1px 5px;
  border: none;
  margin: 0px 10px 0px 0px;
  margin-bottom: 5px;
}


.box-wrap {
  /* width: 100vw; height: 100vh; */
  display: flex;
  align-items: center;
  justify-content: center;
  flex-wrap: wrap;
  align-content: stretch;
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
  transition: all 0.3s ease-in-out;
}

.box .info {
  position: absolute;
  left: 5px;
  bottom: 10px;
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
