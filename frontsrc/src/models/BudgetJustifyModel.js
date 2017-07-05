
import {
  getBudgetJustifyList,
  getBudgetJustifyCompareList,
} from '../services/BudgetService';
import { message } from 'antd';

export default {
  namespace: 'BudgetJustifyModel',

  state: {
    loading: false,
    showJustifyDetail: false, // 是否展示调整详情页
    /** 调整列表 */
    budgetJustifyList: [],
    budgetJustifyPage: 0,
    budgetJustifyNum: 0,
    /** 调整详情页 */
    budgetJustifyCompareList: [],
    budgetJustifyComparePage: 0,
    budgetJustifyCompareyNum: 0,
    /** 调整的id */
    compareId: '',

    /** 预算调整已经上传过的地址 */

  },
  effects: {
    // 获取项目预算调整表
    * getBudgetJustifyList({ payload }, { call, put, select }) {
      const { projectInfo } = yield select(state => state.baseModel);
      const { budgetJustifyPage } = yield select(state => state.BudgetJustifyModel);
      const projectId = projectInfo.id;
      const data = yield call(getBudgetJustifyList, { projectId, budgetJustifyPage, ...payload });
      if (data.code === '1' && data.result) {
        yield put({
          type: 'setState',
          payload: {
            budgetJustifyList: data.result.list,
          }
        });
      }
    },
    // 获取项目预算调整比较
    * getBudgetJustifyCompareList({ payload }, { call, put, select }) {
      const { compareId, budgetJustifyComparePage } = yield select(state => state.BudgetJustifyModel);
      const data = yield call(getBudgetJustifyCompareList, { compareId, budgetJustifyComparePage });
      
      if (data.code === '1' && data.result) {
        yield put({
          type: 'setState',
          payload: {
            budgetJustifyCompareList: data.result.list,
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
