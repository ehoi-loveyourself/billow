<template>
  <b-navbar toggleable="lg">
    <b-navbar-nav>
      <br />
      <b-nav-item>
        <div id="drop">
          <input
            v-model="searchValue"
            class="form-control"
            type="text"
            name="search"
            placeholder="프로그램 검색"
            required
            @keyup.enter="onSubmit()"
          />
        </div>
      </b-nav-item>

      <b-nav-item class="profile">
        <router-link :to="{ name: 'userinfo' }" class="nav-link">
          <b-avatar
            class="avatar"
            variant="info"
            :src="profileUrl"
            size="2.4vw"
          ></b-avatar
        ></router-link>
      </b-nav-item>
    </b-navbar-nav>
    <a href="#" @click="logout()" class="button"
      ><span style="font-size: 0.8vw">LOGOUT</span></a
    >
  </b-navbar>
</template>

<script>
import { mapState, mapActions } from "vuex";
export default {
  data() {
    return {
      searchValue: "",
    };
  },
  computed: {
    ...mapState(["userInfo", "profileUrl"]),
  },
  created() {
    this.getUserInfo();
  },
  methods: {
    ...mapActions(["getSearchProgram", "getUserInfo"]),
    onSubmit() {
      if (this.searchValue == "") {
        alert("검색어를 입력하세요.");
        return;
      }
      this.getSearchProgram(this.searchValue);
      this.$router.push("/searchresult");
      this.searchValue = "";
    },

    logout() {
      localStorage.clear();
      this.$router.push("/loginmain");
    },
  },
};
</script>

<style scoped>
a.button {
  /* padding: 0.02%; */
  font-weight: 500;
  text-align: center;
  /* line-height: 50px; */
  color: #fff;
  border-radius: 5px;
  transition: all 0.2s;
}

nav .navbar-nav a.router-link-active {
  color: white;
  font-weight: bold;
}
nav .navbar-nav a:hover {
  text-decoration: none;
  color: #e5e5e5;
}
.navbar {
  font-size: large;
  color: #a1a1a1;
  right: 3%;
  justify-content: flex-end;
  display: flex;
  flex-grow: 1;
  position: absolute;
  top: 0;
  align-items: center;
}
.navbar-expand-lg {
  padding: 8px 0px;
}
#drop input[type="text"] {
  display: block;
  margin: 0 0 0 auto;
  width: 56.5%;
  box-sizing: border-box;
  border: 2px solid #2b2b2b;
  border-radius: 4px;
  font-size: 16px;
  border-color: #2b2b2b;
  background: none;
  color: #ffffff;
  background-position: 0 0;
  background-repeat: no-repeat;
  padding: 12px 20px 12px 12px;
  -webkit-transition: width 0.25s ease-in-out;
  transition: width 0.25s ease-in-out;
  margin-top: 4%;
}
#drop input[type="text"]:focus {
  width: 106%;
}
#drop input {
  line-height: 1em;
  height: 2.3vw;
}
#drop input::placeholder {
  color: #e8e0e0;
}
</style>
