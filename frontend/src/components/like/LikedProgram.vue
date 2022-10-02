<template>
  <div style="padding-left: 70px; margin-top: 10px">
    <div class="flex">
      <div id="Img" v-for="like in state.likeList">
        <!-- <router-link
          :to="{ name: 'detail' }"
          @click="moveProgramDetail(like.programId)"
          class="nav-link"
        > -->
        <img class="liked_2" :src="like.posterImg" alt="Image" />
        <div class="button">
          <!-- <button @click="addToFavorites(like.programId)" :id="like.programId" v-show="!isFavorite" style="border: none; background: none">
            <img class="hearted" src="@/assets/white_heart.png" />
          </button> -->
          <button @click="deleteFromFavorites(like.programId)" :id="like.programId" v-show="isFavorite" style="background: none; border: none">
            <img class="hearted" src="@/assets/red_heart.png" />
          </button>
          <!-- <button @click="setFavorites(isFavorite)" :id="like.programId" v-show="!isFavorite" style="border: none; background: none">
            <img class="hearted" src="@/assets/white_heart.png" />
          </button>
          <button @click="setFavorites(isFavorite)" :id="like.programId" v-show="isFavorite" style="background: none; border: none">
            <img class="hearted" src="@/assets/red_heart.png" />
          </button> -->
        </div>
        <!-- </router-link> -->
      </div>

      <!-- <div id="Img">
        <img
          class="liked"
          v-for="(d, idx) in state.newProgramPosterImg"
          :key="idx"
          :src="state.newProgramPosterImg[idx]"
          alt="Image"
        />
        <div class="button">
          <button
            v-for="(d, idx) in state.likedIndex"
            :key="idx"
            @click="addToFavorites()"
            v-show="!isFavorite[idx]"
            style="border: none; background: none"
          >
            <img class="hearted" src="@/assets/white_heart.png" />
          </button>
          <button
            v-for="(d, idx) in state.likedIndex"
            :key="idx"
            @click="deleteFromFavorites()"
            v-show="isFavorite[idx]"
            style="background: none; border: none"
          >
            <img class="hearted" src="@/assets/red_heart.png" />
          </button>
        </div>
      </div> -->

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
import { mapActions } from "vuex";
import { mapState } from "vuex";

export default {
  name: "LikedProgram",
  data() {
    return {
      isFavorite: true,
    };
  },
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
    // addToFavorites(programId) {
    //   this.isFavorite = true;
    //   this.userRegistBookmark(programId);
    // },
    deleteFromFavorites(programId) {
      this.isFavorite = false;

      // alert("내가 찜한 콘텐츠에서 삭제되었습니다.");
      if (confirm("내가 찜한 콘텐츠에서 삭제하시겠습니까?") == true){
        this.userDeleteBookmark(programId);
        history.go();
      }
      else{
        history.go();
      }
      
    },
    // setFavorites(isFavorite) {
    //   if (isFavorite) {
    //     this.isFavorite = false;
    //     this.userDeleteBookmark(programId);
    //   }
    //   else {
    //     this.isFavorite = true;
    //     this.userRegistBookmark(programId);
    //   }
    // },
    moveProgramDetail(programId) {
      this.getProgramDetail(programId);
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
  /* height: 600px; */
  flex-wrap: wrap;
  align-content: stretch;
}
</style>
