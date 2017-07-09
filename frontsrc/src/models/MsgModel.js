import { message } from 'antd';
import {
getBudgetMsgList,
getAchiveMsgList,
saveBudgetReply,
 } from '../services/MsgService';

export default {
  namespace: 'msgModel',

  state: {
    loading: false,
    budgetMsgList: [],
    budgetMsgListPage: 1,
    budgetMsgListNum: 0,
    budgetAchiveList: [],
    budgetAchiveListPage: 1,
    budgetAchiveListNum: 0,
  },

  effects: {
    * saveBudgetReply({ payload }, { call, put, select }) {
      // @RequestParam String id,@RequestParam String content,@RequestParam String userId
      const { userId } = yield select(state => state.baseModel);
      const data = yield call(saveBudgetReply, { userId, ...payload });
      if (data && data.code === '1' && data.result) {
        yield put({
          type: 'getBudgetMsgList',
        });
        message.info('回复成功');
      }
    },
    * getBudgetMsgList({ payload }, { call, put }) {
      const data = yield call(getBudgetMsgList);
      if (data && data.code === '1' && data.result) {
        yield put({
          type: 'setState',
          payload: {
            budgetMsgList: data.result,
            budgetMsgListNum: data.result.total,
          }
        });
      }
    },

    * getAchiveMsgList({ payload }, { call, put }) {
      const data = yield call(getAchiveMsgList);
      if (data && data.code === '1' && data.result) {
        yield put({
          type: 'setState',
          payload: {
            budgetAchiveList: data.result,
            budgetAchiveListNum: data.result.total,
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
