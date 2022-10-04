<template>
  <div style="margin-top: 10px">
    <br />
    <!-- <div style="text-align: center">
      지금은 9월 26일 월요일 11시 55분 입니다.
    </div> -->
    <br />
    <div class="flex">
      <b-table-simple bordered fixed style="border-color: #ffffff; color: #ffffff; font-weight: 400">
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
                <div v-for="organization in schedule.organizationResponseList" style="white-space: nowrap">
                  <span class="time">{{ organization.broadcastingTime }}&nbsp;</span><span class="station">{{
                  organization.broadcastingStation }}&nbsp;</span>
                  <span class="episode" v-if="organization.broadcastingEpisode">{{ organization.broadcastingEpisode
                  }}&nbsp;</span>
                  <span class="box" v-if="organization.broadcastingRerun"><span class="box_design">재</span>&nbsp;</span>
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
  </div>

  <!-- <b-modal
    size="sm"
    hide-footer
    id="modal-1"
    centered
    no-stacking
    title="알림 설정"
    style="text-align: center"
  >
    <div style="margin-bottom: 5%">
      <img src="@/assets/alarm_md.png" /><span class="alarm-info"
        >15분 전에 알림을 드립니다.</span
      >
    </div>
    <b-button
      @click="registAlarm"
      style="background-color: blue"
      data-bs-dismiss="modal"
      aria-label="Close"
      >좋아요!</b-button
    >
  </b-modal> -->
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
  font-family: "Watcha Sans", Roboto, "Noto Sans KR", "Apple SD Gothic Neo",
    "Nanum Gothic", "Malgun Gothic", sans-serif;
  font-size: 0.7vw;
  font-weight: 600;
  vertical-align: middle;
  /* line-height: 18px; */
  /* padding: 1px 5px; */
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
  /* height: 600px; */
  /* flex-wrap: wrap;
  align-content: stretch; */
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
</style>
