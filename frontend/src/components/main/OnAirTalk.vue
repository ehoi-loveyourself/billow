<template>
  <div style="margin-top: 10px; width:100%; align-content: center;">
    <br />
    <b-card style="background-color:#1f1f1f; border-radius: 1vw; margin-left: 1vw; margin-right: 1vw;">
      <!-- list size -->
      <div style="margin-top:2%">
        <template v-if="onairTalk != null && onairTalk.length > 0">
          <span style="padding-left:3%">{{ onairTalk.length }}</span>
        </template>
        <template v-else> <span style="padding-left:3%">0</span> </template>
        개의 TALK
      </div>
      <b-row style="margin-top: 0.7%">
        <b-col cols="11" style="padding-right: 0;">
          <input v-model="message" class="form-control" type="text" name="search" placeholder="주제와 무관한 톡은 삭제될 수 있습니다."
            required @keyup.enter="onSubmit()" style="width:97%; float:right" />
        </b-col>
        <b-col cols="1" style="text-align:left">
          <b-button size="md" type="submit" @click="onSubmit()">
            <span>전송</span>
          </b-button>
        </b-col>
      </b-row>

      <br />

      <article class="onairtalk_set">
        <template v-if="onairTalk != null && onairTalk.length > 0">
          <div v-for="talk in onairTalk" track-by="id">
            <div v-if="talk.userNickName == userInfo.nickName">
              <b-row>
                <b-col cols="5"></b-col>
                <b-col cols="6" style="text-align: right; padding-right: 0;">
                  <span class="time"> {{ talk.regDateTime }} </span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="username">{{
                  talk.userNickName
                  }}</span>
                  <br />
                  <p class="myTalk" style="float: right">
                    {{ talk.content }}
                  </p>
                </b-col>
                <b-col cols="1" style="padding-left: 1%">
                  <b-avatar class="avatar" variant="info" :src="talk.userProfile" size="3rem" style="float: left">
                  </b-avatar>
                </b-col>
              </b-row>
            </div>
            <div v-else>
              <b-row>
                <b-col cols="1" style="text-align: right; padding-right: 0.1%">
                  <b-avatar class="avatar" variant="info" :src="talk.userProfile" size="3rem"></b-avatar>
                </b-col>
                <b-col cols="6">
                  <span class="username">{{ talk.userNickName }}</span>&nbsp;&nbsp;&nbsp;&nbsp;
                  <span class="time">{{ talk.regDateTime }}</span>
                  <p class="theirTalk">
                    {{ talk.content }}
                  </p>
                </b-col>
                <b-col cols="5"></b-col>
              </b-row>
            </div>
          </div>
        </template>
        <template v-else>
          <h2 style="
            color: white;
            margin-left: 70px;
            font-size: 1.1vw;
            font-weight: 300;
          ">
            온에어톡 내용이 없습니다.
          </h2>
        </template>
      </article>
    </b-card>
    <br />
  </div>
</template>

<script>
import { mapActions, mapState, mapMutations } from "vuex";
import { getStompClient } from "@/store/socket";
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
export default {
  name: "Star",
  computed: {
    ...mapState(["onairTalk", "userInfo", "programId"]),
  },
  data() {
    return {
      stompClient: null,
      message: "",
      onairTalkCount: 0,
    };
  },
  created() {
    this.getStomp();
    this.connect();
  },
  methods: {
    ...mapActions(["sendMessage"]),
    ...mapMutations(["ADD_CHATTING"]),
    onSubmit() {
      if (this.message == "") {
        alert("내용을 입력하세요.");
        return;
      }
      if (this.stompClient.connected) {
        this.stompClient.send(
          "/pub/message",
          { "Auth-access": localStorage.getItem("authToken") },
          JSON.stringify({
            content: this.message,
            programId: this.programId,
          })
        );
      }
      // else {
      //   this.sendMessage(this.message);
      // }
      this.message = "";
    },
    getStomp() {
      this.stompClient = getStompClient();
    },
    connect() {
      this.stompClient.connect({}, (frame) => {
        this.stompClient.subscribe(
          "/sub/chat/program/" + this.programId,
          (data) => {
            const newMessage = JSON.parse(data.body);
            this.ADD_CHATTING(newMessage);
          }
        );
      });
    },
  },
};
</script>

<style scoped>
.onairtalk_set {
  display: flex;
  flex-direction: column-reverse;
  /* max-height: 80vh; */
  overflow-y: auto;
  overflow-x: hidden;
  width: 94.5%;
}

.username {
  font-weight: 600;
  font-size: 0.9rem;
  margin: 0;
}

.time {
  font-size: 0.8rem;
  color: #a1a1a1;
}

p.myTalk {
  font-size: 0.9rem;
  color: #141414;
  background-color: #eee3cb;
  border-radius: 20px 0px 20px 20px;
  max-width: 300px;
  width: fit-content;
  padding: 0.4rem 0.7rem 0.4rem 0.7rem;
}

p.theirTalk {
  font-size: 0.9rem;
  color: #141414;
  background-color: #e6edeb;
  border-radius: 0px 20px 20px 20px;
  max-width: 300px;
  width: fit-content;
  padding: 0.4rem 0.7rem 0.4rem 0.7rem;
}

.onairtalk_set::-webkit-scrollbar {
  width: 0.3vw;
  /* padding-left: 1%; */
}

.onairtalk_set::-webkit-scrollbar-track {
  background-color: transparent;
}

.onairtalk_set::-webkit-scrollbar-thumb {
  border-radius: 10px;
  background-color: #a1a1a1;
}

.onairtalk_set::-webkit-scrollbar-button {
  width: 0;
  height: 0;
}
</style>
