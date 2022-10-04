<template>
  <router-link
    :to="{ name: 'detail' }"
    @click="moveProgramDetail(rating.id)"
    class="nav-link"
  >
    <img class="poster" :src="rating.posterImg" alt="Image" />
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
    <img
      v-show="!isTrue1"
      @click="setFlagTrue1"
      class="hearted"
      src="@/assets/grey_star_small.png"
    />
    <img
      v-show="isTrue1"
      @click="setFlagFalse1"
      class="hearted"
      src="@/assets/blue_star_small.png"
    />
    <img
      v-show="!isTrue2"
      @click="setFlagTrue2"
      class="hearted"
      src="@/assets/grey_star_small.png"
    />
    <img
      v-show="isTrue2"
      @click="setFlagFalse2"
      class="hearted"
      src="@/assets/blue_star_small.png"
    />
    <img
      v-show="!isTrue3"
      @click="setFlagTrue3"
      class="hearted"
      src="@/assets/grey_star_small.png"
    />
    <img
      v-show="isTrue3"
      @click="setFlagFalse3"
      class="hearted"
      src="@/assets/blue_star_small.png"
    />
    <img
      v-show="!isTrue4"
      @click="setFlagTrue4"
      class="hearted"
      src="@/assets/grey_star_small.png"
    />
    <img
      v-show="isTrue4"
      @click="setFlagFalse4"
      class="hearted"
      src="@/assets/blue_star_small.png"
    />
    <img
      v-show="!isTrue5"
      @click="setFlagTrue5"
      class="hearted"
      src="@/assets/grey_star_small.png"
      style="margin-right: 5%"
    />
    <img
      v-show="isTrue5"
      @click="setFlagFalse5"
      class="hearted"
      src="@/assets/blue_star_small.png"
      style="margin-right: 5%"
    />

    <a href="#" @click="ratingDelete(rating.ratingId)" class="button"
      ><img src="@/assets/trash.png" style="width: 8%"
    /></a>
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
  created() {
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
    ...mapActions(["deleteRating", "getProgramDetail", "registRating"]),
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
</style>
