
import {
  getBudgetJustifyList,
  getBudgetJustifyCompareList,
  getLastAdjust,
  changeCheckStatus,
} from '../services/BudgetService';
import { message } from 'antd';

export default {
  namespace: 'BudgetJustifyModel',

  state: {
    loading: false,
    showJustifyDetail: false, // 是否展示调整详情页
    /** 调整列表 */
    budgetJustifyList: [],
    budgetJustifyPage: 1,
    budgetJustifyPageSize: 10,
    budgetJustifyNum: 0,
    /** 调整详情页 */
    budgetJustifyCompareList: [],
    budgetJustifyComparePage: 1,
    budgetJustifyCompareyNum: 0,
    /** 调整的id */
    compareId: '',

    /** 预算调整已经上传过的地址 */
    desFile: '', // 说明文件
    requestFile: '',  // 请求文件
    fileName: '', // 调整文件 xls
    id: '', // 调整id
    auditStatus: null, // 调整审核状态
  },
  effects: {
    // 获取项目预算调整表
    * getLastAdjust({ payload }, { call, put, select }) {
      const { projectNo } = yield select(state => state.baseModel);
      const data = yield call(getLastAdjust, {
        projectNo,
      });
      if (data.code === '1' && data.result) {
        const {
          desFile,
          requestFile,
          fileName,
          id
        } = data.result;

        yield put({
          type: 'setState',
          payload: {
            desFile,
            requestFile,
            fileName,
            id
          }
        });
      }
    },

    // 获取项目预算调整表
    * getBudgetJustifyList({ payload }, { call, put, select }) {
      const { projectNo } = yield select(state => state.baseModel);
      const { budgetJustifyPage, budgetJustifyPageSize, auditStatus } = yield select(state => state.BudgetJustifyModel);

      const data = yield call(getBudgetJustifyList, {
        projectNo,
        status: auditStatus,
        page: budgetJustifyPage,
        size: budgetJustifyPageSize
      });

      if (data.code === '1' && data.result) {
        const { content, pageable, totalPages } = data.result;

        yield put({
          type: 'setState',
          payload: {
            budgetJustifyList: content,
            budgetJustifyNum: totalPages,
          }
        });
      }
    },
    // 获取项目预算调整比较
    * getBudgetJustifyCompareList({ payload }, { call, put, select }) {
      const { compareId, budgetJustifyComparePage } = yield select(state => state.BudgetJustifyModel);
      const data = yield call(getBudgetJustifyCompareList, { id: compareId });

      if (data.code === '1' && data.result) {
        yield put({
          type: 'setState',
          payload: {
            showJustifyDetail: true,
            budgetJustifyCompareList: data.result,
          }
        });
      }
    },
    // 通过调整审核
    * changeCheckStatus({ payload }, { call, put }) {
      const data = yield call(changeCheckStatus, payload);

      if (data.code === '1') {
        message.info('审核成功');
        yield put({
          type: 'getBudgetJustifyList'
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
