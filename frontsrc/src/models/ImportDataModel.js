import { getProjectInfoById } from '../services/ProjectService';

export default {
  namespace: 'ImportData',

  state: {
    loading: false,
    projectInfo: {
      schoolName: '4314'
    },
    showUpload16: true,
  },
  effects: {

    * getProjectInfo({ payload }, { call, put }) {
      const data = yield call(getProjectInfoById, payload);
      if (data && data.code === '1') {
        yield put({
          type: 'setState',
          payload: {
            projectInfo: data.result,
            showUpload16: true,
          }
        });
      }
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
