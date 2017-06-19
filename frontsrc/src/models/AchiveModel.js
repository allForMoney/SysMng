export default {
  namespace: 'baseModel',

  state: {
    loading: false,
    target1: '',
    target2: '',
    target3: '',
    achiveList: [],

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
