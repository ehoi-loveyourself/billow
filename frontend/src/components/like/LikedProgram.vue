<template>
  <div style="padding-left: 70px; margin-top: 10px; padding-right: 70px;">
    <div class="flex">
      <template v-if="likeList.length > 0">
        <div id="Img" v-for="like in this.likeList">
          <LikedItem v-bind:like="like" />
        </div>
      </template>
      <template v-else>
        <h2
          style="
            color: white;
            padding-left: 70px;
            font-size: 1.1vw;
            font-weight: 300;
            padding-top: 3%;
          "
        >
          찜한 콘텐츠가 없습니다.
        </h2></template
      >
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
  data() {
    return {
      likeList: [],
    };
  },
  created() {
    axios
      .get(`/api/bookmark`)
      .then((res) => {
        console.log(res.data);
        this.likeList = res.data;
      })
      .catch((ex) => {
        if (ex.response.status == 404) {
          this.likeList = [];
        } else {
          alert("로그인이 필요한 서비스입니다.");
        }
      });
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