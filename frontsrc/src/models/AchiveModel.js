
import {
  saveAchiveTarget,
  changeCheckStatus,
} from '../services/AchiveService';
import { message } from 'antd';

export default {
  namespace: 'achiveModel',

  state: {
    loading: false,
    targetImplement: '',
    targetFirstYear: '',
    targetSecondYear: '',
    achiveTargetList: [],

  },
  effects: {
    * saveAchiveTarget({ payload }, { call, select }) {
      const {
        targetImplement,
        targetFirstYear,
        targetSecondYear,
        achiveTargetList,
      } = yield select(state => state.achiveModel);
      const data = yield call(saveAchiveTarget, {
        targetImplement,
        targetFirstYear,
        targetSecondYear,
        achiveTargetList,
      });
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
