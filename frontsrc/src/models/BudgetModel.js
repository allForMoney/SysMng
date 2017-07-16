
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
    projectYear: 0,
    quarterNum: 0,
    editBudgetSteps: 0,
    buggetInComeList: [],
    buggetOutComeList: [],
    auditStatus: 0, // 审核状态

    budgetRecordList: [],
    budgetRecordPage: 1,
    budgetRecordNum: 45,
    budgetProjectList: [],
    importType: '',

    showSeasonExport: false,
  },
  effects: {
    // 获取项目预算表
    * getBudgetProjectList({ payload }, { call, put, select }) {
      const { projectInfo } = yield select(state => state.baseModel);
      const projectId = projectInfo.id;
      const data = yield call(getBudgetProjectList, { projectId, ...payload });
      if (data.code === '1' && data.result) {
        yield put({
          type: 'setState',
          payload: {
            budgetProjectList: data.result.budgetImportDetaillList,
            importType: data.result.importType,
          }
        });
      }
    },

    // 获取预算季报信息
    * getBudgetSeasonList({ payload }, { call, put, select }) {
      const { projectInfo } = yield select(state => state.baseModel);
      const { projectYear, quarterNum } = yield select(state => state.budgetModel);
      const projectId = projectInfo.id;
      const data = yield call(getBudgetSeasonList, { projectId, projectYear, quarterNum });

      if (data && data.code === '1' && data.result) {
        let { fundsIns, fundsOuts, auditStatus } = data.result;
        if (!fundsIns) {
          fundsIns = [];
        }
        if (!fundsOuts) {
          fundsOuts = [];
        }
        if (!auditStatus) {
          auditStatus = 0;
        }
        yield put({
          type: 'setState',
          payload: {
            buggetInComeList: fundsIns,
            buggetOutComeList: fundsOuts,
            auditStatus,
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
      const data = yield call(getBudgetRecList, { page: budgetRecordPage });
      if (data && data.code === '1' && data.result) {
        const { content, totalPages } = data.result;

        yield put({
          type: 'setState',
          payload: {
            budgetRecordList: content,
            budgetRecordNum: totalPages,
          }
        });
      }
    },

    /** 更新项目执行季报  */
    * updateSeasonBudget({ payload }, { call, select }) {
      const { buggetOutComeList, buggetInComeList, quarterNum, projectYear } = yield select(state => state.budgetModel);
      const { userId, projectInfo } = yield select(state => state.baseModel);
      if (buggetInComeList.length < 1) {
        message.info('收入预算不可为空');
        return;
      }
      if (buggetOutComeList.length < 1) {
        message.info('收入预算不可为空');
        return;
      }
      const data = yield call(updateSeasonBudget, {
        fundsOuts: buggetOutComeList,
        fundsIns: buggetInComeList,
        quarterNum,
        projectId: projectInfo.id,
        projectYear,
        userId,
      });
      if (data && data.code === 1) {
        message('更新成功');
      }
    },

    /** 切换审核状态 */
    * changeCheckStatus({ payload }, { call }) {
      const data = yield call(changeCheckStatus, payload);
      if (data && data.code === '1') {
        message.info('审核成功');
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
