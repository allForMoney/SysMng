import { logout, login, isLogin, getProviceInfo } from '../services/BaseService';
import { routerRedux } from 'dva/router';

export default {
  namespace: 'baseModel',

  state: {
    loading: false,
    userType: 'finace',  // inputer/finace/school/admin/country
    userName: '12345',
    showMsgModal: false,
    projectInfo: {}, // 单个项目基本信息
    projectList: [],
    projectTotal: 34,
    projectPage: 1,
    userId: '',
    priviceList: [], // 省份对应的列表
  },
  
  subscriptions: {
    init({ dispatch }) {
      
      dispatch({
        type: 'getProviceInfo'
      });
      dispatch({
        type: 'isLogin'
      });
    }

  },
  effects: {
    * isLogin({ payload }, { call, put }) {
      const data = yield call(isLogin, payload);
      if (data && data.code === '1') {
        const result = data.result;
        if (!result) {
          yield put(routerRedux.push({
            pathname: '/login'
          }));
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
            userType = 'finace';
            break;
          case '4':
            userType = 'school';
            break;
          case '5':
          case '6':
            userType = 'country';
            break;
        }

        console.log(result.id);

        yield put({
          type: 'setState',
          payload: {
            userId: result.id,
            projectNo: result.projectNo,
            userType,
            projectInfo: result.project,
            projectName: result.majorName,
          }
        });
        yield put({
          type: 'doRouter',
          payload: { userType }
        });
      }
    },

    * login({ payload }, { call, put }) {
      const data = yield call(login, payload);
      if (data && data.code === '1') {
        const result = data.result;
        let userType = 'admin';
        switch (result.userRole) {
          case '1':
            userType = 'admin';
            break;
          case '2':
            userType = 'inputer';
            break;
          case '3':
            userType = 'finace';
            break;
          case '4':
            userType = 'school';
            break;
          case '5':
          case '6':
            userType = 'country';
            break;
        }
        if (result) {
          let projectInfo = {};
          if (result.project) {
            projectInfo = result.project;
          }

          yield put({
            type: 'setState',
            payload: {
              userId: result.id,
              userName: result.username,
              userType,
              projectInfo,
              projectName: result.majorName,
            }
          });
        }
        yield put({
          type: 'doRouter',
          payload: { userType }
        });
      }
    },

    * getProviceInfo({ payload }, { call, put }) {
      const data = yield call(getProviceInfo, payload);
      if (data && data.code === '1') {
        yield put({
          type: 'setState',
          payload: {
            priviceList: data.result,
          }
        });
      }
    },
    
    * logout({ payload }, { call }) {
      const data = yield call(logout, payload);
    },

    * doRouter({ payload }, { put }) {
      const userType = payload.userType;
      // TODO 根据用户角色不同,跳转到不同 的界面
      let pathname = '/base/projectList'; // 管理员的默认界面
      switch (userType) {
        case 'country':
          pathname = '/base/projectList';
          break;
        case 'inputer':
        case 'finace':
        case 'school':
          pathname = '/budget/base';
          break;
        default:
          break;
      }
      yield put(routerRedux.push({
        pathname
      }));
      yield put({
        type: 'setState',
        payload
      }
      );
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
