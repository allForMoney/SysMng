import { getExpertList, addExpert, updateExpert, deleteExpert } from '../services/ExpertService';
import { message } from 'antd';

export default {
  namespace: 'expertModel',

  state: {
    expertList: [],
    expertPage: 1,
    expertNum: 0,
    loading: false,
  },

  effects: {
    * getExpertList({ payload }, { call, put }) {
      const data = yield call(getExpertList, payload);
      if (data && data.code === '1') {
        yield put({
          type: 'setState',
          payload: {
            expertList: data.result,
            expertPage: data.result.page,
            expertNum: data.result.total,
          }
        });
      }
    },

    * deleteExpert({ payload }, { call, put }) {
      const data = yield call(deleteExpert, payload);
      if (data && data.code === '1') {
        message.info('删除成功');
        yield put({
          type: 'getExpertList',
        });
      }
    },
    * addExpert({ payload }, { call, put }) {
      const data = yield call(addExpert, payload);
      if (data && data.code === '1') {
        message.info('添加成功');
        yield put({
          type: 'getExpertList',
        });
      }
    },
    * updateExpert({ payload }, { call, put }) {
      const data = yield call(updateExpert, payload);
      if (data && data.code === '1') {
        message.info('更新成功成功');
        yield put({
          type: 'getExpertList',
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
