import Vue from "vue";
import Vuex from "vuex";
import router from "@/router";
import axios from "axios";

// Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    programDetail: null,
  },
  getters: {},
  mutations: {
    SET_PROGRAM_DETAIL(state, program) {
      state.programDetail = program;
    },
  },
  actions: {
    getProgramDetail({ commit }, programId) {
      axios.get(`/api/program/${programId}`).then((res) => {
        //프로그램 정보 조회 GET
        console.log(res.data);
        commit("SET_PROGRAM_DETAIL", res.data);
      });
    },
  },
  modules: {},
});
