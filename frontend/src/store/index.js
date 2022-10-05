import Vue from "vue";
import Vuex from "vuex";
import router from "@/router";
import axios from "axios";

let isTokenRefreshing = false;
let refreshSubscribers = [];

const onTokenRefreshed = (accessToken) => {
  refreshSubscribers.map((callback) => callback(accessToken));
};
const addRefreshSubscriber = (callback) => {
  refreshSubscribers.push(callback);
};

axios.interceptors.request.use(
  function (config) {
    config.headers["Auth-access"] = localStorage.getItem("authToken");
    return config;
  },
  function (error) {
    return Promise.reject(error);
  }
);

axios.interceptors.response.use(
  (response) => {
    return response;
  },
  async (error) => {
    const {
      config,
      response: { status },
    } = error;
    const originalRequest = config;
    if (status === 401) {
      if (!isTokenRefreshing) {
        isTokenRefreshing = true;
        axios
          .post("/api/users/refresh", {
            email: localStorage.getItem("email"),
            refreshToken: localStorage.getItem("refreshToken"),
          })
          .then((response) => {
            isTokenRefreshing = false;
            localStorage.setItem(
              "authToken",
              JSON.stringify(response.data.authToken).replace(/\"/gi, "")
            );
            onTokenRefreshed(localStorage.getItem("authToken"));
          })
          .catch((err) => {
            alert("재로그인이 필요합니다.");
            console.log(err);
          });
      }
      const retryOriginalRequest = new Promise((resolve) => {
        addRefreshSubscriber((accessToken) => {
          originalRequest.headers["Auth-access"] = accessToken;
          resolve(axios(originalRequest));
        });
      });
      return retryOriginalRequest;
    }
    if (status === 403) {
      alert("재로그인이 필요합니다.");
      router.push("/loginmain");
    }
    if (status === 400) {
      alert("오류가 발생하여 로그인 페이지로 이동합니다.");
      router.push("/loginmain");
    }
    return Promise.reject(error);
  }
);

// Vue.use(Vuex);
export default new Vuex.Store({
  state: {
    authToken: "",
    userInfo: null,
    userProfileImgId: 0,
    randomProgram: null,
    userRecommend: null,
    conditionRecommend: null,
    who: "",
    genre: "",
    conditionId: [],

    hotProgram: null,
    newProgram: null,
    genderAgeProgram: null,
    userAge: 0,
    userGender: null,
    actorProgram: null,
    actorName: "",
    onairProgram: null,
    userNickName: "",

    programId: 0,
    organizationId: 0,
    programDetail: null,
    programSchedule: null,
    programReview: null,
    searchWord: null,
    searchProgram: null,
    onairTalk: null,
    castInfoList: null,
    ratingList: null,
    alarmList: null,
    isFavorite: false,
    userRating: 0,
    isTrue1: false,
    isTrue2: false,
    isTrue3: false,
    isTrue4: false,
    isTrue5: false,
    isLoading: true
  },
  getters: {},
  mutations: {
    // SET_AUTH_TOKEN(state, authToken) {
    //   state.authToken = authToken;
    // },
    SET_USER_INFO(state, userInfo) {
      state.userInfo = userInfo;
      state.profileUrl =
        "https://j7b309.p.ssafy.io/api/profile/" + userInfo.profileId;
      console.log("===================");
      console.log(userInfo.profileId);
    },
    SET_RANDOM_PROGRAM(state, randomProgram) {
      state.randomProgram = randomProgram;
    },
    SET_USER_RECOMMEND_PROGRAM(state, userRecommend) {
      state.userRecommend = userRecommend;
    },
    SET_CONDITION_RECOMMEND_PROGRAM(state, conditionRecommend) {
      state.conditionRecommend = conditionRecommend;
    },
    SET_CONDITION(state, condition) {
      state.who = condition.who;
      state.genre = condition.genre;
    },
    ADD_CONDITION_ID(state, conditionId) {
      state.conditionId.push(conditionId);
      console.log(state.conditionId);
    },
    DELETE_CONDITION_ID(state, conditionId) {
      const idx = state.conditionId.findIndex(function (item) {
        return item === conditionId;
      });
      state.conditionId.splice(idx, 1);
      console.log(state.conditionId);
    },
    CLEAR_CONDITION_ID(state) {
      state.conditionId = [];
    },
    SET_HOT_PROGRAM(state, hotProgram) {
      state.hotProgram = hotProgram;
    },
    SET_NEW_PROGRAM(state, newProgram) {
      state.newProgram = newProgram;
    },
    SET_GENDERAGE_PROGRAM(state, genderAgeProgram) {
      state.genderAgeProgram = genderAgeProgram;
      state.userAge = genderAgeProgram[0].userAge;
      state.userGender = genderAgeProgram[0].userGender;
    },
    SET_ACTOR_PROGRAM(state, actorProgram) {
      console.log(actorProgram);
      state.actorProgram = actorProgram;
    },
    SET_ONAIR_PROGRAM(state, onairProgram) {
      state.onairProgram = onairProgram;
    },
    SET_PROGRAM_ID(state, programId) {
      state.programId = programId;
    },
    SET_ORGANIZATION_ID(state, organizationId) {
      state.organizationId = organizationId;
    },
    SET_PROGRAM_DETAIL(state, programDetail) {
      state.programDetail = programDetail;
    },
    SET_PROGRAM_SCHEDULE(state, programSchedule) {
      state.programSchedule = programSchedule;
    },
    CLEAR_PROGRAM_SCHEDULE(state) {
      state.programSchedule = null;
    },
    SET_PROGRAM_REVIEW(state, programReview) {
      state.programReview = programReview;
    },
    SET_ONAIR_TALK(state, onairTalk) {
      state.onairTalk = onairTalk;
    },
    SET_CAST_INFO(state, castInfoList) {
      state.castInfoList = castInfoList;
    },
    SET_SEARCH_PROGRAM(state, searchProgram) {
      state.searchProgram = searchProgram;
    },
    SET_SEARCH_WORD(state, searchWord) {
      state.searchWord = searchWord;
    },
    SET_RATING_LIST(state, ratingList) {
      state.ratingList = ratingList;
    },
    SET_ALARM_LIST(state, alarmList) {
      state.alarmList = alarmList;
    },
    SET_BOOKMARK_TRUE(state) {
      state.isFavorite = true;
    },
    SET_BOOKMARK_FALSE(state) {
      state.isFavorite = false;
    },
    SET_USER_RATING(state, userRating) {
      state.userRating = userRating;
      if (state.userRating != null) {
        if (state.userRating.score >= 2) {
          state.isTrue1 = true;
        }
        if (state.userRating.score >= 4) {
          state.isTrue2 = true;
        }
        if (state.userRating.score >= 6) {
          state.isTrue3 = true;
        }
        if (state.userRating.score >= 8) {
          state.isTrue4 = true;
        }
        if (state.userRating.score >= 10) {
          state.isTrue5 = true;
        }
      }
    },
    CLEAR_USER_RATING(state) {
      state.isTrue1 = false;
      state.isTrue2 = false;
      state.isTrue3 = false;
      state.isTrue4 = false;
      state.isTrue5 = false;
    },
    SET_FLAG_TRUE5(state) {
      state.isTrue5 = true;
    },
    SET_FLAG_TRUE4(state) {
      state.isTrue4 = true;
    },
    SET_FLAG_TRUE3(state) {
      state.isTrue3 = true;
    },
    SET_FLAG_TRUE2(state) {
      state.isTrue2 = true;
    },
    SET_FLAG_TRUE1(state) {
      state.isTrue1 = true;
    },
    SET_FLAG_FALSE5(state) {
      state.isTrue5 = false;
    },
    SET_FLAG_FALSE4(state) {
      state.isTrue4 = false;
    },
    SET_FLAG_FALSE3(state) {
      state.isTrue3 = false;
    },
    SET_FLAG_FALSE2(state) {
      state.isTrue2 = false;
    },
    SET_FLAG_FALSE1(state) {
      state.isTrue1 = false;
    },
    SET_IS_LOADING_FALSE(state){
      state.isLoading = false;
    },
    SET_IS_LOADING_TRUE(state){
      state.isLoading = true;
    }
  },
  actions: {
    getUserInfo({ commit }) {
      axios.get("/api/users").then((res) => {
        // 사용자 정보 데이터 GET
        console.log(res.data);
        commit("SET_USER_INFO", res.data);
      });
    },
    modifyUserInfo({ commit, dispatch, state }) {
      console.log(state.userInfo);
      axios
        .put("/api/users", {
          nickName: state.userInfo.nickName,
          gender: state.userInfo.gender,
          age: state.userInfo.age,
          region: state.userInfo.region,
          tvCarrier: state.userInfo.tvCarrier,
          profileImgId: state.userInfo.profileId,
          mobile: state.userInfo.mobile,
        })
        .then((res) => {
          // 사용자 정보 수정 PUT
          console.log(res.data);
          dispatch("getUserInfo");
        });
    },
    deleteUserInfo({ commit }) {
      axios.delete("/api/users").then((res) => {
        // 사용자 정보 삭제 DELETE
        console.log(res.data);
        // window.localStorage.removeItem("authToken");
        // window.localStorage.removeItem("name");
        // window.localStorage.removeItem("email");
        localStorage.clear();
        
        router.push("/loginmain");
      });
    },
    getRandomProgram({ commit }) {
      axios.get("/api/program/random").then((res) => {
        // 랜덤 프로그램 추천 데이터 GET
        console.log(res.data);
        commit("SET_RANDOM_PROGRAM", res.data);
      });
    },
    getUserRecommendProgram({ commit }) {
      axios.get("/api/mf/user-recommend").then((res) => {
        // 사용자 맞춤 프로그램 추천 데이터 GET
        console.log(res.data);
        commit("SET_USER_RECOMMEND_PROGRAM", res.data);
      });
    },
    getConditionRecommendProgram({ commit, state }) {
      commit("SET_IS_LOADING_TRUE");
      axios
        .post("/api/mf/condition-recommend", {
          who: state.who,
          genre: state.genre,
          programList: state.conditionId,
        })
        .then((res) => {
          // 상황별 프로그램 추천 데이터 GET
          console.log(res.data);
          commit("SET_CONDITION_RECOMMEND_PROGRAM", res.data);
          commit("SET_IS_LOADING_FALSE");
        });
    },
    getRecommendProgram({ commit }) {
      axios.get("/api/recommend/popular").then((res) => {
        // 인기 프로그램 추천 데이터 GET
        console.log(res.data);
        commit("SET_HOT_PROGRAM", res.data);
      });

      axios.get("/api/recommend/new").then((res) => {
        // 신규 프로그램 추천 데이터 GET
        console.log(res.data);
        commit("SET_NEW_PROGRAM", res.data);
      });

      axios.get("/api/recommend/gender-age").then((res) => {
        // 성연령별 프로그램 추천 데이터 GET
        console.log(res.data);
        commit("SET_GENDERAGE_PROGRAM", res.data);
      });

      axios.get("/api/recommend/actor").then((res) => {
        // 출연진 프로그램 추천 데이터 GET
        console.log(res.data);
        commit("SET_ACTOR_PROGRAM", res.data);
      });

      axios.get("/api/recommend/onair").then((res) => {
        // 온에어 프로그램 추천 데이터 GET
        console.log(res.data);
        commit("SET_ONAIR_PROGRAM", res.data);
      });
    },

    getProgramDetail({ commit, dispatch }, programId) {
      commit("SET_PROGRAM_ID", programId);
      axios.get(`/api/program/${programId}`).then((res) => {
        //프로그램 정보 조회 GET
        console.log(res.data);
        commit("SET_PROGRAM_DETAIL", res.data);
        commit("CLEAR_USER_RATING");
        commit("CLEAR_PROGRAM_SCHEDULE");
        dispatch("getProgramOrganization", programId);
        dispatch("getProgramReview", programId);
        dispatch("getProgramOnairTalk", programId);
        dispatch("getProgramCastInfo", programId);
        dispatch("getUserBookmark", programId);
        dispatch("getUserRating", programId);
      });
    },
    getProgramOrganization({ commit }, programId) {
      axios.get(`/api/organization/${programId}`).then((res) => {
        //편성표 정보 조회 GET
        console.log(res.data);
        commit("SET_PROGRAM_SCHEDULE", res.data);
      });
    },
    getProgramCastInfo({ commit }, programId) {
      axios.get(`/api/program/cast/${programId}`).then((res) => {
        //출연진 정보 조회 GET
        console.log(res.data);
        commit("SET_CAST_INFO", res.data);
      });
    },
    getProgramReview({ commit }, programId) {
      axios.get(`/api/review/${programId}`).then((res) => {
        //리뷰 정보 조회 GET
        console.log(res.data);
        commit("SET_PROGRAM_REVIEW", res.data);
      });
    },
    registReview({ commit, dispatch, state }, review) {
      //리뷰 등록 POST
      axios
        .post(`/api/review/${state.programId}`, {
          content: review,
        })
        .then((res) => {
          console.log(res.data);
          dispatch("getProgramReview", state.programId);
        })
        .catch((ex) => {
          alert("로그인이 필요한 서비스입니다.");
        });
    },
    modifyProgramReview({ commit, dispatch, state }, modifyReview) {
      axios
        .put(`/api/review/${modifyReview.reviewId}`, {
          content: modifyReview.review,
        })
        .then((res) => {
          //리뷰 수정 PUT
          console.log(res.data);
          alert("리뷰를 수정했습니다.");
          dispatch("getProgramReview", state.programId);
        });
    },
    deleteProgramReview({ commit, dispatch, state }, reviewId) {
      axios.delete(`/api/review/${reviewId}`).then((res) => {
        //리뷰 삭제 DELETE
        console.log(res.data);
        alert("리뷰를 삭제했습니다.");
        dispatch("getProgramReview", state.programId);
      });
    },
    getProgramOnairTalk({ commit }, programId) {
      axios.get(`/api/chat/${programId}`).then((res) => {
        //온에어톡 조회 GET
        console.log(res.data);
        commit("SET_ONAIR_TALK", res.data);
      });
    },
    sendMessage({ commit, dispatch, state }, message) {
      axios
        .post(`/api/chat/${state.programId}`, {
          content: message,
        })
        .then((res) => {
          //온에어 톡 메시지 POST
          console.log(res.data);
          dispatch("getProgramOnairTalk", state.programId);
        })
        .catch((ex) => {
          alert("로그인이 필요한 서비스입니다.");
        });
    },
    registAlarm({ commit }, id) {
      axios
        .post(`/api/alarm/${id}`)
        .then((res) => {
          //방영 알림 등록 POSt
          console.log(res.data);
        })
        .catch((ex) => {
          alert("로그인이 필요한 서비스입니다.");
        });
    },
    getAlarm({ commit }) {
      axios.get(`/api/alarm`).then((res) => {
        //방영 알림 조회 GET
        console.log(res.data);
        commit("SET_ALARM_LIST", res.data);
      });
    },
    deleteAlarm({ commit, dispatch, state }, broadcastingAlarmId) {
      axios.delete(`/api/alarm/${broadcastingAlarmId}`).then((res) => {
        //방영 알림 삭제 DELETE
        console.log(res.data);
        dispatch("getAlarm", state.programId);
      });
    },
    getRating({ commit }) {
      axios.get(`/api/users/rating`).then((res) => {
        //평점 조회 GET
        console.log(res.data);
        commit("SET_RATING_LIST", res.data);
      });
    },
    getUserRating({ commit }, programId) {
      axios
        .get(`/api/program/rating/${programId}`)
        .then((res) => {
          //사용자 평점 조회 GET
          console.log(res.data);
          commit("SET_USER_RATING", res.data);
        })
        .catch((ex) => {
          commit("SET_USER_RATING", res.data);
        });
    },
    deleteRating({ commit, dispatch, state }, ratingId) {
      axios.delete(`/api/users/rating/${ratingId}`).then((res) => {
        //평점 삭제 DELETE
        console.log(res.data);
        dispatch("getRating", state.programId);
      });
    },
    registRating({ commit }, rating) {
      axios
        .post(`/api/program/${rating.programId}`, { score: rating.score })
        .then((res) => {
          //평점 등록 POST
          console.log(res.data);
          if (rating.score == 10) {
            commit("SET_FLAG_TRUE1");
            commit("SET_FLAG_TRUE2");
            commit("SET_FLAG_TRUE3");
            commit("SET_FLAG_TRUE4");
            commit("SET_FLAG_TRUE5");
          } else if (rating.score == 8) {
            commit("SET_FLAG_TRUE1");
            commit("SET_FLAG_TRUE2");
            commit("SET_FLAG_TRUE3");
            commit("SET_FLAG_TRUE4");
            commit("SET_FLAG_FALSE5");
          } else if (rating.score == 6) {
            commit("SET_FLAG_TRUE1");
            commit("SET_FLAG_TRUE2");
            commit("SET_FLAG_TRUE3");
            commit("SET_FLAG_FALSE4");
            commit("SET_FLAG_FALSE5");
          } else if (rating.score == 4) {
            commit("SET_FLAG_TRUE1");
            commit("SET_FLAG_TRUE2");
            commit("SET_FLAG_FALSE3");
            commit("SET_FLAG_FALSE4");
            commit("SET_FLAG_FALSE5");
          } else if (rating.score == 2) {
            commit("SET_FLAG_TRUE1");
            commit("SET_FLAG_FALSE2");
            commit("SET_FLAG_FALSE3");
            commit("SET_FLAG_FALSE4");
            commit("SET_FLAG_FALSE5");
          }
        })
        .catch((ex) => {
          alert("로그인이 필요한 서비스입니다.");
        });
    },
    getSearchProgram({ commit }, word) {
      commit("SET_SEARCH_WORD", word);
      axios
        .get("/api/program", { params: { word: word } })
        .then((res) => {
          //프로그램 검색 GET
          console.log(res.data);
          commit("SET_SEARCH_PROGRAM", res.data);
        })
        .catch((ex) => {
          commit("SET_SEARCH_PROGRAM", null);
        });
    },
    getUserBookmark({ commit }, programId) {
      axios
        .get(`/api/bookmark/${programId}`)
        .then((res) => {
          //사용자 즐겨찾기 조회 GET
          commit("SET_BOOKMARK_TRUE");
        })
        .catch((ex) => {
          commit("SET_BOOKMARK_FALSE");
        });
    },
    registBookmark({ commit, state }) {
      axios.post(`/api/bookmark/${state.programId}`).then((res) => {
        //즐겨찾기 등록 POST
        console.log(res.data);
        commit("SET_BOOKMARK_TRUE");
      });
    },
    deleteBookmark({ commit, state }) {
      axios.delete(`/api/bookmark/${state.programId}`).then((res) => {
        //즐겨찾기 삭제 DELETE
        console.log(res.data);
        commit("SET_BOOKMARK_FALSE");
      });
    },
    userRegistBookmark({ commit }, programId) {
      axios
        .post(`/api/bookmark/${programId}`)
        .then((res) => {
          //사용자 즐겨찾기 등록 POST
          console.log(res.data);
        })
        .catch((ex) => {
          this.SET_BOOKMARK_FALSE();
          alert("로그인이 필요한 서비스입니다.");
        });
    },
    userDeleteBookmark({ commit }, programId) {
      axios
        .delete(`/api/bookmark/${programId}`)
        .then((res) => {
          //사용자 즐겨찾기 삭제 DELETE
          console.log(res.data);
        })
        .catch((ex) => {
          this.SET_BOOKMARK_FALSE();
          alert("로그인이 필요한 서비스입니다.");
        });
    },
  },
  modules: {},
});
