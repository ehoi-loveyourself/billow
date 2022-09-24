export default {
    namespaced: true,
    state() {
      return {
        programList: [],
        programDetail: '',
      }
    },
    mutations: {
      programListState(state, payload) {
        if (payload.programList) {
          const newlist = payload.programList
          state.programList = [...state.programList, ...newlist]
        } else {
          state.programList = []
        }
      },
      initProgramList(state) {
        state.programList = []
      },
      programDetailState(state, { getDetail }) {
        // 선택 요구사항 높은 해상도 사용
        if (getDetail) {
          getDetail.Poster = getDetail.Poster.replace('SX300', 'SX500')
        }
        state.programDetail = getDetail
      },
    },
    actions: {
      async getProgram({ commit }, options) {
        if (options.title) {
          const getProgram = await fetch('/.netlify/functions/getProgram', {
            method: 'POST',
            body: JSON.stringify(options),
          }).then((res) => res.json())
  
          const programList = getProgram.Search
          commit('programListState', {
            programList,
          })
        }
      },
  
      async getDetail({ commit }, options) {
        options = { id: options }
        const getDetail = await fetch('/.netlify/functions/getDetail', {
          method: 'POST',
          body: JSON.stringify(options),
        }).then((res) => res.json())
  
        commit('programDetailState', {
          getDetail,
        })
      },
    },
  }