import Vue from "vue";
import Vuex from "vuex";
import router from "@/router";
import axios from "axios";

// Vue.use(Vuex);

export default new Vuex.Store({
  state: {
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
  },
  getters: {},
  mutations: {
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
      staet.userGender = genderAgeProgram[0].userGender;
    },
    SET_ACTOR_PROGRAM(state, actorProgram) {
      state.actorProgram = actorProgram;
      state.actorName = actorProgram[0].actorName;
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
  },
  actions: {
    getError({ commit }) {
      alert("오류가 발생하였습니다.");
      router.push("/loginmain");
    },
    getAuthError({ commit }) {
      alert("로그인이 필요한 서비스입니다.");
      // router.push("/loginmain");
    },
    getRandomProgram({ commit, dispatch }) {
      axios
        .get("/api/program/random")
        .then((res) => {
          // 랜덤 프로그램 추천 데이터 GET
          console.log(res.data);
          commit("SET_RANDOM_PROGRAM", res.data);
        })
        .catch((ex) => {
          dispatch("getError");
        });
    },
    getUserRecommendProgram({ commit, dispatch }) {
      axios
        .get("/api/mf/user-recommend")
        .then((res) => {
          // 사용자 맞춤 프로그램 추천 데이터 GET
          console.log(res.data);
          commit("SET_USER_RECOMMEND_PROGRAM", res.data);
        })
        .catch((ex) => {
          dispatch("getAuthError");
        });
    },
    getConditionRecommendProgram({ commit, dispatch, state }) {
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
        })
        .catch((ex) => {
          dispatch("getAuthError");
        });
    },
    getRecommendProgram({ commit, dispatch }) {
      axios
        .get("/api/recommend/popular")
        .then((res) => {
          // 인기 프로그램 추천 데이터 GET
          console.log(res.data);
          commit("SET_HOT_PROGRAM", res.data);
        })
        .catch((ex) => {
          dispatch("getError");
        });

      axios
        .get("/api/recommend/new")
        .then((res) => {
          // 신규 프로그램 추천 데이터 GET
          console.log(res.data);
          commit("SET_NEW_PROGRAM", res.data);
        })
        .catch((ex) => {
          dispatch("getError");
        });

      axios
        .get("/api/recommend/gender-age")
        .then((res) => {
          // 성연령별 프로그램 추천 데이터 GET
          console.log(res.data);
          commit("SET_GENDERAGE_PROGRAM", res.data);
        })
        .catch((ex) => {
          // dispatch("getAuthError");
        });

      axios
        .get("/api/recommend/actor")
        .then((res) => {
          // 출연진 프로그램 추천 데이터 GET
          console.log(res.data);
          commit("SET_ACTOR_PROGRAM", res.data);
        })
        .catch((ex) => {});

      axios
        .get("/api/recommend/onair")
        .then((res) => {
          // 온에어 프로그램 추천 데이터 GET
          console.log(res.data);
          commit("SET_ONAIR_PROGRAM", res.data);
        })
        .catch((ex) => {
          dispatch("getError");
        });
    },

    getProgramDetail({ commit, dispatch }, programId) {
      commit("SET_PROGRAM_ID", programId);
      axios
        .get(`/api/program/${programId}`)
        .then((res) => {
          //프로그램 정보 조회 GET
          console.log(res.data);
          commit("SET_PROGRAM_DETAIL", res.data);
          commit("CLEAR_USER_RATING", res.data);
          dispatch("getProgramOrganization", programId);
          dispatch("getProgramReview", programId);
          dispatch("getProgramOnairTalk", programId);
          dispatch("getProgramCastInfo", programId);
          dispatch("getBookmark", programId);
          dispatch("getUserRating", programId);
        })
        .catch((ex) => {
          dispatch("getError");
        });
    },
    getProgramOrganization({ commit, dispatch }, programId) {
      axios
        .get(`/api/organization/${programId}`)
        .then((res) => {
          //편성표 정보 조회 GET
          console.log(res.data);
          commit("SET_PROGRAM_SCHEDULE", res.data);
        })
        .catch((ex) => {
          dispatch("getError");
        });
    },
    getProgramCastInfo({ commit, dispatch }, programId) {
      axios
        .get(`/api/program/cast/${programId}`)
        .then((res) => {
          //출연진 정보 조회 GET
          console.log(res.data);
          commit("SET_CAST_INFO", res.data);
        })
        .catch((ex) => {
          dispatch("getError");
        });
    },
    getProgramReview({ commit, dispatch }, programId) {
      axios
        .get(`/api/review/${programId}`)
        .then((res) => {
          //리뷰 정보 조회 GET
          console.log(res.data);
          commit("SET_PROGRAM_REVIEW", res.data);
        })
        .catch((ex) => {
          dispatch("getError");
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
        })
        .catch((ex) => {
          dispatch("getAuthError");
        });
    },
    deleteProgramReview({ commit, dispatch, state }, reviewId) {
      axios
        .delete(`/api/review/${reviewId}`)
        .then((res) => {
          //리뷰 삭제 DELETE
          console.log(res.data);
          alert("리뷰를 삭제했습니다.");
          dispatch("getProgramReview", state.programId);
        })
        .catch((ex) => {
          dispatch("getAuthError");
        });
    },
    getProgramOnairTalk({ commit, dispatch }, programId) {
      axios
        .get(`/api/chat/${programId}`)
        .then((res) => {
          //온에어톡 조회 GET
          console.log(res.data);
          commit("SET_ONAIR_TALK", res.data);
        })
        .catch((ex) => {
          dispatch("getError");
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
    getAlarm({ commit, dispatch }) {
      axios
        .get(`/api/alarm`)
        .then((res) => {
          //방영 알림 조회 GET
          console.log(res.data);
          commit("SET_ALARM_LIST", res.data);
        })
        .catch((ex) => {
          dispatch("getAuthError");
        });
    },
    deleteAlarm({ commit, dispatch, state }, broadcastingAlarmId) {
      axios
        .delete(`/api/alarm/${broadcastingAlarmId}`)
        .then((res) => {
          //방영 알림 삭제 DELETE
          console.log(res.data);
          dispatch("getAlarm", state.programId);
        })
        .catch((ex) => {
          dispatch("getAuthError");
        });
    },
    getRating({ commit, dispatch }) {
      axios
        .get(`/api/users/rating`)
        .then((res) => {
          //평점 조회 GET
          console.log(res.data);
          commit("SET_RATING_LIST", res.data);
        })
        .catch((ex) => {
          dispatch("getAuthError");
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
      axios
        .delete(`/api/users/rating/${ratingId}`)
        .then((res) => {
          //평점 삭제 DELETE
          console.log(res.data);
          dispatch("getRating", state.programId);
        })
        .catch((ex) => {
          dispatch("getAuthError");
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
    getSearchProgram({ commit, dispatch }, word) {
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
          dispatch("getError");
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
    registBookmark({ commit, dispatch, state }) {
      axios
        .post(`/api/bookmark/${state.programId}`)
        .then((res) => {
          //즐겨찾기 등록 POST
          console.log(res.data);
          commit("SET_BOOKMARK_TRUE");
        })
        .catch((ex) => {
          dispatch("getAuthError");
        });
    },
    deleteBookmark({ commit, dispatch, state }) {
      axios
        .delete(`/api/bookmark/${state.programId}`)
        .then((res) => {
          //즐겨찾기 삭제 DELETE
          console.log(res.data);
          commit("SET_BOOKMARK_FALSE");
        })
        .catch((ex) => {
          dispatch("getAuthError");
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
