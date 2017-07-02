
import {
  saveAchiveTarget,
  changeCheckStatus,
  getProjectArchive,
  updateAchiveTarget,
} from '../services/AchiveService';
import { message } from 'antd';

export default {
  namespace: 'achiveModel',

  state: {
    loading: false,
    id: '',
    indicatorBase: {},
    indicatorDetails: [],
  },
  effects: {

    * getProjectArchive({ payload }, { call, select, put }) {
      const { projectInfo } = yield select(state => state.baseModel);
      const projectId = projectInfo.id;

      const data = yield call(getProjectArchive, { projectId });
      if (data && data.code === '1') {
        console.log(data.result);
        const { id, indicatorBase, indicatorDetails } = data.result;
        yield put({
          type: 'setState',
          payload: {
            id,
            indicatorBase,
            indicatorDetails,
          }
        });

      }
    },

    * updateAchiveTarget({ payload }, { call, select }) {
      const {
        indicatorBase,
        indicatorDetails,
      } = yield select(state => state.achiveModel);
      const data = yield call(updateAchiveTarget, {
        indicatorBase,
        indicatorDetails,
      });
      if (data && data.code === '1') {
        message('更新成功');
      }
    },

    * changeCheckStatus({ payload }, { call }) {
      const data = yield call(changeCheckStatus, payload);
      if (data && data.code === 1) {
        message('审核成功');
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
