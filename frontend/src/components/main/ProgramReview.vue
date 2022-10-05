<template>
  <div style="margin-top: 10px">
    <br />
    <b-row style="margin-top: 20px">
      <b-col cols="10" style="padding-right:0; margin-left: 4.2%;">
        <input v-model="message" class="form-control" type="text" name="search" placeholder="리뷰를 작성해주세요." required
          @keyup.enter="onSubmit()" />
      </b-col>
      <b-col cols="1">
        <b-button size="md" type="text" @click="onSubmit()" style="text-align:left">
          <span>등록</span>
        </b-button>
      </b-col>
    </b-row>
    <br />

    <article class="review_set">
      <template v-if="programReview != null && programReview.length > 0">
        <b-row class="reviews" v-for="review in programReview" track-by="id">
          <b-col cols="1" style="text-align: right; padding-right: 0.1%">
            <b-avatar class="avatar" variant="info" :src="review.userProfile" size="3rem"></b-avatar>
          </b-col>
          <b-col cols="11">
            <span class="username">{{ review.userNickName }}</span>&nbsp;&nbsp;
            <span class="time"> {{ review.regDateTime }}&nbsp;&nbsp;&nbsp;</span>
            <span v-if="userInfo.nickName==review.userNickName">
              <a href="#" @click="setReviewId(review.reviewId)" class="button btnBorder btnBlue" v-b-modal.modal-5><span
                  style="font-size: 0.8vw">수정</span></a>&nbsp;
              <a href="#" @click="reviewDelete(review.reviewId)" class="button btnBorder btnRed"
                style="background-color:#990000"><span style="font-size: 0.8vw">삭제</span></a></span>
            <p>{{ review.content }}</p>
          </b-col>
        </b-row>
      </template>
      <template v-else>
        <h2 style="
            color: white;
            margin-left: 70px;
            font-size: 1.1vw;
            font-weight: 300;
          ">
          등록된 리뷰가 없습니다.
        </h2>
      </template>
    </article>

    <br />
  </div>

  <b-modal size="m" hide-footer id="modal-5" centered no-stacking title="리뷰 수정하기"
    style="text-align: center; color: #ffffff;">
    <div style="margin-bottom: 5%">
      <input v-model="modifyReview" class="form-control" type="text" placeholder="리뷰를 작성해주세요." required
        @keyup.enter="modifyReview()" />
    </div>
    <b-button size="sm" type="submit" @click="reviewModify()" style="background-color: #141414" data-bs-dismiss="modal"
      aria-label="Close">
      <span style="color:#ffffff">수정</span>
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
    ...mapState(["programReview", "userInfo"]),
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

a.button {
  padding: 0.2%;
  padding-top: 0;
  font-weight: 600;
  text-align: center;
  color: #fff;
  border-radius: 5px;
  text-decoration: none;
  transition: all 0.2s;
}

.btnBlue.btnBorder {
  box-shadow: 0px 0px 0px 0px #212682;
}

.btnBlue.btnBorder:hover {
  box-shadow: 0px 0px 0px 3px #212682;
}

.btnBlue {
  background: #002bae;
}

.btnRed.btnBorder {
  box-shadow: 0px 0px 0px 0px #823621;
}

.btnRed.btnBorder:hover {
  box-shadow: 0px 0px 0px 3px #823621;
}

.username {
  font-weight: 600;
  font-size: 0.9rem;
  margin: 0;
  color: #f1f1f1;
}
</style>
<style>
.modal-content {
  background-color: #1f1f1f;
}

.modal-header {
  border-bottom: #1f1f1f;
}

.modal-header .btn-close {
  color: white;
}
</style>
