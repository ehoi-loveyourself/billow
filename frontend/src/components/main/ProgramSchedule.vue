<template>
  <div style="margin-top: 10px">
    <br />
    <br />
    <div class="flex">
      <div v-if="programSchedule != null && programSchedule.length > 0">
        <b-table-simple
          bordered
          fixed
          style="border-color: #ffffff; color: #ffffff; font-weight: 400"
        >
          <b-thead style="background-color: #5a5a5a">
            <b-tr>
              <b-th v-for="schedule in programSchedule">
                {{ schedule.day }}
              </b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <b-tr style="padding: 5">
              <b-td v-for="schedule in programSchedule">
                <div v-if="schedule.organizationResponseList != null">
                  <div
                    v-for="organization in schedule.organizationResponseList"
                    style="white-space: nowrap"
                  >
                    <span class="time"
                      >{{ organization.broadcastingTime }}&nbsp;</span
                    ><span class="station"
                      >{{ organization.broadcastingStation }}&nbsp;</span
                    >
                    <span
                      class="episode"
                      v-if="organization.broadcastingEpisode"
                      >{{ organization.broadcastingEpisode }}&nbsp;</span
                    >
                    <span class="box" v-if="organization.broadcastingRerun"
                      ><span class="box_design">재</span>&nbsp;</span
                    >
                    <span @click="alarm(organization.programOrganizationId)">
                      <img src="@/assets/alarm.png" style="cursor: pointer" />
                    </span>
                  </div>
                </div>
                <div v-else>편성정보 없음</div>
              </b-td>
            </b-tr>
          </tbody>
        </b-table-simple>
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
          편성 정보가 없습니다.
        </h2>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
export default {
  name: "Star",
  computed: {
    ...mapState(["programSchedule"]),
  },
  methods: {
    ...mapActions(["registAlarm"]),
    alarm(id) {
      if (confirm("방영 15분전 알람을 등록합니다.") == true) {
        this.registAlarm(id);
      } else {
        return;
      }
    },
  },
};
</script>

<style scoped>
.box_design {
  display: inline-block;
  background: rgb(46, 47, 49);
  color: rgb(255, 255, 255);
  font-size: 0.7vw;
  font-weight: 600;
  vertical-align: middle;
  border-radius: 3px;
  padding-left: 1%;
  padding-right: 1%;
}

.box {
  padding-top: 0;
  padding-bottom: 0;
  margin-left: 0;
  margin-right: 0;
}

.time {
  color: #a1a1a1;
  font-size: 1.1vw;
}

.flex {
  display: flex;
  overflow-x: auto;
}

.station {
  color: #b1c6f3;
  font-size: 1vw;
}

.episode {
  font-size: 0.9vw;
}

th {
  font-weight: 400;
  text-align: center;
}

.flex::-webkit-scrollbar {
  height: 10px;
}

.flex::-webkit-scrollbar-track {
  background-color: transparent;
}

.flex::-webkit-scrollbar-thumb {
  border-radius: 10px;
  background-color: #E4DADA;
}

.flex::-webkit-scrollbar-button {
  width: 0;
  height: 0;
}
</style>
