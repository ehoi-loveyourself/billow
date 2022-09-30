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
  },
  getters: {},
  mutations: {
    SET_PROGRAM_ID(state, programId) {
      state.programId = programId;
    },
    SET_PROGRAM_DETAIL(state, program) {
      state.programDetail = program;
    },
    SET_PROGRAM_SCHEDULE(state, schedule) {
      state.programSchedule = schedule;
    },
    SET_PROGRAM_REVIEW(state, review) {
      state.programReview = review;
    },
    SET_ORGANIZATION_ID(state, id) {
      state.organizationId = id;
    },
    SET_SEARCH_PROGRAM(state, search) {
      state.searchProgram = search;
    },
    SET_SEARCH_WORD(state, word) {
      state.searchWord = word;
    },
  },
  actions: {
    getProgramDetail({ commit }, programId) {
      commit("SET_PROGRAM_ID", programId);
      axios.get(`/api/program/${programId}`).then((res) => {
        //프로그램 정보 조회 GET
        console.log(res.data);
        commit("SET_PROGRAM_DETAIL", res.data);
      });

      axios.get(`/api/organization/${programId}`).then((res) => {
        //편성표 정보 조회 GET
        console.log(res.data);
        commit("SET_PROGRAM_SCHEDULE", res.data);
      });

      axios.get(`/api/review/${programId}`).then((res) => {
        //리뷰 정보 조회 GET
        console.log(res.data);
        commit("SET_PROGRAM_REVIEW", res.data);
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
  },
  modules: {},
});
