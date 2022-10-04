<template>
  <router-link
    :to="{ name: 'detail' }"
    @click="moveProgramDetail(like.programId)"
    class="nav-link"
  >
    <img class="liked_2" :src="like.posterImg" alt="Image" />

    <!-- 원래는 /template 위이나 라우터 링크가 우선적으로 되야 하므로 티빙과 같은 형식으로 임시 조치 -->
  </router-link>

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
  props: ["like"],
  computed: {
    ...mapState(["programId"]),
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
