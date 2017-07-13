import { getDataAnaList } from '../services/dataAnaService';

export default {
  namespace: 'dataAnalysisModel',

  state: {
    analySisList: [],
    analySisPage: 1,
    year: 2017,
    analySisNum: 0,
    loading: false,
  },

  effects: {
    * getDataAnaList({ payload }, { call, put, select }) {
      const { year, analySisPage } = yield select(state => state.dataAnalysisModel);

      const data = yield call(getDataAnaList, {
        year,
        page: analySisPage
      });

      if (data && data.code === '1' && data.result) {
        yield put({
          type: 'setState',
          payload: {
            analySisList: data.result,
            analySisNum: data.result.total,
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
