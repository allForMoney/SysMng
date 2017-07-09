import { message } from 'antd';
import {
  updateReportTime,
  sendSMS,
  resetPwd,
  getReportTime,
  getUserList,
 } from '../services/OtherService';
import { getProjectInfoById } from '../services/ProjectService';


export default {
  namespace: 'OtherModel',

  state: {
    loading: false,
    showSMSText: false,
    projectInfo: {},
    userRecList: [],
    userRecPageNum: 1,
    userRecTotal: 0,
    reportTimeObj: {},
  },

  subscriptions: {
  },

  effects: {
    * sendSMS({ payload }, { call, select }) {
      const { projectInfo } = yield select(state => state.OtherModel);
      const data = yield call(sendSMS, { ...payload, projectId: projectInfo.id });
      if (data && data.code === '1') {
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

    * getUserList({ payload }, { call, put, select }) {
      const { userRecPageNum } = yield select(state => state.OtherModel);
      const data = yield call(getUserList, { userRecPageNum, ...payload });
      if (data && data.code === '1' && data.result) {
        yield put({
          type: 'setState',
          payload: {
            userRecList: data.result.content,
            userRecTotal: data.result.totalPages,
          }
        });
      }
    },

    * resetPwd({ payload }, { call }) {
      const data = yield call(resetPwd, payload);
      if (data && data.code === '1' && data.result) {
        message.info('密码重置成功!');
      }
    },

    * saveReportTime({ payload }, { call }) {
      const data = yield call(updateReportTime, payload);
      if (data && data.code === '1') {
        message.info('保存成功');
      }
    },

    * getReportTime({ payload }, { call, put }) {
      const data = yield call(getReportTime, payload);
      if (data && data.code === '1') {
        yield put({
          type: 'setState',
          payload: { reportTimeObj: data.result },
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
