<template>
  <img class="poster" :src="random.posterImg" alt="Image" />
  <br />
  <div class="star-rating space-x-4 mx-auto">
    <img
      v-show="!isTrue5"
      @click="setFlagTrue5"
      class="hearted"
      src="@/assets/grey_star_small.png"
    />
    <img
      v-show="isTrue5"
      @click="setFlagFalse5"
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
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  data() {
    return {
      ratings: 5,
      isTrue1: false,
      isTrue2: false,
      isTrue3: false,
      isTrue4: false,
      isTrue5: false,
    };
  },
  props: ["random"],
  methods: {
    ...mapActions(["registRating"]),
    setFlagTrue5() {
      this.isTrue1 = true;
      this.isTrue2 = true;
      this.isTrue3 = true;
      this.isTrue4 = true;
      this.isTrue5 = true;
      this.registRating({ programId: this.random.id, score: 10 });
    },
    setFlagFalse5() {
      this.isTrue5 = false;
      this.registRating({ programId: this.random.id, score: 8 });
    },
    setFlagTrue4() {
      this.isTrue1 = true;
      this.isTrue2 = true;
      this.isTrue3 = true;
      this.isTrue4 = true;
      this.isTrue5 = false;
      this.registRating({ programId: this.random.id, score: 8 });
    },
    setFlagFalse4() {
      //   this.isTrue1 = false;
      //   this.isTrue2 = false;
      //   this.isTrue3 = false;
      //   this.isTrue4 = false;
      this.isTrue5 = false;
      this.registRating({ programId: this.random.id, score: 8 });
    },
    setFlagTrue3() {
      this.isTrue1 = true;
      this.isTrue2 = true;
      this.isTrue3 = true;
      //   this.isTrue4 = false;
      //   this.isTrue5 = false;
      this.registRating({ programId: this.random.id, score: 6 });
    },
    setFlagFalse3() {
      //   this.isTrue1 = false;
      //   this.isTrue2 = false;
      //   this.isTrue3 = false;
      this.isTrue4 = false;
      this.isTrue5 = false;
      this.registRating({ programId: this.random.id, score: 6 });
    },
    setFlagTrue2() {
      this.isTrue1 = true;
      this.isTrue2 = true;
      this.isTrue3 = false;
      this.isTrue4 = false;
      this.isTrue5 = false;
      this.registRating({ programId: this.random.id, score: 4 });
    },
    setFlagFalse2() {
      //   this.isTrue1 = false;
      //   this.isTrue2 = false;
      this.isTrue3 = false;
      this.isTrue4 = false;
      this.isTrue5 = false;
      this.registRating({ programId: this.random.id, score: 4 });
    },
    setFlagTrue1() {
      this.isTrue1 = true;
      this.isTrue2 = false;
      this.isTrue3 = false;
      this.isTrue4 = false;
      this.isTrue5 = false;
      this.registRating({ programId: this.random.id, score: 2 });
    },
    setFlagFalse1() {
      this.isTrue1 = true;
      this.isTrue2 = false;
      this.isTrue3 = false;
      this.isTrue4 = false;
      this.isTrue5 = false;
      this.registRating({ programId: this.random.id, score: 2 });
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
