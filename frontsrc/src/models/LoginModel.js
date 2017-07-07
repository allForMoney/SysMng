import { login } from '../services/BaseService';
import { Modal } from 'antd';

export default {
  namespace: 'loginModel',

  state: {
  },

  effects: {
    * login({ payload }, { call }) {
      const data = yield call(login, payload);
      if (data && data.code === '1') {
        window.location = '../index.html';
        sessionStorage.username = payload.username;
        sessionStorage.password = payload.password;
      } else {
        Modal.info({ content: '登录失败,请检查用户名密码', maskClosable: true });
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
