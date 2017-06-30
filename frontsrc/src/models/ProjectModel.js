import { getProjectList, AddProject, deletePro, UpdateProject } from '../services/ProjectService';

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
      const { filterParam, projectListPage } = yield select(state => state.ProjectModel);
      const data = yield call(getProjectList, { ...filterParam, page: projectListPage, size: 20 });
      if (data.code === '1') {
        yield put({
          type: 'setState',
          payload: {
            projectList: data.result.content,
            projectListPage: data.result.number,
            projectListNum: data.result.totalElements,
          }
        });
      }
    },

    * AddProject({ payload }, { call, put }) {
      console.log(payload);
      const data = yield call(AddProject, payload);
      if (data && data.code === '1') {
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

    * UpdateProject({ payload }, { call, put }) {
      console.log(payload);
      const data = yield call(UpdateProject, payload);
      if (data && data.code === '1') {
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
      if (data && data.code === '1') {
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
