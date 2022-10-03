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
  },
  actions: {
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
        dispatch("getProgramOrganization", programId);
        dispatch("getProgramReview", programId);
        dispatch("getProgramOnairTalk", programId);
        dispatch("getProgramCastInfo", programId);
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
          console.warn("ERROR!!!!! : ", ex);
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
        });
    },
    registAlarm({ commit }, id) {
      axios.post(`/api/alarm/${id}`).then((res) => {
        //방영 알림 등록 POSt
        console.log(res.data);
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
    deleteRating({ commit, dispatch, state }, ratingId) {
      axios.delete(`/api/users/rating/${ratingId}`).then((res) => {
        //평점 삭제 DELETE
        console.log(res.data);
        dispatch("getRating", state.programId);
      });
    },
    sendRating({commit, state}, programId){
      axios.post(`/api/users/rating/${programId}`).then((res) => {
        //평점 등록 POST
        console.log(res.data);
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
          console.log("프로그램이 없습니다.");
        });
    },
    registBookmark({ commit, state }) {
      axios.post(`/api/bookmark/${state.programId}`).then((res) => {
        //즐겨찾기 등록 POST
        console.log(res.data);
      });
    },
    deleteBookmark({ commit, state }) {
      axios.delete(`/api/bookmark/${state.programId}`).then((res) => {
        //즐겨찾기 삭제 DELETE
        console.log(res.data);
      });
    },
    userRegistBookmark({ commit }, programId) {
      axios.post(`/api/bookmark/${programId}`).then((res) => {
        //사용자 즐겨찾기 등록 POST
        console.log(res.data);
      });
    },
    userDeleteBookmark({ commit }, programId) {
      axios.delete(`/api/bookmark/${programId}`).then((res) => {
        //사용자 즐겨찾기 삭제 DELETE
        console.log(res.data);
      });
    },
  },
  modules: {},
});
