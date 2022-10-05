<template>
  <div class="star-rating space-x-4 mx-auto">
    <img v-show="!isTrue5" @click="setFlagTrue5" class="hearted" src="@/assets/grey_star_big.png" />
    <img v-show="isTrue5" @click="setFlagFalse5" class="hearted" src="@/assets/blue_star_big.png" />
    <img v-show="!isTrue4" @click="setFlagTrue4" class="hearted" src="@/assets/grey_star_big.png" />
    <img v-show="isTrue4" @click="setFlagFalse4" class="hearted" src="@/assets/blue_star_big.png" />
    <img v-show="!isTrue3" @click="setFlagTrue3" class="hearted" src="@/assets/grey_star_big.png" />
    <img v-show="isTrue3" @click="setFlagFalse3" class="hearted" src="@/assets/blue_star_big.png" />
    <img v-show="!isTrue2" @click="setFlagTrue2" class="hearted" src="@/assets/grey_star_big.png" />
    <img v-show="isTrue2" @click="setFlagFalse2" class="hearted" src="@/assets/blue_star_big.png" />
    <img v-show="!isTrue1" @click="setFlagTrue1" class="hearted" src="@/assets/grey_star_big.png" />
    <img v-show="isTrue1" @click="setFlagFalse1" class="hearted" src="@/assets/blue_star_big.png" />
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import axios from "axios";
export default {
  computed: {
    ...mapState([
      "programId",
      "userRating",
      "isTrue1",
      "isTrue2",
      "isTrue3",
      "isTrue4",
      "isTrue5",
    ]),
  },
  methods: {
    ...mapActions(["registRating"]),
    ...mapMutations([
      "SET_FLAG_TRUE5",
      "SET_FLAG_TRUE4",
      "SET_FLAG_TRUE3",
      "SET_FLAG_TRUE2",
      "SET_FLAG_TRUE1",
      "SET_FLAG_FALSE5",
      "SET_FLAG_FALSE4",
      "SET_FLAG_FALSE3",
      "SET_FLAG_FALSE2",
      "SET_FLAG_FALSE1",
    ]),

    setFlagTrue5() {
      this.registRating({ programId: this.programId, score: 10 });
    },
    setFlagFalse5() {
      this.registRating({ programId: this.programId, score: 8 });
    },
    setFlagTrue4() {
      this.registRating({ programId: this.programId, score: 8 });
    },
    setFlagFalse4() {
      this.registRating({ programId: this.programId, score: 8 });
    },
    setFlagTrue3() {
      this.registRating({ programId: this.programId, score: 6 });
    },
    setFlagFalse3() {
      this.registRating({ programId: this.programId, score: 6 });
    },
    setFlagTrue2() {
      this.registRating({ programId: this.programId, score: 4 });
    },
    setFlagFalse2() {
      this.registRating({ programId: this.programId, score: 4 });
    },
    setFlagTrue1() {
      this.registRating({ programId: this.programId, score: 2 });
    },
    setFlagFalse1() {
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

.star-rating :checked~label {
  -webkit-text-fill-color: gold;
}

.star-rating label:hover,
.star-rating label:hover~label {
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
