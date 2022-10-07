<template>
  <br/>
  <div class="help-tip">
    <p>
      회원님께서 남겨주신 평점은 추천 알고리즘에 모두 반영됩니다.<br />
      평점 정보가 너무 적으면 추천의 정확성이 떨어지기 때문에, 최소한은 남겨주시는 것이 좋아요!</p>
  </div>
  <div v-if="ratingList != null">
    <span class="flex">
      <div id="Img" v-for="rating in ratingList">
        <UserRatingItem v-bind:rating="rating" />
      </div>
    </span>
    <br /><br /><br /><br /><br />
  </div>
  <div v-else>
    <h2 style="
        color: white;
        margin-left: 70px;
        font-size: 1.1vw;
        font-weight: 300;
      ">
      평점 내역이
      없습니다.<br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
    </h2>
  </div>
</template>

<script>
import { mapActions, mapState, mapMutations } from "vuex";
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
    ...mapMutations(["SET_PROGRAM_ID"]),
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
  padding-right: 0.5%;
  padding-bottom: 2%;
  position: relative;
}

.flex {
  display: flex;
  flex-wrap: wrap;
  align-content: stretch;
}

.btnRed.btnBorder:hover {
  box-shadow: 0px 0px 0px 5px #823621;
}

.btnRed {
  background: #ae2b00;
}

a.button {
  padding: 1%;
  font-weight: 600;
  text-align: center;
  color: #fff;
  border-radius: 5px;
  text-decoration: none;
  transition: all 0.1s;
  padding-top: 0;
}

img:hover {
  filter: brightness(0.5);
}

.box .info .detailbox {
  font-size: 12px;
}

.box .info .detailbox_design {
  display: inline-block;
  background: rgb(46, 47, 49);
  color: rgb(255, 255, 255);
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
  display: flex;
  align-items: center;
  justify-content: center;
  flex-wrap: wrap;
  align-content: stretch;
}

.box {
  position: relative;
  width: 100%;
  border: none;
  cursor: pointer;
  overflow: hidden;
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
  box-sizing: border-box;
  opacity: 0;
  transition: all 0.3s ease-in-out;
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

.help-tip {
  position: absolute;
  top: 2.5%;
  right:1.2%;
  text-align: center;
  background-color: #6e7c7c;
  border-radius: 50%;
  width: 24px;
  height: 24px;
  font-size: 14px;
  line-height: 26px;
  cursor: default;
  padding-left: 2px;

}

.help-tip:before {
  content: '?';
  font-weight: bold;
  color: #fff;
}

.help-tip:hover p {
  display: block;
  transform-origin: 100% 0%;
  -webkit-animation: fadeIn 0.3s ease-in-out;
  animation: fadeIn 0.3s ease-in-out;
}

.help-tip p {
  display: none;
  text-align: left;
  background-color: #1E2021;
  width: 28vw;
  position: absolute;
  border-radius: 3px;
  box-shadow: 1px 1px 1px rgba(0, 0, 0, 0.2);
  right: -4px;
  color: #FFF;
  font-size: 1vw;
  line-height: 1.4;
  padding:20px;
}

.help-tip p:before {
  position: absolute;
  content: '';
  width: 0;
  height: 0;
  border: 6px solid transparent;
  border-bottom-color: #1E2021;
  left: 20%;
  top: 12px;
}

.help-tip p:after {
  width: 100%;
  height: 40px;
  content: '';
  position: absolute;
  top: -40px;
  left: 0;
}

@-webkit-keyframes fadeIn {
  0% {
    opacity: 0;
    transform: scale(0.6);
  }

  100% {
    opacity: 100%;
    transform: scale(1);
  }
}

@keyframes fadeIn {
  0% {
    opacity: 0;
  }

  100% {
    opacity: 100%;
  }
}
</style>
