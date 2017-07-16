import { getProjectInfoById } from '../services/ProjectService';
import { getAllAchiveRec, getAllImportData } from '../services/ImportService';

export default {
  namespace: 'ImportData',

  state: {
    loading: false,
    projectInfo: {
    },
    budgetRecList: [],
    budgetRecPageNum: 0,
    budgetRecPageTotal: 0,
    budgetRecPageSize: 20,
    achiveRecList: [],
    achiveRecPageNum: 0,
    achiveRecPageTotal: 0,
    achiveRecPageSize: 20,
  // public static String BUDGET2016 = "yusuan2016";
  // public static String BUDGET2015 = "yusuan";
  // public static String BUDGET_ADJUST = "yusuan2";
  // public static String BUDGET_ADJUST_2016 = "adjust2016";
  // public static String TARGET = "jixiao";

    allImportData: [],
    importFileType: '',
    allImportPage: 1,
    allImportNum: 0,
    showUpload16: false,
    showUpload15: false,
  },
  effects: {

    * getProjectInfo({ payload }, { call, put }) {
      const data = yield call(getProjectInfoById, payload);
      if (data && data.code === '1' && data.result) {
        yield put({
          type: 'setState',
          payload: {
            projectInfo: data.result,
            [payload.attr]: true,
          }
        });
      }
    },

    * getAllImportData({ payload }, { call, put, select }) {
      const { allImportPage, importFileType } = yield select(state => state.ImportData);
      const data = yield call(getAllImportData, {
        type: importFileType,
        page: allImportPage
      });
      if (data && data.code === '1' && data.result) {
        yield put({
          type: 'setState',
          payload: {
            allImportData: data.result.content,
            allImportNum: data.result.totalPages,
          }
        });
      }
    },

    * getAllAchiveRec({ payload }, { call, put }) {
      const data = yield call(getAllAchiveRec, payload);
      if (data && data.code === '1' && data.result) {
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
