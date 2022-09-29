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
    registAlarm({ commit }) {
      axios.post(`/api/alarm//${state.organizationId}`).then((res) => {
        //방영 알림 등록 POSt
        console.log(res.data);
      });
    },
  },
  modules: {},
});
