<template>
  <div style="padding-left: 70px; margin-top: 10px">
    <div class="flex">
      <div id="Img">
        <img class="liked_2" src="@/assets/runningman.png" alt="Image" />
        <div class="button">
          <button @click="addToFavorites()" v-show="!isFavorite" style="border:none; background: none"><img
              class="hearted" src="@/assets/white_heart.png" /></button>
          <button @click="deleteFromFavorites()" v-show="isFavorite" style="background:none; border:none;"><img
              class="hearted" src="@/assets/red_heart.png" /></button>
        </div>
      </div>

      <div id="Img">
        <img class="liked_2" src="@/assets/thatman.png" alt="Image" />
        <div class="button">
          <button @click="addToFavorites()" v-show="!isFavorite" style="border:none; background: none"><img
              class="hearted" src="@/assets/white_heart.png" /></button>
          <button @click="deleteFromFavorites()" v-show="isFavorite" style="background:none; border:none;"><img
              class="hearted" src="@/assets/red_heart.png" /></button>
        </div>
      </div>

      <div id="Img">
        <img class="liked_2" src="@/assets/nangman.png" alt="Image" />
        <div class="button">
          <button @click="addToFavorites" v-show="!isFavorite" style="border:none; background: none"><img
              class="hearted" src="@/assets/white_heart.png" /></button>
          <button @click="deleteFromFavorites" v-show="isFavorite" style="background:none; border:none;"><img
              class="hearted" src="@/assets/red_heart.png" /></button>
        </div>
      </div>

      <div id="Img">
        <img class="liked_2" src="@/assets/image_2.png" alt="Image" />
        <div class="button">
          <button @click="addToFavorites" v-show="!isFavorite" style="border:none; background: none"><img
              class="hearted" src="@/assets/white_heart.png" /></button>
          <button @click="deleteFromFavorites" v-show="isFavorite" style="background:none; border:none;"><img
              class="hearted" src="@/assets/red_heart.png" /></button>
        </div>
      </div>

      <div id="Img">
        <img class="liked_2" src="@/assets/gs.png" alt="Image" />
        <div class="button">
          <button @click="addToFavorites" v-show="!isFavorite" style="border:none; background: none"><img
              class="hearted" src="@/assets/white_heart.png" /></button>
          <button @click="deleteFromFavorites" v-show="isFavorite" style="background:none; border:none;"><img
              class="hearted" src="@/assets/red_heart.png" /></button>
        </div>
      </div>
      <div id="Img">
        <img class="liked_2" src="@/assets/laggi.png" alt="Image" />
        <div class="button">
          <button @click="addToFavorites" v-show="!isFavorite" style="border:none; background: none"><img
              class="hearted" src="@/assets/white_heart.png" /></button>
          <button @click="deleteFromFavorites" v-show="isFavorite" style="background:none; border:none;"><img
              class="hearted" src="@/assets/red_heart.png" /></button>
        </div>
      </div>
     
      <div id="Img">
        <img class="liked" v-for="(d, idx) in state.newProgramPosterImg" :key="idx"
          :src="state.newProgramPosterImg[idx]" alt="Image" />
        <div class="button">
          <button v-for="(d, idx) in state.likedIndex" :key="idx" @click="addToFavorites()" v-show="!isFavorite[idx]" style="border:none; background: none"><img
              class="hearted" src="@/assets/white_heart.png" /></button>
          <button v-for="(d, idx) in state.likedIndex" :key="idx" @click="deleteFromFavorites()" v-show="isFavorite[idx]" style="background:none; border:none;"><img
              class="hearted" src="@/assets/red_heart.png" /></button>
        </div>
      </div>


      <!-- <img
      :src="state.data"
      alt="Image"
      v-for="(d, idx) in state.data"
      :key="idx"
    /> -->
    </div>
  </div>
  <!-- <div class="memos">
    <button class="btn btn-primary" @click="add()">추가하기</button>
    <ul>
      <li v-for="(d, idx) in state.data" :key="idx">{{ d }}</li>
    </ul>
  </div> -->
</template>

<script>
import { reactive } from "@vue/reactivity";
import axios from "axios";
export default {
  name: "LikedProgram",
  data() {
    return {
      isFavorite: true,
    }
  },
  setup() {
    const state = reactive({
      data: [],
      newProgramPosterImg: [],
      likedIndex: []
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
        state.likedIndex[index] = res.data[index].id;
      }

      // for (index = 0; index < res.data.length; index++) {
      //   state.newProgramTitle[index] = res.data[index].title;
      // }

      // for (index = 0; index < res.data.length; index++) {
      //   state.newProgramAge[index] = res.data[index].age;
      // }

      // for (index = 0; index < res.data.length; index++) {
      //   state.newProgramBroadcastingDay[index] =
      //     res.data[index].broadcastingDay;
      // }

      // for (index = 0; index < res.data.length; index++) {
      //   state.newProgramBroadcastingStation[index] =
      //     res.data[index].broadcastingStation;
      // }

      // for (index = 0; index < res.data.length; index++) {
      //   state.newProgramGenres[index] = res.data[index].genres;
      // }
    });

    return { state };
  },

  methods: {
    addToFavorites() {
      this.isFavorite = true;
    },
    deleteFromFavorites() {
      this.isFavorite = false;
    }
  },
};
</script>

<style scoped>
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

img:hover + .button,
.button:hover {
  display: inline-block;
  position: absolute;
  top: 1vw;
  left: 1vw;
}

.favoriting {
  display: inline-block
}

.favorite__heart {
  display: inline-block;
  padding: 3px;
  vertical-align: middle;
  line-height: 1;
  font-size: 16px;
  color: #ABABAB;
  cursor: pointer;
  -webkit-transition: color .2s ease-out;
  transition: color .2s ease-out;
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
  /* height: 600px; */
  flex-wrap: wrap;
  align-content: stretch;
}
</style>