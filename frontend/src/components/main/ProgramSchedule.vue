<template>
  <div style="margin-top: 10px;">
    <br />
    <div style="text-align:center">
      지금은 9월 26일 월요일 11시 55분 입니다.
    </div>
    <br />
    <div class="flex">
      <b-table-simple bordered fixed style="border-color:#ffffff; color:#ffffff; font-weight: 400;">
        <b-thead style="background-color: #5a5a5a;">
          <b-tr>
            <b-th>9/26(월)</b-th>
            <b-th>9/27(화)</b-th>
            <b-th>9/28(수)</b-th>
            <b-th>9/29(목)</b-th>
            <b-th>9/30(금)</b-th>
            <b-th>10/1(토)</b-th>
            <b-th>10/2(일)</b-th>
          </b-tr>
        </b-thead>
        <tbody>
          <b-tr style="padding: 5;">
            <b-td>
              <div style="white-space:nowrap;"><span class="time">10:59&nbsp;</span><span
                  class="station">드라마큐브&nbsp;</span><span class="episode">137회&nbsp;</span><span class="box"><span
                    class="box_design">재</span>&nbsp;</span><img src="@/assets/alarm.png" v-b-modal.modal-1
                  style="cursor:pointer;" /></div>
              <div style="white-space:nowrap;"><span class="time">12:47&nbsp;</span><span class="station">ENA
                  DRAMA&nbsp;</span><span class="episode">490회&nbsp;</span><span class="box"><span
                    class="box_design">재</span>&nbsp;</span><img src="@/assets/alarm.png" v-b-modal.modal-1
                  style="cursor:pointer;" /></div>
            </b-td>
            <b-td>
              <div style="white-space:nowrap;"><span class="time">10:59&nbsp;</span><span
                  class="station">드라마큐브&nbsp;</span><span class="episode">137회&nbsp;</span><span class="box"><span
                    class="box_design">재</span>&nbsp;</span><img src="@/assets/alarm.png" v-b-modal.modal-1
                  style="cursor:pointer;" /></div>
              <div style="white-space:nowrap;"><span class="time">12:47&nbsp;</span><span class="station">ENA
                  DRAMA&nbsp;</span><span class="episode">490회&nbsp;</span><span class="box"><span
                    class="box_design">재</span>&nbsp;</span><img src="@/assets/alarm.png" v-b-modal.modal-1
                  style="cursor:pointer;" /></div>
            </b-td>
            <b-td>
              <div style="white-space:nowrap;"><span class="time">10:59&nbsp;</span><span
                  class="station">드라마큐브&nbsp;</span><span class="episode">137회&nbsp;</span><span class="box"><span
                    class="box_design">재</span>&nbsp;</span><img src="@/assets/alarm.png" v-b-modal.modal-1
                  style="cursor:pointer;" /></div>
              <div style="white-space:nowrap;"><span class="time">12:47&nbsp;</span><span class="station">ENA
                  DRAMA&nbsp;</span><span class="episode">490회&nbsp;</span><span class="box"><span
                    class="box_design">재</span>&nbsp;</span><img src="@/assets/alarm.png" v-b-modal.modal-1
                  style="cursor:pointer;" /></div>
            </b-td>
            <b-td>
              <div style="white-space:nowrap;"><span class="time">10:59&nbsp;</span><span
                  class="station">드라마큐브&nbsp;</span><span class="episode">137회&nbsp;</span><span class="box"><span
                    class="box_design">재</span>&nbsp;</span><img src="@/assets/alarm.png" v-b-modal.modal-1
                  style="cursor:pointer;" /></div>
              <div style="white-space:nowrap;"><span class="time">12:47&nbsp;</span><span class="station">ENA
                  DRAMA&nbsp;</span><span class="episode">490회&nbsp;</span><span class="box"><span
                    class="box_design">재</span>&nbsp;</span><img src="@/assets/alarm.png" v-b-modal.modal-1
                  style="cursor:pointer;" /></div>
            </b-td>
            <b-td>
              <div style="white-space:nowrap;"><span class="time">10:59&nbsp;</span><span
                  class="station">드라마큐브&nbsp;</span><span class="episode">137회&nbsp;</span><span class="box"><span
                    class="box_design">재</span>&nbsp;</span><img src="@/assets/alarm.png" v-b-modal.modal-1
                  style="cursor:pointer;" /></div>
              <div style="white-space:nowrap;"><span class="time">12:47&nbsp;</span><span class="station">ENA
                  DRAMA&nbsp;</span><span class="episode">490회&nbsp;</span><span class="box"><span
                    class="box_design">재</span>&nbsp;</span><img src="@/assets/alarm.png" v-b-modal.modal-1
                  style="cursor:pointer;" /></div>
            </b-td>
            <b-td>
              <div style="white-space:nowrap;"><span class="time">10:59&nbsp;</span><span
                  class="station">드라마큐브&nbsp;</span><span class="episode">137회&nbsp;</span><span class="box"><span
                    class="box_design">재</span>&nbsp;</span><img src="@/assets/alarm.png" v-b-modal.modal-1
                  style="cursor:pointer;" /></div>
              <div style="white-space:nowrap;"><span class="time">12:47&nbsp;</span><span class="station">ENA
                  DRAMA&nbsp;</span><span class="episode">490회&nbsp;</span><span class="box"><span
                    class="box_design">재</span>&nbsp;</span><img src="@/assets/alarm.png" v-b-modal.modal-1
                  style="cursor:pointer;" /></div>
            </b-td>
            <b-td>
              <div style="white-space:nowrap;"><span class="time">10:59&nbsp;</span><span
                  class="station">드라마큐브&nbsp;</span><span class="episode">137회&nbsp;</span><span class="box"><span
                    class="box_design">재</span>&nbsp;</span><img src="@/assets/alarm.png" v-b-modal.modal-1
                  style="cursor:pointer;" /></div>
              <div style="white-space:nowrap;"><span class="time">12:47&nbsp;</span><span class="station">ENA
                  DRAMA&nbsp;</span><span class="episode">490회&nbsp;</span><span class="box"><span
                    class="box_design">재</span>&nbsp;</span><img src="@/assets/alarm.png" v-b-modal.modal-1
                  style="cursor:pointer;" /></div>
            </b-td>
          </b-tr>
        </tbody>
      </b-table-simple>
    </div>
  </div>

  <b-modal size="sm" hide-footer id="modal-1" centered no-stacking title="알림 설정" style="text-align: center;">
    <div style="margin-bottom:5%"><img src="@/assets/alarm_md.png" /><span class="alarm-info">15분 전에 알림을 드립니다.</span></div>
    <b-button @click="Submit" style="background-color:blue" data-bs-dismiss="modal" aria-label="Close">좋아요!</b-button>
  </b-modal>
</template>

<script>

</script>

<style scoped>
.box_design {
  display: inline-block;
  background: rgb(46, 47, 49);
  color: rgb(255, 255, 255);
  font-family: "Watcha Sans", Roboto, "Noto Sans KR", "Apple SD Gothic Neo", "Nanum Gothic", "Malgun Gothic", sans-serif;
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
  overflow-x: scroll;
}

.station {
  color: #B1C6F3;
  font-size: 1vw;
}

.episode {
  font-size: 0.9vw;
}

th{
  font-weight:400;
  text-align: center;
}
</style>