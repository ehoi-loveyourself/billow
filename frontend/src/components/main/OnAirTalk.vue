<template>
  <div style="margin-top: 10px">
    <br />
    <div>3개의 TALK</div>
    <b-row style="margin-top: 20px">
      <b-col cols="12">
        <!-- <b-form-input
          v-model="message"
          placeholder="주제와 무관한 톡은 삭제될 수 있습니다."
          required
          style="border-color: #a48282"
        >
        </b-form-input> -->
        <input
          v-model="message"
          class="form-control"
          type="text"
          name="search"
          placeholder="주제와 무관한 톡은 삭제될 수 있습니다."
          required
          @keyup.enter="onSubmit()"
        />
      </b-col>
      <!-- <b-col cols="1">
        <b-button size="md" type="submit" onclick="onSubmit">
          <span>등록</span>
        </b-button>
      </b-col> -->
    </b-row>
    <br />
    <section>
      <article class="review_set">
        <article class="reviews" v-for="talk in onairTalk">
          <span class="username">
            {{ talk.userNickName }}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span
          ><span class="time">{{ talk.regDateTime }}</span>
          <p>{{ talk.content }}</p>
        </article>
      </article>
    </section>
    <br />
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
export default {
  // name: "App",
  name: "Star",
  computed: {
    ...mapState(["onairTalk"]),
  },
  data() {
    return {
      message: "",
    };
  },
  methods: {
    ...mapActions(["sendMessage"]),
    onSubmit() {
      if (this.message == "") {
        alert("내용을 입력하세요.");
        return;
      }
      this.sendMessage(this.message);
      this.message = "";
    },
  },
};
</script>

<style scoped>
.reviews {
  background: left/contain content-box border-box no-repeat
    url("@/assets/toystory.png") #141414;
  margin-bottom: 1.5rem;
  background-size: auto;
}
.reviews > .username {
  padding-left: 3.5rem;
  font-weight: 600;
  font-size: 0.9rem;
  margin: 0;
}
.reviews > .time {
  font-size: 0.8rem;
  color: #a1a1a1;
}
.reviews > p {
  margin-left: 3.5rem;
  font-size: 0.9rem;
  color: #141414;
  background-color: #e6edeb;
  border-radius: 0px 20px 20px 20px;
  max-width: 300px;
  width: fit-content;
  padding: 0.4rem 0.7rem 0.4rem 0.7rem;
}
</style>
