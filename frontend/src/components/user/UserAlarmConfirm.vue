<template>
  <br />
  <div class="flex" v-if="alarmList != null && alarmList.length > 0">
    <div id="Img" v-for="alarm in alarmList">
      <div class="box-wrap">
        <div class="box">
          <img
            class="poster"
            :src="alarm.posterImg"
            alt="Image"
            @click="moveProgramDetail(alarm.id)"
          />
          <div class="button">
            <button
              @click="alarmDelete(alarm.broadcastingAlarmId)"
              style="border: none; background: none"
            >
              <img class="trash" src="@/assets/trash.png" style="width: 40%" />
            </button>
          </div>
          <div class="info">
            <h3>{{ alarm.title }}</h3>
          </div>
        </div>
      </div>
      <span>
        <!-- <img src="@/assets/alarm.png" style="cursor: pointer" />&nbsp; -->
        <div>
          <span class="station">{{ alarm.alarmStation }}</span
          >&nbsp;
          <span class="episode" v-if="alarm.alarmEpisode"
            >{{ alarm.alarmEpisode }}&nbsp;&nbsp;</span
          >
          <!-- <a href="#" @click="alarmDelete(alarm.broadcastingAlarmId)" class="button"><img
              src="@/assets/trash.png" style="width:8%" /></a> -->
        </div>
        <div style="margin-bottom: 10%">
          <span class="day">{{ alarm.alarmDay }} </span>&nbsp;&nbsp;<span
            class="time"
          >
            {{ alarm.alarmTime }}</span
          >&nbsp;
        </div>
      </span>
    </div>

    <br /><br /><br /><br />
  </div>
  <div v-else>
    <h2
      style="
        color: white;
        margin-left: 70px;
        font-size: 1.1vw;
        font-weight: 300;
      "
    >
      예약된 방영 알림이 없습니다.
    </h2>
  </div>
</template>

<script>
import { reactive } from "@vue/reactivity";
import axios from "axios";
import { mapActions, mapState, mapMutations } from "vuex";

export default {
  name: "Star",
  data() {
    return {
      score: 0,
    };
  },
  computed: {
    ...mapState(["alarmList"]),
  },
  created() {
    this.getAlarm();
  },
  methods: {
    ...mapActions(["getAlarm", "deleteAlarm", "getProgramDetail"]),
    ...mapMutations(["SET_PROGRAM_ID"]),
    check(index) {
      this.score = index + 1;
    },
    alarmDelete(broadcastingAlarmId) {
      if (
        confirm("알림을 삭제하시겠습니까?") == true ||
        broadcastingAlarmId == null
      ) {
        this.deleteAlarm(broadcastingAlarmId);
      } else {
        return;
      }
    },
    moveProgramDetail(programId) {
      this.SET_PROGRAM_ID(programId);
      window.location.replace(`/detail`);
    },
  },
};
</script>

<style scoped>
.startButton {
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
  width: 12vw;
  height: 18vw;
  padding-right: 0.2%;
  padding-bottom: 0.2%;
  margin-bottom: 3%;
}

#Img {
  text-align: center;
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
/* .saveButton {
  background-color: #ffffff;
  padding: 3px 30px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
} */

.btnRed.btnBorder {
  box-shadow: 0px 0px 0px 0px #823621;
}

.btnRed.btnBorder:hover {
  box-shadow: 0px 0px 0px 5px #823621;
}

.btnRed {
  background: #ae2b00;
}

/* a.button {
  padding: 1%;
  font-weight: 600;
  text-align: center;
  color: #fff;
  border-radius: 5px;
  text-decoration: none;
  transition: all 0.1s;
  padding-top: 0;
} */
.day {
  color: #a0a0a0;
  font-size: 1vw;
}

.time {
  color: #a0a0a0;
  font-size: 1.1vw;
}

.station {
  color: #b1c6f3;
  font-size: 1vw;
}

.episode {
  font-size: 0.9vw;
}

.poster:hover {
  filter: brightness(0.5);
}

.box .info .detailbox {
  font-size: 12px;
}

.box .info .detailbox_design {
  display: inline-block;
  background: rgb(46, 47, 49);
  color: rgb(255, 255, 255);
  font-size: 12px;
  font-weight: 400;
  vertical-align: top;
  line-height: 18px;
  padding: 1px 5px;
  border: none;
  margin: 0px 10px 0px 0px;
  margin-bottom: 5px;
}

.box-wrap {
  /* width: 100vw; height: 100vh; */
  display: flex;
  align-items: center;
  justify-content: center;
  flex-wrap: wrap;
  align-content: stretch;
}

.box {
  position: relative;
  /* background: #000; */
  width: 100%;
  border: none;
  cursor: pointer;
  overflow: hidden;
  /* box-shadow: 1px 1px 3px rgba(0,0,0,0.4); */
}

.box .poster {
  transition: all 0.3s ease-in-out;
}

.box .info {
  position: absolute;
  left: 5px;
  bottom: 10px;
  color: #fff;
  width: 100%;
  /* padding: 15px; */
  box-sizing: border-box;
  opacity: 0;
  transition: all 0.3s ease-in-out;
}

.box .info h3 {
  font-size: 12px;
  font-weight: 600;
  line-height: 20px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  padding-bottom: 3px;
  text-align: left;
}

.box:hover .info {
  opacity: 1;
}

.box:hover img {
  opacity: 1;
}

.box:hover:before {
  width: 60px;
}

.box:hover:after {
  height: 60px;
}

@font-face {
  font-family: "GoyangIlsan";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/GoyangIlsan.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

body {
  background-color: #1a237e;
  font-family: "GoyangIlsan";
}

.button {
  display: none;
}

.poster:hover + .button,
.button:hover {
  display: inline-block;
  position: absolute;
  top: 1vw;
  right: 0;
}
</style>
