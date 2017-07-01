import { logout, login } from '../services/BaseService';
import { routerRedux } from 'dva/router';

export default {
  namespace: 'baseModel',

  state: {
    loading: false,
    userType: 'inputer',  // inputer/finan/manager/admin/ministry
    userName: '12345',
    showMsgModal: false,
    projectName: 'kfkkfkfkfkfkfkfk',
    projectId: '4028a1765cf97115015cf98f6f01006a',
    projectNo: '2016-02',
    projectInfo: {}, // 单个项目基本信息
    projectList: [],
    projectTotal: 34,
    projectPage: 1,
  },
  effects: {
    * login({ payload }, { call, put }) {
      const data = yield call(login, payload);
      if (data && data.code === '1') {
        const result = data.result;
        if (!result) {
          return;
        }
        let userType = 'admin';
        switch (result.userRole) {
          case '1':
            userType = 'admin';
            break;
          case '2':
            userType = 'inputer';
            break;
          case '3':
            userType = 'finan';
            break;
          case '4':
            userType = 'manager';
            break;
          case '5':
          case '6':
            userType = 'ministry';
            break;
        }

        yield put({
          type: 'setState',
          payload: {
            projectId: result.id,
            projectNo: result.username,
            userType,
            projectName: result.majorName,
          }
        });
        yield put({
          type: 'doRouter',
          payload: { userType }
        });
      }
      console.log(data);
    },


    * logout({ payload }, { call }) {
      const data = yield call(logout, payload);
      console.log(data);
    },

    * doRouter({ payload }, { put }) {
      const userType = payload.userType;
      // TODO 根据用户角色不同,跳转到不同 的界面
      let pathname = '/base/projectList'; // 管理员的默认界面
      switch (userType) {
        case 'ministry':
          pathname = '/base/projectList';
          break;
        case 'inputer':
        case 'finan':
        case 'manager':
          pathname = '/budget/base';
          break;
        default:
          break;
      }
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
