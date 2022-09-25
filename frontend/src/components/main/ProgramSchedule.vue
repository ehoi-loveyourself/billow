<template>
<div style="padding-left: 70px; margin-top: 10px;">
    <br/>
    <div style="text-align:center">
        지금은 9월 26일 월요일 11시 55분 입니다.
    </div>
<br/>
    <b-table-simple fixed style="border-color:#ffffff; color:#ffffff;  font-size: 1.1vw;">
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
12:47 490회<span class="box"><span class="box_design">재</span></span><img src="@/assets/alarm.png"/><br/>
14:31 470회<br/>
17:30 661회</b-td>
                <b-td>10:59 137회<br/>
12:47 490회<br/>
14:31 470회<br/>
17:30 661회</b-td>
                <b-td>10:59 137회<br/>
12:47 490회<br/>
14:31 470회<br/>
17:30 661회</b-td>
                <b-td>10:59 137회<br/>
12:47 490회<br/>
14:31 470회<br/>
17:30 661회</b-td>
                <b-td>10:59 137회<br/>
12:47 490회<br/>
14:31 470회<br/>
17:30 661회</b-td>
                <b-td>10:59 137회<br/>
12:47 490회<br/>
14:31 470회<br/>
17:30 661회</b-td>
                <b-td>10:59 137회<br/>
12:47 490회<br/>
14:31 470회<br/>
17:30 661회</b-td>
              </b-tr>
            </tbody>
        </b-table-simple>
    </div>
</template>

<script>

</script>

<style scoped>
.box_design{
    display: inline-block;
    background: rgb(46, 47, 49);
    color: rgb(255, 255, 255);
    font-family: "Watcha Sans", Roboto, "Noto Sans KR", "Apple SD Gothic Neo", "Nanum Gothic", "Malgun Gothic", sans-serif;
    font-size: 1vw;
    font-weight: 600;
    vertical-align: top;
    /* line-height: 18px; */
    /* padding: 1px 5px; */
    border-radius: 3px;
    padding-left: 2%;
    padding-right: 2%;
}
</style>