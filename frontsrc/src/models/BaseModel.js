import { logout, login } from '../services/BaseService';
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
      const data =yield call(login, payload);
      console.log(data);
    },

    * logout({ payload }, { call, put }) {
      const data =yield call(login, payload);
      console.log(data);
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
