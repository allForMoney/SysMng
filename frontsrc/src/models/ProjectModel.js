import { getProjectList, AddProject, deletePro } from '../services/ProjectService';

export default {
  namespace: 'ProjectModel',

  state: {
    loading: false,
    projectList: [{ projectNo: 321, finaceHeaderQq: 999 }],
    projectListNum: 32,
    projectListPage: 1,
    filterParam: {},
    projectInfo: { partnerSchool: '1,2,3,4,5' }, // 项目基本信息
  },
  effects: {

    * getProjectList({ payload }, { call, put, select }) {
      console.log('getProjectList');
      const { filterParam, projectListPage } = yield select(state => state.ProjectModel);
      const data = yield call(getProjectList, { filterParam, projectListPage });
      if (data && data.code === 1) {
        yield put({
          type: 'setState',
          payload: {
            projectList: data.result,
            projectListPage: 1,
            projectListNum: data.result.length, // TODO 属性再议
          }
        });
      }
    },

    * AddProject({ payload }, { call, put }) {
      console.log(payload);
      const data = yield call(AddProject, payload);
      if (data && data.code === 1) {
        yield put({
          type: 'setState',
          payload: {
            projectListPage: 1,
          }
        });

        yield put({
          type: 'getProjectList',
        });
      }
    },
    * deletePro({ payload }, { call, put }) {
      console.log(payload);
      const data = yield call(deletePro, payload);
      if (data && data.code === 1) {
        yield put({
          type: 'setState',
          payload: {
            projectListPage: 1,
          }
        });

        yield put({
          type: 'getProjectList',
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
