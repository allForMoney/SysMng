import { getDataAnaList } from '../services/dataAnaService';

export default {
  namespace: 'dataAnalysisModel',

  state: {
    analySisList: [],
    analySisPage: 1,
    year: 2017,
    projectYear: 2015,
    analySisNum: 0,
    loading: false,
  },

  effects: {
    * getDataAnaListYear({ payload }, { call, put, select }) {
      console.log(5543);

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

    * getDataAnaListBudgetYear({ payload }, { call, put, select }) {
      const { projectYear, analySisPage } = yield select(state => state.dataAnalysisModel);

      const data = yield call(getDataAnaList, {
        projectYear,
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
