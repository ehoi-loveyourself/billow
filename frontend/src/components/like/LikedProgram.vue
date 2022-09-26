<template>
  <div style="padding-left: 70px; margin-top: 10px">
    <img src="@/assets/runningman.png" alt="Image" />
    <img src="@/assets/thatman.png" alt="Image" />
    <img src="@/assets/nangman.png" alt="Image" />
    <img src="@/assets/jugun.png" alt="Image" />
    <img src="@/assets/image_2.png" alt="Image" />
    <img src="@/assets/gs.png" alt="Image" />
    <img src="@/assets/laggi.png" alt="Image" />
    <img :src="state.data" alt="Image" />
    <img
      :src="state.data"
      alt="Image"
      v-for="(d, idx) in state.data"
      :key="idx"
    />
    <br />
  </div>
  <div class="memos">
    <button class="btn btn-primary" @click="add()">추가하기</button>
    <ul>
      <li v-for="(d, idx) in state.data" :key="idx">{{ d }}</li>
    </ul>
  </div>
</template>

<script>
import { reactive } from "@vue/reactivity";
import axios from "axios";
export default {
  name: "LikedProgram",
  setup() {
    const state = reactive({
      data: [],
    });

    const add = () => {
      // 지금 안됨.
      // state.data.push("추가한 내용");
      axios.post("/api/recommend/new").then((res) => {
        console.log(res.data);
      });
    };

    axios.get("/api/recommend/new").then((res) => {
      // 데이터 가져오는 거.
      console.log(res);
      console.log(res.data);
      console.log(res.data[0]);
      console.log(res.data[0].title);
      console.log(res.data[0].backdropPath);
      console.log(res.data[0].posterImg);
      state.data = res.data[0].posterImg;

      var step;

      for (step = 0; step < 20; step++) {
        // Runs 5 times, with values of step 0 through 4.
        console.log("Walking east one step");
      }
    });

    return { state, add };
  },
};
</script>

<style scoped>
img {
  padding-right: 0.5%;
  padding-bottom: 0.5%;
  margin-right: 0;
  width: 16.1%;
}
img:hover {
  filter: brightness(0.5);
}
</style>