import { message } from 'antd';
import { updateReportTime } from '../services/OtherService';
export default {
  namespace: 'OtherModel',

  state: {
    loading: false,
    projectList: [],
    projectListNum: 0,
    projectListPage: 1,
    filterParam: {},
    projectInfo: { }, // 项目基本信息
  },
  effects: {
    * saveReportTime({ payload }, { call, put }) {
      const data = yield call(updateReportTime, payload);
      if (data && data.code === '1') {
        message.info('保存成功');
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
