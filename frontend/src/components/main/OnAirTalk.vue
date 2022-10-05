<template>
  <div style="margin-top: 10px">
    <br />
    <div style="padding-left: 4%; padding-right: 4%">
      <!-- list size -->
      <div>
        <template v-if="onairTalk != null && onairTalk.length > 0"
          >&nbsp;{{ onairTalk.length }}
        </template>
        <template v-else> &nbsp;0 </template>
        개의 TALK
      </div>
      <b-row style="margin-top: 20px;">
        <b-col cols="11" style="padding-right:0">
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
        <b-col cols="1">
        <b-button size="md" type="submit" @click="onSubmit()">
          <span>등록</span>
        </b-button>
      </b-col>
      </b-row>
    </div>
    <br />
    <!-- <section>
      <article class="review_set">
        <article class="reviews" v-for="talk in onairTalk" track-by="id">
          <span class="username">
            {{ talk.userNickName }}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span
          ><span class="time">{{ talk.regDateTime }}</span>
          <p>{{ talk.content }}</p>
        </article>
      </article>
    </section> -->
    <article class="review_set">
      <template v-if="onairTalk != null && onairTalk.length > 0">
        <b-row class="reviews" v-for="talk in onairTalk" track-by="id">
          <b-col cols="1" style="text-align: right; padding-right: 0.1%">
            <b-avatar
              class="avatar"
              variant="info"
              :src="talk.userProfile"
              size="3rem"
            ></b-avatar>
          </b-col>
          <b-col cols="11">
            <span class="username">
              {{
                talk.userNickName
              }}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span
            ><span class="time">{{ talk.regDateTime }}</span>
            <p>{{ talk.content }}</p>
          </b-col>
        </b-row>
      </template>
      <template v-else>
        <h2
          style="
            color: white;
            margin-left: 70px;
            font-size: 1.1vw;
            font-weight: 300;
          "
        >
          온에어톡 내용이 없습니다.
        </h2></template
      >
    </article>
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
      onairTalkCount: 0,
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
.review_set {
  display: flex;
  flex-direction: column-reverse;
}
/* .reviews {
  background: left/contain content-box border-box no-repeat
    url("@/assets/toystory.png") #141414;

  background-size: auto;
} */
.username {
  font-weight: 600;
  font-size: 0.9rem;
  margin: 0;
}
.time {
  font-size: 0.8rem;
  color: #a1a1a1;
}
p {
  font-size: 0.9rem;
  color: #141414;
  background-color: #e6edeb;
  border-radius: 0px 20px 20px 20px;
  max-width: 300px;
  width: fit-content;
  padding: 0.4rem 0.7rem 0.4rem 0.7rem;
}
</style>
