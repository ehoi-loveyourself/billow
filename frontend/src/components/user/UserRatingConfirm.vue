<template>
  <br />
  <div>
    <br />
    <div class="flex">
      <template v-if="ratingList != null">
        <div id="Img" v-for="rating in ratingList">
          <UserRatingItem v-bind:rating="rating" />
        </div>
      </template>
      <template v-else>평점 내역이 없습니다.</template>
    </div>
    <br /><br /><br /><br />
  </div>
  <p style="text-align: center">
    <a class="saveButton" href="#" role="button" style="border-radius: 15px">
      저장
    </a>
  </p>
  <br /><br /><br /><br />
</template>

<script>
import { mapActions, mapState } from "vuex";
import UserRatingItem from "./UserRatingItem.vue";

export default {
  name: "Star",
  components: {
    UserRatingItem,
  },
  data() {
    return {
      score: 0,
    };
  },
  computed: {
    ...mapState(["ratingList"]),
  },
  created() {
    this.getRating();
  },
  methods: {
    ...mapActions(["getRating", "deleteRating", "getProgramDetail"]),
    check(index) {
      this.score = index + 1;
    },
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
