<template>
  <router-link :to="{ name: 'detail' }" @click="moveProgramDetail(rating.id)" class="nav-link">
    <div class="box-wrap">
      <div class="box">
        <img class="poster" :src="rating.posterImg" alt="Image" />
        <div class="info">
          <h3>{{ rating.title }}</h3>
        </div>
      </div>
    </div>

  </router-link>
  <div style="text-align: center; margin-bottom: 18%; margin-top: 3%">
    <!-- <span>
      <span v-for="index in 5" :key="index" @click="check(index)">
        <span v-if="index < rating.score"
          ><img src="@/assets/blue_star_small.png" style="width: 8%"
        /></span>
        <span v-if="index >= rating.score"
          ><img src="@/assets/grey_star_small.png" style="width: 8%"
        /></span>
      </span>
    </span> -->
    <img v-show="!isTrue1" @click="setFlagTrue1" class="hearted" src="@/assets/grey_star_small.png" />
    <img v-show="isTrue1" @click="setFlagFalse1" class="hearted" src="@/assets/blue_star_small.png" />
    <img v-show="!isTrue2" @click="setFlagTrue2" class="hearted" src="@/assets/grey_star_small.png" />
    <img v-show="isTrue2" @click="setFlagFalse2" class="hearted" src="@/assets/blue_star_small.png" />
    <img v-show="!isTrue3" @click="setFlagTrue3" class="hearted" src="@/assets/grey_star_small.png" />
    <img v-show="isTrue3" @click="setFlagFalse3" class="hearted" src="@/assets/blue_star_small.png" />
    <img v-show="!isTrue4" @click="setFlagTrue4" class="hearted" src="@/assets/grey_star_small.png" />
    <img v-show="isTrue4" @click="setFlagFalse4" class="hearted" src="@/assets/blue_star_small.png" />
    <img v-show="!isTrue5" @click="setFlagTrue5" class="hearted" src="@/assets/grey_star_small.png"
      style="margin-right:5%" />
    <img v-show="isTrue5" @click="setFlagFalse5" class="hearted" src="@/assets/blue_star_small.png"
      style="margin-right:5%" />

    <a href="#" @click="ratingDelete(rating.ratingId)" class="button"><img src="@/assets/trash.png"
        style="width:8%" /></a>
  </div>
</template>

<script>
import { reactive } from "@vue/reactivity";
import axios from "axios";
import { mapActions, mapState } from "vuex";

export default {
  name: "Star",
  props: ["rating"],
  data() {
    return {
      score: 0,
      isTrue1: false,
      isTrue2: false,
      isTrue3: false,
      isTrue4: false,
      isTrue5: false,
    };
  },
  computed: {
    ...mapState(["ratingList"]),
  },
  created() {
    this.getRating();
    if (this.rating.score >= 2) {
      this.isTrue1 = true;
    }
    if (this.rating.score >= 4) {
      this.isTrue2 = true;
    }
    if (this.rating.score >= 6) {
      this.isTrue3 = true;
    }
    if (this.rating.score >= 8) {
      this.isTrue4 = true;
    }
    if (this.rating.score >= 10) {
      this.isTrue5 = true;
    }
  },
  methods: {
    ...mapActions(["deleteRating", "getProgramDetail", "registRating", "getRating"]),
    // check(index) {
    //   this.score = index + 1;
    // },
    ratingDelete(ratingId) {
      if (confirm("평점을 삭제하시겠습니까?") == true) {
        this.deleteRating(ratingId);
      } else {
        return;
      }
    },
    moveProgramDetail(programId) {
      this.getProgramDetail(programId);
    },
    setFlagTrue5() {
      this.isTrue1 = true;
      this.isTrue2 = true;
      this.isTrue3 = true;
      this.isTrue4 = true;
      this.isTrue5 = true;
      this.registRating({ programId: this.rating.id, score: 10 });
    },
    setFlagFalse5() {
      this.isTrue5 = false;
      this.registRating({ programId: this.rating.id, score: 8 });
    },
    setFlagTrue4() {
      this.isTrue1 = true;
      this.isTrue2 = true;
      this.isTrue3 = true;
      this.isTrue4 = true;
      this.isTrue5 = false;
      this.registRating({ programId: this.rating.id, score: 8 });
    },
    setFlagFalse4() {
      //   this.isTrue1 = false;
      //   this.isTrue2 = false;
      //   this.isTrue3 = false;
      //   this.isTrue4 = false;
      this.isTrue5 = false;
      this.registRating({ programId: this.rating.id, score: 8 });
    },
    setFlagTrue3() {
      this.isTrue1 = true;
      this.isTrue2 = true;
      this.isTrue3 = true;
      //   this.isTrue4 = false;
      //   this.isTrue5 = false;
      this.registRating({ programId: this.rating.id, score: 6 });
    },
    setFlagFalse3() {
      //   this.isTrue1 = false;
      //   this.isTrue2 = false;
      //   this.isTrue3 = false;
      this.isTrue4 = false;
      this.isTrue5 = false;
      this.registRating({ programId: this.rating.id, score: 6 });
    },
    setFlagTrue2() {
      this.isTrue1 = true;
      this.isTrue2 = true;
      this.isTrue3 = false;
      this.isTrue4 = false;
      this.isTrue5 = false;
      this.registRating({ programId: this.rating.id, score: 4 });
    },
    setFlagFalse2() {
      //   this.isTrue1 = false;
      //   this.isTrue2 = false;
      this.isTrue3 = false;
      this.isTrue4 = false;
      this.isTrue5 = false;
      this.registRating({ programId: this.rating.id, score: 4 });
    },
    setFlagTrue1() {
      this.isTrue1 = true;
      this.isTrue2 = false;
      this.isTrue3 = false;
      this.isTrue4 = false;
      this.isTrue5 = false;
      this.registRating({ programId: this.rating.id, score: 2 });
    },
    setFlagFalse1() {
      this.isTrue1 = true;
      this.isTrue2 = false;
      this.isTrue3 = false;
      this.isTrue4 = false;
      this.isTrue5 = false;
      this.registRating({ programId: this.rating.id, score: 2 });
    },
  },
};
</script>

<style scoped>
.saveButton {
  background-color: #ffffff;
  padding: 3px 30px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
}

.logo {
  padding-left: 70px;
  padding-top: 0.05%;
}

.poster {
  width: 14.5vw;
  height: 22vw;
  padding-right: 0.5%;
  padding-bottom: 0.5%;
  margin-bottom: 1.5%;
}

#Img {
  /* text-align: center; */
  padding-right: 0.5%;
  padding-bottom: 2%;
  position: relative;
}

.flex {
  display: flex;
  /* height: 600px; */
  flex-wrap: wrap;
  align-content: stretch;
}

/* .poster:hover {
    filter: brightness(0.5);
  } */

/* .explain {
    display: none;
  }
  
  .poster:hover+.explain,
  .explain:hover {
    display: inline-block;
    position: absolute;
    top: 1vw;
    left: 1vw;
  } */

.btnRed.btnBorder:hover {
  box-shadow: 0px 0px 0px 5px #823621;
}

.btnRed {
  background: #ae2b00;
}

a.button {
  padding: 1%;
  /* margin: 10px 20px 10px 0; */
  font-weight: 600;
  text-align: center;
  /* line-height: 50px; */
  color: #fff;
  border-radius: 5px;
  text-decoration: none;
  transition: all 0.1s;
  padding-top: 0;
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
