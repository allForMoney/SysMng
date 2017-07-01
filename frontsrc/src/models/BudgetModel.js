
import {
  getBudgetSeasonList,
  getOutcomeBudget,
  updateSeasonBudget,
  changeCheckStatus,
  getBudgetRecList,
  getBudgetProjectList
} from '../services/BudgetService';
import { message } from 'antd';

export default {
  namespace: 'budgetModel',

  state: {
    loading: false,
    projectYear: 2017,
    quarterNum: 1,
    editBudgetSteps: 0,
    buggetInComeList: [],
    buggetOutComeList: [],

    budgetRecordList: [],
    budgetRecordPage: 1,
    budgetRecordNum: 45,
    budgetProjectList: []
  },
  effects: {

    * getBudgetProjectList({ payload }, { call, put, select }) {
      const { projectId } = yield select(state => state.baseModel);
      const data = yield call(getBudgetProjectList, { projectId, ...payload });
      if (data.code === '1') {
        yield put({
          type: 'setState',
          payload: {
            budgetProjectList: data.result.content,
            projectListPage: data.result.number,
            projectListNum: data.result.totalElements,
          }
        });
      }
    },

    * getIncomeBudget({ payload }, { call, put, select }) {
      const { projectInfo } = yield select(state => state.baseModel);
      const { projectYear } = yield select(state => state.budgetModel);
      const projectId = projectInfo.id;
      const data = yield call(getBudgetSeasonList, { projectId, projectYear, ...payload });

      if (data && data.code === 1) {
        yield put({
          type: 'setState',
          payload: {
            buggetInComeList: data.result,
          }
        });
      }
      // 更新season
      yield put({
        type: 'setState',
        payload
      });
    },

    * getBudgetRecList({ payload }, { call, put, select }) {
      const { budgetRecordPage } = yield select(state => state.budgetModel);
      const data = yield call(getBudgetRecList, { budgetRecordPage });
      if (data && data.code === 1) {
        yield put({
          type: 'setState',
          payload: {
            budgetRecordList: data.result,
            budgetRecordPage: data.result.page,
            budgetRecordNum: data.result.total,
          }
        });
      }
    },

    * getOutcomeBudget({ payload }, { call, put, select }) {
      const { year, season } = yield select(state => state.budgetModel);
      // 开发方便
      yield put({
        type: 'setState',
        payload: {
          editBudgetSteps: 1
        }
      });
      const data = yield call(getOutcomeBudget, { year, season });

      if (data && data.code === 1) {
        yield put({
          type: 'setState',
          payload: {
            buggetOutComeList: data.result,
            editBudgetSteps: 1

          }
        });
      }
    },

    * updateSeasonBudget({ payload }, { call, select }) {
      const { buggetOutComeList, buggetInComeList } = yield select(state => state.budgetModel);
      const data = yield call(updateSeasonBudget, { buggetOutComeList, buggetInComeList });
      if (data && data.code === 1) {
        message('更新成功');
      }
    },

    * changeCheckStatus({ payload }, { call }) {
      const data = yield call(changeCheckStatus, payload);
      if (data && data.code === 1) {
        message('审核成功');
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
