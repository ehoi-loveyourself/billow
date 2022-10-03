<template>
  <!-- <b-row> -->
  <!-- <b-col cols="8"> -->
  <header-bar />
  <!-- </b-col> -->
  <!-- <b-col cols="4">  -->
  <SecondBar />
  <!-- </b-col> -->
  <!-- </b-row> -->
  <br />
  <!-- <h2
    style="color: white; margin-left: 70px; font-size: 1.1vw; font-weight: 300"
  >
    중복 선택이 가능합니다!
  </h2> -->
  <br /><br />
  <div>
    <div class="left" style="border-right: 1px solid #ffffff">
      <h2 style="color: white; text-align: center">누구와 있으신가요?</h2>
      <img
        style="margin-top: 50px; margin-left: 169px; width: 25%"
        @click="setWho(1)"
        src="@/assets/situation/alone.png"
      />
      <img
        style="margin-top: 50px; margin-left: 50px; width: 25%"
        @click="setWho(2)"
        src="@/assets/situation/friend.png"
      />
      <br /><br />
      <img
        style="margin-left: 169px; margin-top: 15px; width: 25%"
        @click="setWho(3)"
        src="@/assets/situation/couple.png"
      />
      <img
        style="margin-left: 50px; margin-top: 15px; width: 25%"
        @click="setWho(4)"
        src="@/assets/situation/family.png"
      />
    </div>
    <div class="right">
      <h2 style="color: white; text-align: center">기분이 어떠신가요?</h2>
      <img
        style="margin-top: 50px; margin-left: 169px; width: 25%"
        @click="setGenre(1)"
        src="@/assets/situation/happy.png"
      />
      <img
        style="margin-top: 50px; margin-left: 50px; width: 25%"
        @click="setGenre(2)"
        src="@/assets/situation/boring.png"
      />
      <br /><br />
      <img
        style="margin-left: 169px; margin-top: 15px; width: 25%"
        @click="setGenre(3)"
        src="@/assets/situation/angry.png"
      />
      <img
        style="margin-left: 50px; margin-top: 15px; width: 25%"
        @click="setGenre(4)"
        src="@/assets/situation/sad.png"
      />
    </div>
  </div>
  <br />
  <p style="text-align: center">
    <a
      class="startButton"
      href="#"
      role="button"
      style="border-radius: 15px"
      @click="setCondition"
    >
      NEXT
    </a>
  </p>
  <br /><br /><br /><br /><br />
</template>

<script>
import HeaderBar from "@/components/layout/HeaderNavBar.vue";
import SecondBar from "@/components/layout/SecondNavBar.vue";
import { mapMutations } from "vuex";

export default {
  name: "SituationSelect",
  components: {
    HeaderBar,
    SecondBar,
  },
  data() {
    return {
      who: "",
      genre: "",
    };
  },
  methods: {
    ...mapMutations(["SET_CONDITION"]),
    setWho(who) {
      if (who == 1) {
        this.who = "혼자";
      } else if (who == 2) {
        this.who = "친구와";
      } else if (who == 3) {
        this.who = "애인과";
      } else if (who == 4) {
        this.who = "가족과";
      }
    },
    setGenre(genre) {
      if (genre == 1) {
        this.genre = "행복";
      } else if (genre == 2) {
        this.genre = "심심";
      } else if (genre == 3) {
        this.genre = "화남";
      } else if (genre == 4) {
        this.genre = "우울";
      }
    },
    setCondition() {
      if (this.who == "" || this.genre == "") {
        alert("상황을 선택해주세요.");
        return;
      }
      this.SET_CONDITION({ who: this.who, genre: this.genre });
      this.$router.push("/situationcollectdata");
    },
  },
};
</script>

<style scoped>
div {
  width: 100%;
  height: 550px;
}
div.left {
  width: 50%;
  float: left;
  box-sizing: border-box;
}
div.right {
  width: 50%;
  float: right;
  box-sizing: border-box;
}

h2 {
  font-weight: 500;
  font-size: 1.4vw;
}
img {
  width: 15%;
}
img:hover {
  filter: brightness(0.5);
}

.startButton {
  background-color: #ffffff;
  padding: 3px 30px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
}
</style>
