import { getProjectInfoById } from '../services/ProjectService';

export default {
  namespace: 'ImportData',

  state: {
    loading: false,
    projectInfo: {
      schoolName: '4314'
    },
    showUpload16: false,
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
