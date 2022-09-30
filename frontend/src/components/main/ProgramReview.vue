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
      <b-col cols="11">
        <b-form-input
          v-model="review"
          placeholder="리뷰를 작성해주세요."
          required
          style="border-color: #a48282"
        >
        </b-form-input>
      </b-col>
      <b-col cols="1">
        <b-button size="md" type="submit" @click="reviewRegist()">
          <span>등록</span>
        </b-button>
      </b-col>
    </b-row>
    <br />
    <section>
      <article class="review_set">
        <article v-for="review in programReview" class="reviews">
          <h2>
            {{ review.userNickName }}&nbsp;&nbsp;
            <span class="wrap-star">
              {{ review.regDateTime }}
            </span>
            <a href="#" class="button btnBorder btnBlue" v-b-modal.modal-5
              >수정</a
            >&nbsp;
            <a href="#" @click="reviewDelete()" class="button btnBorder btnRed"
              >삭제</a
            >
          </h2>
          <p>{{ review.content }}</p>
        </article>
      </article>
    </section>

    <!-- 리뷰 수정 삭제 버튼 예시 -->
    <section>
      <article class="review_set">
        <article class="reviews">
          <h2>
            BILLOW&nbsp;&nbsp;
            <span class="wrap-star">2022-09-30 10:30&nbsp;&nbsp;&nbsp;</span>
            <a href="#" class="button btnBorder btnBlue" v-b-modal.modal-5
              ><span style="font-size:0.8vw">수정</span></a
            >&nbsp;
            <a href="#" @click="reviewDelete()" class="button btnBorder btnRed"
              ><span style="font-size:0.8vw">삭제</span></a
            >
          </h2>
          <p>이 프로그램 너무 재밌어요!</p>
        </article>
        <article class="reviews">
          <h2>
            billow&nbsp;&nbsp;
            <span class="wrap-star">2022-09-30 11:00&nbsp;&nbsp;&nbsp;</span>
            <a href="#" class="button btnBorder btnBlue" v-b-modal.modal-5
              ><span style="font-size:0.8vw">수정</span></a
            >&nbsp;
            <a href="#" @click="reviewDelete()" class="button btnBorder btnRed"
              ><span style="font-size:0.8vw">삭제</span></a
            >
          </h2>
          <p>저도 너무 재밌어요!</p>
        </article>
      </article>
    </section>
    <br />
  </div>

  <b-modal
    size="m"
    hide-footer
    id="modal-5"
    centered
    no-stacking
    title="리뷰 수정하기"
    style="text-align: center"
  >
    <div style="margin-bottom: 5%">
      <b-form-input
        v-model="review"
        placeholder="리뷰를 작성해주세요."
        required
        style="border-color: #a48282"
      >
      </b-form-input>
    </div>
    <b-button
      size="m"
      type="submit"
      @click="reviewModify()"
      style="background-color: blue"
      data-bs-dismiss="modal"
      aria-label="Close"
    >
      <span>수정</span>
    </b-button>
  </b-modal>
</template>

<script>
import { reactive } from "@vue/reactivity";
import axios from "axios";
import { mapState } from "vuex";

export default {
  name: "ProgramReview",
  computed: {
    ...mapState(["programReview", "programId"]),
  },
  setup() {
    const state = reactive({
      data: [],
    });

    // const add = () => {
    //   // 지금 안됨.
    //   // state.data.push("추가한 내용");
    //   axios.post("/api/recommend/new").then((res) => {
    //     console.log(res.data);
    //   });
    // };

    // axios.get("/api/recommend/new").then((res) => {
    //   // 데이터 가져오는 거.
    //   console.log(res);
    //   console.log(res.data);
    //   console.log(res.data[0]);
    //   console.log(res.data[0].title);
    //   console.log(res.data[0].backdropPath);
    //   console.log(res.data[0].posterImg);
    //   state.data[0] = res.data[0].title;
    //   state.data[1] = res.data[0].backdropPath;
    //   state.data[2] = res.data[0].posterImg;
    // });

    return { state };
  },
  data() {
    return {
      score: 0,
      review: "",
    };
  },
  methods: {
    check(index) {
      this.score = index + 1;
    },
    reviewRegist() {
      // post 사용 가능한 코드입니다. review 등록버튼 클릭 이벤트.
      alert("post 테스트");
      axios
        .post(`/api/review/${this.programId}`, {
          // /review/프로그램아이디
          content: this.review,
        })
        .then((response) => {
          console.warn(response);
          console.log(this.review);
        })
        .catch((ex) => {
          console.warn("ERROR!!!!! : ", ex);
        });
    },
    reviewModify() {
      alert("리뷰 수정 버튼 실행");
    },
    reviewDelete() {
      alert("리뷰 삭제 버튼 실행");
    },
  },
};
</script>

<style scoped>
.reviews {
  background: left/contain content-box border-box no-repeat
    url("@/assets/toystory.png") #141414;
  margin-bottom: 1.5rem;
}

.reviews > h2 {
  padding-left: 4.5rem;
  font-weight: 600;
  font-size: 0.9rem;
  margin: 0;
}

.reviews > p {
  padding-left: 4.5rem;
  font-size: 0.9rem;
  color: #84868d;
}

.star-rating {
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
}

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
</style>
