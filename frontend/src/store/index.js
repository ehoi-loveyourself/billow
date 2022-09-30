import Vue from "vue";
import Vuex from "vuex";
import router from "@/router";
import axios from "axios";

// Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    programId: 0,
    organizationId: 0,
    programDetail: null,
    programSchedule: null,
    programReview: null,
    searchWord: null,
    searchProgram: null,
    onairTalk: null,
  },
  getters: {},
  mutations: {
    SET_PROGRAM_ID(state, programId) {
      state.programId = programId;
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
    SET_ORGANIZATION_ID(state, organizationId) {
      state.organizationId = organizationId;
    },
    SET_SEARCH_PROGRAM(state, searchProgram) {
      state.searchProgram = searchProgram;
    },
    SET_SEARCH_WORD(state, searchWord) {
      state.searchWord = searchWord;
    },
    SET_ONAIR_TALK(state, onairTalk) {
      state.onairTalk = onairTalk;
    },
    ADD_ONAIR_TALK(state, onairTalk) {
      state.onairTalk += onairTalk;
    },
  },
  actions: {
    getProgramDetail({ commit, dispatch }, programId) {
      commit("SET_PROGRAM_ID", programId);
      axios.get(`/api/program/${programId}`).then((res) => {
        //프로그램 정보 조회 GET
        console.log(res.data);
        commit("SET_PROGRAM_DETAIL", res.data);
        dispatch("getProgramOrganization", programId);
        dispatch("getProgramReview", programId);
        dispatch("getProgramOnairTalk", programId);
      });
    },
    getProgramOrganization({ commit }, programId) {
      axios.get(`/api/organization/${programId}`).then((res) => {
        //편성표 정보 조회 GET
        console.log(res.data);
        commit("SET_PROGRAM_SCHEDULE", res.data);
      });
    },
    getProgramReview({ commit }, programId) {
      axios.get(`/api/review/${programId}`).then((res) => {
        //리뷰 정보 조회 GET
        console.log(res.data);
        commit("SET_PROGRAM_REVIEW", res.data);
      });
    },
    registReview({ commit, dispatch }, review) {
      //리뷰 등록 POST
      axios
        .post(`/api/review/${review.programId}`, {
          content: review.review,
        })
        .then((res) => {
          console.log(res.data);
          dispatch("getProgramReview", review.programId);
        })
        .catch((ex) => {
          console.warn("ERROR!!!!! : ", ex);
        });
    },
    getProgramOnairTalk({ commit }, programId) {
      axios.get(`/api/chat/${programId}`).then((res) => {
        //온에어톡 조회 GET
        console.log(res.data);
        commit("SET_ONAIR_TALK", res.data);
      });
    },
    registAlarm({ commit }, id) {
      axios.post(`/api/alarm/${id}`).then((res) => {
        //방영 알림 등록 POSt
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
    sendMessage({ commit, dispatch }, talk) {
      console.log(talk);
      axios
        .post(`/api/chat/${talk.programId}`, { content: talk.message })
        .then((res) => {
          //온에어 톡 메시지 POST
          console.log(res.data);
          dispatch("getProgramOnairTalk", talk.programId);
        });
    },
  },
  modules: {},
});
