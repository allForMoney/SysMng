import { message } from 'antd';
import { updateReportTime, sendSMS } from '../services/OtherService';
import { getProjectInfoById } from '../services/ProjectService';


export default {
  namespace: 'OtherModel',

  state: {
    loading: false,
    showSMSText: false,
    projectInfo: {},
  },
  effects: {
    * sendSMS({ payload }, { call, select }) {
      const { projectInfo } = yield select(state => state.OtherModel);
      const data = yield call(sendSMS, { ...payload, ...projectInfo });
      if (data && data.code === '1' && data.result) {
        message.info('短信发送成功');
      }
    },
    * getProjectInfo({ payload }, { call, put }) {
      const data = yield call(getProjectInfoById, payload);
      if (data && data.code === '1' && data.result) {
        yield put({
          type: 'setState',
          payload: {
            projectInfo: data.result,
            showSMSText: true,
          }
        });
      }
    },

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
