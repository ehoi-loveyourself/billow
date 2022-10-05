<template>
  <b-row>
    <b-col cols="8">
      <header-bar />
    </b-col>
    <b-col cols="4">
      <SecondBar />
    </b-col>
  </b-row>
  <br />
  <div
    style="
      padding-left: 70px;
      padding-right: 70px;
      color: white;
      margin-top: 10px;
    "
  >
    <b-tabs content-class="mt-3" id="tabs" style="align-items: center">
      <b-tab title="회원 정보 수정" active>
        <UserInfoConfirm />
      </b-tab>
      <b-tab title="내가 남긴 평점 조회">
        <UserRatingConfirm />
      </b-tab>
      <b-tab title="방영 알림 조회">
        <UserAlarmConfirm />
      </b-tab>
    </b-tabs>
  </div>
</template>

<script>
import HeaderBar from "@/components/layout/HeaderNavBar.vue";
import SecondBar from "@/components/layout/SecondNavBar.vue";
import UserInfoConfirm from "@/components/user/UserInfoConfirm.vue";
import UserRatingConfirm from "@/components/user/UserRatingConfirm.vue";
import UserAlarmConfirm from "./UserAlarmConfirm.vue";
export default {
  components: {
    HeaderBar,
    SecondBar,
    UserInfoConfirm,
    UserRatingConfirm,
    UserAlarmConfirm,
  },
};
</script>

<style>
#tabs .nav-link {
  color: #a1a1a1;
  background: none;
  border: none;
}

#tabs .nav-tabs .nav-link.active,
.nav-tabs .nav-item.show .nav-link {
  color: #ffffff;
}
</style>
