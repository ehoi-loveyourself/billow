<template>
  <br />
  <div>
    <div class="flex">
      <div v-if="ratingList != null">
        <div id="Img" v-for="rating in ratingList">
          <UserRatingItem v-bind:rating="rating" />
        </div>
      </div>
      <div v-else>평점 내역이 없습니다.</div>
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
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/GoyangIlsan.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

body {
  background-color: #1a237e;
  font-family: "GoyangIlsan";
}
</style>
