<template>
<div style="padding-left: 70px; margin-top: 10px;">
    <br/>
    <div style="text-align:center">
        지금은 9월 26일 월요일 11시 55분 입니다.
    </div>
<br/>
    <b-table-simple bordered fixed style="border-color:#ffffff; color:#ffffff;  font-size: 1.1vw;">
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
                <b-td>10:59 137회<br/>
<span class="time">12:47 </span>490회&nbsp;<span class="box"><span class="box_design">재</span></span>&nbsp;<img src="@/assets/alarm.png" v-b-modal.modal-1 style="cursor:pointer;"/><br/>
14:31 470회<br/>
17:30 661회</b-td>
                <b-td>10:59 137회<br/>
                  <span class="time">12:47 </span>490회&nbsp;<span class="box"><span class="box_design">재</span></span>&nbsp;<img src="@/assets/alarm.png" v-b-modal.modal-1 style="cursor:pointer;"/><br/>
14:31 470회<br/>
17:30 661회</b-td>
                <b-td>10:59 137회<br/>
                  <span class="time">12:47 </span>490회&nbsp;<span class="box"><span class="box_design">재</span></span>&nbsp;<img src="@/assets/alarm.png" v-b-modal.modal-1 style="cursor:pointer;"/><br/>
14:31 470회<br/>
17:30 661회</b-td>
                <b-td>10:59 137회<br/>
                  <span class="time">12:47 </span>490회&nbsp;<span class="box"><span class="box_design">재</span></span>&nbsp;<img src="@/assets/alarm.png" v-b-modal.modal-1 style="cursor:pointer;"/><br/>
14:31 470회<br/>
17:30 661회</b-td>
                <b-td>10:59 137회<br/>
                  <span class="time">12:47 </span>490회&nbsp;<span class="box"><span class="box_design">재</span></span>&nbsp;<img src="@/assets/alarm.png" v-b-modal.modal-1 style="cursor:pointer;"/><br/>
14:31 470회<br/>
17:30 661회</b-td>
                <b-td>10:59 137회<br/>
                  <span class="time">12:47 </span>490회&nbsp;<span class="box"><span class="box_design">재</span></span>&nbsp;<img src="@/assets/alarm.png" v-b-modal.modal-1 style="cursor:pointer;"/><br/>
14:31 470회<br/>
17:30 661회</b-td>
                <b-td>10:59 137회<br/>
                  <span class="time">12:47 </span>490회&nbsp;<span class="box"><span class="box_design">재</span></span>&nbsp;<img src="@/assets/alarm.png" v-b-modal.modal-1 style="cursor:pointer;"/><br/>
14:31 470회<br/>
17:30 661회</b-td>
              </b-tr>
            </tbody>
        </b-table-simple>
    </div>

    <b-modal size="sm" hide-footer id="modal-1" centered no-stacking title="알림 설정" style="text-align: center;">
      <div class="alarm"><img src="@/assets/alarm_md.png"/><span class="alarm-info">15분 전에 알림을 드립니다.</span></div>
      <b-button @click="Submit" style="background-color:blue" data-bs-dismiss="modal" aria-label="Close">좋아요!</b-button>
    </b-modal>
</template>

<script>

</script>

<style scoped>
.box_design{
    display: inline-block;
    background: rgb(46, 47, 49);
    color: rgb(255, 255, 255);
    font-family: "Watcha Sans", Roboto, "Noto Sans KR", "Apple SD Gothic Neo", "Nanum Gothic", "Malgun Gothic", sans-serif;
    font-size: 0.9vw;
    font-weight: 600;
    vertical-align: middle;
    /* line-height: 18px; */
    /* padding: 1px 5px; */
    border-radius: 3px;
    padding-left: 2%;
    padding-right: 2%;

}
.box{
  padding-top:0;
  padding-bottom:0;
}
.time{
  color:#a1a1a1;
}
.alarm-info{
  font-size:1.1vw;
  font-weight:400;
}
.alarm{
  padding-bottom: 3%;
}
</style>