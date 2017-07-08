import { logout, login, updateConcat, getProviceInfo,
  getProjectInfoById } from '../services/BaseService';
import { routerRedux } from 'dva/router';
import { message, Modal } from 'antd';

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
        type: 'reLogin'
      });
    }

  },
  effects: {
    * reLogin({ payload }, { call, put }) {
      const { username, password, isLogined } = sessionStorage;
      // if (isLogined) {
      //   return;
      // }
      const data = yield call(login, { username, password });
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
              projectNo: projectInfo.projectNo,
              projectInfo,
              projectName: result.majorName,
            }
          });
          sessionStorage.isLogined = true;
        }
        yield put({
          type: 'doRouter',
          payload: { userType }
        });
      } else {
        window.location = '../login.html';
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
      const hash = window.location.hash;
      const start = hash.indexOf('?');
      console.log(start);
      if (start > 2) {
        return;
      }
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

    * getProjectInfoById({ payload }, { call, put }) {
      const data = yield call(getProjectInfoById, payload);

      if (data && data.code === '1') {
        yield put({
          type: 'setState',
          payload: {
            projectInfo: data.result.project,
          }
        });
      }
    },

    * updateConcat({ payload }, { call, put, select }) {
      const { projectInfo } = yield select(state => state.baseModel);
      const projectId = projectInfo.id;
      const data = yield call(updateConcat, { ...payload, projectId });

      if (data && data.code === '1') {
        yield put({
          type: 'setState',
          payload: {
            projectInfo: data.result.project,
          }
        });
      }
    }
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
