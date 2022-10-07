<template>
  <div style="margin-top: 10px">
    <br />
    <div>
      <div class="flex">
        <template v-if="castInfoList != null && castInfoList.length > 0">
          <b-card
            v-for="cast in castInfoList"
            style="
              width: 10%;
              height: 13vw;
              background-color: #1f1f1f;
              text-align: center;
              margin-bottom: 1%;
              margin-right: 1%;
              border-radius: 1vw;
            "
          >
            <img :src="cast.castImageUrl" />
            <div class="actorName">{{ cast.actorName }}</div>
            <div class="playerName">{{ cast.playName }}</div>
          </b-card>
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
            출연진 정보가 없습니다.
          </h2></template
        >
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "Star",
  computed: {
    ...mapState(["castInfoList"]),
  },
  data() {
    return {};
  },
  methods: {},
};
</script>

<style scoped>
.actorName {
  color: #ffffff;
  font-size: 1vw;
}

.playerName {
  color: #a1a1a1;
  font-size: 0.9vw;
}

.flex {
  display: flex;
  flex-wrap: wrap;
  align-content: stretch;
}

img {
  width: 6rem;
  margin-bottom: 7%;
  border-radius: 1vw;
}
</style>
