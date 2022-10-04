<template>
  <div class="star-rating space-x-4 mx-auto">
    <img
      v-show="!isTrue5"
      @click="setFlagTrue5"
      class="hearted"
      src="@/assets/grey_star_big.png"
    />
    <img
      v-show="isTrue5"
      @click="setFlagFalse5"
      class="hearted"
      src="@/assets/blue_star_big.png"
    />
    <img
      v-show="!isTrue4"
      @click="setFlagTrue4"
      class="hearted"
      src="@/assets/grey_star_big.png"
    />
    <img
      v-show="isTrue4"
      @click="setFlagFalse4"
      class="hearted"
      src="@/assets/blue_star_big.png"
    />
    <img
      v-show="!isTrue3"
      @click="setFlagTrue3"
      class="hearted"
      src="@/assets/grey_star_big.png"
    />
    <img
      v-show="isTrue3"
      @click="setFlagFalse3"
      class="hearted"
      src="@/assets/blue_star_big.png"
    />
    <img
      v-show="!isTrue2"
      @click="setFlagTrue2"
      class="hearted"
      src="@/assets/grey_star_big.png"
    />
    <img
      v-show="isTrue2"
      @click="setFlagFalse2"
      class="hearted"
      src="@/assets/blue_star_big.png"
    />
    <img
      v-show="!isTrue1"
      @click="setFlagTrue1"
      class="hearted"
      src="@/assets/grey_star_big.png"
    />
    <img
      v-show="isTrue1"
      @click="setFlagFalse1"
      class="hearted"
      src="@/assets/blue_star_big.png"
    />
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import axios from "axios";
export default {
  data() {
    return {
      userRating: null,
      isTrue1: false,
      isTrue2: false,
      isTrue3: false,
      isTrue4: false,
      isTrue5: false,
    };
  },
  computed: {
    ...mapState(["programId"]),
  },
  created() {
    axios.get(`/api/program/rating/${this.programId}`).then((res) => {
      //사용자 평점 조회 GET
      console.log(res.data);
      this.userRating = res.data;
      if (this.userRating != null) {
        if (this.userRating.score >= 2) {
          this.isTrue1 = true;
        }
        if (this.userRating.score >= 4) {
          this.isTrue2 = true;
        }
        if (this.userRating.score >= 6) {
          this.isTrue3 = true;
        }
        if (this.userRating.score >= 8) {
          this.isTrue4 = true;
        }
        if (this.userRating.score >= 10) {
          this.isTrue5 = true;
        }
      }
    });
  },
  methods: {
    ...mapActions(["registRating"]),
    setFlagTrue5() {
      this.isTrue1 = true;
      this.isTrue2 = true;
      this.isTrue3 = true;
      this.isTrue4 = true;
      this.isTrue5 = true;
      this.registRating({ programId: this.programId, score: 10 });
    },
    setFlagFalse5() {
      this.isTrue5 = false;
      this.registRating({ programId: this.programId, score: 8 });
    },
    setFlagTrue4() {
      this.isTrue1 = true;
      this.isTrue2 = true;
      this.isTrue3 = true;
      this.isTrue4 = true;
      this.isTrue5 = false;
      this.registRating({ programId: this.programId, score: 8 });
    },
    setFlagFalse4() {
      //   this.isTrue1 = false;
      //   this.isTrue2 = false;
      //   this.isTrue3 = false;
      //   this.isTrue4 = false;
      this.isTrue5 = false;
      this.registRating({ programId: this.programId, score: 8 });
    },
    setFlagTrue3() {
      this.isTrue1 = true;
      this.isTrue2 = true;
      this.isTrue3 = true;
      //   this.isTrue4 = false;
      //   this.isTrue5 = false;
      this.registRating({ programId: this.programId, score: 6 });
    },
    setFlagFalse3() {
      //   this.isTrue1 = false;
      //   this.isTrue2 = false;
      //   this.isTrue3 = false;
      this.isTrue4 = false;
      this.isTrue5 = false;
      this.registRating({ programId: this.programId, score: 6 });
    },
    setFlagTrue2() {
      this.isTrue1 = true;
      this.isTrue2 = true;
      this.isTrue3 = false;
      this.isTrue4 = false;
      this.isTrue5 = false;
      this.registRating({ programId: this.programId, score: 4 });
    },
    setFlagFalse2() {
      //   this.isTrue1 = false;
      //   this.isTrue2 = false;
      this.isTrue3 = false;
      this.isTrue4 = false;
      this.isTrue5 = false;
      this.registRating({ programId: this.programId, score: 4 });
    },
    setFlagTrue1() {
      this.isTrue1 = true;
      this.isTrue2 = false;
      this.isTrue3 = false;
      this.isTrue4 = false;
      this.isTrue5 = false;
      this.registRating({ programId: this.programId, score: 2 });
    },
    setFlagFalse1() {
      this.isTrue1 = true;
      this.isTrue2 = false;
      this.isTrue3 = false;
      this.isTrue4 = false;
      this.isTrue5 = false;
      this.registRating({ programId: this.programId, score: 2 });
    },
  },
};
</script>

<style>
.star-rating {
  display: flex;
  flex-direction: row-reverse;
  font-size: 1.8rem;
  line-height: 2.5rem;
  justify-content: space-around;
  padding: 0 0.2em;
  text-align: center;
  width: 5em;
}

.star-rating input {
  display: none;
}

.star-rating label {
  -webkit-text-fill-color: transparent;
  -webkit-text-stroke-width: 0.1vw;
  -webkit-text-stroke-color: #2b2a29;
  cursor: pointer;
}

.star-rating :checked ~ label {
  -webkit-text-fill-color: gold;
}

.star-rating label:hover,
.star-rating label:hover ~ label {
  -webkit-text-fill-color: #fff58c;
}

.poster {
  width: 14vw;
  height: 21vw;
  padding-right: 0.5%;
  padding-bottom: 0.5%;
}

#Img {
  text-align: center;
  padding-right: 0.5%;
  padding-bottom: 2%;
  position: relative;
}

.flex {
  display: flex;
  flex-wrap: wrap;
  align-content: stretch;
}
</style>
