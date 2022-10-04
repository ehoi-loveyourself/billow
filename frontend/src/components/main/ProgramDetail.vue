<template>
  <b-row>
    <b-col cols="8">
      <header-bar />
    </b-col>
    <b-col cols="4">
      <SecondBar />
    </b-col>
  </b-row>
  <div style="padding-left: 70px; padding-right: 70px; color: white">
    <br />
    <section>
      <div class="column-1">
        <div style="padding-bottom: 4%">
          <button
            @click="addToFavorites"
            v-show="!isFavorite"
            style="border: none; background-color: #141414"
          >
            <img src="@/assets/white_heart.png" />
          </button>
          <button
            @click="deleteFromFavorites"
            v-show="isFavorite"
            style="background: none; border: none"
          >
            <img src="@/assets/red_heart.png" />
          </button>
        </div>
        <img class="posterImg" :src="programDetail.posterImg" />
      </div>
      <div class="column-2"></div>
      <div class="column-3">
        <span class="title">{{ programDetail.title }}</span
        ><span
          >&nbsp;&nbsp;&nbsp;&nbsp;

          <img src="@/assets/one-star.png" /> </span
        >&nbsp;<span style="vertical-align: middle">{{
          programDetail.averageRating
        }}</span
        >&nbsp;&nbsp;&nbsp;

        <div class="box">
          <span class="box_design" v-if="programDetail.age">{{
            programDetail.age
          }}</span>
          <span class="box_design" v-if="programDetail.broadcastingDay">{{
            programDetail.broadcastingDay
          }}</span>

          <span class="box_design" v-if="programDetail.broadcastingEpisode">{{
            programDetail.broadcastingEpisode
          }}</span>
          <span class="box_design" v-if="programDetail.broadcastingStation">{{
            programDetail.broadcastingStation
          }}</span>
        </div>
        <div class="genre">
          <span v-for="genre in programDetail.genres">
            #{{ genre }}&nbsp;&nbsp;
          </span>
        </div>
        <div class="ott">
          <span v-for="ott in programDetail.otts">
            <a :href="ott.url" target="_blank">
              <img class="ottImg" :src="ott.imgUrl"
            /></a>
          </span>
        </div>
        <div class="blabla">{{ programDetail.summary }}</div>
      </div>
      <div class="column-4"></div>
      <div class="column-5">
        <b-card
          style="
            background-color: #ffffff;
            color: #141414;
            width: fit-content;
            float: right;
          "
        >
          <div class="waviy" style="padding-bottom: 6%">
            <span style="--i: 1">이</span>
            <span style="--i: 2">&nbsp;</span>
            <span style="--i: 3">프</span>
            <span style="--i: 4">로</span>
            <span style="--i: 5">그</span>
            <span style="--i: 6">램</span>
            <span style="--i: 7">을</span>
            <span style="--i: 8">&nbsp;</span>
            <span style="--i: 9">보</span>
            <span style="--i: 10">셨</span>
            <span style="--i: 11">다</span>
            <span style="--i: 12">면</span>
            <span style="--i: 13">?</span>
          </div>
          <div>
            <StarRating />
          </div>
        </b-card>
      </div>
    </section>
    <br /><br /><br />

    <b-tabs id="tabs" style="align-items: center">
      <b-tab title="SCHEDULE" active>
        <Schedule />
      </b-tab>
      <b-tab title="TALK">
        <OnAirTalk />
      </b-tab>
      <b-tab title="REVIEW">
        <Review />
      </b-tab>
      <b-tab title="CAST">
        <Cast />
      </b-tab>
      <!-- <b-tab title="RELATED">
                    <LikedProgram />
                </b-tab> -->
    </b-tabs>

    <br /><br /><br />
  </div>
</template>

<script>
import HeaderBar from "@/components/layout/HeaderNavBar.vue";
import SecondBar from "@/components/layout/SecondNavBar.vue";
import Review from "@/components/main/ProgramReview.vue";
import OnAirTalk from "@/components/main/OnAirTalk.vue";
import Schedule from "@/components/main/ProgramSchedule.vue";
import Cast from "@/components/main/CastingInfo.vue";
import StarRating from "@/components/main/StarRating.vue";
import { reactive } from "@vue/reactivity";
import axios from "axios";
import { mapState, mapActions } from "vuex";

export default {
  // name: "App",
  name: "Star",
  computed: {
    ...mapState(["programDetail", "programId"]),
  },
  data() {
    return {
      score: 0,
    };
  },
  methods: {
    ...mapActions(["registBookmark", "deleteBookmark"]),
    check(index) {
      this.score = index + 1;
    },
    addToFavorites() {
      this.registBookmark();
    },
    deleteFromFavorites() {
      this.deleteBookmark();
    },
  },
  components: {
    HeaderBar,
    SecondBar,
    Review,
    OnAirTalk,
    Schedule,
    Cast,
    StarRating,
  },
};
</script>

<style scoped>
.star-rating {
  width: 100px;
}

/* .star-rating,
.star-rating span {
  display: inline-block;
  height: 18px;
  overflow: hidden;
  background: url(@/assets/star_1.png) no-repeat;
} */

.star-rating span {
  background-position: left bottom;
  line-height: 0;
  vertical-align: top;
}

/* .container {
    margin: 0;
    padding: 0;
} */

.column-1 {
  float: left;
  width: 17%;
}

.column-2 {
  float: inline-block;
  width: 2%;
}

.column-3 {
  float: right;
  width: 63.5%;
}

.column-4 {
  float: inline-block;
  width: 1.5%;
}

.column-5 {
  width: 16%;
}

section {
  align-items: flex-end;
  display: flex;
}

.title {
  font-size: 60px;
  margin-bottom: 10px;
}

.box {
  font-size: 12px;
}

.blabla {
  margin-top: 8px;
  color: white;
  font-family: Noto Sans KR, -apple-system, BlinkMacSystemFont, Roboto, Segoe UI,
    Oxygen, Ubuntu, Cantarell, Open Sans, Helvetica Neue, sans-serif;
}

.box_design {
  display: inline-block;
  background: rgb(46, 47, 49);
  color: rgb(255, 255, 255);
  font-family: "Watcha Sans", Roboto, "Noto Sans KR", "Apple SD Gothic Neo",
    "Nanum Gothic", "Malgun Gothic", sans-serif;
  font-size: 12px;
  font-weight: 900;
  vertical-align: top;
  line-height: 18px;
  height: 20px;
  padding: 1px 5px;
  border-radius: 3px;
  margin: 0px 10px 0px 0px;
}

.genre {
  color: #a1a1a1;
  font-size: 17px;
  font-family: Noto Sans KR, -apple-system, BlinkMacSystemFont, Roboto, Segoe UI,
    Oxygen, Ubuntu, Cantarell, Open Sans, Helvetica Neue, sans-serif;
  margin-top: 8px;
}

.talent {
  color: white;
  font-family: Noto Sans KR, -apple-system, BlinkMacSystemFont, Roboto, Segoe UI,
    Oxygen, Ubuntu, Cantarell, Open Sans, Helvetica Neue, sans-serif;
  margin-top: 4px;
}

.talent_label {
  color: #a2a2a2;
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
* {
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}

body {
  background-color: white;
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 0.1vh;
}
.waviy {
  position: relative;
  -webkit-box-reflect: below -1px linear-gradient(transparent, rgba(0, 0, 0, 0.1));
}
.waviy span {
  position: relative;
  display: inline-block;
  color: #1a1f63;
  text-transform: uppercase;
  animation: waviy 3s infinite;
  animation-delay: calc(0.1s * var(--i));
}
@keyframes waviy {
  0%,
  40%,
  100% {
    transform: translateY(0);
  }
  20% {
    transform: translateY(-10px);
  }
}
</style>

<style>
#tabs .nav-link {
  color: #a1a1a1;
  background: none;
  border: none;
}

#tabs .nav-tabs .nav-link.active,
.nav-tabs .nav-item.show .nav-link {
  color: #ffffff;
}

.posterImg {
  width: 100%;
}

.ottImg {
  margin-top: 1%;
  border-radius: 50%;
  width: 5%;
  margin-right: 1.5%;
}
</style>
