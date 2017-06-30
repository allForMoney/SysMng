import { logout, login } from '../services/BaseService';
import { routerRedux } from 'dva/router';

export default {
  namespace: 'baseModel',

  state: {
    loading: false,
    userType: 'admin',  // inputer/finan/manager/admin/ministry
    userName: '12345',
    showMsgModal: false,
    projectName: 'kfkkfkfkfkfkfkfk',
    projectId: 72,
    projectInfo: {}, // 单个项目基本信息
    projectList: [],
    projectTotal: 34,
    projectPage: 1,
  },
  effects: {
    * login({ payload }, { call, put }) {
      const data = yield call(login, payload);
      console.log(data);
    },

    * logout({ payload }, { call, put }) {
      const data = yield call(login, payload);
      console.log(data);
    },
    * doRouter({ payload }, { put }) {
      // TODO 根据用户角色不同,跳转到不同 的界面
      let pathname = '/base/projectList'; // 管理员的默认界面
      yield put(routerRedux.push({
        pathname
      }));
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
