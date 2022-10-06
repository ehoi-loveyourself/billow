<template>
  <div class="box-wrap">
    <div class="box">
      <img
        class="liked_2"
        :src="like.posterImg"
        alt="Image"
        @click="moveProgramDetail(like.programId)"
      />
      <div class="button">
        <button
          @click="addToFavorites(like.programId)"
          :id="like.programId"
          v-show="!isFavorite"
          style="border: none; background: none"
        >
          <img class="hearted" src="@/assets/white_heart.png" />
        </button>
        <button
          @click="deleteFromFavorites(like.programId)"
          :id="like.programId"
          v-show="isFavorite"
          style="background: none; border: none"
        >
          <img class="hearted" src="@/assets/red_heart.png" />
        </button>
      </div>
      <div class="info">
        <div class="title">{{ like.title }}</div>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive } from "@vue/reactivity";
import axios from "axios";
import { mapState, mapActions, mapMutations } from "vuex";

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
    ...mapMutations(["SET_PROGRAM_ID"]),
    addToFavorites(programId) {
      this.isFavorite = true;
      this.userRegistBookmark(programId);
    },
    deleteFromFavorites(programId) {
      this.isFavorite = false;
      this.userDeleteBookmark(programId);
    },
    moveProgramDetail(programId) {
      this.SET_PROGRAM_ID(programId);
      this.$router.push({ name: "detail" });
      window.location.replace(`/detail`);
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

.liked_2:hover {
  filter: brightness(0.3);
}

.button {
  display: none;
}

#Img {
  position: relative;
  margin-right: 0.5%;
  margin-bottom: 0.5%;
}

.liked_2:hover + .button,
.button:hover {
  display: inline-block;
  position: absolute;
  top: 1vw;
  right: 1vw;
}

.flex {
  display: flex;
  flex-wrap: wrap;
  align-content: stretch;
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
  box-sizing: border-box;
  opacity: 0;
  transition: all 0.3s ease-in-out;
}

.box .info .title {
  font-size: 0.9vw;
  font-weight: 400;
  text-align: left;
  white-space: normal;
}
.box:hover .info {
  opacity: 1;
}

.box:hover img {
  opacity: 1;
}

.box:hover:before {
  width: 60px;
}

.box:hover:after {
  height: 60px;
}

@font-face {
  font-family: "GoyangIlsan";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/GoyangIlsan.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

body {
  background-color: #1a237e;
  font-family: "GoyangIlsan";
}
</style>
