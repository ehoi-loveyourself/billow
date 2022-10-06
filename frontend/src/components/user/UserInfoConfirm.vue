<template>
  <br />
  <br />
  <div v-if="userInfo != null">
    <section style="font-size: 0.96vw">
      <b-row style="margin-bottom: 2%">
        <b-col cols="1" style="text-align: right"> 프로필 </b-col>

        <b-col cols="11" style="padding-left: 1%">
          <input
            type="radio"
            name="choice"
            id="choose-1"
            value="1"
            v-model="userInfo.profileId"
          />
          <label for="choose-1">
            <b-avatar
              class="avatar"
              variant="info"
              src="https://j7b309.p.ssafy.io/api/profile/1"
              size="4rem"
            ></b-avatar>
          </label>

          <input
            type="radio"
            name="choice"
            id="choose-2"
            value="2"
            v-model="userInfo.profileId"
          />
          <label for="choose-2">
            <b-avatar
              class="avatar"
              variant="info"
              src="https://j7b309.p.ssafy.io/api/profile/2"
              size="4rem"
            ></b-avatar>
          </label>

          <input
            type="radio"
            name="choice"
            id="choose-3"
            value="3"
            v-model="userInfo.profileId"
          />
          <label for="choose-3">
            <b-avatar
              class="avatar"
              variant="info"
              src="https://j7b309.p.ssafy.io/api/profile/3"
              size="4rem"
            ></b-avatar>
          </label>

          <input
            type="radio"
            name="choice"
            id="choose-4"
            value="4"
            v-model="userInfo.profileId"
          />
          <label for="choose-4">
            <b-avatar
              class="avatar"
              variant="info"
              src="https://j7b309.p.ssafy.io/api/profile/4"
              size="4rem"
            ></b-avatar>
          </label>

          <input
            type="radio"
            name="choice"
            id="choose-5"
            value="5"
            v-model="userInfo.profileId"
          />
          <label for="choose-5">
            <b-avatar
              class="avatar"
              variant="info"
              src="https://j7b309.p.ssafy.io/api/profile/5"
              size="4rem"
            ></b-avatar>
          </label>

          <input
            type="radio"
            name="choice"
            id="choose-6"
            value="6"
            v-model="userInfo.profileId"
          />
          <label for="choose-6">
            <b-avatar
              class="avatar"
              variant="info"
              src="https://j7b309.p.ssafy.io/api/profile/6"
              size="4rem"
            ></b-avatar>
          </label>

          <input
            type="radio"
            name="choice"
            id="choose-7"
            value="7"
            v-model="userInfo.profileId"
          />
          <label for="choose-7">
            <b-avatar
              class="avatar"
              variant="info"
              src="https://j7b309.p.ssafy.io/api/profile/7"
              size="4rem"
            ></b-avatar>
          </label>

          <input
            type="radio"
            name="choice"
            id="choose-8"
            value="8"
            v-model="userInfo.profileId"
          />
          <label for="choose-8">
            <b-avatar
              class="avatar"
              variant="info"
              src="https://j7b309.p.ssafy.io/api/profile/8"
              size="4rem"
            ></b-avatar>
          </label>

          <input
            type="radio"
            name="choice"
            id="choose-9"
            value="9"
            v-model="userInfo.profileId"
          />
          <label for="choose-9">
            <b-avatar
              class="avatar"
              variant="info"
              src="https://j7b309.p.ssafy.io/api/profile/9"
              size="4rem"
            ></b-avatar>
          </label>

          <input
            type="radio"
            name="choice"
            id="choose-10"
            value="10"
            v-model="userInfo.profileId"
          />
          <label for="choose-10">
            <b-avatar
              class="avatar"
              variant="info"
              src="https://j7b309.p.ssafy.io/api/profile/10"
              size="4rem"
            ></b-avatar>
          </label>
          <br />
          <input
            type="radio"
            name="choice"
            id="choose-11"
            value="11"
            v-model="userInfo.profileId"
          />
          <label for="choose-11">
            <b-avatar
              class="avatar"
              variant="info"
              src="https://j7b309.p.ssafy.io/api/profile/11"
              size="4rem"
            ></b-avatar>
          </label>

          <input
            type="radio"
            name="choice"
            id="choose-12"
            value="12"
            v-model="userInfo.profileId"
          />
          <label for="choose-12">
            <b-avatar
              class="avatar"
              variant="info"
              src="https://j7b309.p.ssafy.io/api/profile/12"
              size="4rem"
            ></b-avatar>
          </label>

          <input
            type="radio"
            name="choice"
            id="choose-13"
            value="13"
            v-model="userInfo.profileId"
          />
          <label for="choose-13">
            <b-avatar
              class="avatar"
              variant="info"
              src="https://j7b309.p.ssafy.io/api/profile/13"
              size="4rem"
            ></b-avatar>
          </label>

          <input
            type="radio"
            name="choice"
            id="choose-14"
            value="14"
            v-model="userInfo.profileId"
          />
          <label for="choose-14">
            <b-avatar
              class="avatar"
              variant="info"
              src="https://j7b309.p.ssafy.io/api/profile/14"
              size="4rem"
            ></b-avatar>
          </label>

          <input
            type="radio"
            name="choice"
            id="choose-15"
            value="15"
            v-model="userInfo.profileId"
          />
          <label for="choose-15">
            <b-avatar
              class="avatar"
              variant="info"
              src="https://j7b309.p.ssafy.io/api/profile/15"
              size="4rem"
            ></b-avatar>
          </label>

          <input
            type="radio"
            name="choice"
            id="choose-16"
            value="16"
            v-model="userInfo.profileId"
          />
          <label for="choose-16">
            <b-avatar
              class="avatar"
              variant="info"
              src="https://j7b309.p.ssafy.io/api/profile/16"
              size="4rem"
            ></b-avatar>
          </label>

          <input
            type="radio"
            name="choice"
            id="choose-17"
            value="17"
            v-model="userInfo.profileId"
          />
          <label for="choose-17">
            <b-avatar
              class="avatar"
              variant="info"
              src="https://j7b309.p.ssafy.io/api/profile/17"
              size="4rem"
            ></b-avatar>
          </label>

          <input
            type="radio"
            name="choice"
            id="choose-18"
            value="18"
            v-model="userInfo.profileId"
          />
          <label for="choose-18">
            <b-avatar
              class="avatar"
              variant="info"
              src="https://j7b309.p.ssafy.io/api/profile/18"
              size="4rem"
            ></b-avatar>
          </label>

          <input
            type="radio"
            name="choice"
            id="choose-19"
            value="19"
            v-model="userInfo.profileId"
          />
          <label for="choose-19">
            <b-avatar
              class="avatar"
              variant="info"
              src="https://j7b309.p.ssafy.io/api/profile/19"
              size="4rem"
            ></b-avatar>
          </label>

          <input
            type="radio"
            name="choice"
            id="choose-20"
            value="20"
            v-model="userInfo.profileId"
          />
          <label for="choose-20">
            <b-avatar
              class="avatar"
              variant="info"
              src="https://j7b309.p.ssafy.io/api/profile/20"
              size="4rem"
            ></b-avatar>
          </label>
        </b-col>
      </b-row>
      <b-row style="margin-bottom: 2%">
        <b-col cols="1" style="text-align: right"> 성별 </b-col>
        <b-col cols="11" style="padding-left: 1%">
          <b-row>
            <b-col cols="1">
              <b-form-radio
                v-model="userInfo.gender"
                :aria-describedby="ariaDescribedby"
                name="some-radios"
                value="남"
                >남
              </b-form-radio>
            </b-col>
            <b-col>
              <b-form-radio
                v-model="userInfo.gender"
                :aria-describedby="ariaDescribedby"
                name="some-radios"
                value="여"
                >여
              </b-form-radio>
            </b-col>
          </b-row>
        </b-col>
      </b-row>
      <b-row style="margin-bottom: 2%">
        <b-col cols="1" style="text-align: right"> 연령대 </b-col>
        <b-col cols="11" style="padding-left: 1%">
          <b-form-select
            v-model="userInfo.age"
            :options="options_age"
            style="width: 21%"
          ></b-form-select>
        </b-col>
      </b-row>
      <b-row style="margin-bottom: 2%">
        <b-col cols="1" style="text-align: right"> 닉네임 </b-col>
        <b-col cols="11" style="padding-left: 1%">
          <b-form-input
            v-model="userInfo.nickName"
            placeholder="닉네임을 입력하세요."
            style="width: 21%"
          ></b-form-input>
        </b-col>
      </b-row>
      <b-row style="margin-bottom: 2%">
        <b-col cols="1" style="text-align: right"> 지역 </b-col>
        <b-col cols="11" style="padding-left: 1%">
          <b-form-select
            v-model="userInfo.region"
            :options="options_region"
            style="width: 21%"
          ></b-form-select>
        </b-col>
      </b-row>
      <b-row style="margin-bottom: 2%">
        <b-col cols="1" style="text-align: right"> TV 서비스 </b-col>
        <b-col cols="11" style="padding-left: 1%">
          <b-form-select
            v-model="userInfo.tvCarrier"
            :options="options_tv_carrier"
            style="width: 21%"
          ></b-form-select>
        </b-col>
      </b-row>
      <b-row style="margin-bottom: 2%">
        <b-col cols="1" style="text-align: right"> 휴대폰 번호 </b-col>
        <b-col cols="11" style="padding-left: 1%">
          <b-form-input
            v-model="userInfo.mobile"
            placeholder="000-0000-0000"
            style="width: 21%"
          ></b-form-input>
          <br /><br /><br />
          <a href="#" @click="modifyUser()" class="button btnBorder btnBlue" style="margin-right:2%"
        ><span style="font-size: 1vw;">수정</span></a
      >
      <a href="#" @click="deleteUser()" class="button btnBorder btnRed"
        ><span style="font-size: 1vw">탈퇴</span></a
      >
        </b-col>
      </b-row>
      
    </section>
  </div>
  <br /><br /><br /><br /><br />
</template>

<script>
import { mapState, mapActions } from "vuex";

export default {
  name: "UserInfoConfirm",
  computed: {
    ...mapState(["userInfo"]),
  },
  created() {
    this.getUserInfo();
  },
  data() {
    return {
      options_age: [
        { value: null, text: "연령대를 선택하세요" },
        { value: "10", text: "10대" },
        { value: "20", text: "20대" },
        { value: "30", text: "30대" },
        { value: "40", text: "40대" },
        { value: "50", text: "50대" },
        { value: "60", text: "60대 이상" },
      ],
      options_region: [
        { value: null, text: "지역을 선택하세요" },
        { value: "서울/인천/경기", text: "서울/인천/경기" },
        { value: "부산/경남", text: "부산/경남" },
        { value: "대구/경북", text: "대구/경북" },
        { value: "광주/전남", text: "광주/전남" },
        { value: "전북", text: "전북" },
        { value: "대전/세종/충남", text: "대전/세종/충남" },
        { value: "충북", text: "충북" },
        { value: "강원", text: "강원" },
        { value: "울산", text: "울산" },
        { value: "제주", text: "제주" },
      ],
      options_tv_carrier: [
        { value: null, text: "TV 서비스를 선택하세요" },
        { value: "SK 브로드밴드", text: "SK 브로드밴드" },
        { value: "KT olleh", text: "KT olleh" },
        { value: "LG U+", text: "LG U+" },
        { value: "SkyLife", text: "SkyLife" },
        { value: "LG 헬로비전", text: "LG 헬로비전" },
        { value: "티브로드", text: "티브로드" },
        { value: "아름방송(성남)", text: "아름방송(성남)" },
        { value: "딜라이브", text: "딜라이브" },
        { value: "현대 HCN(새로넷 방송)", text: "현대 HCN(새로넷 방송)" },
        { value: "서경방송", text: "서경방송" },
        { value: "NIB 남인천방송", text: "NIB 남인천방송" },
        { value: "CCS 충북방송", text: "CCS 충북방송" },
        { value: "CMB방송", text: "CMB방송" },
        { value: "JCN울산중앙방송", text: "JCN울산중앙방송" },
        { value: "하나방송", text: "하나방송" },
      ],
    };
  },

  methods: {
    ...mapActions(["getUserInfo", "modifyUserInfo", "deleteUserInfo"]),
    modifyUser() {
      if (
        this.userInfo.nickName == "" ||
        this.userInfo.gender == "" ||
        this.userInfo.age == null ||
        this.userInfo.region == null ||
        this.userInfo.tvCarrier == null ||
        this.userInfo.profileId == null ||
        this.userInfo.mobile == ""
      ) {
        alert("모든 정보를 입력해주세요.");
        return;
      }
      this.modifyUserInfo();
      alert("회원님의 정보 수정이 완료되었습니다.")
    },
    deleteUser() {
      if (confirm("정말 탈퇴하시겠습니까?") == true){
        this.deleteUserInfo();
        alert("그동안 BILLOW를 이용해주셔서 감사합니다. 다음 기회에 다시 만나요!")
      }
    },
  },
};
</script>

<style scoped>
section {
  color: #ffffff;
  padding-left: 70px;
  padding-right: 70px;
  font-size: 1.1vw;
}

h2 {
  font-weight: 500;
  /* font-size: 1.4vw; */
}

.avatar:hover {
  filter: brightness(0.5);
}

.submitButton {
  background-color: blue;
  padding: 3px 30px;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  float: left;
}

.logo {
  padding-left: 70px;
  padding-top: 0.05%;
}

label {
  margin-bottom: 2%;
  margin-right: 2%;
}

input[type="radio"] {
  display: none;
}

.avatar:hover {
  opacity: 0.6;
  cursor: pointer;
}

.avatar:active {
  opacity: 0.4;
  cursor: pointer;
}

input[type="radio"]:checked + label > .avatar {
  border: 6px solid rgb(228, 207, 94);
}
.form-check-input:checked {
  background-color: blue;
  border-color: blue;
}

a.button {
  padding: 0.5%;
  /* margin: 10px 20px 10px 0; */
  font-weight: 400;
  text-align: center;
  /* line-height: 50px; */
  color: #fff;
  border-radius: 5px;
  text-decoration: none;
  transition: all 0.2s;
  font-size:0.8vw;
}

.btnBlue.btnBorder {
  box-shadow: 0px 0px 0px 0px #212682;
}

.btnBlue.btnBorder:hover {
  box-shadow: 0px 0px 0px 5px #212682;
}

.btnBlue {
  background: #002bae;
}

.btnRed.btnBorder {
  box-shadow: 0px 0px 0px 0px #823621;
}

.btnRed.btnBorder:hover {
  box-shadow: 0px 0px 0px 5px #823621;
}

.btnRed {
  background: #ae2b00;
}
</style>
