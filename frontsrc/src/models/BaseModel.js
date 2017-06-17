export default {
  namespace: 'baseModel',

  state: {
    loading: false,
    userType: 'manager',  // inputer/finan/manager/admin/ministry
    userName: '12345',
    showMsgModal: false,
    projectName: 'kfkkfkfkfkfkfkfk',
    projectId: 72,
    projectList: [],
    projectTotal: 34,
    projectPage: 1,
  },
  effects: {

    * updateConcat({ payload }, { call, put }) {
      console.log(payload);
    },

    * updateAdvice({ payload }, { call, put }) {
      console.log(payload);
    },

    * modifyPass({ payload }, { call, put }) {
      console.log(payload);
    },
    * saveMsg({ payload }, { call, put }) {
      console.log(payload);
    },
  },

  reducers: {
    setState(state, action) {
      return { ...state, ...action.payload };
    },


    setLoading(state, action) {
      return { ...state, loading: action.payload };
    },
  },
};
