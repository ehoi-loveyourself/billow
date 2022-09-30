<template>
    <br />
    <div>

        <br />
        <div class="flex">
            <div id="Img" v-for="random in state.randomProgram">
                <img class="poster" :src="random.posterImg" alt="Image" />
                <br />
                <div style="text-align:center; margin-top: 5%; margin-bottom: 8%;">
                    <span>
                        <span v-for="index in 5" :key="index" @click="check(index)">
                            <span v-if="index < score"><img src="@/assets/blue_star_small.png"
                                    style="width: 8%" /></span>
                            <span v-if="index >= score"><img src="@/assets/grey_star_small.png"
                                    style="width: 8%" /></span>
                        </span>
                    </span>

                    &nbsp;
                    <a href="#" @click="ratingDelete()" class="button btnBorder btnRed"><span
                            style="font-size:0.8vw">삭제</span></a>
                </div>
            </div>

            <!-- <div id="Img">
          <img class="poster" src="@/assets/sign.png" alt="Image" />
          <br />
          <span class="explain">
            <span v-for="index in 5" :key="index" @click="check(index)">
              <span v-if="index < score"><img src="@/assets/blue_star_small.png" style="width:13%" /></span>
              <span v-if="index >= score"><img src="@/assets/grey_star_small.png" style="width:13%" /></span>
            </span>
          </span>
        </div> -->

            <!-- <div id="Img">
          <img class="poster" v-for="(d, idx) in state.newProgramPosterImg" :key="idx"
            :src="state.newProgramPosterImg[idx]" alt="Image" />
          <br />
          <span v-for="(d, idx) in state.newProgramId" :key="idx" class="explain">
            <span v-for="index[idx] in 5" :key="index" @click="check(index[idx])">
              <span v-if="index[idx] < score[idx]"><img src="@/assets/blue_star_small.png" style="width:13%" /></span>
              <span v-if="index >= score"><img src="@/assets/grey_star_small.png" style="width:13%" /></span>
            </span>
          </span>
        </div> -->
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
import { reactive } from "@vue/reactivity";
import axios from "axios";

export default {
    name: "Star",
    data() {
        return {
            score: 0,
        };
    },
    methods: {
        check(index) {
            this.score = index + 1;
        },
        ratingDelete() {
            alert("평점 삭제 버튼 실행");
        }
    },

    setup() {
        const state = reactive({
            randomProgram: [],
        });

        axios.get("/api/program/random").then((res) => {
            // 랜덤 프로그램 추천 데이터 GET
            console.log(res.data);
            state.randomProgram = res.data;
        });
        return { state };
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
  