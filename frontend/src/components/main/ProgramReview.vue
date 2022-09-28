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
        <article class="reviews">
          <h2>
            걷지말고뛰어라&nbsp;&nbsp;
            <span class="wrap-star">
              <span class="star-rating">
                <span style="width: 100%"></span>
              </span>
            </span>
          </h2>
          <p>너무 재밌어요!</p>
        </article>
        <article class="reviews">
          <h2>
            뛰지말고누워라&nbsp;&nbsp;<span class="wrap-star">
              <span class="star-rating">
                <span style="width: 40%"></span>
              </span>
            </span>
          </h2>
          <p>노잼..</p>
        </article>
        <article class="reviews">
          <h2>
            눕지마라&nbsp;&nbsp;
            <span class="wrap-star">
              <span class="star-rating">
                <span style="width: 80%"></span>
              </span>
            </span>
          </h2>
          <p>재밌네요</p>
        </article>
      </article>
    </section>
    <br />
  </div>
</template>
    
<script>
import { reactive } from "@vue/reactivity";
import axios from "axios";

export default {
  name: "ProgramReview",
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

    axios.get("/api/recommend/new").then((res) => {
      // 데이터 가져오는 거.
      console.log(res);
      console.log(res.data);
      console.log(res.data[0]);
      console.log(res.data[0].title);
      console.log(res.data[0].backdropPath);
      console.log(res.data[0].posterImg);
      state.data[0] = res.data[0].title;
      state.data[1] = res.data[0].backdropPath;
      state.data[2] = res.data[0].posterImg;
    });

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
    reviewRegist() { // post 사용 가능한 코드입니다. review 등록버튼 클릭 이벤트.
      alert("post 테스트");
      axios
        .post("/api/review/269", { // /review/프로그램아이디
          content: this.review,
        })
        .then((response) => {
          console.warn(response);
          console.log(content);
          console.log(this.review);
        })
        .catch((ex) => {
          console.warn("ERROR!!!!! : ", ex);
        });
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
</style>