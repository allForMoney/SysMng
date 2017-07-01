import { message } from 'antd';
import { getProjectList, AddProject, deletePro, UpdateProject } from '../services/ProjectService';
export default {
  namespace: 'OtherModel',

  state: {
    loading: false,
    projectList: [],
    projectListNum: 0,
    projectListPage: 1,
    filterParam: {},
    projectInfo: { }, // 项目基本信息
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
      } else {
        message.info('添加失败,请稍后刷新页面重试');
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
      } else {
        message.info('修改失败,请稍后刷新页面重试');
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
      } else {
        message.info('删除失败,请稍后刷新页面重试');
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
