<template>
  <div style="padding-left: 70px; margin-top: 10px">
    <div class="flex">
      <div id="Img" v-for="like in state.likeList">
        <LikedItem v-bind:like="like" />
      </div>
    </div>
  </div>
</template>

<script>
import { reactive } from "@vue/reactivity";
import axios from "axios";
import { mapActions } from "vuex";
import { mapState } from "vuex";
import LikedItem from "@/components/like/LikedItem.vue";

export default {
  name: "LikedProgram",
  components: {
    LikedItem,
  },
  setup() {
    const state = reactive({
      likeList: [],
    });
    axios.get(`/api/bookmark`).then((res) => {
      console.log(res.data);
      state.likeList = res.data;
    });
    return { state };
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

img:hover + .button,
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
</style>

<!-- 밑은 재현 코드 -->
<!-- 
<template>
  <div style="padding-left: 70px; margin-top: 10px">
    <div class="flex">
      <div id="Img" v-for="like in state.likeList">
        <router-link
          :to="{ name: 'detail' }"
          @click="moveProgramDetail(like.programId)"
          class="nav-link"
        >
        <img class="liked_2" :src="like.posterImg" alt="Image" />
        <div class="button">
          <button @click="deleteFromFavorites(like.programId)" :id="like.programId" v-show="isFavorite" style="background: none; border: none">
            <img class="hearted" src="@/assets/red_heart.png" />
          </button>
        </div>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import router from "@/router";
import { reactive } from "@vue/reactivity";
import axios from "axios";
import { RouterLink } from "vue-router";
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
    deleteFromFavorites(programId) {
      if (confirm("내가 찜한 콘텐츠에서 삭제하시겠습니까?") == true){
        this.isFavorite = false;
        this.userDeleteBookmark(programId);
        history.go();
      }
      else{
        this.isFavorite = true;
        history.go();
      }
    },
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
  flex-wrap: wrap;
  align-content: stretch;
}
</style> -->
