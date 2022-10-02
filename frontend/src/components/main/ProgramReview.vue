<template>
  <div style="margin-top: 10px">
    <br />
    <b-row style="margin-top: 20px">
      <!-- <b-col cols="2">
        <span class="inner">
          <span>
            <span v-for="index in 5" :key="index" @click="check(index)">
              <span v-if="index < score"
                ><img src="@/assets/blue_star_big.png"
              /></span>
              <span v-if="index >= score"
                ><img src="@/assets/grey_star_big.png"
              /></span>
            </span>
          </span>
        </span>
      </b-col> -->
      <b-col cols="12" style="padding-left:4.8%; padding-right: 4.8%;">
        <!-- <b-form-input
          v-model="review"
          placeholder="리뷰를 작성해주세요."
          required
          style="border-color: #a48282"
        >
        </b-form-input> -->
        <input v-model="message" class="form-control" type="text" name="search" placeholder="리뷰를 작성해주세요." required
          @keyup.enter="onSubmit()" />
      </b-col>
      <!-- <b-col cols="1">
        <b-button size="md" type="submit" @click="reviewRegist()">
          <span>등록</span>
        </b-button>
      </b-col> -->
    </b-row>
    <br />

    <article class="review_set">
      <b-row class="reviews" v-for="review in programReview" track-by="id">
        <b-col cols="1" style="text-align:right; padding-right:0.1%">
          <b-avatar class="avatar" variant="info" src="https://j7b309.p.ssafy.io/api/profile/6" size="3rem"></b-avatar>
        </b-col>
        <b-col cols="11">
            <span class="username">{{ review.userNickName }}</span>&nbsp;&nbsp;
            <span class="time">
              {{ review.regDateTime }}&nbsp;
            </span>
            <a href="#" @click="setReviewId(review.reviewId)" class="button btnBorder btnBlue" v-b-modal.modal-5><span
                style="font-size: 0.8vw">수정</span></a>&nbsp;
            <a href="#" @click="reviewDelete(review.reviewId)" class="button btnBorder btnRed"><span
                style="font-size: 0.8vw">삭제</span></a>
          <p>{{ review.content }}</p>
        </b-col>
      </b-row>
    </article>

    <br />
  </div>

  <b-modal size="m" hide-footer id="modal-5" centered no-stacking title="리뷰 수정하기" style="text-align: center">
    <div style="margin-bottom: 5%">
      <input v-model="modifyReview" class="form-control" type="text" placeholder="리뷰를 작성해주세요." required
        @keyup.enter="modifyReview()" />
    </div>
    <b-button size="m" type="submit" @click="reviewModify()" style="background-color: blue" data-bs-dismiss="modal"
      aria-label="Close">
      <span>수정</span>
    </b-button>
  </b-modal>
</template>

<script>
import { reactive } from "@vue/reactivity";
import axios from "axios";
import { mapState, mapActions, mapMutations } from "vuex";

export default {
  name: "ProgramReview",
  computed: {
    ...mapState(["programReview"]),
  },
  setup() {
    const state = reactive({
      data: [],
    });
    return { state };
  },
  data() {
    return {
      score: 0,
      message: "",
      modifyReview: "",
      reviewId: 0,
    };
  },
  methods: {
    ...mapActions([
      "registReview",
      "deleteProgramReview",
      "modifyProgramReview",
    ]),
    check(index) {
      this.score = index + 1;
    },
    onSubmit() {
      if (this.message == "") {
        alert("내용을 입력하세요.");
        return;
      }
      this.registReview(this.message);
      this.message = "";
    },
    setReviewId(reviewId) {
      this.reviewId = reviewId;
    },
    reviewModify() {
      if (this.modifyReview == "") {
        alert("내용을 입력하세요.");
        return;
      }
      this.modifyProgramReview({
        reviewId: this.reviewId,
        review: this.modifyReview,
      });
      this.modifyReview = "";
    },
    reviewDelete(reviewId) {
      if (confirm("리뷰를 삭제하시겠습니까?") == true) {
        this.deleteProgramReview(reviewId);
      } else {
        return;
      }
    },
  },
};
</script>

<style scoped>
.review_set {
  display: flex;
  flex-direction: column-reverse;
}

.reviews>h2 {
  padding-left: 4.5rem;
  font-weight: 600;
  font-size: 0.9rem;
  margin: 0;
}

.reviews>p {
  padding-left: 4.5rem;
  font-size: 0.9rem;
  color: #84868d;
}

.time {
  font-size: 0.8rem;
  color: #a1a1a1;
}

/* .star-rating {
  width: 100px;
}

.star-rating,
.star-rating span {
  display: inline-block;
  height: 18px;
  overflow: hidden;
  background: url(@/assets/star_1.png) no-repeat;
}

.star-rating span {
  background-position: left bottom;
  line-height: 0;
  vertical-align: top;
} */

a.button {
  padding: 0.2%;
  /* margin: 10px 20px 10px 0; */
  font-weight: 600;
  text-align: center;
  /* line-height: 50px; */
  color: #fff;
  border-radius: 5px;
  text-decoration: none;
  transition: all 0.2s;
}

.btnBlue.btnBorder {
  box-shadow: 0px 0px 0px 0px #212682;
}

.btnBlue.btnBorder:hover {
  box-shadow: 0px 0px 0px 5px #212682;
}

.btnBlue {
  background: #002bae;
}

.btnRed.btnBorder {
  box-shadow: 0px 0px 0px 0px #823621;
}

.btnRed.btnBorder:hover {
  box-shadow: 0px 0px 0px 5px #823621;
}

.btnRed {
  background: #ae2b00;
}
.username {
  font-weight: 600;
  font-size: 0.9rem;
  margin: 0;
  color:#f1f1f1
}
</style>
